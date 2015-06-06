package com.sun.xacml.debug;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.sun.xacml.Constants;


/**
 * This class has two purposes: 
 * <ul>
 * <li>Manage the information captured at PDP startup, i.e., the parsing
 * 		information (for now: fileName and lineNumber; may change with 
 * 		new/other PolicyLoaders)</li>
 * <li>Manage the call stack information at Runtime</li>
 * </ul>
 * 
 * 
 * At runtime, this class is responsible for managing the Runtime Information 
 * within the XACML Engine. This class itself is <b>NOT</b> able to capture all 
 * required information at runtime. Instead, it has to be called from the outside and
 * provided with the required information. 
 * <br/>
 * This may be implemented by external code, e.g., by using aspectJ, capturing
 * all relevant events and provide the according information to 
 * <ul>
 * <li>setCalledFrom(Object calledFrom), when an object is called, whereas 
 * 		calledFrom is the calling (XACML) object</li>
 * <li>unsetCalledFrom(Object calledFrom), when a call returns, i.e., the 
 * 		evaluation is finished. This function is mainly to detect bugs in
 * 		the implementation and may be removed in productive environments</li>
 * </ul>
 * <br/>
 * To capture all required events, the following functions have to be monitored:
 * <ul>
 * <li>PolicyTreeElement.evaluate(EvaluationCtx), which includes 
 * 		Policy, PolicySet, PolicyReference, Rule </li>
 * <li>Evaluatable.evaluate(EvaluationCtx), which includes AttributeDesignator, 
 * 		AttributeSelector, AttributeValue, VariableReference, Condition, Apply</li>
 * <li>MatchElement.match(EvaluationCtx ), which includes Target, TargetMatch, 
 * 		TargetMatchGroup, TargetSection</li>
 * <li>Function.evaluate(List<Expression>, EvaluationCtx) which includes all 
 * 		Functions</li>
 * <li>CombiningAlgorithm.combine(EvaluationCtx, List<CombinerParameter>, List<CombinerElement>) 
 * 		which includes all Combing Algorithms</li>
 * </ul> 
 * 
 * 
 *
 */
public class RuntimeInfo {
	
	/**
	 * line number, -1 if undefined 
	 */
	private int lineNumber = -1;
	/**
	 * File name, null if undefined
	 */
	private String fileName = null;
	
	/**
	 * Defines the element type this RuntimeInfo object is assigned to 
	 */
	private ELEMENT_TYPE eleType;

	private boolean indirectLocateable = false;
	
	/**
	 * needed to create the call stack
	 */
	private RuntimeInfo calledFrom;
	
	/**
	 * 
	 */
	private Locatable xacmlObject;
	
	private static Logger logger = Logger.getLogger(RuntimeInfo.class);
	
	public enum SOURCE_TYPE {
		FILE
	}
	
	public enum ELEMENT_TYPE {
		POLICY_SET ("Policy Set"), 						//Locatable
		POLICY ("Policy"),								//Locatable
		POLICY_REFERENCE ("Policy Reference"),			//Locatable
		TARGET ("Target"),								//Locatable
		TARGET_SECTION ("Target Section"),				//Locatable
		TARGET_MATCH_GROUP ("Target Match Group"),		//Locatable
		TARGET_MATCH ("Target Match"),					//Locatable
		RULE ("Rule"),									//Locatable
		ATTRIBUTE_VALUE ("Attribute Value"),			//Locatable
		ATTRIBUTE_DESIGNATOR ("Attribute Designator"),	//Locatable
		ATTRIBUTE_SELECTOR ("Attribtue Selector"),		//Locatable
		VARIABLE_REFERENCE ("Variable Reference"),		//Locatable
		VARIABLE_DEFINITION ("Variable Definition"),	//Locatable
		CONDITION ("Condition"),						//Locatable
		APPLY ("Apply"),								//Locatable
		FUNCTION ("Function"), 							//IndirectLocatable
		COMBINING_ALG ("Combining Algorithm"), 			//IndirectLocatable
		OBLIGATION ("Obligation"),						//Locatable
		COMBINER_PARAMETER ("Combiner Parameter"); 		//Locatable
		//COMBINER_ELEMENT ("Cimbiner Element"); 			//Locatable
		
		
		private String name;
		
		ELEMENT_TYPE(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
		
	}
	
	protected RuntimeInfo(Locatable xacmlElement, Node node, ELEMENT_TYPE type) {
		this.xacmlObject = xacmlElement;
		Object tmp = node.getUserData(Constants.LINE_NUMBER);
		if (tmp instanceof Integer ) {
			lineNumber = ((Integer) tmp).intValue();
		}
		
		tmp = node.getUserData(Constants.SOURCE_FILE);
		if ( tmp instanceof String ) {
			fileName = (String) tmp;
		}
		this.eleType = type;
	}
	
	/**
	 * returns the line number of the object within the
	 * XACML source file
	 * @return
	 */
	public int getLineNumber() {
		return this.lineNumber;
	}
	
	/**
	 * returns the source file name  
	 * @return
	 */
	public String getFileName() {
		return this.fileName;
	}
	
	public ELEMENT_TYPE getElementType() {
		return this.eleType;
	}
	
