package com.prwss.mis.finance.voucher.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.finance.voucher.VoucherDetailBean;

public class VoucherDetailDaoImpl extends HibernateDaoSupport implements VoucherDetailDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<VoucherDetailBean> findVoucherDetail(
			VoucherDetailBean voucherDetailBean, List<String> statusList)
			throws DataAccessException {
		List<VoucherDetailBean> voucherDetailBeans = null;
		
		try {
			if(MisUtility.ifEmpty(voucherDetailBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(VoucherDetailBean.class);
				if(MisUtility.ifEmpty(voucherDetailBean.getId()))
					criteria.add(Restrictions.eq("id", voucherDetailBean.getId()));
				if(MisUtility.ifEmpty(voucherDetailBean.getVocId()))
					criteria.add(Restrictions.eq("vocId", voucherDetailBean.getVocId()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				voucherDetailBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return voucherDetailBeans;
	}

	@Override
	public boolean saveOrUpdateVoucherDetail(
			Collection<VoucherDetailBean> voucherDetailBeans)
			throws DataAccessException {
		try {
			for (Iterator iterator = voucherDetailBeans.iterator(); iterator.hasNext();) {
				VoucherDetailBean voucherDetailBean = (VoucherDetailBean) iterator.next();
				getHibernateTemplate().merge(voucherDetailBean);
			}
			
			//getHibernateTemplate().saveOrUpdateAll(voucherDetailBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

}
