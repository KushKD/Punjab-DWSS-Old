package com.prwss.mis.masters.component.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class ComponentDaoImpl extends HibernateDaoSupport implements ComponentDao{
	
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 
	 * @param componentBean
	 * @return
	 * @throws DataAccessException
	 */
	public ComponentBean loadComponent(String componentId) throws DataAccessException{
	
		ComponentBean componentBean = null;
		try {
			componentBean = getHibernateTemplate().load(ComponentBean.class, componentId);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return componentBean;		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ComponentBean> findComponent(ComponentBean componentBean, List<String> statusList) throws DataAccessException{
		List<ComponentBean> componentList = null;
		try {
			String componentId = componentBean.getComponentId();
			String componentName = componentBean.getComponentName();
			String componentDescription = componentBean.getComponentDescription();
			log.debug("statusList\t"+statusList);
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentBean.class);
			
			if(MisUtility.ifEmpty(componentId))
				criteria.add(Restrictions.eq("componentId", componentId).ignoreCase());			
			if(MisUtility.ifEmpty(componentName))
				criteria.add(Restrictions.eq("componentName", componentName).ignoreCase());			
			if(MisUtility.ifEmpty(componentDescription))
				criteria.add(Restrictions.eq("componentDescription", componentDescription).ignoreCase());			
			if(!MisUtility.ifEmpty(statusList))
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			log.debug("criteria\n"+criteria);			
			componentList = getHibernateTemplate().findByCriteria(criteria);
			
			log.debug("componentBean\t"+componentList);
		} catch (DataAccessException e) {
			throw e;
		}
		return componentList;
	}
	
	public boolean saveComponent(ComponentBean componentBean) throws DataAccessException{
		try {
			log.debug(componentBean+"----------------------");
			
			getHibernateTemplate().save(componentBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		
		// if anything fails we are throwing an exception 
		// which will be handled in the calling method
		return true;	
	}
	
	public boolean updateComponent(ComponentBean componentBean) throws DataAccessException{
		
		try {
			getHibernateTemplate().update(componentBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		// if anything fails we are throwing an exception 
		// which will be handled in the calling method
		return true;
	}
	
   public boolean updateComponent(List<ComponentBean> componentBeans) throws DataAccessException{
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		try {
			hibernateTemplate.saveOrUpdateAll(componentBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		// if anything fails we are throwing an exception 
		// which will be handled in the calling method
		return true;
	}
   
   @SuppressWarnings("unchecked")
   @Override
	public List<ComponentBean> findComponent(List<String> componentIds) throws DataAccessException {
	  List<ComponentBean> componentBeans= null;
	  
	  if(!MisUtility.ifEmpty(componentIds)){
		  try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentBean.class);
			if(!MisUtility.ifEmpty(componentIds)){
			  criteria.add(Restrictions.in("componentId", componentIds));
			  componentBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
	  } 
		return componentBeans;
	}
	
	@SuppressWarnings("unchecked")
	public Set<ComponentBean> getDistinctComponentCodes() throws DataAccessException{
		
		Set<ComponentBean> componentBeans;
		try {
			componentBeans = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentBean.class);
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			componentBeans = new TreeSet<ComponentBean>(getHibernateTemplate().findByCriteria(criteria));//("from ComponentBean"));
		} catch (DataAccessException e) {
			throw e;
		}
		
		return componentBeans;
	}


	@Override
	public List<ComponentBean> findComponentForRequestLoc(
			ComponentBean componentBean, List<String> statusList)
			throws DataAccessException {
		
		List<ComponentBean> componentList = null;
		try {
			String componentId = componentBean.getComponentId();
			String componentName = componentBean.getComponentName();
			String componentDescription = componentBean.getComponentDescription();
			log.debug("statusList\t"+statusList);
			DetachedCriteria criteria = DetachedCriteria.forClass(ComponentBean.class);
			
			if(MisUtility.ifEmpty(componentId))
				criteria.add(Restrictions.eq("componentId", componentId).ignoreCase());			
			if(MisUtility.ifEmpty(componentName))
				criteria.add(Restrictions.eq("componentName", componentName).ignoreCase());			
			if(MisUtility.ifEmpty(componentDescription))
				criteria.add(Restrictions.eq("componentDescription", componentDescription).ignoreCase());			
			if(!MisUtility.ifEmpty(statusList))
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			log.debug("criteria\n"+criteria);			
			componentList = getHibernateTemplate().findByCriteria(criteria);
			
			log.debug("componentBean\t"+componentList);
		} catch (DataAccessException e) {
			throw e;
		}
		return componentList;
	}



}
