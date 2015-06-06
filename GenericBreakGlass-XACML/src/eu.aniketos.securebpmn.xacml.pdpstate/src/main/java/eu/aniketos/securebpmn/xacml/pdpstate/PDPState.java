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

package eu.aniketos.securebpmn.xacml.pdpstate;

import java.io.BufferedInputStream;
import java.io.File;
import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.Document;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;
import eu.aniketos.securebpmn.xacml.api.autho.AuthoAttribute;
import eu.aniketos.securebpmn.xacml.pdpstate.InvalidAssignmentException.Reason;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeAssignment;
import eu.aniketos.securebpmn.xacml.pdpstate.db.AttributeType;
import eu.aniketos.securebpmn.xacml.pdpstate.db.ContextAttribute;
import eu.aniketos.securebpmn.xacml.pdpstate.db.ContextAttributeAssignment;
import eu.aniketos.securebpmn.xacml.pdpstate.db.HibernateUtil;

/**
 * 
 * Manages the state of the PDP as it can be retrieved from the PDPStateModule.
 * 
 * Changes have to be done through this class ans especially through the HibernateUtil,
 * as here some caching is implemented. Bypassing this caching mechanism will result in 
 * strange errors.
 * 
 */
public class PDPState {
	
	private static PDPState instance;
	public static final String CONFFILE_NAME = "hibernate.pdpState.cfg.xml";
	
	private SessionFactory factory;
	private HibernateUtil hibernateUtil;
	
	private static final Logger logger = Logger.getLogger(PDPState.class);
	
	private static final String ATTRTYPE = "attrType",
			VALIDAT = "validAt",
			VALIDFROM = "validFrom",
			VALIDTO = "validTo",
			VALUE = "value",
			CTXATTRTYP0 = "ctxAttrTypeNull",
			CTXATTR0 = "ctxAttrNull",
			CTXATTRTYP1 = "ctxAttrTypeOne",
			CTXATTR1 = "ctxAttrOne";
	
	public static PDPState getInstance() {
		if ( instance == null ) {
			createInstance();
		}
		return instance;
	}
	
	private static synchronized void createInstance() {
		if ( instance == null ) {
			instance = new PDPState();
		}
	}
	
	private PDPState() {
		logger.debug("Starting PDPState Server: Creating SessionFactory");
		factory = buildSessionFactory();
		factory.openSession();

		logger.debug("Creating HibernateUtil");
		hibernateUtil = HibernateUtil.createHibernateUtil(factory);
		
	}
	
