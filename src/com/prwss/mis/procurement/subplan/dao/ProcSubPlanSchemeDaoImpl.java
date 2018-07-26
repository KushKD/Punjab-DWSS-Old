package com.prwss.mis.procurement.subplan.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class ProcSubPlanSchemeDaoImpl extends HibernateDaoSupport implements ProcSubPlanSchemeDao {
    private Logger log = Logger.getLogger(ProcSubPlanSchemeDaoImpl.class);
    private HibernateTemplate hibernateTemplate;
    @Override
	@SuppressWarnings("unchecked")
    public List<SubPlanSchemeBean> findSchemeId(String SchemeId,List<String> statusList){
    	//boolean status;	
    	List<SubPlanSchemeBean> subPlanSchemeBean = null;
    	try{
    	if(MisUtility.ifEmpty(SchemeId)){
    			DetachedCriteria criteria = DetachedCriteria.forClass(SubPlanSchemeBean.class);
    			criteria.add(Restrictions.eq("schemeId", SchemeId));
    			criteria.add(Restrictions.in("misAuditBean.status", statusList));
    			hibernateTemplate = getHibernateTemplate();
    			hibernateTemplate.flush();
    			subPlanSchemeBean = hibernateTemplate.findByCriteria(criteria);
    		}
    	}
    	catch(DataAccessException e){
    		log.error(e.getLocalizedMessage(),e);
    		throw e;
    	}
    	return subPlanSchemeBean;
	
    	}
    
   

    
	public List<SubPlanSchemeBean> findSubPlanScheme(
			SubPlanSchemeBean subPlanSchemeBean, List<String> statusList)
			throws DataAccessException {
    	List<SubPlanSchemeBean> subPlanSchemeBeans = null;
    	System.out.println("in find");
    	try {
    		if(MisUtility.ifEmpty(subPlanSchemeBean)){
    			DetachedCriteria criteria = DetachedCriteria.forClass(SubPlanSchemeBean.class);
    			if(MisUtility.ifEmpty(subPlanSchemeBean.getSubPlanId()))
    				criteria.add(Restrictions.eq("subPlanId", subPlanSchemeBean.getSubPlanId()));
    			if(MisUtility.ifEmpty(subPlanSchemeBean.getSchemeId()))
    				criteria.add(Restrictions.eq("schemeId", subPlanSchemeBean.getSchemeId()));
    			if(!MisUtility.ifEmpty(statusList))
    				criteria.add(Restrictions.in("misAuditBean.status", statusList));
    			hibernateTemplate = getHibernateTemplate();
    			hibernateTemplate.flush();
    			subPlanSchemeBeans = hibernateTemplate.findByCriteria(criteria);
    		}
    	} catch (DataAccessException e) {
    		log.error(e.getLocalizedMessage(),e);
    		throw e;
    	}
    	return subPlanSchemeBeans;
	}


	@Override
	public boolean saveOrUpdateSubPlanScheme(
			Collection<SubPlanSchemeBean> subPlanSchemeBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(subPlanSchemeBeans);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

}
