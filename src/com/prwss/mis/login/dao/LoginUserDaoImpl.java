package com.prwss.mis.login.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class LoginUserDaoImpl extends HibernateDaoSupport implements LoginUserDao {
	private Logger log = Logger.getLogger(LoginUserDaoImpl.class);
	@Override
	public LoginUserBean getUserDetails(String userId) throws DataAccessException,Exception {
		LoginUserBean loginUserBean = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserBean.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			System.out.println("-------------------------------------------------------------------------------------");
			loginUserBean =(LoginUserBean)getHibernateTemplate().findByCriteria(criteria).get(0);
			//loginUserBean = getHibernateTemplate().get(LoginUserBean.class, userId);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			System.out.println("-------------------------------------------------------------------------------------");
			throw e;
		}catch(Exception e){
			throw e;
		}
		
		return loginUserBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginUserBean> findLoginUser(LoginUserBean loginUserBean,
			List<String> statusList) throws DataAccessException {
		List<LoginUserBean> loginUserBeans = new ArrayList<LoginUserBean>();
		try {
			if(MisUtility.ifEmpty(loginUserBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserBean.class);
				if(MisUtility.ifEmpty(loginUserBean.getUserId()))
					criteria.add(Restrictions.eq("userId", loginUserBean.getUserId()));
				if(MisUtility.ifEmpty(loginUserBean.getUserName()))
					criteria.add(Restrictions.eq("userName", loginUserBean.getUserName()));
				if(MisUtility.ifEmpty(loginUserBean.getRoleId()))
					criteria.add(Restrictions.eq("roleId", loginUserBean.getRoleId()));
				if(MisUtility.ifEmpty(loginUserBean.getUserTelephone()))
					criteria.add(Restrictions.eq("userTelephone", loginUserBean.getUserTelephone()));
				
				if(MisUtility.ifEmpty(loginUserBean.getEmployeeId()))
					criteria.add(Restrictions.eq("employeeId", loginUserBean.getEmployeeId()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				loginUserBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return loginUserBeans;
	}

	@Override
	public boolean saveLoginBean(LoginUserBean loginUserBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(loginUserBean);
			
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}
	
	

	@Override
	public boolean updateLoginBean(LoginUserBean loginUserBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(loginUserBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

}
