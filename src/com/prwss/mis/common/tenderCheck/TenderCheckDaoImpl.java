package com.prwss.mis.common.tenderCheck;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.notification.dao.NotificationBean;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.check.TenderCheckBean;
import com.prwss.mis.tender.biddersdetail.dao.BidderHeaderBean;
import com.prwss.mis.tender.dao.TenderHeaderBean;

public class TenderCheckDaoImpl extends HibernateDaoSupport implements TenderCheckDao {
	//private HibernateTemplate hibernateTemplate;
	@SuppressWarnings("unchecked")
	@Override
	public List<TenderHeaderBean> findAllTenders() {
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
	@SuppressWarnings("unchecked")
	@Override
	public List<TenderCheckBean> findAllTender(String tenderId) {
		List<TenderCheckBean> tenderCheckBeans = null;
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(TenderCheckBean.class);
				//System.out.println("getPlanId id: "+tenderBean.getPlanId());
			//System.out.println("tenderCheckBeans id: "+tenderId);
			//System.out.println("hi");
			
			criteria.add(Restrictions.eq("tenderId",tenderId));
				//Log.debug(criteria);
			criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
			tenderCheckBeans = getHibernateTemplate().findByCriteria(criteria); 
			getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
			}
		catch(DataAccessException e){
			throw e;
		}
		return tenderCheckBeans;
	}
	
	
}