    private SessionFactory buildSessionFactory() {
        try {
        	
			File confFile = new File(CONFFILE_NAME);
			if ( ! confFile.exists() ) {
				// try with conf before...
				confFile = new File("conf/" +CONFFILE_NAME);
			}
			if ( ! confFile.exists() ) {
				// try from mvn default test location
				confFile = new File("src/test/resources/" +CONFFILE_NAME);
			}
			
			if ( confFile.exists()) {
				logger.debug("Reading PDPState hibernate configuration from " + confFile.getAbsolutePath());
				return new Configuration().configure(confFile).buildSessionFactory(); 
			}
			else {
				logger.warn("Reading PDPState hibernate configuration from jar; You might define a more accurate " + CONFFILE_NAME);
				BufferedInputStream bIs = new BufferedInputStream(this.getClass().getResourceAsStream( "/" + CONFFILE_NAME));
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setValidating(false); // do not validate with DTD (and, therefore, do not die offline with an unkown host exception
				factory.setFeature("http://xml.org/sax/features/namespaces", false);
				factory.setFeature("http://xml.org/sax/features/validation", false);
				factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
				factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
				Document confDoc =  factory.newDocumentBuilder().parse(bIs).getDocumentElement().getOwnerDocument();
				return new Configuration().configure(confDoc).buildSessionFactory();
			}
        }
        catch (Throwable ex) {
        	ex.printStackTrace();
            logger.error("Initial SessionFactory creation failed." + ex.getClass().getName() + " - " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }

	
	public HibernateUtil getHibernateUtil() {
		return this.hibernateUtil;
	}
	
	/**
	 * adds a new attribute type. values in AuthoAttribute are ignored
	 * @param attributeId
	 * @param contextInformation
	 */
	public AttributeType addAttributeType(AuthoAttribute attribute, List<AuthoAttribute> contextAttributes) {
		
		List<AttributeIdentifier> contextIds = null;
		if ( contextAttributes != null && contextAttributes.size() > 0 ) {
			contextIds = new Vector<AttributeIdentifier>();
			for (AuthoAttribute attr : contextAttributes ) {
				contextIds.add(attr.getAttributeIdentifier());
			}
		}
		return hibernateUtil.addAttributeType(attribute.getAttributeIdentifier(), contextIds);
	}
	
	public List<String> getAttribute(AttributeIdentifier attrId, List<AuthoAttribute> contextAttrValues) {
		return getAttribute(attrId, contextAttrValues, new java.util.Date());
	}
	
	public List<String> getAttribute(AttributeIdentifier attrId, List<AuthoAttribute> contextAttrValues, Date validAt) {
		return getAttribute(attrId, contextAttrValues, new Timestamp(validAt.getTime()));
	}
	
	
	public List<String> getAttribute(AttributeIdentifier attrId, List<AuthoAttribute> contextAttrValues, Timestamp validAt) {
		AttributeType attrType = hibernateUtil.getAttributeType(attrId);
		List<ContextAttributeAssignment> contextDBAttrs = transformCtxAttr(attrType, contextAttrValues);
		return getAttribute(attrType, contextDBAttrs, validAt);
	}

	/**
	 * WARNING: This function performs NO sanity checks, thus, you may get weird behavior
	 * if you provide unchecked and inconsistent parameters. 
	 * <br/>
	 * 
	 * @param attrType
	 * @param contextDBAttrs
	 * @param validAt
	 * @return
	 */
	public List<String> getAttribute(AttributeType attrType, List<ContextAttributeAssignment> contextDBAttrs) {
		return getAttribute(attrType, contextDBAttrs, new Timestamp(new java.util.Date().getTime()));
	}
	

	
	/**
	 * WARNING: This function performs NO sanity checks, thus, you may get weird behavior
	 * if you provide unchecked and inconsistent parameters. 
	 * <br/>
	 * 
	 * @param attrType
	 * @param contextDBAttrs
	 * @param validAt
	 * @return
	 */
	@SuppressWarnings("unchecked") // needed for List<String> list =  query.list();
	public List<String> getAttribute(AttributeType attrType, 
			List<ContextAttributeAssignment> contextDBAttrs, Timestamp validAt) {
		
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Query query = null;
		
		//if ( attrType.getCtxTypes().size() == 0 ) {
		if ( contextDBAttrs == null || contextDBAttrs.size() == 0 ) {
			//no context attributes are required, easy case!
			query = session.getNamedQuery("getAttribute0");
		} else if ( contextDBAttrs.size() == 1 ) {
			//only one context attribute is required 
			query = session.getNamedQuery("getAttribute1");
			query.setParameter(CTXATTRTYP0, contextDBAttrs.get(0).getCtxAttribute()); 
			query.setParameter(CTXATTR0, contextDBAttrs.get(0).getValue());	
		} else if ( contextDBAttrs.size() == 2 ) {
			query = session.getNamedQuery("getAttribute2");
			query.setParameter(CTXATTRTYP0, contextDBAttrs.get(0).getCtxAttribute()); 
			query.setParameter(CTXATTR0, contextDBAttrs.get(0).getValue());	
			query.setParameter(CTXATTRTYP1, contextDBAttrs.get(1).getCtxAttribute()); 
			query.setParameter(CTXATTR1, contextDBAttrs.get(1).getValue());	
		} else {
			throw new RuntimeException("Only two constraining attributes are supported");
		}
		query.setParameter(ATTRTYPE, attrType);	
		query.setParameter(VALIDAT, validAt);

		List<String> list =  query.list();
		t.commit();
		return list;
	}

	public List<Long> getAssignmentIds(AttributeIdentifier attrId, String value, 
			List<AuthoAttribute> contextAttrValues, 
			Date validFrom, Date validTo) {
		return getAssignmentIds(attrId, value, contextAttrValues, new Date(validFrom.getTime()), new Date(validTo.getTime()));
	}

	
	public List<Long> getAssignmentIds(AttributeIdentifier attrId, String value, 
			List<AuthoAttribute> contextAttrValues, Timestamp validFrom, Timestamp validTo) {
		AttributeType attrType = hibernateUtil.getAttributeType(attrId);
		List<ContextAttributeAssignment> contextDBAttrs = transformCtxAttr(attrType, contextAttrValues);
		return getAssignmentIds(attrType, value, contextDBAttrs, validFrom, validTo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAssignmentIds(AttributeType attrType, String value, 
			List<ContextAttributeAssignment> contextDBAttrs, Date validFrom, Date validTo) {
		/* Task: get all IDs which are within the boundaries defined by :validFrom and :validTo
		 * Idea:
		 * exclude those which are not within the boundaries, i.e., one of (OR) 
		 * 1) both a.validFrom and a.validTo lie before :validFrom
		 * 2) both a.validFrom and a.validTo lie after :validTo
		 * thus,
		 * NOT ( ( a.validFrom < :validFrom AND a.validTo < :validTo) OR
		 *       ( a.validFrom > :validFrom AND a.validTo > :validTo) )
		 * resolving the NOT: 
		 * (a.validFrom >= :validFrom  OR a.validTo >= :validTo) AND
		 * (a.validFrom <= :validFrom  OR a.validTo <= :validTo)
		 * 
		 * TODO make version from arni
		 * a.validFrom <= :validTo AND a.validTo >= :validFrom
		 */
		
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Query query = null;
		
		if ( contextDBAttrs == null || contextDBAttrs.size() == 0 ) {
			query = session.getNamedQuery("getAssignmentIds0");
		} else if ( contextDBAttrs.size() == 1) {
			query = session.getNamedQuery("getAssignmentIds1");
			query.setParameter(CTXATTRTYP0, contextDBAttrs.get(0).getCtxAttribute()); 
			query.setParameter(CTXATTR0, contextDBAttrs.get(0).getValue());	
		} else if ( contextDBAttrs.size() == 2) {
			query = session.getNamedQuery("getAssignmentIds2");
			query.setParameter(CTXATTRTYP0, contextDBAttrs.get(0).getCtxAttribute()); 
			query.setParameter(CTXATTR0, contextDBAttrs.get(0).getValue());	
			query.setParameter(CTXATTRTYP1, contextDBAttrs.get(1).getCtxAttribute()); 
			query.setParameter(CTXATTR1, contextDBAttrs.get(1).getValue());	
		 }else {
			throw new RuntimeException("Only two constraining attributes are supported");
		}
		query.setParameter(ATTRTYPE, attrType);	
		query.setParameter(VALIDFROM, validFrom);
		query.setParameter(VALIDTO, validTo);
		query.setParameter(VALUE, value);

		List<Long> list = query.list();
		t.commit();
		return list;
	}
	
	public AttributeAssignment addAssignment(AuthoAttribute attribute, 
			Date validFrom, Date validTo, List<AuthoAttribute> contextAttributes) {
		return addAssignment(attribute, 
				validFrom == null ? null : new Timestamp(validFrom.getTime()), 
				validTo == null ? null : new Timestamp(validTo.getTime()), contextAttributes);
		//return addAssignment(attribute, new Date(validFrom.getTime()), new Date(validTo.getTime()), contextAttributes);
	}
	
	public AttributeAssignment addAssignment(AuthoAttribute attribute, 
			Timestamp validFrom, Timestamp validTo, List<AuthoAttribute> contextAttributes) 
				throws InvalidAssignmentException {
		AttributeType attrType = hibernateUtil.getAttributeType(attribute.getAttributeIdentifier());
		if ( attrType == null ) {
			logger.info("AttributeType is not jet known by PDPState. Creating new AttributeType for attribute " + attribute.getAttributeIdentifier());
			attrType = addAttributeType(attribute, contextAttributes);
		}
		// transform input to values with db IDs
		List<ContextAttributeAssignment> contextDBAttrs = transformCtxAttr(attrType, contextAttributes);
		return addAssignment(attrType, attribute.getValue(), validFrom, validTo, contextDBAttrs);
	}

	
	public AttributeAssignment addAssignment(AttributeType attrType, 
			String value, Timestamp validFrom, Timestamp validTo, 
			List<ContextAttributeAssignment> contextDBAttrs) {
		
		AttributeAssignment assignment = new AttributeAssignment();
		assignment.setValue(value);
		assignment.setValidFrom(validFrom);
		assignment.setValidTo(validTo);

		if ( assignment.getValidFrom().after(assignment.getValidTo()) ) {
			throw new InvalidAssignmentException(Reason.INVALID_DATE_NO_TIMEFRAME);
		}
		if ( new Date(new java.util.Date().getTime() - 60*1000).after(assignment.getValidFrom())) {
			throw new InvalidAssignmentException(Reason.INVALID_DATE_MODIFICATION_OF_PAST);
		}

		logger.info("Adding assignment with value \"" + value + "\" for " + attrType.getAttrType().toString());
		logger.debug("Checking that there is no existing attribute in the defined time period");
		List<Long> assignedIds = getAssignmentIds(attrType, value, contextDBAttrs, validFrom, validTo);
		if ( assignedIds != null && assignedIds.size() > 0 ) {
			String message = "There are already " + assignedIds.size() + " assignment(s) within the defined scope and time frame";
			logger.error(message);
			throw new RuntimeException(message);
		}
		
		assignment.setAttrType(attrType);
		assignment.setCtxAttrAssignments(contextDBAttrs);

		if ( contextDBAttrs != null ) {
			for ( ContextAttributeAssignment attr : contextDBAttrs ) {
				attr.setAttrAssignment(assignment);
			}
		}
		
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(assignment);
		tx.commit();
	
		return assignment;
	}
	
	public void endAssignment(AuthoAttribute attribute, Date validTo, 
			List<AuthoAttribute> contextAttrValues) {
		endAssignment(attribute, validTo == null ? null : new Date(validTo.getTime()), contextAttrValues);
	}
	
	public void endAssignment(AuthoAttribute attribute, Timestamp validTo, 
			List<AuthoAttribute> contextAttrValues) {
		AttributeType attrType = hibernateUtil.getAttributeType(attribute.getAttributeIdentifier());
		List<ContextAttributeAssignment> contextDBAttrs = transformCtxAttr(attrType, contextAttrValues);
		endAssignment(attrType, attribute.getValue(), validTo, contextDBAttrs);
	}	
	
	
	public void endAssignment(AttributeType attrType, 
			String value, Timestamp validTo, 
			List<ContextAttributeAssignment> contextDBAttrs){
		if ( validTo == null ) { 
			//per default, end assignment with now
			validTo = new Timestamp(new java.util.Date().getTime());
		} else if ( new Date(new java.util.Date().getTime() - 1000).after(validTo)) {
			logger.error("It is not permitted to end an assignment in the past");
			throw new InvalidAssignmentException(Reason.INVALID_DATE_MODIFICATION_OF_PAST);
		}
		
		//Date now = new Date(new java.util.Date().getTime());
		List<Long> ids = getAssignmentIds(attrType, value, contextDBAttrs, validTo, validTo);
		
		if ( ids.size() == 0 ) {
			logger.error("There is no assignment which could be ended");
			throw new InvalidAssignmentException(Reason.NO_ASSIGNMENT_AVAILABLE);
		} else if (ids.size() > 1) {
			logger.fatal("There are several assignments valid at one time: TODO log details");
			throw new InvalidAssignmentException(Reason.INVALID_STATE);
		}
		
		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		//TODO check before in the same session?
		AttributeAssignment attrAssign = (AttributeAssignment) session.get(AttributeAssignment.class, ids.get(0));
		attrAssign.setValidTo(validTo);
		session.update(attrAssign);
		tx.commit();
	}
	
	public String replaceValue() {
		//TODO for assignment where only one value at one point in time is valid
		// e.g. active policy version
		// 
		
		//return old value 
		
		return null;
	}
	
	

	/**
	 * This function does mainly two things: first, the attribute definitions which are contained in 
	 * contextAttributes are transfered to a List of ContextAttributeAssignment (i.e., containing
	 * a reference to the actual ContextAttribute). Second, a sanity check is done, i.e., if the attributes
	 * defined by contextAttributes match to the required contextAttributes defined for the
	 * attributeType
	 * @param attributeType
	 * @param contextAttributes
	 * @return
	 */
	protected List<ContextAttributeAssignment> transformCtxAttr(AttributeType attributeType, List<AuthoAttribute> contextAttributes) {
		
		// check if meta info defined by AttributeType defines same size of contextAttributes as provided
		if  ( (attributeType.getCtxTypes() == null ? 0 : attributeType.getCtxTypes().size()) 
				!= (contextAttributes == null ? 0 : contextAttributes.size())) {
			String message = "AttributeType for " + attributeType.getAttrType() + " defines " +
				(attributeType.getCtxTypes() == null ? 0 : attributeType.getCtxTypes().size()) + " context attributes, but received " + 
				(contextAttributes == null ? 0 : contextAttributes.size());
			logger.error("Invalid Assignment: " + message);
			throw new InvalidParameterException(message);
		}
		
		AttributeAssignment assignment = new AttributeAssignment();
		
		if ( contextAttributes != null && contextAttributes.size() > 0 ) {
			List<ContextAttributeAssignment> contextDBAttrs = new Vector<ContextAttributeAssignment>();
			
			//in most cases there will be only one ctx Attribute; implement "fast way"
			if ( contextAttributes.size() == 1 ) {
				AuthoAttribute provAttr = contextAttributes.get(0);
				ContextAttribute ctxAttr = attributeType.getCtxTypes().get(0);
				//check if required IDs match
				if ( provAttr.getAttributeIdentifier().hashCode() != 
					ctxAttr.getAttrId().hashCode()) {
					String message = "AttributeType for " + attributeType.getAttrType() + " defines " +
						"another required context attribute (" + ctxAttr.getAttrId() + 
						") than provided (" + provAttr.getAttributeIdentifier();
					logger.error("Invalid Assignment: " + message);
					throw new InvalidParameterException(message);
				} else {
					contextDBAttrs.add(new ContextAttributeAssignment(provAttr.getValue(), ctxAttr, assignment));
					return contextDBAttrs;
				}
			} else {
				Map<AttributeIdentifier, ContextAttribute> tmpMap = new HashMap<AttributeIdentifier, ContextAttribute>();
				for ( ContextAttribute ctxAttr : attributeType.getCtxTypes() ) {
					tmpMap.put(ctxAttr.getAttrId(), ctxAttr);
				}
				for ( AuthoAttribute attrValue : contextAttributes ) {
					if ( tmpMap.containsKey(attrValue.getAttributeIdentifier()) ) {
						ContextAttribute ctxAttr = tmpMap.get(attrValue.getAttributeIdentifier());
						contextDBAttrs.add(new ContextAttributeAssignment(attrValue.getValue(), ctxAttr, assignment));
					} else {
						String message = "AttributeType " + attributeType.getAttrType() + 
							" does not require the  context attribute " + attrValue.getAttributeIdentifier();
						logger.error("Invalid Assignment: " + message);
						throw new InvalidParameterException(message);
					}
				}
				return contextDBAttrs;
			}
		} else {
			return null;
		}
	}
	
//	session.createQuery("SELECT a.id FROM " + 
//			AttributeAssignment.class.getName() + " AS a " +
//		" INNER JOIN a.ctxAttrAssignments as c " + 
//		" WHERE a.attrType = :attrType " +  
//		" AND  ( ( a.validFrom >= :validFrom OR a.validTo >= :validTo ) AND " +
//		"		 ( a.validFrom <= :validFrom OR a.validTo <= :validTo ) ) " +  
//		" AND a.value = :value " +
//		" AND c.ctxAttribute = :ctxAttrTypeNull " +  
//		" AND c.value = :ctxAttrNull");
}
