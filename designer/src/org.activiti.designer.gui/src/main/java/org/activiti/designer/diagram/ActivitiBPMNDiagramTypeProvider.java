package org.activiti.designer.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
// <SecureBPMN>
import org.eclipse.graphiti.features.ConfigurableFeatureProviderWrapper;
// </SecureBPMN>

public class ActivitiBPMNDiagramTypeProvider extends AbstractDiagramTypeProvider {

	private IToolBehaviorProvider[] toolBehaviorProviders;

	public ActivitiBPMNDiagramTypeProvider() {
		super();
		// <SecureBPMN>
		setFeatureProvider(
				// START SAP Research SCVM Extensions
				// Support for making the Diagram read-only during attack trace visualization.
				new ConfigurableFeatureProviderWrapper(
						// END SAP Research SCVM Extensions
						new ActivitiBPMNFeatureProvider(this)));
       // </SecureBPMN>
	}

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] { new ActivitiToolBehaviorProvider(this) };
		}
		return toolBehaviorProviders;
	}

}
