package com.sun.xacml.finder.impl;

import java.util.HashSet;
import java.util.Set;

import com.sun.xacml.EvaluationCtx;
import com.sun.xacml.ParsingException;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.attr.AttributeDesignator;
import com.sun.xacml.attr.StandardAttributeFactory;
import com.sun.xacml.ctx.Attribute;
import com.sun.xacml.finder.RequiredAttributesModule;

/**
 * 
 * Simple class / naiv implementation which does not do any prefetching 
 * and simply returns the currently required attribute
 * 
 *
 */
public class CurrentRequiredAttributeModule extends RequiredAttributesModule {
	
    private static StandardAttributeFactory attrFactory = StandardAttributeFactory.getFactory();

	@Override
	public Set<Attribute> resolveRequiredAttributes(EvaluationCtx context,
			AttributeDesignator attrDesignator) {
		
		Set<Attribute> attr = new HashSet<Attribute>();
		
		try {
			attr.add(new Attribute(attrDesignator.getId(), 
					attrDesignator.getIssuer() == null ? null : attrDesignator.getIssuer().toString(), 
					attrFactory.createValue(attrDesignator.getType(), null)));
		} catch (UnknownIdentifierException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		}
		
		return attr;
	}

}
