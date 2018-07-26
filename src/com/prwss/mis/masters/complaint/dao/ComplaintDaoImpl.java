package com.prwss.mis.masters.complaint.dao;

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

public class ComplaintDaoImpl extends HibernateDaoSupport implements ComplaintDao {
	
	private Logger log = Logger.getLogger(ComplaintDaoImpl.class);
	private HibernateTemplate hibernateTemplate = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<ComplaintBean> findComplaint(ComplaintBean complaintBean, List<String> statusList)
			throws DataAccessException {
		List<ComplaintBean> complaintBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComplaintBean.class);
			
			if(MisUtility.ifEmpty(complaintBean)){
				if(MisUtility.ifEmpty(complaintBean.getComplaintId()))
					criteria.add(Restrictions.eq("complaintId", complaintBean.getComplaintId()));
				if(MisUtility.ifEmpty(complaintBean.getComplaintType()))
					criteria.add(Restrictions.eq("complaintType", complaintBean.getComplaintType()));
				criteria.add(Restrictions.in("status", statusList));
			}	
			
			complaintBeans = getHibernateTemplate().findByCriteria(criteria);
			log.debug("ComplaintBeans\n"+complaintBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return complaintBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComplaintBean> findComplaint(List<Long> complaintIds) throws DataAccessException {
		List<ComplaintBean> complaintBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComplaintBean.class);
				if(!MisUtility.ifEmpty(complaintIds))
					criteria.add(Restrictions.in("complaintId", complaintIds));
			
			complaintBeans = getHibernateTemplate().findByCriteria(criteria);
			log.debug("ComplaintBeans\n"+complaintBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		return complaintBeans;
	}

	@Override
	public boolean saveComplaint(ComplaintBean complaintBean) throws DataAccessException {

		try {
			hibernateTemplate = getHibernateTemplate(); 
			hibernateTemplate.save(complaintBean);
			hibernateTemplate.flush();
		}  catch (DataAccessException e) {
			throw e;
		}

		return true;
	}

	@Override
	public boolean updateComplaint(ComplaintBean complaintBean) throws DataAccessException {
		try {
			hibernateTemplate = getHibernateTemplate(); 
			hibernateTemplate.update(complaintBean);
			hibernateTemplate.flush();
		}  catch (DataAccessException e) {
			throw e;
		}

		return true;
	}

	@Override
	public boolean updateComplaint(List<ComplaintBean> complaintBeans) throws DataAccessException {
		try {
			hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.saveOrUpdateAll(complaintBeans);
			hibernateTemplate.flush();
		}  catch (DataAccessException e) {
			throw e;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<ComplaintBean> getDistinctComplaintType() throws DataAccessException {
		Set<ComplaintBean> complaintBeans = null;
		 try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ComplaintBean.class);
			
			 complaintBeans = new TreeSet<ComplaintBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}

		return complaintBeans;
	}

}
