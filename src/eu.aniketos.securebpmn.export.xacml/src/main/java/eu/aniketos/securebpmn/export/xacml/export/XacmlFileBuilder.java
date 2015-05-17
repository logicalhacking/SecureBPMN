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

package eu.aniketos.securebpmn.export.xacml.export;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.sun.xacml.*;

import com.sun.xacml.attr.AnyURIAttribute;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.AttributeValue;
import com.sun.xacml.attr.StringAttribute;
import com.sun.xacml.attr.TypeIdentifierConstants;

import com.sun.xacml.combine.PolicyCombiningAlgorithm;
import com.sun.xacml.combine.RuleCombiningAlgorithm;
import com.sun.xacml.cond.Condition;
import com.sun.xacml.cond.EqualFunction;
import com.sun.xacml.cond.Evaluatable;
import com.sun.xacml.cond.Function;

/**
 * Provides the methods to create a complete XACML policy.
 *
 */
public class XacmlFileBuilder {

    /**
     * output is used in {@link #encodePolicySet()} to provide a source for the
     * XML encoded Data
     */
    private OutputStream output;

    private PolicySet policySet;

    private List<Rule> rules;
    private List<PolicyTreeElement> policies;

    private List<TargetMatch> matches;
    private List<TargetMatchGroup> matchGroups;
    private List<TargetSection> targetSections;

    public XacmlFileBuilder() throws FileNotFoundException, ParsingException, UnknownIdentifierException {

        // createSodCondition();
        output = new ByteArrayOutputStream();
        policies = new ArrayList<PolicyTreeElement>();
        rules = new ArrayList<Rule>();
        matches = new ArrayList<TargetMatch>();
        matchGroups = new ArrayList<TargetMatchGroup>();
        targetSections = new ArrayList<TargetSection>();
    }

