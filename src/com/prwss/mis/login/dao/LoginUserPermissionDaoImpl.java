package com.prwss.mis.login.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;

public class LoginUserPermissionDaoImpl extends HibernateDaoSupport implements LoginUserPermissionDao {
	private Logger log = Logger.getLogger(LoginUserPermissionDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginUserPermissionBean> getUserSpecificMenus(String roleId) throws DataAccessException {
		List<LoginUserPermissionBean> loginUserPermissionBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserPermissionBean.class);
			criteria.add(Restrictions.eq("roleId", roleId).ignoreCase());
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			log.debug(criteria);
			loginUserPermissionBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return loginUserPermissionBeans;
	}

	@Override
	public boolean saveUserSpecificMenus(Collection<LoginUserPermissionBean> loginUserPermissionBeans)
			throws DataAccessException {
		
		try {
			System.out.println("ho admin"+loginUserPermissionBeans);
			getHibernateTemplate().saveOrUpdateAll(loginUserPermissionBeans);
			
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

}
