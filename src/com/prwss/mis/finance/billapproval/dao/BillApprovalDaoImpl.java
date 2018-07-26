package com.prwss.mis.finance.billapproval.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class BillApprovalDaoImpl extends HibernateDaoSupport implements BillApprovalDao {
private Logger log = Logger.getLogger(BillApprovalDaoImpl.class);
	@Override
	public boolean saveBillApproval(BillApprovalBean billApprovalBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(billApprovalBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BillApprovalBean> findBillApproval(
			BillApprovalBean billApprovalBean, List<String> statusList)
			throws DataAccessException {
		List<BillApprovalBean> billApprovalBeans = null;
		try {
			if(MisUtility.ifEmpty(billApprovalBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(BillApprovalBean.class);
				if(MisUtility.ifEmpty(billApprovalBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", billApprovalBean.getLocationId()));
				if(MisUtility.ifEmpty(billApprovalBean.getVocId()))
					criteria.add(Restrictions.eq("vocId",billApprovalBean.getVocId()));
				if(MisUtility.ifEmpty(billApprovalBean.getContractHeaderBean().getContractId()))
					criteria.add(Restrictions.eq("contractHeaderBean.contractId",billApprovalBean.getContractHeaderBean().getContractId()));
				
				billApprovalBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		return billApprovalBeans;
	}

}
