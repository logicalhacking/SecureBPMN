package com.sun.xacml.debug;


/**
 * 
 * This interface is used for all elements which are locatable, 
 * i.e., elements which 
 * <ul>
 * <li>have a representation in the source code of the policy, i.e., 
 *     are constructed from a dom node</li>
 * <li>should be traceable at runtime, i.e., before/after events can 
 *     be subsribed at evaluation runtime</li>
 * </ul>
 * 
 *
 */
public interface Locatable {
	
	/**
	 * 
	 * @return a valid RuntimeInfo or null, if no information is available,
	 * either, during the parsing of the policies the line tracking feature 
	 * was not activated, or, the element does not have a representation within
	 * the source policy, e.g., it was retrieved from a AttributeDesignator from
	 * or is the result of a function.
	 */
	RuntimeInfo getRuntimeInfo();
}
