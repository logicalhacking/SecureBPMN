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

package eu.aniketos.securebpmn.export.html.export;

import java.util.List;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.BusinessRuleTask;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CandidateGroup;
import org.eclipse.bpmn2.CandidateUser;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.IOParameter;
import org.eclipse.bpmn2.MailTask;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.UserTask;

/**
 * Helper class for creating the HTML property string for a given FlowElement.
 *
 */
public class PropertiesStringBuilder {

    /**
     * Creates the HTML properties string for a given FlowElement.
     *
     * @param flowElement
     *            The FlowElement for which the properties should be written to
     *            a string.
     * @return The properties of the given FlowElement represented as a String.
     */
    public static String create(FlowElement flowElement) {
        if (flowElement instanceof SequenceFlow) {
            return createSequenceFlowString((SequenceFlow) flowElement);
        } else if (flowElement instanceof StartEvent) {
            return createStartEventString((StartEvent) flowElement);
        } else if (flowElement instanceof EndEvent) {
            return createEndEventString((EndEvent) flowElement);
        } else if (flowElement instanceof UserTask) {
            return createUserTaskString((UserTask) flowElement);
        } else if (flowElement instanceof ScriptTask) {
            return createScriptTaskString((ScriptTask) flowElement);
        } else if (flowElement instanceof ServiceTask) {
            return createServiceTaskString((ServiceTask) flowElement);
        } else if (flowElement instanceof MailTask) {
            return createMailTaskString((MailTask) flowElement);
        } else if (flowElement instanceof ExclusiveGateway) {
            return createExclusiveGatewayString((ExclusiveGateway) flowElement);
        } else if (flowElement instanceof BusinessRuleTask) {
            return createBusinessRuleTaskString((BusinessRuleTask) flowElement);
        } else if (flowElement instanceof CallActivity) {
            return createCallActivityString((CallActivity) flowElement);
        } else if (flowElement instanceof BoundaryEvent) {
            return createBoundaryEventString((BoundaryEvent) flowElement);
        } else {
            return "&nbsp;";
        }
    }

    /**
     * Writes the source ID, target ID and condition expression of a
     * SequenceFlow to a String.
     *
     * @param sequenceFlow
     *            The SequenceFlow for which the String should be generated.
     * @return The properties represented as a String.
     */
    public static String createSequenceFlowString(SequenceFlow sequenceFlow) {
        String res = "source ID: " + sequenceFlow.getSourceRef().getId()
                     + "<br>target ID: " + sequenceFlow.getTargetRef().getId();

        if (sequenceFlow.getConditionExpression() != null) {
            res += "<br>condition: "
                   + sequenceFlow.getConditionExpression().getBody();
        }
        return res;
    }

    /**
     * Writes the initiator and the form key of a StartEvent to a String.
     * Additionally, for a TimerStartEvent the String contains the time
     * duration, time date and the time cycle.
     *
     * @param startEvent
     *            The StartEvent for which the String should be generated.
     * @return The properties represented as a String.
     */
    public static String createStartEventString(StartEvent startEvent) {
        boolean first = true;
        String res = "";

        if (startEvent.getInitiator() != null
                && startEvent.getInitiator().length() > 0) {
            res += "initiator: " + startEvent.getInitiator();
            first = false;
        }

        if (startEvent.getFormKey() != null
                && startEvent.getFormKey().length() > 0) {
            if (!first)
                res += "<br>";
            res += "form key: " + startEvent.getFormKey();
            if (first)
                first = false;
        }

        if (startEvent.getEventDefinitions().size() > 0) {
            TimerEventDefinition timerDef = (TimerEventDefinition) startEvent
                                            .getEventDefinitions().get(0);

            if (timerDef.getTimeDuration() != null
                    && (((FormalExpression) timerDef.getTimeDuration())
                        .getBody() != null && ((FormalExpression) timerDef
                                               .getTimeDuration()).getBody().length() > 0)) {

                if (!first)
                    res += "<br>";
                res += "time duration: "
                       + ((FormalExpression) timerDef.getTimeDuration())
                       .getBody();

            } else if (((FormalExpression) timerDef.getTimeDate()).getBody() != null
                       && ((FormalExpression) timerDef.getTimeDate()).getBody()
                       .length() > 0) {

                if (!first)
                    res += "<br>";
                res += "time date: "
                       + ((FormalExpression) timerDef.getTimeDate()).getBody();

            } else if (((FormalExpression) timerDef.getTimeCycle()).getBody() != null
                       && ((FormalExpression) timerDef.getTimeCycle()).getBody()
                       .length() > 0) {

                if (!first)
                    res += "<br>";
                res += "time cycle: "
                       + ((FormalExpression) timerDef.getTimeCycle())
                       .getBody();

            }
        }

        if (res == "")
            res = "&nbsp;";
        return res;
    }

