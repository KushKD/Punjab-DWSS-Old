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
public class CommitteeMemberDaoImpl extends HibernateDaoSupport implements CommitteeMemberDao {
	private Logger log = Logger.getLogger(CommitteeMemberDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<CommitteeMemberBean> findCommitteeMember(
			CommitteeMemberBean committeeMemberBean, List<String> statusList)
			throws DataAccessException {
    List<CommitteeMemberBean> committeeMemberBeans = null;
	
    
   
		try {
			if(MisUtility.ifEmpty(committeeMemberBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(CommitteeMemberBean.class);
				
				if(MisUtility.ifEmpty(committeeMemberBean.getMemberId()))
					criteria.add(Restrictions.eq("memberId", committeeMemberBean.getMemberId()));
				
				if(MisUtility.ifEmpty(committeeMemberBean.getMemberName()))
					criteria.add(Restrictions.eq("memberName", committeeMemberBean.getMemberName()));
				
				if(MisUtility.ifEmpty(committeeMemberBean.getContactNumber()))
					criteria.add(Restrictions.eq("contactNumber", committeeMemberBean.getContactNumber()));
				
				if(MisUtility.ifEmpty(committeeMemberBean.getCommitteeId()))
					criteria.add(Restrictions.eq("committeeId", committeeMemberBean.getCommitteeId()));
				
				if(MisUtility.ifEmpty(committeeMemberBean.getMemberStatus()))
					criteria.add(Restrictions.eq("memberStatus", committeeMemberBean.getMemberStatus()));
				
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				committeeMemberBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return committeeMemberBeans;
	}

	@Override
	public boolean saveOrUpdateCommitteeMember(
			Collection<CommitteeMemberBean> committeeMemberBeans)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(committeeMemberBeans);
			} catch (DataAccessException e) {
				log.error(e.getLocalizedMessage(),e);
				throw e;
			}
			return true;
	}

}
