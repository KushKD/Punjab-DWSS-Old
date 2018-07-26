/**
 * 
 */
package com.prwss.mis.pmm.labtesting.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.labtesting.LabTestingBean;
import com.prwss.mis.pmm.sustassessment.dao.SustAssessmentDaoImpl;

/**
 * @author pjha
 *
 */
public class LabTestingDaoImpl extends HibernateDaoSupport implements LabTestingDao {
	private Logger log = Logger.getLogger(SustAssessmentDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<LabTestingBean> findLabTesting(LabTestingBean labTestingBean,
			List<String> statusList) throws DataAccessException {
		List<LabTestingBean> labTestingBeans = new  ArrayList<LabTestingBean>();
		try {
			if(MisUtility.ifEmpty(labTestingBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(LabTestingBean.class);
				if(MisUtility.ifEmpty(labTestingBean.getLabTestid()))
					criteria.add(Restrictions.eq("labTestid",labTestingBean.getLabTestid()));
				
				if(MisUtility.ifEmpty(labTestingBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId",labTestingBean.getVillageId()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				labTestingBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return labTestingBeans;
	}

	@Override
	public long saveLabTesting(LabTestingBean labTestingBean)
			throws DataAccessException {
		long labTestNumber = 0;
		try {
			labTestNumber = (Long)getHibernateTemplate().save(labTestingBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return labTestNumber;
	}

	@Override
	public boolean saveOrUpdateLabTesting(LabTestingBean labTestingBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(labTestingBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

}
