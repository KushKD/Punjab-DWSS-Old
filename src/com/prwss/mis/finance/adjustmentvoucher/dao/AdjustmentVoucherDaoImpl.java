/**
 * 
 */
package com.prwss.mis.finance.adjustmentvoucher.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.adjustmentvoucher.AdjustmentVoucherBean;

/**
 * @author PJHA
 *
 */
public class AdjustmentVoucherDaoImpl extends HibernateDaoSupport implements AdjustmentVoucherDao{
	private Logger log = Logger.getLogger(AdjustmentVoucherDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<AdjustmentVoucherBean> findAdjustmentVoucher(
			AdjustmentVoucherBean adjustmentVoucherBean, List<String> statusList)
			throws DataAccessException {
		List<AdjustmentVoucherBean> adjustmentVoucherBeans = new  ArrayList<AdjustmentVoucherBean>();
		try {
			if(MisUtility.ifEmpty(adjustmentVoucherBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(AdjustmentVoucherBean.class);
				
				if(MisUtility.ifEmpty(adjustmentVoucherBean.getVocId()))
					criteria.add(Restrictions.eq("vocId", adjustmentVoucherBean.getVocId()));
				
				if(MisUtility.ifEmpty(adjustmentVoucherBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", adjustmentVoucherBean.getLocationId()));
				
				if(MisUtility.ifEmpty(adjustmentVoucherBean.getProgramId()))
					criteria.add(Restrictions.eq("programId", adjustmentVoucherBean.getProgramId()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				adjustmentVoucherBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return adjustmentVoucherBeans;
	}

	@Override
	public long saveAdjustmentVoucher(
			AdjustmentVoucherBean adjustmentVoucherBean)
			throws DataAccessException {
		long vocId = 0;
		try {
			vocId=(Long)getHibernateTemplate().save(adjustmentVoucherBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return vocId;
	}

	@Override
	public boolean updateAdjustmentVoucher(
			AdjustmentVoucherBean adjustmentVoucherBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(adjustmentVoucherBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}
	

}