    /**
     * Takes the completed {@link PolicySet} and encodes it to XML-format.
     *
     * @throws RuntimeException
     *             if encoding fails
     * @return {@link XacmlFileBuilder#output} The encoded
     *         <code>PolicySet</code> in its XML-formatted version.
     */
    public OutputStream encodePolicySet() {

        try {
            this.policySet.encode(output, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            throw new RuntimeException("error during encoding process", e);
        }
        return output;
    }

    /**
     * Creates a new {@link PolicySet}. <br>
     * </br> And clears the {@link #policies}-list for the next
     * <code>PolicySet</code>
     *
     * @param policySetID
     *            the <code>PolicySet</code> identifier
     * @param policyCombiningAlg
     *            the <code>CombiningAlgorithm</code> used on the policies in
     *            this set
     * @param target
     *            the <code>Target</code> for this <code>PolicySet</code> (may
     *            be null)
     * @param policies
     *            a list of the policies in this set
     */
    public void createPolicySet(URI policySetID,
                                PolicyCombiningAlgorithm policyCombiningAlg, Target target,
                                List<PolicyTreeElement> policies) {

        policySet = new PolicySet(policySetID, policyCombiningAlg, target,
                                  policies);
        this.policies.clear();
    }
//TODO finish sod check if needed after pdp impl
    /*
    	public boolean evalSodCondition(Task currentTask, List<Task> connectedTasks) {
    		// check if user of currentTask has claimed/completed any of the tasks
    		// in the connectedTasks list
    		Main_Test main_Test = new Main_Test();

    		TaskService ts = main_Test.getTaskService();
    		HistoryService hs = main_Test.getHistoryService();

    		// get the current user of the given sod task
    		org.activiti.engine.task.Task focusedTask = ts.createTaskQuery()
    				.processDefinitionKey(currentTask.getId()).singleResult();
    		String currentUser = focusedTask.getAssignee();

    		// check if this user has already performed an action on a connected sod
    		// task
    		for (Iterator<Task> iterator = connectedTasks.iterator(); iterator
    				.hasNext();) {
    			Task task = (Task) iterator.next();

    			String taskIdToCheck = task.getId();
    			if (hs.createHistoricTaskInstanceQuery().taskAssignee(currentUser)
    					.count() != 0) {
    				return false;
    			}

    		}
    		return true;

    	}
    */
//TODO finish sod check if needed after pdp impl
    /*
    	public Condition createSodCondition(Task currentTask,
    			List<Task> connectedTasks) {

    		Expression expression = new EqualFunction(EqualFunction.NAME_BOOLEAN_EQUAL);

    		Condition condition = new Condition(expression);

    		return condition;

    	}
    */
    /**
     * Creates a new {@link Rule}.
     *
     * @param ruleID
     *            the identifier of the <code>Rule</code>
     * @param effect
     *            the result if the <code>Rule</code> applies as defined in
     *            {@link Result}
     * @param ruleDescription
     *            a textual description for the <code>Rule</code> (may be null)
     * @param target
     *            the <code>Target</code> for the <code>Rule</code>, or null if
     *            it should be inherited from the parent <code>Policy</code> or
     *            <code>PolicySet</code>
     * @param condition
     *            the <code>Condition</code> for this <code>Rule</code> (may be
     *            null)
     */
    public void createRule(URI ruleID, int effect, String ruleDescription,
                           Target target, Condition condition) {

        Rule rule = new Rule(ruleID, effect, ruleDescription, target, condition);

        if (!rules.contains(rule)) {
            rules.add(rule);
        }
    }

    /**
     * Creates a new {@link Policy}.
     *
     * @param policyID
     *            the identifier of the <code>Policy</code>
     * @param ruleCombiningAlg
     *            the <code>CombiningAlgorithm</code> used on the rules in this
     *            <code>Policy</code>
     * @param target
     *            the <code>Target</code> for the <code>Policy</code>, or null
     *            if it should be inherited
     * @param rules
     *            a list of the rules in this <code>Policy</code>
     */
    public void createPolicy(URI policyID,
                             RuleCombiningAlgorithm ruleCombiningAlg, Target target,
                             List<Rule> rules) {

        Policy policy = new Policy(policyID, ruleCombiningAlg, target, rules);

        if (!policies.contains(policy)) {
            policies.add(policy);
        }
        this.rules.clear();
    }

    /**
     * Creates a new {@link TargetMatch}.
     *
     * @param matchType
     *            used to determine which match type will be used <li>
     *            1:Subject-Role</li> <li>2:Resource</li> <li>3:Action</li>
     * @param attrValueString
     *            the name of the target to match
     * @throws URISyntaxException
     */
    public void createTargetMatch(int matchType, String attrValueString) throws URISyntaxException {

        URI category = null;
        URI attrType = null;
        URI attrId = null;
        Function function = null;
        AttributeValue attrValue = null;

        switch (matchType) {

        case 1:
            category = Constants.SUBJECT_CAT;
            attrType = TypeIdentifierConstants.STRING_URI;
            attrId = URI.create("urn:custom:subject:role");
            function = new EqualFunction(EqualFunction.NAME_STRING_EQUAL);
            attrValue = new StringAttribute(attrValueString);
            break;

        case 2:
            category = Constants.RESOURCE_CAT;
            attrType = TypeIdentifierConstants.ANYURI_URI;
            attrId = Constants.RESOURCE_ID;
            function = new EqualFunction(EqualFunction.NAME_ANYURI_EQUAL);
            attrValue = new AnyURIAttribute(new URI(attrValueString));
            break;

        case 3:
            category = Constants.ACTION_CAT;
            attrType = TypeIdentifierConstants.STRING_URI;
            attrId = Constants.ACTION_ID;
            function = new EqualFunction(EqualFunction.NAME_STRING_EQUAL);
            attrValue = new StringAttribute(attrValueString);
            break;

        default:
            System.out.println("\ncategory error\n");
            break;

        }
        Evaluatable eval = new AttributeDesignator(category, attrType, attrId,
                false, null);
        TargetMatch tm = new TargetMatch(function, eval, attrValue);
        matches.add(tm);
    }

    /**
     * Creates a new {@link TargetMatchGroup}. <br>
     * And adds it to the local {@link #matchGroups}-list</br>
     */
    private void createTargetMatchGroup() {

        TargetMatchGroup tmg = new TargetMatchGroup(matches);
        matchGroups.add(tmg);
    }

    /**
     * Creates a new {@link TargetSection}. <br>
     * And adds it to the local {@link #targetSections}-list</br>
     */
    private void createTargetSection() {

        TargetSection ts = new TargetSection(matchGroups);
        targetSections.add(ts);
    }

    /**
     * Creates a new {@link Target}. <br>
     * </br> Uses {@link #createTargetMatchGroup()} and
     * {@link #createTargetSection()} to create a <code>Target</code>. <br>
     * It clears the {@link #matches}, {@link #matchGroups},
     * {@link #targetSections}-lists for the next <code>Target</code>.</br>
     *
     * @return <b>target</b> the created <code>Target</code>
     */
    public Target createTarget() {

        createTargetMatchGroup();
        createTargetSection();
        Target target = new Target(getTargetSections());
        matches.clear();
        matchGroups.clear();
        targetSections.clear();

        return target;
    }

    /*
     * non-javadoc
     *
     * following are the needed getters for the XacmlExportMarshaller
     */
    private List<TargetSection> getTargetSections() {
        return targetSections;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public List<PolicyTreeElement> getPolicies() {
        return policies;
    }
}