	/**
	 * create a new Runtime Information object or null, if no source locations are available within 
	 * the node
	 * @param node
	 * @return
	 */
	public static RuntimeInfo getRuntimeInfo(Node node, ELEMENT_TYPE type ) {
		if ( node.getUserData(Constants.LINE_NUMBER) != null || 
				node.getUserData(Constants.SOURCE_FILE) != null ) {
			return new RuntimeInfo(null, node, type);
		} else {
			return null;
		}
	}
	
	/**
	 * create a new Runtime Information object or null, if no source locations are available within 
	 * the node
	 * @param node
	 * @return
	 */
	public static RuntimeInfo getRuntimeInfo(Locatable xacmlObject, Node node, ELEMENT_TYPE type ) {
		if ( node.getUserData(Constants.LINE_NUMBER) != null || 
				node.getUserData(Constants.SOURCE_FILE) != null ) {
			return new RuntimeInfo(xacmlObject, node, type);
		} else {
			return null;
		}
	}
	
	//public void setXACMLObject(Object xacmlObject) {
	public void setXACMLObject(Locatable xacmlObject) {
		this.xacmlObject = xacmlObject;
	}
	
	
	public Object getXACMLObject() {
		return xacmlObject;
	}
	
	private RuntimeInfo() {
		//empty constructor
	}
	
	/**
	 * This function provides a new SourceLocator object which is used to set and unset
	 * the SourceLocator objects of IndirectLoatable elements, (i.e., used as parameter
	 * for setSourceLocator and unsetSourceLocator methods). 
	 * <br/>
	 * Note: it is mainly a copy constructor which sets the new element type, thus, allow
	 * to differentiate 
	 * 
	 * @param contextSrcLocator
	 * @return
	 */
	public RuntimeInfo getIndirectRuntimeInfo(IndirectLocatable xacmlObject, ELEMENT_TYPE type) {
		RuntimeInfo src = new RuntimeInfo();
		src.fileName = this.fileName;
		src.lineNumber = this.lineNumber;
		src.indirectLocateable = true;
		src.calledFrom = this;
		src.xacmlObject = xacmlObject;
		src.eleType = type; 
		return src;
	}
	
	/**
	 * This function provides a new SourceLocator object with the same location
	 * information as it is based on.
	 * <br/> This function should be used at parsing time and not at runtime, 
	 * as the calledFrom information is not set (this is done at runtime). 
	 * Currently, the only case where it is used is for XACML1.0 when an apply 
	 * element is created to wrap a function defined by the FunctionId of the
	 * Condition element.
	 * @param xacmlObject
	 * @param type
	 * @return
	 */
	public RuntimeInfo getSourceLocator(Locatable xacmlObject, ELEMENT_TYPE type) {
		RuntimeInfo src = new RuntimeInfo();
		src.fileName = this.fileName;
		src.lineNumber = this.lineNumber;
		src.xacmlObject = xacmlObject;
		src.eleType = type; 
		return src;
	}
	
	public boolean isIndirectLocateable() {
		return indirectLocateable;
	}
	
	public String getLocationMsgForError() {
		return " near line " + ( lineNumber == -1 ? "unkown" : lineNumber ) 
			+ " from file " + ( fileName == null ? "unkown" : fileName );
			//+ ( eleType == null ? "" : " (" + eleType.getName() + ")");  
	}
	
	public String getLocationMsgShort() {
		return " (line " + ( lineNumber == -1 ? "unkown" : lineNumber ) 
			+ "@" + ( fileName == null ? "unkown" : fileName ) + ")";
			//+ ( eleType == null ? "" : " (" + eleType.getName() + ")");  
		// + (this.calledFrom != null ? " from " + calledFrom.getClass().getSimpleName() : "")
	}
	
	public String getLocationMsgRuntime() {
		if ( calledFrom != null && calledFrom.xacmlObject != null ) {
			return getLocationMsgShort() 
				+ " called from " + calledFrom.xacmlObject.getClass().getSimpleName() 
				+ ( calledFrom instanceof Locatable && ((Locatable) calledFrom).getRuntimeInfo() != null  ? 
					   ((Locatable) calledFrom).getRuntimeInfo().getLocationMsgShort() : "" ) ;
		} else {
			return getLocationMsgShort();
		}
	}
	
	public String getElementDescr() {
		return xacmlObject.getClass().getSimpleName() + getLocationMsgForError();
	}

	public void setCalledFrom(RuntimeInfo calledFrom) {
		if ( indirectLocateable ) {
			if ( calledFrom != this.calledFrom ) {
				logger.warn("For indirect locateable RuntimeInfo objects, calledFrom provided to this" +
						"function should be the same als already stored");
			}
		} else if ( this.calledFrom != null ) {
			logger.warn("Overwriting calledFrom object: " + this.calledFrom.getElementDescr() + " overwritten with " + calledFrom.getElementDescr());
			if (logger.isDebugEnabled()) {
				try {
					throw new RuntimeException("getStackTrace for Overwriting calledFrom object");
				} catch(RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
		this.calledFrom = calledFrom;
	}
	
	public void unsetCalledFrom(RuntimeInfo calledFrom) {
		if ( this.calledFrom == null ) {
			logger.warn("calledFrom is already unset!");
		} else if ( this.calledFrom != calledFrom) {
			logger.warn("Unsetting different object (" + calledFrom.getClass().getSimpleName() 
					+ ") than was set (" + this.calledFrom.getClass().getSimpleName() + ")!");
		}
		this.calledFrom = null;
	}
	
	public RuntimeInfo getCalledFrom() {
		return calledFrom;
	}
}
