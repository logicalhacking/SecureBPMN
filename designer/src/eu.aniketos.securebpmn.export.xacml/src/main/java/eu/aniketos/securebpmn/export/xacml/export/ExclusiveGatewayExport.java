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

import java.util.List;

import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.Task;

/**
 * Creates the ASLan representation for a BPMN 2.0 ExclusiveGateway element.
 *
 */
public class ExclusiveGatewayExport {
//
//	/**
//	 * Generates the ASLan representation of the provided ExclusiveGateway
//	 * element and sends the output to the provided AslanFileBuilder.
//	 *
//	 * @param exclusiveGateway
//	 *            The ExclusiveGateway for which the representation should be
//	 *            generated.
//	 * @param afb
//	 *            The AslanFileBuilder the output is sent to.
//	 */
//	public static void createExclusiveGatewayElements(
//			ExclusiveGateway exclusiveGateway, XacmlFileBuilder afb) {
//
//		final List<SequenceFlow> incoming = exclusiveGateway.getIncoming();
//
//		if (incoming.size() > 1) {
//
//			// XOR-join
//			final List<SequenceFlow> outgoing = exclusiveGateway.getOutgoing();
//
//			FlowElement successor = null;
//			String auxFact = "";
//
//			if (outgoing.size() > 0) {
//				successor = outgoing.get(0).getTargetRef();
//				auxFact += exclusiveGateway.getId() + "_to_"
//						+ successor.getId();
//			}
//
//			int branchCounter = 1;
//			for (SequenceFlow parentFlow : incoming) {
//
//				final FlowElement predecessor = parentFlow.getSourceRef();
//
//				String lhs = "";
//
//				if (predecessor instanceof Task) {
//
//					String natVar = afb.addNatVar();
//
//					lhs += "(" + natVar + ") := done(task("
//							+ predecessor.getId() + "," + natVar + "))";
//
//				} else if (predecessor instanceof Gateway) {
//
//					lhs += " := " + predecessor.getId() + "_to_"
//							+ exclusiveGateway.getId();
//
//				} else if (predecessor instanceof StartEvent) {
//
//					lhs += " := start_event_" + predecessor.getId();
//
//				}
//
//				if (lhs.length() > 0) {
//
//					afb.addType("fact", auxFact);
//					afb.addRule("step " + exclusiveGateway.getId() + "_branch"
//							+ branchCounter + lhs + " => " + auxFact);
//
//					branchCounter++;
//				}
//			}
//
//		} else {
//
//			// XOR-split
//			FlowElement predecessor = null;
//
//			if (incoming.size() > 0) {
//				predecessor = incoming.get(0).getSourceRef();
//			} else {
//				// ExclusiveGateway not reachable
//				return;
//			}
//
//			String lhs = "";
//
//			if (predecessor instanceof Task) {
//
//				String natVar = afb.addNatVar();
//
//				lhs += "(" + natVar + ") := done(task(" + predecessor.getId()
//						+ "," + natVar + "))";
//
//			} else if (predecessor instanceof Gateway) {
//
//				lhs += " := " + predecessor.getId() + "_to_"
//						+ exclusiveGateway.getId();
//
//			} else if (predecessor instanceof StartEvent) {
//
//				lhs += " := start_event_" + predecessor.getId();
//
//			}
//
//			int branchCounter = 1;
//			for (SequenceFlow childFlow : exclusiveGateway.getOutgoing()) {
//
//				final String auxFact = exclusiveGateway.getId() + "_to_"
//						+ childFlow.getTargetRef().getId();
//
//				afb.addType("fact", auxFact);
//				afb.addRule("step " + exclusiveGateway.getId() + "_branch"
//						+ branchCounter + lhs + " => " + auxFact);
//
//				branchCounter++;
//
//			}
//		}
//	}

}
