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

package eu.aniketos.securebpmn.xacml.finder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class MySVNClient {
    private SVNRepository repository;
    private String svn_url, username, password;
    private long version;

    private Logger logger = Logger.getLogger(MySVNClient.class);

    public static final String COMMIT_VERSION = "svn:entry:committed-rev",
                               COMMIT_DATE = "svn:entry:committed-date",
                               VERION = "svn:entry:revision";


    public MySVNClient(String svn_url, String username, String password, long version) {
        this.svn_url = svn_url;
        this.username = username;
        this.password = password;
        this.version = version;
        init();
    }

    public MySVNClient(List<String> args) {
        if ( args.size() < 3 ) {
            throw new RuntimeException("Missing parameters for " + this.getClass().toString());
        } else {
            svn_url = args.get(0);
            username = args.get(1);
            password = args.get(2);

            version = -1;

            if ( args.size() >= 4 ) {
                try {
                    version = Integer.parseInt(args.get(3));
                } catch(NumberFormatException e) {
                    logger.warn("Could not retreive int value from " + args.get(3) + ", using -1 as default");
                }
            }
        }
        init();
    }

    // String svn_url, String username, String password, int version
    private void init() {
        logger.info("Creating MySVNClient for svn " + svn_url + ", username " + username + ", password " + password.replaceAll(".", "\\*"));

        DAVRepositoryFactory.setup( );
        SVNURL url;
        try {
            url = SVNURL.parseURIDecoded(svn_url);
            repository = SVNRepositoryFactory.create( url );

            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( username , password );
            repository.setAuthenticationManager( authManager );
        } catch (SVNException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public long getCheckinSVNVersion(String svn_path) {

        SVNProperties props = new SVNProperties();
        try {
            repository.getFile(svn_path, version, props, null);
            return Long.parseLong(props.getStringValue(COMMIT_VERSION));
        } catch (SVNException e) {
            logger.error("Could not retreive file infos for " + svn_path + ": " + e.getMessage(), e);
            return -1;
        } catch (NumberFormatException e) {
            logger.warn("Could not transform committed revision " + props.getStringValue("svn:entry:committed-rev") + " to a long value");
            return -1;
        }
    }

    public long getSVNVersion(String svn_path) {

        SVNProperties props = new SVNProperties();
        try {
            repository.getFile(svn_path, version, props, null);
            return Long.parseLong(props.getStringValue(VERION));
        } catch (SVNException e) {
            logger.error("Could not retreive file infos for " + svn_path + ": " + e.getMessage(), e);
            return -1;
        } catch (NumberFormatException e) {
            logger.warn("Could not transform committed revision " + props.getStringValue("svn:entry:committed-rev") + " to a long value");
            return -1;
        }
    }



    public long downloadFile(String svn_path, String local_path) throws SVNException, IOException {
        SVNNodeKind nodeKind = repository.checkPath(svn_path, version);

        if ( nodeKind == SVNNodeKind.FILE ) {
            SVNProperties props = new SVNProperties();
            FileOutputStream fOS = new FileOutputStream(new File(local_path));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            long updated = repository.getFile(svn_path, version, props, fOS);
            buffer.flush();
            buffer.close();
            try {
                updated = Long.parseLong(props.getStringValue(COMMIT_VERSION));
            } catch (NumberFormatException e) {
                logger.warn("Could not transform committed revision " + props.getStringValue("svn:entry:committed-rev") + " to a long value");
            }
            return updated;
        } else {
            throw new SVNException(SVNErrorMessage.create(SVNErrorCode.BAD_FILENAME, "requested file " + svn_path + " is not a file"));
        }

    }

    public String getTextFile(String name) throws SVNException {
        return getTextFile(name, this.version, null, null);
    }

    public String getTextFile(String name, int version) throws SVNException {
        return getTextFile(name, version, null, null);
    }

    public String getTextFile(String name, long version, SVNProperties props, ByteArrayOutputStream buffer) throws SVNException {
        if ( logger.isDebugEnabled() )  {
            logger.debug("Try to retreive file "  + repository.getLocation().toString() + "/" + name + " in version " + version);
        }
        SVNNodeKind nodeKind = repository.checkPath(name, version );

        if ( nodeKind == SVNNodeKind.FILE ) {
            if ( buffer == null ) {
                buffer = new ByteArrayOutputStream();
            } else {
                buffer.reset();
            }
            long revision = repository.getFile( name , version , props , buffer );
            if ( logger.isDebugEnabled() ) {
                logger.debug("got " + name  + " in revision " + revision);
            }

            return buffer.toString();
        } else {
            logger.error("URL \"" + svn_url + "/" + name + "\" does not point to a file or does not exist");
            return null;
        }
    }

    public String getSvn_url() {
        return svn_url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getVersion() {
        return version;
    }
}