    /**
     * Writes the error code of an EndEvent to a String.
     *
     * @param endEvent
     *            The EndEvent for which the String should be generated.
     * @return The property represented as a String.
     */
    public static String createEndEventString(EndEvent endEvent) {
        if (endEvent.getEventDefinitions().size() > 0) {
            ErrorEventDefinition errorDef = (ErrorEventDefinition) endEvent
                                            .getEventDefinitions().get(0);
            if (errorDef.getErrorCode() != null
                    && errorDef.getErrorCode().length() > 0) {

                return "error code: " + errorDef.getErrorCode();

            }
        }

        return "&nbsp;";
    }

    /**
     * Writes the assignee/candidate groups/candidate users, form key, due date,
     * priority and documentation of a UserTask to a String.
     *
     * @param userTask
     *            The UserTask for which the String should be generated.
     * @return The properties represented as a String.
     */
    public static String createUserTaskString(UserTask userTask) {
        boolean first = true;
        String res = "";

        if (userTask.getAssignee() != null
                && userTask.getAssignee().length() > 0) {
            res += "assignee: " + userTask.getAssignee();
            first = false;

        } else if (userTask.getCandidateUsers() != null
                   && userTask.getCandidateUsers().size() > 0) {
            res += "candidate groups: ";
            boolean firstInGroup = true;

            for (CandidateGroup candidateGroup : userTask.getCandidateGroups()) {
                if (firstInGroup) {
                    res += candidateGroup.getGroup();
                    firstInGroup = false;
                } else {
                    res += ", " + candidateGroup.getGroup();
                }
            }
            first = false;

        } else if (userTask.getCandidateGroups() != null
                   && userTask.getCandidateGroups().size() > 0) {
            res += "candidate users: ";
            boolean firstInGroup = true;

            for (CandidateUser candidateUser : userTask.getCandidateUsers()) {
                if (firstInGroup) {
                    res += candidateUser.getUser();
                    firstInGroup = false;
                } else {
                    res += ", " + candidateUser.getUser();
                }
            }
            first = false;
        }

        if (userTask.getFormKey() != null && userTask.getFormKey().length() > 0) {
            if (!first)
                res += "<br>";
            res += "form key: " + userTask.getFormKey();
            first = false;
        }

        if (userTask.getDueDate() != null && userTask.getDueDate().length() > 0) {
            if (!first)
                res += "<br>";
            res += "due date: " + userTask.getDueDate();
            first = false;
        }

        if (userTask.getPriority() != null) {
            if (!first)
                res += "<br>";
            res += "priority: " + userTask.getPriority();
            first = false;
        }

        if (userTask.getDocumentation() != null
                && userTask.getDocumentation().size() > 0) {
            if (!first)
                res += "<br>";

            final Documentation documentation = userTask.getDocumentation()
                                                .get(0);

            if (documentation.getText() != null
                    && !"".equals(documentation.getText())) {
                res += "documentation: " + documentation.getText();
            }
        }

        if (res == "")
            res = "&nbsp;";
        return res;
    }

    /**
     * Writes the script language and the script itself of a ScriptTask to a
     * String.
     *
     * @param scriptTask
     *            The ScriptTask for which the String should be generated.
     * @return The properties represented as a String.
     */
    public static String createScriptTaskString(ScriptTask scriptTask) {

        return "language: " + scriptTask.getScriptFormat() + "<br>script: "
               + scriptTask.getScript();
    }

