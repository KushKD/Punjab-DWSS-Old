package com.prwss.mis.common.contractCheck;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.check.ContractCheckBean;
import com.prwss.mis.tender.dao.TenderHeaderBean;

public class ContractNotificationDaoImpl extends HibernateDaoSupport implements ContractNotificationDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ContractCheckBean> findAllContracts() {
		List<ContractCheckBean> contractCheckBeans = null;
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(ContractCheckBean.class);
			//System.out.println("TenderID===="+TenderID);
		//	criteria.add(Restrictions.eq("tenderId",tenderId));
			criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
			contractCheckBeans = getHibernateTemplate().findByCriteria(criteria); 	
			getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
			
		}
		catch(DataAccessException e){
			throw e;
		}
		return contractCheckBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TenderHeaderBean> findAllContract() {
		List<TenderHeaderBean> tenderCheckBeans = null;
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(TenderHeaderBean.class);
			criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
			tenderCheckBeans = getHibernateTemplate().findByCriteria(criteria); 
			}
		catch(DataAccessException e){
			throw e;
		}
		return tenderCheckBeans;
		}

}
