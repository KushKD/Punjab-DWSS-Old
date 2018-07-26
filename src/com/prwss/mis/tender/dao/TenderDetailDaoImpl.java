package com.prwss.mis.tender.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class TenderDetailDaoImpl extends HibernateDaoSupport implements TenderDetailDao {
	
	private Logger log = Logger.getLogger(TenderDetailDaoImpl.class);
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public Set<TenderDetailBean> findTenderDetails(String tenderId) throws DataAccessException {
		
		Set<TenderDetailBean> tenderDetailBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TenderDetailBean.class);
			if(MisUtility.ifEmpty(tenderId))
				criteria.add(Restrictions.eq("tenderId", tenderId));
			
			hibernateTemplate = getHibernateTemplate();
			log.debug(criteria);
			tenderDetailBeans = new TreeSet<TenderDetailBean>(hibernateTemplate.findByCriteria(criteria)) ;
		} catch (DataAccessException e) {
			throw e;
		}
		
		return tenderDetailBeans;
	}

	@Override
	public boolean saveOrupdateTenderDetails(List<TenderDetailBean> tenderDetailBeans) throws DataAccessException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(tenderDetailBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}


}
