package com.prwss.mis.tender.award.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class TenderAwardDaoImpl extends HibernateDaoSupport implements TenderAwardDao {

	private Logger log = Logger.getLogger(TenderAwardDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<TenderAwardBean> findTenderAwarded(TenderAwardBean tenderAwardBean, List<String> statusList) throws DataAccessException {
		
		List<TenderAwardBean> tenderAwardBeans = null;
		try {
			if(MisUtility.ifEmpty(tenderAwardBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(TenderAwardBean.class);
				
				if(MisUtility.ifEmpty(tenderAwardBean.getTenderId()))
					criteria.add(Restrictions.eq("tenderId", tenderAwardBean.getTenderId()).ignoreCase());
				if(MisUtility.ifEmpty(tenderAwardBean.getTenderAmount()))
					criteria.add((Restrictions.eq("tenderAmount", tenderAwardBean.getTenderAmount())));
				if(MisUtility.ifEmpty(tenderAwardBean.getContractNo()))
					criteria.add((Restrictions.eq("contractNo", tenderAwardBean.getContractNo())).ignoreCase());
				if(MisUtility.ifEmpty(tenderAwardBean.getContractStartDate()))
					criteria.add((Restrictions.eq("contractStartDate", tenderAwardBean.getContractStartDate())));
				if(MisUtility.ifEmpty(tenderAwardBean.getContractEndDate()))
					criteria.add((Restrictions.eq("contractEndDate", tenderAwardBean.getContractEndDate())));
				if(MisUtility.ifEmpty(tenderAwardBean.getVendorId()))
					criteria.add((Restrictions.eq("vendorId", tenderAwardBean.getVendorId())).ignoreCase());
				if(MisUtility.ifEmpty(tenderAwardBean.getSigningOfContract()))
					criteria.add((Restrictions.eq("signingOfContract", tenderAwardBean.getSigningOfContract())));
				if(MisUtility.ifEmpty(tenderAwardBean.getNoticeToProceed()))
					criteria.add((Restrictions.eq("noticeToProceed", tenderAwardBean.getNoticeToProceed())));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add((Restrictions.in("misAuditBean.status", statusList)));
				log.debug("Criteria\t"+criteria);
				tenderAwardBeans = getHibernateTemplate().findByCriteria(criteria);
			
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return tenderAwardBeans;
	}

	@Override
	public String saveTenderAwarded(TenderAwardBean tenderAwardBean) throws DataAccessException {
		String tenderId = null;
		try {
			tenderId = (String)getHibernateTemplate().save(tenderAwardBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return tenderId;
	}

	@Override
	public boolean updateTenderAwarded(TenderAwardBean tenderAwardBean) throws DataAccessException {
		try {
			
			getHibernateTemplate().update(tenderAwardBean);
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;		
		}
		return true;
	}

}
