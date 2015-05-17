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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.Task;

/**
 * Creates the ASLan representation for a BPMN 2.0 ParallelGateway element.
 *
 */
public class ParallelGatewayExport {
//
//	/**
//	 * Generates the ASLan representation of the provided ParallelGateway
//	 * element and sends the output to the provided AslanFileBuilder.
//	 *
//	 * @param parallelGateway
//	 *            The ParallelGateway for which the representation should be
//	 *            generated.
//	 * @param afb
//	 *            The AslanFileBuilder the output is sent to.
//	 */
//	public static void createParallelGatewayElements(
//			ParallelGateway parallelGateway, XacmlFileBuilder afb) {
//
//		final List<SequenceFlow> incoming = parallelGateway.getIncoming();
//		String rule = "step w_" + parallelGateway.getId();
//
//		if (incoming.size() > 0) {
//
//			// AND-join, AND-split
//			String ruleDef = " := ";
//
//			boolean firstDone = true;
//			List<String> natVars = new ArrayList<String>();
//			for (SequenceFlow parentFlow : incoming) {
//
//				final FlowElement predecessor = parentFlow.getSourceRef();
//
//				if (predecessor instanceof Task) {
//
//					if (firstDone) {
//						firstDone = false;
//					} else {
//						ruleDef += ". ";
//					}
//
//					String natVar = afb.addNatVar();
//
//					ruleDef += "done(task(" + predecessor.getId() + ","
//							+ natVar + "))";
//					natVars.add(natVar);
//
//				} else if (predecessor instanceof Gateway) {
//
//					if (firstDone) {
//						firstDone = false;
//					} else {
//						ruleDef += ". ";
//					}
//
//					ruleDef += predecessor.getId() + "_to_"
//							+ parallelGateway.getId();
//
//				} else if (predecessor instanceof StartEvent) {
//
//					if (firstDone) {
//						firstDone = false;
//					} else {
//						ruleDef += ". ";
//					}
//
//					ruleDef += "start_event_" + predecessor.getId();
//
//				}
//			}
//
//			if (natVars.size() > 0) {
//
//				rule += "(";
//
//				for (int i = 0; i < natVars.size() - 1; i++) {
//					rule += natVars.get(i) + ",";
//				}
//				rule += natVars.get(natVars.size() - 1) + ")";
//
//			}
//
//			rule += ruleDef + " => ";
//
//			boolean firstAuxFact = true;
//			for (SequenceFlow childFlow : parallelGateway.getOutgoing()) {
//
//				final FlowElement successor = childFlow.getTargetRef();
//
//				if (successor instanceof Task || successor instanceof Gateway
//						|| successor instanceof EndEvent) {
//
//					final String auxFact = parallelGateway.getId() + "_to_"
//							+ successor.getId();
//					afb.addType("fact", auxFact);
//
//					if (firstAuxFact) {
//						rule += auxFact;
//						firstAuxFact = false;
//					} else {
//						rule += ". " + auxFact;
//					}
//				}
//			}
//
//			afb.addRule(rule);
//
//		}
//	}

}
