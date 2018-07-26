package com.prwss.mis.finance.voucher.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.voucher.VoucherHeaderBean;

public class VoucherHeaderDaoImpl extends HibernateDaoSupport implements VoucherHeaderDao {
	private Logger log = Logger.getLogger(VoucherHeaderDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<VoucherHeaderBean> findVoucher(
			VoucherHeaderBean voucherHeaderBean, List<String> statusList)
			throws DataAccessException {
		List<VoucherHeaderBean> voucherHeaderBeans = new  ArrayList<VoucherHeaderBean>();
		try {
			if(MisUtility.ifEmpty(voucherHeaderBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(VoucherHeaderBean.class);
				if(MisUtility.ifEmpty(voucherHeaderBean.getVocId()))
					criteria.add(Restrictions.eq("vocId", voucherHeaderBean.getVocId()));
				
				if(MisUtility.ifEmpty(voucherHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", voucherHeaderBean.getLocationId()));
				
				if(MisUtility.ifEmpty(voucherHeaderBean.getProgramId()))
					criteria.add(Restrictions.eq("programId", voucherHeaderBean.getProgramId()));
				
				if(MisUtility.ifEmpty(voucherHeaderBean.getDocumentNo()))
					criteria.add(Restrictions.eq("documentNo",voucherHeaderBean.getDocumentNo()));
			
				if(MisUtility.ifEmpty(voucherHeaderBean.getVocType()))
					criteria.add(Restrictions.eq("vocType",voucherHeaderBean.getVocType()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				voucherHeaderBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return voucherHeaderBeans;
	}

	@Override
	public long saveVoucher(VoucherHeaderBean voucherHeaderBean)
			throws DataAccessException {
		long vocId = 0;
		try {
			vocId=	(Long)getHibernateTemplate().save(voucherHeaderBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		System.out.println(" In savePaymentVoucherdao "+ vocId);
		return vocId;
	}

	@Override
	public boolean updateVoucher(VoucherHeaderBean voucherHeaderBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(voucherHeaderBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}

}
