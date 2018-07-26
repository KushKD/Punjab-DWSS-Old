package com.prwss.mis.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.component.dao.ComponentBean;

public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {
	Logger log = Logger.getLogger(this.getClass());
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<RoleBean> getRoleIds() throws DataAccessException {
		Set<RoleBean> roleBeans = null; 
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(RoleBean.class);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status",statusList));
			roleBeans = new TreeSet<RoleBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}
		
		return roleBeans;
	}

	@Override
	public List<RoleBean> findRole(RoleBean roleBean, List<String> statusList)
			throws DataAccessException {
		List<RoleBean> roleList = null;
		try {
			String roleId = roleBean.getRoleId();
			String roleName = roleBean.getRoleName();
			String roleDescription = roleBean.getRoleDesc();
			log.debug("statusList\t"+statusList);
			System.out.println("-------------RoleDao"+roleId);
			DetachedCriteria criteria = DetachedCriteria.forClass(RoleBean.class);
			
			if(MisUtility.ifEmpty(roleId))
				criteria.add(Restrictions.eq("roleId", roleId).ignoreCase());			
			if(MisUtility.ifEmpty(roleName))
				criteria.add(Restrictions.eq("roleName", roleName).ignoreCase());			
			if(MisUtility.ifEmpty(roleDescription))
				criteria.add(Restrictions.eq("roleDescription", roleDescription).ignoreCase());			
			if(!MisUtility.ifEmpty(statusList))
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			log.debug("criteria\n"+criteria);			
			roleList = getHibernateTemplate().findByCriteria(criteria);
			
			log.debug("componentBean\t"+roleList);
		} catch (DataAccessException e) {
			throw e;
		}
		return roleList;
	}

	@Override
	public boolean saveRole(RoleBean roleBean) throws DataAccessException {
		try {
			log.debug(roleBean+"----------------------");
			
			getHibernateTemplate().save(roleBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		
		// if anything fails we are throwing an exception 
		// which will be handled in the calling method
		return true;	
	}

	@Override
	public boolean updateRole(RoleBean roleBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(roleBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		// if anything fails we are throwing an exception 
		// which will be handled in the calling method
		return true;
	}

	
	
}
