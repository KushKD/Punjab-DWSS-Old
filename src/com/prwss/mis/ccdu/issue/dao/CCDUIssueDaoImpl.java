package com.prwss.mis.ccdu.issue.dao;

import java.util.Set;
import java.util.TreeSet;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;

public class CCDUIssueDaoImpl extends HibernateDaoSupport implements CCDUIssueDao {

	@SuppressWarnings("unchecked")
	@Override
	public Set<CCDUIssueBean> getDistinctCCDUIssueCodes() throws DataAccessException {
		Set<CCDUIssueBean> ccduIssueBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CCDUIssueBean.class);		
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			
			ccduIssueBeans = new TreeSet<CCDUIssueBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		
		return ccduIssueBeans;
	}

}
