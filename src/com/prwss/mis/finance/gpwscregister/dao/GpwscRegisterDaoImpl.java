package com.prwss.mis.finance.gpwscregister.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class GpwscRegisterDaoImpl extends HibernateDaoSupport implements GpwscRegisterDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<GpwscRegisterBean> findGPWSCRegister(
			GpwscRegisterBean gpwscRegisterBean, List<String> statusList)
			throws DataAccessException {
		List<GpwscRegisterBean> gpwscRegisterBeans = null;

		try {
			System.out.println("impl t no is-----"+gpwscRegisterBean.getTransactionNumber());
			DetachedCriteria criteria = DetachedCriteria.forClass(GpwscRegisterBean.class);
			if(MisUtility.ifEmpty(gpwscRegisterBean)){

				if(MisUtility.ifEmpty(gpwscRegisterBean.getCommitteeBean()))
					criteria.add(Restrictions.eq("committeeBean.committeeId", gpwscRegisterBean.getCommitteeBean().getCommitteeId()));
				
				if(MisUtility.ifEmpty(gpwscRegisterBean.getTransactionNumber()))
					criteria.add(Restrictions.eq("transactionNumber",gpwscRegisterBean.getTransactionNumber()));
				
				if(MisUtility.ifEmpty(gpwscRegisterBean.getTransactionType()))
					criteria.add(Restrictions.eq("transactionType",gpwscRegisterBean.getTransactionType()));
//				
//				if(MisUtility.ifEmpty(villageBean.getBlockId()))
//					criteria.add(Restrictions.eq("blockId",villageBean.getBlockId()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				gpwscRegisterBeans = getHibernateTemplate().findByCriteria(criteria);
			}
			System.out.println("----------Size of Found Beans "+gpwscRegisterBeans.size());
			
		} catch (DataAccessException e) {
			throw e;
		}
//		
		return gpwscRegisterBeans;
	}

	@Override
	public long saveGPWSCRegister(GpwscRegisterBean gpwscRegisterBean)
			throws DataAccessException {
		long transactionNumber=0;
		try {
			transactionNumber = (Long)getHibernateTemplate().save(gpwscRegisterBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return transactionNumber;
	}

	@Override
	public boolean updateGPWSCRegister(GpwscRegisterBean gpwscRegisterBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(gpwscRegisterBean);
			} catch (DataAccessException e) {
				throw e;
			}
			return true;
	}

}
