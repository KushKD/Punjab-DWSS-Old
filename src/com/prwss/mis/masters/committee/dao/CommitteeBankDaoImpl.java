/**
 * 
 */
package com.prwss.mis.masters.committee.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

/**
 * @author pjha
 *
 */
public class CommitteeBankDaoImpl extends HibernateDaoSupport implements CommitteeBankDao{
	private Logger log = Logger.getLogger(CommitteeBankDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<CommitteeBankBean> findCommitteeBank(
			CommitteeBankBean committeeBankBean, List<String> statusList)
			throws DataAccessException {
       List<CommitteeBankBean> committeeBankBeans = null;
       
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CommitteeBankBean.class);
			if(MisUtility.ifEmpty(committeeBankBean)){
				
				if(MisUtility.ifEmpty(committeeBankBean.getAccountNumber()))
					criteria.add(Restrictions.eq("accountNumber", committeeBankBean.getAccountNumber()));
				
				if(MisUtility.ifEmpty(committeeBankBean.getBankId()))
					criteria.add(Restrictions.eq("bankId", committeeBankBean.getBankId()));
				
				if(MisUtility.ifEmpty(committeeBankBean.getBankName()))
					criteria.add(Restrictions.eq("bankName", committeeBankBean.getBankName()));
				
				if(MisUtility.ifEmpty(committeeBankBean.getBankBranch()))
					criteria.add(Restrictions.eq("bankBranch", committeeBankBean.getBankBranch()));
				
				if(MisUtility.ifEmpty(committeeBankBean.getAccountType()))
					criteria.add(Restrictions.eq("accountType", committeeBankBean.getAccountType()));
								
				if(MisUtility.ifEmpty(committeeBankBean.getCommitteeId()))
					criteria.add(Restrictions.eq("committeeId", committeeBankBean.getCommitteeId()));
								
				/*if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));*/
			}	
				log.debug(criteria);
				committeeBankBeans = getHibernateTemplate().findByCriteria(criteria);
			
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			e.getStackTrace();
			throw e;
		}
		return committeeBankBeans;
	}

	@Override
	public boolean saveOrUpdateCommitteeBank(
			Collection<CommitteeBankBean> committeeBankBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(committeeBankBeans);
			} catch (DataAccessException e) {
				log.error(e.getLocalizedMessage(),e);
				throw e;
			}
			return true;
	}

}
