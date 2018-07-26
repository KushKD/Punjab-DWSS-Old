package com.prwss.mis.masters.employee.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

 

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.util.MisUtility;

public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {
	
	private Logger log = Logger.getLogger(EmployeeDaoImpl.class);
	private HibernateTemplate hibernateTemplate;
	private MISJdcDaoImpl misJdcDaoImpl;
	
	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> findEmployee(EmployeeBean employeeBean, List<String> statusList)
			throws DataAccessException {
		
		List<EmployeeBean> employeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
			if(MisUtility.ifEmpty(employeeBean)){
				System.out.println("daooooooo+++++++++++");
				if(MisUtility.ifEmpty(employeeBean.getEmployeeId()))
					criteria.add(Restrictions.eq("employeeId", employeeBean.getEmployeeId()));
				
				if(MisUtility.ifEmpty(employeeBean.getEmployeeName())){
					 criteria.add(Restrictions.ilike("employeeName",employeeBean.getEmployeeName(),MatchMode.ANYWHERE));
				}
				if(MisUtility.ifEmpty(employeeBean.getFatherName()))
					criteria.add(Restrictions.eq("fatherName",employeeBean.getFatherName()).ignoreCase());
				
				if(MisUtility.ifEmpty(employeeBean.getDateOfBirth()))
					criteria.add(Restrictions.eq("dateOfBirth", employeeBean.getDateOfBirth()));
				if(MisUtility.ifEmpty(employeeBean.getDesigId())){
					System.out.println("--------Check for Desig Id "+employeeBean.getDesigId());
					criteria.add(Restrictions.eq("desigId", employeeBean.getDesigId()));					
				}
					
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
			}
			log.debug(criteria);
			employeeBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		//System.out.println("daooooooo+++++++++++"+employeeBeans);
		return employeeBeans;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> findEmployee(List<Long> employeeIds) throws DataAccessException {
		List<EmployeeBean> employeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);

			if(!MisUtility.ifEmpty(employeeIds))
				criteria.add(Restrictions.in("employeeId", employeeIds));

			log.debug(criteria);
			employeeBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return employeeBeans;
	}

	@Override
	public long saveEmployee(EmployeeBean employeeBean) throws DataAccessException {
		long employeeId = 0;
		try {
			employeeId = (Long) getHibernateTemplate().save(employeeBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return employeeId;
	}

	@Override
	public boolean updateEmployee(EmployeeBean employeeBean) throws DataAccessException {

		try {
			hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.flush();
			hibernateTemplate.update(employeeBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean updateEmployee(List<EmployeeBean> employeeBeans) throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(employeeBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<EmployeeBean> getDistinctEmployeeIds(String locationId,List<String> statusList ) throws DataAccessException {
		Set<EmployeeBean> employeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
			if(MisUtility.ifEmpty(locationId))
				criteria.add(Restrictions.eq("locationId", locationId));
				 
			
			if(!MisUtility.ifEmpty(statusList))
			criteria.add(Restrictions.in("misAuditBean.status",statusList));

			employeeBeans = new TreeSet<EmployeeBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return employeeBeans;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public Set<EmployeeBean> getDistinctEmployeeIdsForPermanentEmployees(String locationId,List<String> statusList ) throws DataAccessException {
//		Set<EmployeeBean> employeeBeans = null;
//		try {
//			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
//			if(MisUtility.ifEmpty(locationId))
//				criteria.add(Restrictions.eq("locationId", locationId));
//				criteria.add(Restrictions.eq("employeeType","PERMANENT"));
//			
//			if(!MisUtility.ifEmpty(statusList))
//			criteria.add(Restrictions.in("misAuditBean.status",statusList));
//
//			employeeBeans = new TreeSet<EmployeeBean>(getHibernateTemplate().findByCriteria(criteria));
//		} catch (DataAccessException e) {
//			log.error(e);
//			throw e;
//		}
//
//		return employeeBeans;
//	}
	@SuppressWarnings("unchecked")
	@Override
	public Set<EmployeeBean> getEmployeeByReportingOfficer(EmployeeBean employeeBean,List<String> statusList ) throws DataAccessException {
		Set<EmployeeBean> employeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
			if(MisUtility.ifEmpty(employeeBean)){
				System.out.println("innnnnn");
				System.out.println(employeeBean.getReportingOfficerId());
			if(MisUtility.ifEmpty(employeeBean.getLocationId()))
				criteria.add(Restrictions.eq("locationId",employeeBean.getLocationId()));
			if(MisUtility.ifEmpty(employeeBean.getReportingOfficerId()))
				criteria.add(Restrictions.eq("reportingOfficerId",employeeBean.getReportingOfficerId()));
			if(!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("misAuditBean.status",statusList));

			employeeBeans = new TreeSet<EmployeeBean>(getHibernateTemplate().findByCriteria(criteria));
			}
			
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		System.out.println(employeeBean);
		return employeeBeans;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<EmployeeBean> getEmployeeByOldOfficer(EmployeeBean employeeBean,List<String> statusList ) throws DataAccessException {
		List<EmployeeBean> employeeBeans = new ArrayList<EmployeeBean>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
			if(MisUtility.ifEmpty(employeeBean)){
				System.out.println("innnnnn");
				System.out.println("empiddddddddddd"+employeeBean.getEmployeeId());
				System.out.println(employeeBean.getReportingOfficerId());
			if(MisUtility.ifEmpty(employeeBean.getEmployeeId()))
				criteria.add(Restrictions.eq("employeeId",employeeBean.getEmployeeId()));
			if(MisUtility.ifEmpty(employeeBean.getReportingOfficerId()))
				criteria.add(Restrictions.eq("reportingOfficerId",employeeBean.getReportingOfficerId()));
			if(!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("misAuditBean.status",statusList));

			employeeBeans = getHibernateTemplate().findByCriteria(criteria);
			}
			
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		//System.out.println(employeeBeans);
		return employeeBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<EmployeeBean> getEmployeeByDeployedLocation(EmployeeBean employeeBean,List<String> statusList ) throws DataAccessException {
		Set<EmployeeBean> employeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
			if(MisUtility.ifEmpty(employeeBean)){
			if(MisUtility.ifEmpty(employeeBean.getLocationId()))
				criteria.add(Restrictions.eq("locationId",employeeBean.getLocationId()));
			if(!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("misAuditBean.status",statusList));

			employeeBeans = new TreeSet<EmployeeBean>(getHibernateTemplate().findByCriteria(criteria));
			}
			
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return employeeBeans;
	}

	@Override
	public List<EmployeeBean> getEmployeeByName(long employeeId,
			List<String> statusList) throws DataAccessException {
		
		List<EmployeeBean> employeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
			if(MisUtility.ifEmpty(employeeId))
				criteria.add(Restrictions.eq("employeeId", employeeId));
			if(!MisUtility.ifEmpty(statusList))
			criteria.add(Restrictions.in("misAuditBean.status",statusList));

			employeeBeans = new ArrayList<EmployeeBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return employeeBeans;
	}

	 
 
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getDistinctEmployeeDetails(long employeeId,List<String> statusList ) throws DataAccessException {
		List<EmployeeBean> employeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
			if(MisUtility.ifEmpty(employeeId))
				criteria.add(Restrictions.eq("employeeId", employeeId));
			
			if(!MisUtility.ifEmpty(statusList))
			criteria.add(Restrictions.in("misAuditBean.status",statusList));

			employeeBeans = new ArrayList<EmployeeBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return employeeBeans;
	}

	@Override
	public int updateEmployeeReportingOfficer(long[] empIds,
			long reportingofficer, String reportingOfficerLocaion) throws DataAccessException {
		int rows = 0;
		System.out.println("inside impl");
		 try{
   			    DataSource db = misJdcDaoImpl.getDataSource();
				Connection con = db.getConnection();
				Statement stmt = con.createStatement();
			 	for(Long emp : empIds){
			 		String query = "UPDATE prwss_main.mst_employee set reporting_officer_id="+reportingofficer+", reporting_officer_loaction_id='"+reportingOfficerLocaion+"' where employee_id="+emp;
					 rows+= stmt.executeUpdate(query);
					 System.out.println(rows);
			 	}
		 }catch(SQLException e){
			 
		 }
		 catch(DataAccessException e){
			 e.printStackTrace();
			 
		 }
		return rows;
	}
	@Override
	public int updateEmployeeLocation(int employeeId,
			String previousLocation, String changeLocation)throws DataAccessException {
		int status = 0;
		try{
			DataSource db = misJdcDaoImpl.getDataSource();
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
			System.out.println("changed Location========"+changeLocation);
			
			String query = "UPDATE prwss_main.mst_employee set location_id='"+changeLocation+"' where employee_id="+employeeId;
			status = stmt.executeUpdate(query);
		}catch(SQLException e){
			 e.printStackTrace();
		 }
		 catch(DataAccessException e){
			 e.printStackTrace();
			 
		 }
		 
		return status;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> findEmployeeForLocation(EmployeeBean employeeBean, List<String> statusList)
			throws DataAccessException {
		
		List<EmployeeBean> employeeBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EmployeeBean.class);
			if(MisUtility.ifEmpty(employeeBean)){
				if(MisUtility.ifEmpty(employeeBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", employeeBean.getLocationId()));
				
				
				
				criteria.add(Restrictions.eq("desigId", employeeBean.getDesigId()));					
				
					
				if(MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
			}			
			employeeBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(),e);
			throw e;
		}
		//System.out.println("daooooooo+++++++++++"+employeeBeans);
		return employeeBeans;
		
	}

	

}
