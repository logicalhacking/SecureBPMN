package com.sun.xacml.finder;

import java.util.Set;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.ctx.Attribute;

public abstract class RequiredAttributesModule {
	
	public abstract Set<Attribute> resolveRequiredAttributes(EvaluationCtx context, AttributeDesignator attrDesignator);

}
