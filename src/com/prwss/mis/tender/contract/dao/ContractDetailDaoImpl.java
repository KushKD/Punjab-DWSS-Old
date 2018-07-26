package com.prwss.mis.tender.contract.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.sun.mail.imap.protocol.Status;

public class ContractDetailDaoImpl extends HibernateDaoSupport implements ContractDetailDao {
	
	private Logger log = Logger.getLogger(ContractDetailDaoImpl.class);
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<ContractDetailBean> getContractDetails(String contractId) throws DataAccessException {
		List<ContractDetailBean> contractDetailBeans = null;
		try {
			if(MisUtility.ifEmpty(contractId)){
			DetachedCriteria criteria = DetachedCriteria.forClass(ContractDetailBean.class);
			criteria.add(Restrictions.eq("contractId", contractId));
			hibernateTemplate = getHibernateTemplate();
			log.debug("Criteria\t"+criteria);
			contractDetailBeans = hibernateTemplate.findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return contractDetailBeans;
	}

	@Override
	public boolean saveOrUpdateContractDetail(Collection<ContractDetailBean> contractDetailBeans) throws DataAccessException {
try {
	   				Iterator<ContractDetailBean> iterator = contractDetailBeans.iterator();
	   				while(iterator.hasNext()){
	   					ContractDetailBean contractDetailBean = (ContractDetailBean)iterator.next();
	   					getHibernateTemplate().merge(contractDetailBean);
	   					getHibernateTemplate().flush();
	   					getHibernateTemplate().clear();
	
	   				}
		/*	getHibernateTemplate().saveOrUpdateAll(contractDetailBeans);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();*/
			}
			
		 catch (DataAccessException e) {
			throw e;
		}
		return true;
	}
	@Override
	public boolean saveOrUpdateContractDetail1(Collection<ContractDetailBean> contractDetailBeans1) throws DataAccessException {
		try {
			String a=contractDetailBeans1.iterator().next().getMisAuditBean().getStatus();
			if(a.equals("D"))
			{	
				Iterator<ContractDetailBean> iterator = contractDetailBeans1.iterator();
				while(iterator.hasNext()){
					ContractDetailBean contractDetailBean = (ContractDetailBean)iterator.next();
					getHibernateTemplate().merge(contractDetailBean);
					getHibernateTemplate().flush();
					getHibernateTemplate().clear();
				
				}
			}
			
			
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}
	@Override
	public boolean saveOrUpdateContractDetail2(Collection<ContractDetailBean> contractDetailBeans2) throws DataAccessException {
try {
	
			getHibernateTemplate().saveOrUpdateAll(contractDetailBeans2);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();
			}
			
		 catch (DataAccessException e) {
			throw e;
		}
		return true;
	}
}
