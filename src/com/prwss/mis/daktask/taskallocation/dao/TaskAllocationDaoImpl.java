package com.prwss.mis.daktask.taskallocation.dao;

import java.util.Collection;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.prwss.mis.common.util.MisUtility;;

public class TaskAllocationDaoImpl extends HibernateDaoSupport implements TaskAllocationDao {	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskAllocationBean> getTaskAllocationBeans(
			TaskAllocationBean taskAllocationBean, List<String> statusList)
			throws DataAccessException {
		List<TaskAllocationBean> taskAllocationBeans=null;
	try{
		if(MisUtility.ifEmpty(taskAllocationBean))
		{
			DetachedCriteria criteria = DetachedCriteria.forClass(TaskAllocationBean.class);
			if(MisUtility.ifEmpty(taskAllocationBean.getLocationId()))
			{
				criteria.add(Restrictions.eq("locationId", taskAllocationBean.getLocationId()));				
			}
			if(MisUtility.ifEmpty(MisUtility.convertStringToDate(taskAllocationBean.getSearchDate())))
			{
				criteria.add(Restrictions.eq("misAuditBean.entDate", taskAllocationBean.getSearchDate()));				
			}
			taskAllocationBeans=getHibernateTemplate().findByCriteria(criteria);
		}
		return taskAllocationBeans;
	}catch(DataAccessException e)
	{
		throw e;
	}
		
	}

	@Override
	public boolean saveOrUpdateTaskAllocationBean(
			Collection<TaskAllocationBean> taskAllocationBeans)
			throws DataAccessException {
		try{
		 getHibernateTemplate().saveOrUpdateAll(taskAllocationBeans);
		}catch(DataAccessException e)
		{
			throw e;
		}
		return true;
	}
	
	

}
