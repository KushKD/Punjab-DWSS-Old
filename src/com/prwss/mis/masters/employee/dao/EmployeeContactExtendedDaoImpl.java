package com.prwss.mis.masters.employee.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class EmployeeContactExtendedDaoImpl extends HibernateDaoSupport implements EmployeeContactExtendedDao{
	private Logger log = Logger.getLogger(EmployeeContactExtendedDaoImpl.class);
	@Override
	public boolean saveOrUpdateEmployeeContract(
			Collection<EmployeeContactExtendedBean> employeeContactExtendedBeans)throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(employeeContactExtendedBeans);
			} catch (DataAccessException e) {
				log.error(e.getLocalizedMessage(),e);
				throw e;
			}
			return true;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeContactExtendedBean> findEmployeeContractExtention(EmployeeContactExtendedBean employeeContactExtendedBean,List<String> statuslist) throws DataAccessException {
		 
		List<EmployeeContactExtendedBean> employeeContactExtendedBeans = null;
		try{
			if(MisUtility.ifEmpty(employeeContactExtendedBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeContactExtendedBean.class);
				if(MisUtility.ifEmpty(employeeContactExtendedBean.getId()))
					criteria.add(Restrictions.eq("id", employeeContactExtendedBean.getId()));
				if(MisUtility.ifEmpty(employeeContactExtendedBean.getEmployeeId()))
					criteria.add(Restrictions.eq("employeeId", employeeContactExtendedBean.getEmployeeId()));
				if(!MisUtility.ifEmpty(statuslist))
					criteria.add(Restrictions.in("misAuditBean.status", statuslist));
				employeeContactExtendedBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		}catch (DataAccessException e) {
			throw e;
		}
		return employeeContactExtendedBeans;
	}
	
	

}
