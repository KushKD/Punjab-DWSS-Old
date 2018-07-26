package com.prwss.mis.pmm.alert.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

public class AlertChecklistDaoImpl extends HibernateDaoSupport implements AlertChecklistDao {
	private Logger log = Logger.getLogger(AlertChecklistDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<AlertChecklistBean> findAlertChecklistBean(String locationId, String month, long year, String mailStatus) throws DataAccessException {
		List<AlertChecklistBean> alertChecklistBeans= null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(AlertChecklistBean.class);
			if(MisUtility.ifEmpty(locationId))
				criteria.add(Restrictions.eq("locationId", locationId));
			if(MisUtility.ifEmpty(month))
				criteria.add(Restrictions.eq("tMonth", month));
			if(MisUtility.ifEmpty(year))
				criteria.add(Restrictions.eq("tYear", year));
			if(MisUtility.ifEmpty(mailStatus))
				criteria.add(Restrictions.eq("mailStatus", mailStatus));
			
			alertChecklistBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}
		return alertChecklistBeans;
	}
	@Override
	
	public List<EmployeeBean> getEmployeeMail()throws DataAccessException{
		List<EmployeeBean> employeeBean=null;
		try{
		DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
		
		employeeBean = getHibernateTemplate().findByCriteria(criteria);
		}
		catch(DataAccessException e){
			throw e;
		}
		return employeeBean;
		
	}


	@Override
	public boolean saveOrUpdate(AlertChecklistBean alertChecklistBean)	throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(alertChecklistBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdateAll(List<AlertChecklistBean> alertChecklistBeans)	throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdateAll(alertChecklistBeans);
			System.out.println("inside alert dao");
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Object> getMailData(String month, long year, String mailStatus) {
				System.out.println("Inside get mail data");
		String query = " select x.user_email,t.location_id,di.divisional_name, t.t_month, t.t_year, t.admin_approval, t.scheme_commissioning, "+ 
					" t.water_connection, t.household, t.operation_sustainability,t.iec_training,t.beneficiay_share,t.spmc_payment_voucher,t.dpmc_payment_voucher," +
					"t.gpwsc_register_entry,t.updation_procurement_plan, t.mail_status from prwss_main.mst_divisional di "+ 
					" left outer join (select * from prwss_main.t_alert_checklist " +
					" where t_month='"+month+"' and t_year="+year+") t on di.divisional_id=t.location_id "+
					" left outer join ( "+
					" select u.user_email,location_id "+ 
					" from prwss_main.sd_user u,prwss_main.sd_user_location ul "+ 
					" where u.user_id=ul.user_id and u.role_id='XEN' and u.user_email is not null "+
					" )x on di.divisional_id=x.location_id "+
					" where is_spmc_dpmc in ('DO','DPMC') "+
					" and x.user_email is not null  and (mail_status='"+mailStatus+"' or mail_status is null)";
		return getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(query).list();
		 
	}
}