    /**
     * Writes the result variable, type and service class/expression/delegate
     * expression of a ServiceTask to a String
     *
     * @param serviceTask
     *            The ServiceTask for which the String should be generated.
     * @return The properties represented as a String.
     */
    public static String createServiceTaskString(ServiceTask serviceTask) {
        String res = "";

        res += "type: " + serviceTask.getImplementationType();

        if (serviceTask.getImplementation() != null
                && serviceTask.getImplementation().length() > 0) {
            if (serviceTask.getImplementationType().equals(
                        "delegateExpressionType")) {
                res += "<br>delegate expression: ";
            } else if (serviceTask.getImplementationType().equals(
                           "expressionType")) {
                res += "<br>expression: ";
            } else {
                res += "<br>java class: ";
            }
            res += serviceTask.getImplementation();
        }

        if (serviceTask.getResultVariableName() != null
                && serviceTask.getResultVariableName().length() > 0) {
            res += "<br>result variable: "
                   + serviceTask.getResultVariableName();
        }

        if (res == "")
            res = "&nbsp;";
        return res;
    }

    /**
     * Writes the to, from, subject, cc, bcc, text and html text properties of a
     * MailTask to a String.
     *
     * @param mailTask
     *            The MailTask for which the String should be generated.
     * @return The properties represented as a String.
     */
    public static String createMailTaskString(MailTask mailTask) {
        String res = "";

        if (mailTask.getTo() != null && mailTask.getFrom() != null)
            res += "to: " + mailTask.getTo() + "<br>from: "
                   + mailTask.getFrom();

        if (mailTask.getSubject() != null && mailTask.getSubject().length() > 0)
            res += "<br>subject: " + mailTask.getSubject();

        if (mailTask.getCc() != null && mailTask.getCc().length() > 0)
            res += "<br>cc: " + mailTask.getCc();

        if (mailTask.getBcc() != null && mailTask.getBcc().length() > 0)
            res += "<br>bcc: " + mailTask.getBcc();

        if (mailTask.getText() != null && mailTask.getHtml() != null)
            res += "<br>text: " + mailTask.getText() + "<br>html text: "
                   + mailTask.getHtml();

        return res;
    }

    /**
     * Writes the rule names, input variables, excluded and result variable
     * properties of a BusinessRuleTask to a String.
     *
     * @param businessRuleTask
     *            The BusinessRuleTask for which the String should be generated.
     * @return The properties represented as a String.
     */
    public static String createBusinessRuleTaskString(
        BusinessRuleTask businessRuleTask) {
        String res = "";
        boolean first = true;

        if (businessRuleTask.getRuleNames().size() > 0) {
            StringBuilder ruleNameBuilder = new StringBuilder();
            for (String ruleName : businessRuleTask.getRuleNames()) {
                if (ruleNameBuilder.length() > 0) {
                    ruleNameBuilder.append(",");
                }
                ruleNameBuilder.append(ruleName);
            }
            res += "rules: " + ruleNameBuilder.toString();
            first = false;
        }

        if (businessRuleTask.getInputVariables().size() > 0) {
            StringBuilder inputBuilder = new StringBuilder();
            for (String input : businessRuleTask.getInputVariables()) {
                if (inputBuilder.length() > 0) {
                    inputBuilder.append(",");
                }
                inputBuilder.append(input);
            }
            if (!first)
                res += "<br>";
            res += "input variables: " + inputBuilder.toString();
            if (first)
                first = false;
        }

        if (businessRuleTask.getRuleNames().size() > 0) {
            if (!first)
                res += "<br>";
            res += "excluded: " + businessRuleTask.isExclude();
            if (first)
                first = false;
        }

        if (businessRuleTask.getResultVariableName() != null
                && businessRuleTask.getResultVariableName().length() > 0) {
            if (!first)
                res += "<br>";
            res += "result variable: "
                   + businessRuleTask.getResultVariableName();
        }

        return res;
    }

