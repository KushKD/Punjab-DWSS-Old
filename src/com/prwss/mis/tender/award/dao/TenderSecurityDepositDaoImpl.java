package com.prwss.mis.tender.award.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class TenderSecurityDepositDaoImpl extends HibernateDaoSupport implements TenderSecurityDepositDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TenderSecurityDepositBean> getTenderSecurityDepositBeans(TenderSecurityDepositBean tenderSecurityDepositBean,
			List<String> statusList) throws DataAccessException {
		List<TenderSecurityDepositBean> tenderSecurityDepositBeans = null;
		
		try {
			if(MisUtility.ifEmpty(tenderSecurityDepositBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(TenderSecurityDepositBean.class);
				
				if(MisUtility.ifEmpty(tenderSecurityDepositBean.getTenderId()))
					criteria.add(Restrictions.eq("tenderId", tenderSecurityDepositBean.getTenderId()).ignoreCase());
				
				if(MisUtility.ifEmpty(tenderSecurityDepositBean.getDepositId()))
					criteria.add(Restrictions.eq("depositId", tenderSecurityDepositBean.getDepositId()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				tenderSecurityDepositBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return tenderSecurityDepositBeans;
	}

	@Override
	public boolean saveOrUpdateTenderSecurityDepositBeans(Collection<TenderSecurityDepositBean> securityDepositBeans)
			throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(securityDepositBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

}
