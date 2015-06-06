/* Copyright 2012-2015 SAP SE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.aniketos.securebpmn.xacml.pdp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eu.aniketos.securebpmn.xacml.api.ErrorType;
import eu.aniketos.securebpmn.xacml.api.ReasonType;
import eu.aniketos.securebpmn.xacml.api.SecurityError;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoResult;
import eu.aniketos.securebpmn.xacml.api.autho.Decision;
import eu.aniketos.securebpmn.xacml.api.autho.IPDP;
import eu.aniketos.securebpmn.xacml.api.autho.IPDPStateManagement;
import eu.aniketos.securebpmn.xacml.api.idm.IdInfo;
import eu.aniketos.securebpmn.xacml.api.log.AccessControlRequest;
import eu.aniketos.securebpmn.xacml.api.log.ILogStore;
import eu.aniketos.securebpmn.xacml.pdp.idHandler.IDHandler;
import eu.aniketos.securebpmn.xacml.AnalysisConfig;
import eu.aniketos.securebpmn.xacml.AnalysisCtx;
import eu.aniketos.securebpmn.xacml.support.RecordAttributeFinder;
import eu.aniketos.securebpmn.xacml.support.RecordEvaluationContext;
import eu.aniketos.securebpmn.xacml.SVNPDPConfig;
import eu.aniketos.securebpmn.xacml.support.XACML2APIMapper;
import eu.aniketos.securebpmn.xacml.support.XACMLDecoder;
import eu.aniketos.securebpmn.xacml.support.XACMLEncoder;
import eu.aniketos.securebpmn.xacml.combine.AnalysisDenyOverridesPolicyAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisDenyOverridesRuleAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisFirstApplicablePolicyAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisFirstApplicableRuleAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisOnlyOneApplicablePolicyAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisOrderedDenyOverridesPolicyAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisOrderedDenyOverridesRuleAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisOrderedPermitOverridesPolicyAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisOrderedPermitOverridesRuleAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisPermitOverridesPolicyAlg;
import eu.aniketos.securebpmn.xacml.combine.AnalysisPermitOverridesRuleAlg;

import com.sun.xacml.ConfigurationStore;
import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.combine.BaseCombiningAlgFactory;
import com.sun.xacml.combine.CombiningAlgFactory;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.finder.AttributeFinder;
import com.sun.xacml.finder.RevocationFinder;

@WebService(endpointInterface = "eu.aniketos.securebpmn.xacml.autho.IPDP", serviceName = "PolicyDecisionPoint", targetNamespace = "http://pdp.aniketos.eu/")
public class PDPServer implements IPDP {

    private static Logger logger = Logger.getLogger(PDPServer.class);

    private static PDPServer pdpServer;

    private PDP xacmlPDP;
    private AttributeFinder attrFinder;
    private RevocationFinder revocFinder;

    private IDHandler idHandler;
    private ILogStore logServer;
    private IPDPStateManagement pdpState;

    private boolean log = false;
    private boolean pdpStateMgt = false;
    private static boolean tomcatEnv = false;
    private static String baseDir = null;

    private long start = -1;

    /**
     * for analysis, some of the static parameters (e.g., CombiningAlgFactory,
     * etc.) have to be reset, i.e., within one JVM only productive OR analysis
     * pdp can exist
     */
    private static PDP_MODE mode = PDP_MODE.UNSET;

    private static boolean replacedFAnalysis = false;

    private static enum PDP_MODE {
        ANALYSIS, PRODUCTIVE, UNSET
    }

    private static final String LOG4J_FILENAME = "log4j.properties",
                                SVN_CONFIG = "svn-config.xml", XACML_CONFIG = "policy-config.xml",
                                CATALINA_BASE = "CATALINA_BASE", WBAPPS_FOLDER = "webapps",
                                WEBAPP_NAME = "pdp", WEB_INF = "WEB-INF", SLASH = System
                                        .getProperty("file.separator");

    public static final String LOG_SERVER = "logServer",
                               PDPSTATE_MGT = "pdpStateManagement";

    static {
        // check if running in tomcat or not
        String catHome = System.getenv(CATALINA_BASE);
        File catHomeF;

        if (catHome != null && catHome.length() > 0
                && (catHomeF = new File(catHome)).exists()) {
            tomcatEnv = true;
            baseDir = catHomeF.getAbsolutePath() + SLASH + WBAPPS_FOLDER
                      + SLASH + WEBAPP_NAME + SLASH + WEB_INF + SLASH;
            String log4jPath = baseDir + LOG4J_FILENAME;

            Properties log4jProps = new Properties();
            try {
                // log4jProps.load(new
                // BufferedInputStream(PDPServer.class.getResourceAsStream(LOG4J_FILEURL)));
                log4jProps.load(new BufferedInputStream(new FileInputStream(
                        new File(log4jPath))));
                PropertyConfigurator.configure(log4jProps);
                logger.info("Loaded log4j configuration from " + log4jPath);
            } catch (IOException e) {
                // logger.warn("Could not load log4j configuration from " +
                // LOG4J_FILEURL + " IOException: " + e.getMessage());
                System.err.println("Could not load log4j configuration from "
                                   + log4jPath + " IOException: " + e.getMessage());
            } catch (Exception e) {
                // logger.warn("Could not load log4j configuration from " +
                // LOG4J_FILEURL + " " + e.getClass() + ": " + e.getMessage());
                System.err.println("Could not load log4j configuration from "
                                   + log4jPath + " " + e.getClass() + ": "
                                   + e.getMessage());
            }
        } else {
            tomcatEnv = false;
            baseDir = new File("foo").getAbsolutePath(); // "foo" must not exist
            baseDir = baseDir.substring(0, baseDir.length() - 3);

            // when used as analysis PDP, host app has to set log4j properties

            logger.info("base direcotry for configuration: " + baseDir);
        }
    }

    /**
     * The empty contructor is used when deploying the PDPServer as productice
     * system into a Tomcat environment; thus, it may not be called outside of a
     * Tomcat
     */
    public PDPServer() {
        if (mode == PDP_MODE.UNSET || mode == PDP_MODE.PRODUCTIVE) {
            mode = PDP_MODE.PRODUCTIVE;
        } else {
            throw new RuntimeException(
                "Cannot create a productive PDPServer in a JVM where an AnalysisPDP already exists");
        }

        if (pdpServer == null) {
            if (!tomcatEnv) {
                throw new RuntimeException(
                    "The empty constructor may not be called when not running within PDP");
            }

            logger.debug("Start loading XACML configuration from configuration files");
            // load pdp configuration from file
            ConfigurationStore config = null;

            try {
                // check if there is a SVN_CONFIG available
                File svn_conf = new File(baseDir + SVN_CONFIG);
                if (svn_conf.exists()) {
                    logger.debug("Found " + SVN_CONFIG + " file in " + baseDir
                                 + "; trying to load configuration from svn");
                    try {
                        PDPConfig pdpconfig = SVNPDPConfig
                                              .getSVNPDPConfig(svn_conf);
                        logger.info("Loaded Configuration from svn");
                        init(pdpconfig);
                        return;
                    } catch (SecurityError e) {
                        logger.error("Could not load svn configuration (SecurityError): "
                                     + e.getMessage());
                    }
                }

                File confFile = new File(baseDir + XACML_CONFIG);
                if (!confFile.exists()) {
                    logger.fatal("Could not find configuration file "
                                 + confFile.getAbsolutePath());
                    return;
                }
                logger.info("Loading config from " + confFile.getAbsolutePath());
                config = new ConfigurationStore(confFile, baseDir);
                // }
            } catch (ParsingException e) {
                logger.fatal("Could not load Configuration: ParsingException: "
                             + e.getMessage());
                return;
            }

            try {
                synchronized (logger) {
                    if (pdpServer == null) {
                        init(config.getDefaultPDPConfig());
                        pdpServer = this;
                    } else {
                        logger.warn("Two threads creating a PDPServer; aborting this thread");
                    }
                }
            } catch (UnknownIdentifierException e) {
                logger.fatal("Could not load Configuration: UnknownIdentifierException: "
                             + e.getMessage());
            }
        } else {
            copyFromStatic();
        }
    }

    /**
     *
     */
    public PDPServer(File confFile) throws FileNotFoundException,
        ParsingException, UnknownIdentifierException {

        logger.info("Loading XACML configuration from "
                    + confFile.getAbsolutePath());
        ConfigurationStore config = new ConfigurationStore(confFile);

        init(config.getDefaultPDPConfig());
    }

    /**
     *
     */
    public PDPServer(File confFile, String baseDir)
    throws FileNotFoundException, ParsingException,
        UnknownIdentifierException {

        logger.info("Loading XACML configuration from "
                    + confFile.getAbsolutePath() + ", baseDir: " + baseDir);
        ConfigurationStore config = new ConfigurationStore(new FileInputStream(
                    confFile), baseDir);

        init(config.getDefaultPDPConfig());
    }

    /**
     * Loads a new PDPSever instance
     */
    public PDPServer(PDPConfig configuration) {

        logger.info("Creating new PDPServer from configuration");
        init(configuration);
    }

    /**
     * @param configuration
     */
    private void init(PDPConfig configuration) {
        // load IDHandler with corresponding settings; IDResolver and IDManager

        // load XACML Engine
        logger.info("Loading XACML engine");
        start = new Date().getTime();
        this.attrFinder = configuration.getAttributeFinder();
        this.revocFinder = configuration.getRevocationFinder();
        xacmlPDP = new PDP(configuration);
        logger.info("Loaded XACML engine"
                    + (start == -1 ? "" : " in " + (new Date().getTime() - start)
                       + "ms"));

        // load logServer
        String logServerName = null;
        if ((logServerName = (String) configuration.getCustomAttr(LOG_SERVER)) != null) {
            Class<?> logServerClass;
            try {
                logger.info("Loading LogServer from class " + logServerName);
                logServerClass = Class.forName(logServerName);
                Method getInstance = logServerClass.getMethod("getInstance",
                                     (Class<?>[]) null);
                start = new Date().getTime();
                logServer = (ILogStore) getInstance.invoke(null,
                            (Object[]) null);
                // TODO changed to false
                log = false;
                this.attrFinder = new RecordAttributeFinder(attrFinder);
                logger.info("Loaded LogServer with class "
                            + logServer.getClass() + " in "
                            + (new Date().getTime() - start) + "ms");
            } catch (Exception e) {
                // check if rootcause is "known"...
                if (e.getCause() != null
                        && e.getCause()
                        .getClass()
                        .getName()
                        .equals("org.hibernate.exception.JDBCConnectionException")) {
                    logger.error("Could not load LogServer: JDBCConnectionException: "
                                 + e.getMessage());
                    e.printStackTrace();
                } else {
                    logger.error(
                        "Could not load LogServer from class "
                        + logServerName + ": " + e.getClass()
                        + " - " + e.getMessage(), e);
                    e.printStackTrace();
                }
            }
        } else {
            logger.debug("No entry for LogServer in configuration found");
        }

        String pdpStateManager = null;
        if ((pdpStateManager = (String) configuration
                               .getCustomAttr(PDPSTATE_MGT)) != null) {
            Class<?> pdpStateMgtClass;
            try {
                logger.info("Loading PDPStateManagement from class "
                            + pdpStateManager);
                pdpStateMgtClass = Class.forName(pdpStateManager);
                Method getInstance = pdpStateMgtClass.getMethod("getInstance",
                                     (Class<?>[]) null);
                start = new Date().getTime();
                pdpState = (IPDPStateManagement) getInstance.invoke(null,
                           (Object[]) null);
                pdpStateMgt = true;
                logger.info("Loaded PDPStateManagement with class "
                            + logServer.getClass() + " in "
                            + (new Date().getTime() - start) + "ms");
            } catch (Exception e) {
                // check if rootcause is "known"...
                if (e.getCause() != null
                        && e.getCause()
                        .getClass()
                        .getName()
                        .equals("org.hibernate.exception.JDBCConnectionException")) {
                    logger.error("Could not load PDPStateManagement: JDBCConnectionException: "
                                 + e.getMessage());
                    e.printStackTrace();
                } else {
                    logger.error(
                        "Could not load PDPStateManagement from class "
                        + logServerName + ": " + e.getClass()
                        + " - " + e.getMessage(), e);
                    e.printStackTrace();
                }
            }
        } else {
            logger.debug("No entry for LogServer in configuration found");
        }
        logger.info("Loaded PDPServer (LogServer: " + log
                    + ", PDPStateManagement: " + pdpStateMgt + ")");
    }

    /**
     * "copy constructor"... PDPServer has to be a singleton, but for the web
     * service framework a public constructor is required; thus, this creates a
     * pseudo-further instance (pseudo in the sense, as it holds the same
     * private elements as the "singleton" instance
     */
    private void copyFromStatic() {
        logger.warn("A second instance of PDPServer is created!");

        this.xacmlPDP = pdpServer.xacmlPDP;
        this.idHandler = pdpServer.idHandler;
        this.log = pdpServer.log;
        // TODO keep up to date
    }

    public String evaluateXACML(String xacmlRequest) throws SecurityError {

        AccessControlRequest logEntry = null;
        Long evaluationId = null;
        if (log) {
            evaluationId = logServer.getNewEvaluationId();
            logEntry = new AccessControlRequest(evaluationId, xacmlRequest);
        }

        RequestCtx request = XACMLDecoder.decodeRequestCtx(xacmlRequest);
        PDPResult res = evaluate(request, evaluationId);
        ResponseCtx response = res.getResponseCtx();
        String responseStr = XACMLEncoder.encodeResponseCtx(response);

        if (log) {
            RecordEvaluationContext ctx = (RecordEvaluationContext) res
                                          .getEvaluationCtx();
            logEntry.finished(request, response, responseStr,
                              ctx.getExecTime(), ctx.getVersion(),
                              ctx.getDesignatorAttributes());
            logServer.storeAccessControlRequest(logEntry);
        }

        return responseStr;
    }

    public AuthoResult evaluate(IdInfo idInfo, String resource, String action,
                                List<AuthoAttribute> attributes) throws SecurityError {

        if (idInfo == null || resource == null) {
            logger.error("Received request with idInfo or resource being null");
            throw new SecurityError(ErrorType.AUTHORIZATION_FAILED,
                                    ReasonType.INVALID_PARAMETERS,
                                    "Parameters \"idInfo\" or \"resource\" may not be null!");
        }
        URI resourceUri = null;
        try {
            resourceUri = new URI(resource);
        } catch (URISyntaxException e) {
            logger.error("Received request with resource not being an URI");
            throw new SecurityError(ErrorType.AUTHORIZATION_FAILED,
                                    ReasonType.INVALID_PARAMETERS,
                                    "Parameters \"resource\" has to be an URI!");
        }

        AccessControlRequest logEntry = null;
        Long evaluationId = null;
        if (log) {
            evaluationId = logServer.getNewEvaluationId();
            logEntry = new AccessControlRequest(evaluationId, idInfo,
                                                resourceUri, action, attributes);
        }
        // create XACML request
        RequestCtx request = XACMLDecoder.decodeRequestCtx(idInfo, resourceUri,
                             action, attributes);

        // TODO sysout comment
        // System.out.println("start with eval of " + evaluationId.longValue() +
        // ", " + XACMLEncoder.encodeRequestCtx(request));
        // evaluate
        PDPResult res = evaluate(request, evaluationId);
        ResponseCtx response = res.getResponseCtx();
        // translate result
        AuthoResult result = XACML2APIMapper.getAuthoResult(res
                             .getResponseCtx());

        if (log) {
            RecordEvaluationContext ctx = (RecordEvaluationContext) res
                                          .getEvaluationCtx();
            logEntry.finished(request, response, result, ctx.getExecTime(),
                              ctx.getVersion(), ctx.getDesignatorAttributes());
            logServer.storeAccessControlRequest(logEntry);
        }
        return result;
    }

    protected PDPResult evaluate(RequestCtx request, Long evaluationId)
    throws SecurityError {

        ResponseCtx response = null;
        ;
        // RecordEvaluationContext context = null;
        EvaluationCtx context = null;

        if (log) {
            if (evaluationId == null) {
                evaluationId = new Long(-1);
            }
            // if logging is activated, create a context which is
            // able to keep track of all resovled attributes
            try {
                context = new RecordEvaluationContext(request, attrFinder,
                                                      revocFinder, evaluationId);
            } catch (ParsingException e) {
                logger.error("ParsingException during creation of RecordEvaluationContext: "
                             + e.getMessage());
                throw new SecurityError(ErrorType.CONFIGURATION_ERROR,
                                        ReasonType.PDE_ENGINE_ERROR);
            }
            response = xacmlPDP.evaluate(context);
        } else {
            response = xacmlPDP.evaluate(request);
        }

        return new PDPResult(response, context);
    }

    /**
     * This method creates, based on an existing configuration, a configuration
     * as needed for running the PDP in analysis mode. For this,
     * <ul>
     * <li>make the static settings</li>
     * <li>modify (i.e. create a new) PDPConfig</li>
     * </ul>
     *
     * @param config
     * @return
     */
    public AnalysisConfig makeAnalysisConfig(PDPConfig config) {

        // replace all elements which are static and are required to change for
        // analysis
        replaceStaticAnalysisStuff();

        return new AnalysisConfig(config);
    }

    public ResponseCtx analyze(AnalysisCtx ctx) {
        return xacmlPDP.evaluate(ctx);
    }

    // TODO check with analysis config?
    private synchronized void replaceStaticAnalysisStuff() {
        if (!replacedFAnalysis) {
            if (mode == PDP_MODE.UNSET || mode == PDP_MODE.ANALYSIS) {
                mode = PDP_MODE.ANALYSIS;
            } else {
                throw new RuntimeException(
                    "Cannot create an Analysis PDPServer in a JVM where an productive PDP already exists");
            }

            replacedFAnalysis = true;
        }

        CombiningAlgFactory factory = new BaseCombiningAlgFactory();

        factory.addAlgorithm(new AnalysisDenyOverridesPolicyAlg());
        factory.addAlgorithm(new AnalysisDenyOverridesRuleAlg());
        factory.addAlgorithm(new AnalysisFirstApplicablePolicyAlg());
        factory.addAlgorithm(new AnalysisFirstApplicableRuleAlg());
        factory.addAlgorithm(new AnalysisOnlyOneApplicablePolicyAlg());
        factory.addAlgorithm(new AnalysisOrderedDenyOverridesPolicyAlg());
        factory.addAlgorithm(new AnalysisOrderedDenyOverridesRuleAlg());
        factory.addAlgorithm(new AnalysisOrderedPermitOverridesPolicyAlg());
        factory.addAlgorithm(new AnalysisOrderedPermitOverridesRuleAlg());
        factory.addAlgorithm(new AnalysisPermitOverridesPolicyAlg());
        factory.addAlgorithm(new AnalysisPermitOverridesRuleAlg());

        CombiningAlgFactory.setAllFactories(factory);
    }

    public String getXACMLPEPConfig() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean logBreakGlassAccess(long arg0, String arg1) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * This function is called by PEPs which have to fulfill the TODO
     * obligation, i.e., notify the PDP about an actually executed action which
     * influences the state of the PDP. For this, the AccessControlRequest is
     * loaded from the logServer and passed to the PDPState manager which
     * updates the PDP State according to the request.
     */
    public void notifyStateChange(long evaluationId) throws SecurityError {
        // get log entry
        if (log) {
            AccessControlRequest request = logServer
                                           .getAccessControlRequest(new Long(evaluationId));
            if (request == null) {
                logger.error("PDP received a notifyChange for evaluationId "
                             + evaluationId
                             + " where no AccessControlRequest was stored for or the entry");
                throw new SecurityError(ErrorType.CONFIGURATION_ERROR,
                                        ReasonType.PDE_ENGINE_ERROR, "Invalid evaluationId");
            } else if (!(request.getResult().getDecision() == Decision.DECISION_PERMIT)) {
                logger.error("PDP received a notifyChange for evaluationId "
                             + evaluationId
                             + " which was not permitted in the first place");
                throw new SecurityError(ErrorType.AUTHORIZATION_FAILED,
                                        ReasonType.DENY, "Referenced request was not permitted");
            } else {
                pdpState.updatePDPState(request);
            }
        } else {
            logger.error("Received a notifyStateChange (evaluationId "
                         + evaluationId + "), but no logService is active!");
        }
    }

    public void unload() {
        if (this.logServer != null) {
            this.logServer.shutdown();
        }
    }

    private class PDPResult {
        private ResponseCtx resCtx;
        private EvaluationCtx recCtx;

        public PDPResult(ResponseCtx resCtx, EvaluationCtx recCtx) {
            this.resCtx = resCtx;
            this.recCtx = recCtx;
        }

        public ResponseCtx getResponseCtx() {
            return resCtx;
        }

        public EvaluationCtx getEvaluationCtx() {
            return recCtx;
        }

        // public AuthoResult getAuthoResult() throws SecurityError {
        // if ( authoResult == null ) {
        // authoResult = new AuthoResult();
        //
        // if (resCtx.getResults().size() != 1 ) {
        // logger.error("Received unexpected number of results: " +
        // resCtx.getResults().size());
        // throw new SecurityError(ErrorType.CONFIGURATION_ERROR,
        // ReasonType.PDE_ENGINE_ERROR,
        // "Received unexpected number of results");
        // }
        //
        // Result xacmlResult = resCtx.getResults().iterator().next();
        //
        // authoResult.setDecision(Decision.getFromInt(xacmlResult.getDecision()));
        // authoResult.setObligations(transform(xacmlResult.getObligations()));
        // authoResult.setStatusCode(xacmlResult.getStatus().getCode());
        // authoResult.setStatusMessage(xacmlResult.getStatus().getMessage());
        //
        // // TODO get missing attributes
        //
        // }
        // return authoResult;
        // }
        //
        // private List<AuthoObligation> transform(Set<Obligation> xacmlOblgs) {
        // if ( xacmlOblgs == null && xacmlOblgs.size() > 0 ) {
        // List<AuthoObligation> oblgs = new Vector<AuthoObligation>();
        //
        // for ( Obligation xacmlOblg : xacmlOblgs ) {
        // AuthoObligation oblg = new AuthoObligation(xacmlOblg.getId());
        // if ( xacmlOblg.getAssignments() != null &&
        // xacmlOblg.getAssignments().size() > 0 ) {
        // Collection<AuthoAttribute> attrs = new
        // Vector<AuthoAttribute>(xacmlOblg.getAssignments().size());
        // for ( Attribute xacmlAttr : xacmlOblg.getAssignments()) {
        // attrs.add(new AuthoAttribute(AuthoAttribute.OBLIGATION_CATEGORY,
        // xacmlAttr.getId(), xacmlAttr.getValue().getType(),
        // xacmlAttr.getValue().toString()));
        // }
        // oblg.setParameters(attrs);
        // }
        // oblgs.add(oblg);
        // }
        // return oblgs;
        // } else {
        // return null;
        // }
        // }

        // protected static AuthoResult createAuthoResult(ResponseCtx response)
        // throws SecurityError {
        // AuthoResult result = new AuthoResult();
        //

        // }

    }
}