    /**
     * Writes the called element, input parameters and output parameters of a
     * CallActivity to a String.
     *
     * @param callActivity
     *            The CallActivity for which the String should be generated.
     * @return The properties represented as a String.
     */
    public static String createCallActivityString(CallActivity callActivity) {
        String res = "";
        boolean first = true;

        if (callActivity.getCalledElement() != null
                && callActivity.getCalledElement().length() > 0) {
            res += "called element: " + callActivity.getCalledElement();
            first = false;
        }

        if (callActivity.getInParameters().size() > 0) {
            if (!first)
                res += "<br>";
            res += "input parameters: ";
            boolean firstLine = true;
            for (IOParameter param : callActivity.getInParameters()) {
                if (!firstLine)
                    res += "<br>";
                res += "source=" + param.getSource() + ", target="
                       + param.getTarget();
                if (firstLine)
                    firstLine = false;
            }
            if (first)
                first = false;
        }

        if (callActivity.getOutParameters().size() > 0) {
            if (!first)
                res += "<br>";
            res += "output parameters: ";
            boolean firstLine = true;
            for (IOParameter param : callActivity.getOutParameters()) {
                if (!firstLine)
                    res += "<br>";
                res += "source=" + param.getSource() + ", target="
                       + param.getTarget();
                if (firstLine)
                    firstLine = false;
            }
        }

        if (res == "")
            res = "&nbsp;";
        return res;
    }

    /**
     * Writes the default flow of an ExclusiveGateway to a String.
     *
     * @param exclusiveGateway
     *            The ExclusiveGateway for which the String should be generated.
     * @return The property represented as a String.
     */
    public static String createExclusiveGatewayString(
        ExclusiveGateway exclusiveGateway) {
        if (exclusiveGateway.getDefault() != null)
            return "default flow ID: " + exclusiveGateway.getDefault().getId();
        else
            return "&nbsp;";
    }

    /**
     * Writes the cancel activity, time duration, time date and time cycle for a
     * TimerBoundaryEvent or the error code for a ErrorBoundaryEvent to a
     * String.
     *
     * @param boundaryEvent
     *            The BoundaryEvent for which the String should be generated.
     * @return The propertie(s) represented as a String.
     */
    public static String createBoundaryEventString(BoundaryEvent boundaryEvent) {
        String res = "";

        List<EventDefinition> eventDefinitionList = boundaryEvent
                .getEventDefinitions();
        if (eventDefinitionList.size() == 1) {
            if (eventDefinitionList.get(0) instanceof TimerEventDefinition) {
                TimerEventDefinition timerDef = (TimerEventDefinition) eventDefinitionList
                                                .get(0);

                // start TimerBoundaryEvent element
                if (boundaryEvent.isCancelActivity()) {
                    res += "cancel activity: true";
                } else {
                    res += "cancel activity: false";
                }

                if (timerDef.getTimeDuration() != null
                        && (((FormalExpression) timerDef.getTimeDuration())
                            .getBody() != null && ((FormalExpression) timerDef
                                                   .getTimeDuration()).getBody().length() > 0)) {

                    res += "<br>time duration: "
                           + ((FormalExpression) timerDef.getTimeDuration())
                           .getBody();

                } else if (((FormalExpression) timerDef.getTimeDate())
                           .getBody() != null
                           && ((FormalExpression) timerDef.getTimeDate())
                           .getBody().length() > 0) {

                    res += "<br>time date: "
                           + ((FormalExpression) timerDef.getTimeDate())
                           .getBody();

                } else if (((FormalExpression) timerDef.getTimeCycle())
                           .getBody() != null
                           && ((FormalExpression) timerDef.getTimeCycle())
                           .getBody().length() > 0) {

                    res += "<br>time cycle: "
                           + ((FormalExpression) timerDef.getTimeCycle())
                           .getBody();

                }
                // end TimerBoundaryEvent element

            } else if (eventDefinitionList.get(0) instanceof ErrorEventDefinition) {
                ErrorEventDefinition errorDef = (ErrorEventDefinition) eventDefinitionList
                                                .get(0);

                // start ErrorBoundaryEvent element
                if (errorDef.getErrorCode() != null
                        && errorDef.getErrorCode().length() > 0) {

                    res += "error code: " + errorDef.getErrorCode();

                }
                // end ErrorBoundaryEvent element
            }
        }
        if (res == "")
            res = "&nbsp;";
        return res;
    }

}
