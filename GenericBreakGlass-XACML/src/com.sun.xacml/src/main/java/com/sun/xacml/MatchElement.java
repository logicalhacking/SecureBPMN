package com.sun.xacml;

import com.sun.xacml.debug.Locatable;

/**
 * 
 *
 */
public interface MatchElement extends Locatable {
	public MatchResult match(EvaluationCtx context);
}
