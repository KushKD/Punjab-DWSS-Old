package com.prwss.mis.tender.contract.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class ContractDaoImpl extends HibernateDaoSupport implements ContractDao {
	
	private Logger log = Logger.getLogger(ContractDaoImpl.class);
	private MISAuditBean misAuditBean;
	
	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ContractHeaderBean> findContracts(ContractHeaderBean contractHeaderBean, List<String> statusList) throws DataAccessException {
		
		List<ContractHeaderBean> contractHeaderBeans = null;
		
		try {
			if(MisUtility.ifEmpty(contractHeaderBean)){
			
				DetachedCriteria criteria = DetachedCriteria.forClass(ContractHeaderBean.class);
				if(MisUtility.ifEmpty(contractHeaderBean.getContractId()))
					criteria.add(Restrictions.eq("contractId", contractHeaderBean.getContractId()));
				if(MisUtility.ifEmpty(contractHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("tenderId", contractHeaderBean.getTenderId()));
				if(MisUtility.ifEmpty(contractHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", contractHeaderBean.getLocationId()));
//				if(MisUtility.ifEmpty(contractHeaderBean.getContractDate()))
//					criteria.add(Restrictions.eq("contractDate", contractHeaderBean.getContractDate()));
//				if(MisUtility.ifEmpty(contractHeaderBean.getLdAmount()))
//					criteria.add(Restrictions.eq("ldAmount", contractHeaderBean.getLdAmount()));
//				if(MisUtility.ifEmpty(contractHeaderBean.getMaxLDRate()))
//					criteria.add(Restrictions.eq("maxLDRate", contractHeaderBean.getMaxLDRate()));
//				if(MisUtility.ifEmpty(contractHeaderBean.getRevisedContractDate()))
//					criteria.add(Restrictions.eq("revisedContractDate", contractHeaderBean.getRevisedContractDate()));
//				if(MisUtility.ifEmpty(contractHeaderBean.getVendorBean()) && MisUtility.ifEmpty(contractHeaderBean.getVendorBean().getVendorId()))
//					criteria.add(Restrictions.eq("vendorBean.vendorId", contractHeaderBean.getVendorBean().getVendorId()));
//				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				log.debug(contractHeaderBean);
				log.debug(criteria);
				contractHeaderBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			log.error(contractHeaderBean);
			throw e;
		}
		return contractHeaderBeans;
	}

	@Override
	public String saveContract(ContractHeaderBean contractHeaderBean) throws DataAccessException {
		String contractId = null;
		
		try {
			log.debug(contractHeaderBean);
			contractId = (String)getHibernateTemplate().save(contractHeaderBean);
		} catch (DataAccessException e) {
			log.error(contractHeaderBean);
			throw e;
		}
		System.out.println("-----------Chico---------"+contractId);
		return contractId;
	}

	@Override
	public boolean updateContract(ContractHeaderBean contractHeaderBean) throws DataAccessException {

		try {
			log.debug(contractHeaderBean);
			getHibernateTemplate().update(contractHeaderBean);
		} catch (DataAccessException e) {
			log.error(contractHeaderBean);
			throw e;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ContractHeaderBean> getContractCodes(String locationId) throws DataAccessException {
		//System.out.println("byeeeeeee");
		Set<ContractHeaderBean> contractHeaderBeans = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			System.out.println("locaton"+locationId);
				//System.out.println("heloo");
				DetachedCriteria criteria = DetachedCriteria.forClass(ContractHeaderBean.class);
				if(MisUtility.ifEmpty(locationId))
					criteria.add(Restrictions.eq("locationId", locationId));
				
				 
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				contractHeaderBeans = new TreeSet<ContractHeaderBean>(getHibernateTemplate().findByCriteria(criteria));
				
		} catch (DataAccessException e) {
			throw e;
		}
		System.out.println(contractHeaderBeans);
		return contractHeaderBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ContractHeaderBean> getContractCodes(ContractHeaderBean contractHeaderBean) throws DataAccessException {
		
		Set<ContractHeaderBean> contractHeaderBeans = null;
		
		try {
				DetachedCriteria criteria = DetachedCriteria.forClass(ContractHeaderBean.class);
				if(MisUtility.ifEmpty(contractHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", contractHeaderBean.getLocationId()));
				if(MisUtility.ifEmpty(contractHeaderBean.getTenderId()))
					criteria.add(Restrictions.eq("tenderId", contractHeaderBean.getTenderId()));
				if(MisUtility.ifEmpty(contractHeaderBean.getContractId()))
					criteria.add(Restrictions.eq("contractId", contractHeaderBean.getContractId()));
				contractHeaderBeans = new TreeSet<ContractHeaderBean>(getHibernateTemplate().findByCriteria(criteria));
				
		} catch (DataAccessException e) {
			throw e;
		}
		return contractHeaderBeans;
	}

//
//	@Override
//	public List<ContractHeaderBean> findTender(ContractHeaderBean contractHeaderBean1) throws DataAccessException {
//		 
//		List<ContractHeaderBean> contractHeaderBeans = null;
//		try{
//			DetachedCriteria criteria = DetachedCriteria.forClass(ContractHeaderBean.class);
//			if(MisUtility.ifEmpty(contractHeaderBean1.getTenderId())){
//				criteria.add(Restrictions.eq("tenderId", contractHeaderBean1.getTenderId()));
//			}
//			
//		}catch (DataAccessException e) {
//			throw e;
//		}
//		return contractHeaderBeans;
//	}

	
	 

}
