package com.prwss.mis.service.ticket.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class ComplaintBookDaoImpl extends HibernateDaoSupport implements ComplaintBookDao {
	
	private Logger log = Logger.getLogger(ComplaintBookDaoImpl.class);
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<ComplaintBookBean> findComplaint(ComplaintBookBean complaintBookBean, Timestamp fromDate, Timestamp toDate) throws DataAccessException {
		Set<ComplaintBookBean> complaintBookBeans = null;
		hibernateTemplate = getHibernateTemplate();
		try {
			long entBy = 0;
			long pendingWith = 0;
			DetachedCriteria criteria = DetachedCriteria.forClass(ComplaintBookBean.class);
			if(MisUtility.ifEmpty(complaintBookBean.getOwnedEmployeeBean())){
				entBy = complaintBookBean.getOwnedEmployeeBean().getEmployeeId();
			}if(MisUtility.ifEmpty(complaintBookBean.getAssignedEmployeeBean())){
				pendingWith = complaintBookBean.getAssignedEmployeeBean().getEmployeeId();
			}
			if(MisUtility.ifEmpty(complaintBookBean) && (MisUtility.ifEmpty(entBy) || MisUtility.ifEmpty(pendingWith))){
				if(MisUtility.ifEmpty(entBy))
					criteria.add(Restrictions.eq("ownedEmployeeBean.employeeId", entBy));
				if(MisUtility.ifEmpty(pendingWith))
					criteria.add(Restrictions.eq("assignedEmployeeBean.employeeId", pendingWith));
				if(MisUtility.ifEmpty(complaintBookBean.getTicketId()))
					criteria.add(Restrictions.eq("ticketId", complaintBookBean.getTicketId()));
				if(MisUtility.ifEmpty(complaintBookBean.getStatus()))
					criteria.add(Restrictions.eq("status", complaintBookBean.getStatus()));
				if(MisUtility.ifEmpty(fromDate) && MisUtility.ifEmpty(toDate) )
					criteria.add(Restrictions.between("entDate", fromDate, toDate));
				if(MisUtility.ifEmpty(complaintBookBean.getComplaintBookType()))
					criteria.add(Restrictions.eq("complaintBookType", complaintBookBean.getComplaintBookType()));
				complaintBookBeans = new TreeSet(hibernateTemplate.findByCriteria(criteria));
			}
			log.debug("Find Complaint\n"+criteria);
			
		} catch (DataAccessException e) {
			throw e;
		}
		return complaintBookBeans;
	}

	@Override
	public long saveComplaint(ComplaintBookBean complaintBookBean) throws DataAccessException {
		long ticketId = 0;
		
		try {
			hibernateTemplate = getHibernateTemplate();
			ticketId = (Long) hibernateTemplate.save(complaintBookBean);
			hibernateTemplate.flush();
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		
		return ticketId;
	}

	@Override
	public boolean updateComplaintBook(ComplaintBookBean complaintBookBean) throws DataAccessException {
		try {
			 getHibernateTemplate().update(complaintBookBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ComplaintBookBean findComplaint(Long ticketId) throws DataAccessException {
		ComplaintBookBean complaintBookBean = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComplaintBookBean.class);
			
			criteria.add(Restrictions.eq("ticketId", ticketId));

			List<ComplaintBookBean> complaintBookBeans = getHibernateTemplate().findByCriteria(criteria);
			if(!MisUtility.ifEmpty(complaintBookBeans))
				complaintBookBean = complaintBookBeans.get(0);
		} catch (DataAccessException e) {
			throw e;
		}
	    
		return complaintBookBean;
	}

	@Override
	public long saveComplaintHistory(ComplaintHistoryBean complaintHistoryBean) throws DataAccessException {
		long historyId = 0;
		
		try {
			hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(complaintHistoryBean);
			hibernateTemplate.flush();
			
		} catch (DataAccessException e) {
//			e.printStackTrace();
			throw e;
		}
		
		return historyId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ComplaintHistoryBean> getComplaintHistory(long ticketId) throws DataAccessException {
		
		Set<ComplaintHistoryBean> complaintHistoryBeans = null;
		try {
			hibernateTemplate = getHibernateTemplate();
			DetachedCriteria criteria = DetachedCriteria.forClass(ComplaintHistoryBean.class);		
			criteria.add(Restrictions.eq("ticketId", ticketId));		
			complaintHistoryBeans = new TreeSet<ComplaintHistoryBean>(hibernateTemplate.findByCriteria(criteria));
		} catch (DataAccessException e) {
//			e.printStackTrace();
			throw e;
		}
		return complaintHistoryBeans;
	}

}
