package com.prwss.mis.masters.bank.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class BankMasterDaoImpl extends HibernateDaoSupport implements BankMasterDao {

	private Logger log = Logger.getLogger(BankMasterDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<BankMasterBean> findBank(BankMasterBean bankMasterBean,
			List<String> statusList) throws DataAccessException {
		List<BankMasterBean> bankMasterBeans = null;
		try {
			
			if(MisUtility.ifEmpty(bankMasterBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(BankMasterBean.class);
				if(MisUtility.ifEmpty(bankMasterBean.getBankId()))
					criteria.add(Restrictions.eq("bankId", bankMasterBean.getBankId()));
				
				if(MisUtility.ifEmpty(bankMasterBean.getBankName()))
					criteria.add(Restrictions.eq("bankName",bankMasterBean.getBankName()).ignoreCase());
				criteria.addOrder(Order.asc("bankName"));
				if(MisUtility.ifEmpty(bankMasterBean.getBankBranch()))
					criteria.add(Restrictions.eq("bankBranch",bankMasterBean.getBankBranch()).ignoreCase());
				criteria.addOrder(Order.asc("bankBranch"));
				
				if(MisUtility.ifEmpty(bankMasterBean.getDistrcit()))
					criteria.add(Restrictions.eq("distrcit.locationId",bankMasterBean.getDistrcit().getLocationId()));
				
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
					bankMasterBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return bankMasterBeans;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BankMasterBean> findBank(List<Long> bankMasterIds)
			throws DataAccessException {
		List<BankMasterBean> bankMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BankMasterBean.class);
			
			if(!MisUtility.ifEmpty(bankMasterIds))
				criteria.add(Restrictions.in("bankId", bankMasterIds));
			
			bankMasterBeans= getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
			
		return bankMasterBeans;
	}
	@Override
	public long saveBank(BankMasterBean bankMasterBean)
			throws DataAccessException {
		long bankId = 0;
		try {
			bankId = (Long)getHibernateTemplate().save(bankMasterBean);
			
		} catch (DataAccessException e) {
			throw e;
		}
		
		return bankId;
	}

	@Override
	public boolean updateBank(BankMasterBean bankMasterBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(bankMasterBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateBank(List<BankMasterBean> bankMasterBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(bankMasterBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Set<BankMasterBean> getBankMasterCodes()
			throws DataAccessException {
		Set<BankMasterBean> bankMasterBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(BankMasterBean.class);
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			bankMasterBeans =  new TreeSet<BankMasterBean>(getHibernateTemplate().findByCriteria(criteria));
			
		} catch (DataAccessException e) {
			
			throw e;
		}
		
		return bankMasterBeans;
	}

}
