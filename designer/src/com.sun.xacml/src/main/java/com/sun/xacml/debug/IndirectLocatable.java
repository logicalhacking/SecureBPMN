package com.sun.xacml.debug;


/**
 * 
 * Some elements, i.e., mainly functions, are only referenced 
 * and do not have a separate java object for every referenced
 * function. Thus, such functions are executed within a specific 
 * context which has to be set before they are called.
 * <br/>  
 * <b>Note:</b>setting and unsetting the context (i.e., the 
 * SourceLocator of the encapsulating element) is <b>NOT</b>
 * thread safe, thus, this feature should not be used when 
 * multiple evaluations are executed in parallel
 * 
 * 
 *
 */
public interface IndirectLocatable extends Locatable {
	
	/**
	 * Sets the SourceLocator of the encapsulating element. This
	 * object <i>should</i> be retrieved from SourceLocator.getExpressionSourceLocator
	 * @param src
	 */
	void setRuntimeInfo(RuntimeInfo src);
	
	/**
	 * For the unset method, the <i>same</i> object should be used
	 * as for the set method. The unset is mainly done for detecting
	 * errors in the implementation.
	 * 
	 * @param src
	 */
	void unsetRuntimeInfo(RuntimeInfo src);
}
