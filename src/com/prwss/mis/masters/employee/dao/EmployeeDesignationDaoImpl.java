package com.prwss.mis.masters.employee.dao;

 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.environment.dao.EnvironmentDaoImpl;

public class EmployeeDesignationDaoImpl extends HibernateDaoSupport implements EmployeeDesignationDao{
	private Logger log = Logger.getLogger(EmployeeDesignationDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeDesignationBean> getEmployeeDesignation(EmployeeDesignationBean employeeDesignationBean) throws DataAccessException {
		
		System.out.println("inside criteria");
		List<EmployeeDesignationBean> employeeDesignationBeans = null;
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeDesignationBean.class);
			if(MisUtility.ifEmpty(employeeDesignationBean.getEmployeeType())){
				criteria.add(Restrictions.eq("employeeType",employeeDesignationBean.getEmployeeType()));
				criteria.addOrder(Order.asc("hierarchyLevel").ignoreCase());
			
			}
			if(MisUtility.ifEmpty(employeeDesignationBean.getDesignationId())){
				criteria.add(Restrictions.eq("designationId", employeeDesignationBean.getDesignationId()));
			}
			employeeDesignationBeans = new ArrayList<EmployeeDesignationBean>(getHibernateTemplate().findByCriteria(criteria));
		}catch(DataAccessException e){
			throw e;
		}
		
		// System.out.println(employeeDesignationBeans);
		/* Collections.sort(employeeDesignationBeans);*/
		return employeeDesignationBeans;
	}

}
