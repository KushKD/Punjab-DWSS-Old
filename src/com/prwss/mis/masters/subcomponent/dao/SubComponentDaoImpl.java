package com.prwss.mis.masters.subcomponent.dao;

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
import com.prwss.mis.masters.component.dao.ComponentBean;

public class SubComponentDaoImpl extends HibernateDaoSupport implements SubComponentDao{
	
	private Logger log = Logger.getLogger(SubComponentDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<SubComponentBean> findSubComponent(SubComponentBean subComponentBean, List<String> statusList) throws DataAccessException{
		List<SubComponentBean> subComponentBeanList = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(SubComponentBean.class);
		if(MisUtility.ifEmpty(subComponentBean)){
			try {
				ComponentBean componentBean = subComponentBean.getComponentBean();
				if(MisUtility.ifEmpty(componentBean)&& MisUtility.ifEmpty(componentBean.getComponentId()))
					criteria.add(Restrictions.eq("componentBean.componentId", componentBean.getComponentId()));
				if(MisUtility.ifEmpty(subComponentBean.getSubComponentId()))
					criteria.add(Restrictions.eq("subComponentId", subComponentBean.getSubComponentId()));
				if(MisUtility.ifEmpty(subComponentBean.getSubComponentName()))
					criteria.add(Restrictions.eq("subComponentName", subComponentBean.getSubComponentName()));
				if(MisUtility.ifEmpty(subComponentBean.getSubComponentDescription()))
					criteria.add(Restrictions.eq("subComponentDescription", subComponentBean.getSubComponentDescription()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("status", statusList));

				subComponentBeanList = getHibernateTemplate().findByCriteria(criteria);
			} catch (DataAccessException e) {
				throw e;
			}
		
		}
		return subComponentBeanList;
	}
	
	public boolean saveSubComponent(SubComponentBean subComponentBean) throws DataAccessException{
		try {
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(subComponentBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}
	
	public boolean updateSubComponent(SubComponentBean subComponentBean) throws DataAccessException{
		
		try {
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(subComponentBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	public boolean deleteSubComponent(SubComponentBean subComponentBean) throws DataAccessException{
	
		try {
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(subComponentBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
	return true;
	}
	
	@SuppressWarnings("unchecked")
	public Set<SubComponentBean> getDistinctSubComponentCodes(String componentId) throws DataAccessException{
		
		Set<SubComponentBean> componentBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SubComponentBean.class);
			if(MisUtility.ifEmpty(componentId))
				criteria.add(Restrictions.eq("componentBean.componentId", componentId));
			criteria.add(Restrictions.eq("status", MISConstants.MASTER_STATUS_APPROVED));			
			componentBeans = new TreeSet<SubComponentBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		} 
		
		return componentBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubComponentBean> findSubComponent(List<String> subComponentIdList) throws DataAccessException{
		List<SubComponentBean> subComponentBeans = null;
		log.debug("subComponentIdList\t"+subComponentIdList);
		if(!MisUtility.ifEmpty(subComponentIdList)){
			try {
				DetachedCriteria criteria = DetachedCriteria.forClass(SubComponentBean.class);
				criteria.add(Restrictions.in("subComponentId", subComponentIdList));
				subComponentBeans = getHibernateTemplate().findByCriteria(criteria);
			} catch (DataAccessException e) {
				throw e;
			}
		}
		return subComponentBeans;
	}

	@Override
	public boolean updateSubComponent(List<SubComponentBean> subComponentBeans) throws DataAccessException {
		log.debug("Inside updateSubComponent\n"+subComponentBeans);
		if(!MisUtility.ifEmpty(subComponentBeans)){
			try {
				HibernateTemplate hibernateTemplate = getHibernateTemplate();
				hibernateTemplate.saveOrUpdateAll(subComponentBeans);
			} catch (DataAccessException e) {
				throw e;
			}
		}
		return true;
	}


}
