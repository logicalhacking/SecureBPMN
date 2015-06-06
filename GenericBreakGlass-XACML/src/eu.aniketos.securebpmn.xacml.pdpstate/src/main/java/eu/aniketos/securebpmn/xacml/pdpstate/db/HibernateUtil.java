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

package eu.aniketos.securebpmn.xacml.pdpstate.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import eu.aniketos.securebpmn.xacml.api.autho.AttributeIdentifier;

/**
 * loads and caches the meta data tables
 *
 */
public class HibernateUtil {
	
	private static final Logger logger = Logger.getLogger(HibernateUtil.class);
	private SessionFactory sessionFactory; 
	
	private Map<AttributeIdentifier, AttributeDBIdentifier> attrIds = null;
	private Map<AttributeIdentifier, AttributeType> attrTypeIds = null;
	
	private static HibernateUtil instance; 

	
	/**
	 * not that the HibernateUtil has to be instanciated first
	 * @return
	 */
	public static HibernateUtil getInstance() {
		return instance;
	}
	
    public synchronized static HibernateUtil createHibernateUtil(SessionFactory factory) {
    	if ( instance != null ) {
    		logger.warn("HibernateUtil instance already exists");
    	}
       	instance = new HibernateUtil(factory);
       	return instance;    	
    }
    
    private HibernateUtil(SessionFactory factory) {
    	this.sessionFactory = factory;
    	attrIds = new HashMap<AttributeIdentifier, AttributeDBIdentifier>();
    	attrTypeIds = new HashMap<AttributeIdentifier, AttributeType>();
    	
    	Session session = sessionFactory.getCurrentSession();
    	Transaction tx = session.beginTransaction();
		
    	Query query = session.createQuery("from " + AttributeDBIdentifier.class.getName());
		List<?> l = query.list();
		for ( Object val : l) {
			AttributeDBIdentifier i = (AttributeDBIdentifier) val;
			attrIds.put(i, i);
			logger.debug("loaded AttrID " + i + ": " + i.getId());
		}
		
		query = session.createQuery("from " + AttributeType.class.getName());
		l = query.list();
		for ( Object val : l ) {
			AttributeType i = ( AttributeType ) val;
			attrTypeIds.put(i.getAttrType(), i);
			logger.debug("loaded AttrType " + i.getAttrType() + ": " + i.getId());
		}
		tx.commit();
		logger.info("Created HibernateUtil: " + attrIds.size() + " AttributeIdentifier(s), " + attrTypeIds.size() + " AttributeType(s)");
    }
    
    /**
     * returns an attribute Identifier containing the ID from the database and therefore
     * useable for database write and update operations 
     * @param attrId
     * @return
     */
    public AttributeDBIdentifier getAttributeDBIdentifier(AttributeIdentifier attrId) {
    	if ( attrIds.containsKey(attrId) ) {
    		return attrIds.get(attrId);
    	} else {
    		return addAttributeIdentifier(attrId);
    	}
    }
    /**
     * returns if an attribute is already stored in the database
     * @param attrId
     * @return
     */
    public boolean contains(AttributeIdentifier attrId) {
    	return attrTypeIds.containsKey(attrId);
    }
    
    /**
     * add an attribute identifier to the database;<br/>
     * 
     * this function checks first if the attribute is already stored, i.e., it
     * can be called without the need to check if the attribute is already there.
     * @param attrId
     * @return
     */
    public synchronized AttributeDBIdentifier addAttributeIdentifier(AttributeIdentifier attrId) {
    	if ( attrIds.containsKey(attrId) ) {
    		return attrIds.get(attrId);
    	} else {
			AttributeDBIdentifier dbid = new AttributeDBIdentifier(attrId);
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.save(dbid);
			
			tx.commit();
			logger.debug("Inserted AttrId " + dbid.getId() + " " + attrId);
			attrIds.put(attrId, dbid);
			return dbid;
    	}
    }
    
    /**
     * checks if already some meta data (i.e., the AttributeType) for an 
     * attribute identifier is known by the database
     * @param attrId
     * @return
     */
    public AttributeType getAttributeType(AttributeIdentifier attrId) {
    	return attrTypeIds.get(attrId);
    }
    
    /**
     * add an attribute type to the database;<br/>
     * 
     * this function checks first if the attribute type is already stored, i.e., it
     * can be called without the need to check if the attribute type is already there.
     * @param attrId
     * @param contextAttr
     * @return
     */
    public synchronized AttributeType addAttributeType(AttributeIdentifier attrId, List<AttributeIdentifier> contextAttr) {
    	if ( attrTypeIds.containsKey(attrId) ) {
    		// this type is already defined, check if the given and the existing definition match
    		AttributeType type =  attrTypeIds.get(attrId);
    		
    		if ( type.getCtxTypes().size() !=  contextAttr.size() ) {
    			logger.warn("Existing AttributeType " + attrId + " does not match provided type definition (different context attribute count)");
    			return null;
    		}
    		if ( type.getCtxTypes().size() == 1 ) {
    			if ( ! type.getCtxTypes().get(0).getAttrId().equals(contextAttr.get(0)) ) {
        			logger.warn("Existing AttributeType " + attrId + " does not match provided type definition (contextAttr type does not match)");
        			return null;
    			}
    		} else if ( type.getCtxTypes().size() > 1 ) {
    			for ( ContextAttribute attr : type.getCtxTypes()) {
    				if ( ! contextAttr.contains(attr.getAttrType())) {
            			logger.warn("Existing AttributeType " + attrId + " does not match provided type definition (contextAttr type do not match)");
            			return null;
    				}
    			}
    		}
    		return type;
    	} else {
//    		List<AttributeDBIdentifier> contextDBAttrs = null;
//    		if ( contextAttr != null && contextAttr.size() > 0 ) {
//        		contextDBAttrs = new Vector<AttributeDBIdentifier>();
//        		for ( AttributeIdentifier id : contextAttr ) {
//        			contextDBAttrs.add(getAttributeDBIdentifier(id));
//        		}
//    		}
    		
    		AttributeType attrType = new AttributeType();
    		attrType.setAttrType(getAttributeDBIdentifier(attrId));
    		
    		List<ContextAttribute> contextDBAttrs = null;
    		if ( contextAttr != null && contextAttr.size() > 0 ) {
    			contextDBAttrs = new Vector<ContextAttribute>();
    			System.out.println("addAttributeType: " + attrType.getAttrType().getId() + ", " + attrType.getAttrType().toString());
        		for ( AttributeIdentifier id : contextAttr ) {
        			contextDBAttrs.add( new ContextAttribute(getAttributeDBIdentifier(id), attrType));
        		}
    		}
    		attrType.setCtxTypes(contextDBAttrs);
    		
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			session.save(attrType);
			
			tx.commit();
			logger.debug("Inserted AttrId " + attrType.getId() + " " + attrType);
			attrTypeIds.put(attrId, attrType);
			return attrType;
    	}
    }
}
