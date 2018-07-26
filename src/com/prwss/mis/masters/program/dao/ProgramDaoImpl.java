/**
 * 
 */
package com.prwss.mis.masters.program.dao;

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

/**
 * @author pjha
 *
 */
public class ProgramDaoImpl extends HibernateDaoSupport implements ProgramDao {
	private Logger log = Logger.getLogger(ProgramDaoImpl.class);
	@Override
	public  List<ProgramBean> findProgram(ProgramBean programBean, List<String> statusList)
			throws DataAccessException {
		List<ProgramBean> programBean2 = new  ArrayList<ProgramBean>();
		try {
			if(MisUtility.ifEmpty(programBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(ProgramBean.class);
				if(MisUtility.ifEmpty(programBean.getProgramId()))
					criteria.add(Restrictions.eq("programId", programBean.getProgramId()));
				if(MisUtility.ifEmpty(programBean.getProgramName()))
					criteria.add(Restrictions.eq("programName", programBean.getProgramName()));
				
				if(MisUtility.ifEmpty(programBean.getBenifciaryShare()))
					criteria.add(Restrictions.eq("benifciaryShare", programBean.getBenifciaryShare()));
				
				if(MisUtility.ifEmpty(programBean.getSponserAgencyShare()))
					criteria.add(Restrictions.eq("sponserAgencyShare",programBean.getSponserAgencyShare()));
				
				if(MisUtility.ifEmpty(programBean.getGoiShare()))
					criteria.add(Restrictions.eq("goiShare", programBean.getGoiShare()));
				
				if(MisUtility.ifEmpty(programBean.getGopShare()))
					criteria.add(Restrictions.eq("gopShare", programBean.getGopShare()));
				
				if(MisUtility.ifEmpty(programBean.getPlannedNonPlanned()))
					criteria.add(Restrictions.eq("plannedNonPlanned", programBean.getPlannedNonPlanned()));
				
				if(MisUtility.ifEmpty(programBean.getSwapNonSwap()))
					criteria.add(Restrictions.eq("swapNonSwap", programBean.getSwapNonSwap()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				programBean2 =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return programBean2;
	}

	@Override
	public boolean saveScheme(ProgramBean programBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(programBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateScheme(ProgramBean programBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(programBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Set<ProgramBean> getDistinctPrograms() throws DataAccessException {
		
		Set<ProgramBean> programBeans=null;
		DetachedCriteria criteria = DetachedCriteria.forClass(ProgramBean.class);
		try{
		criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
		programBeans=new TreeSet<ProgramBean>(getHibernateTemplate().findByCriteria(criteria));
		}catch(DataAccessException e)
		{
			e.printStackTrace();
			throw e;
			
		}
		return programBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProgramBean getProgramById(String Id) throws DataAccessException{
		List<ProgramBean> programBeans = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(ProgramBean.class);
		try{
			criteria.add(Restrictions.eq("programId", Id));
			criteria.add(Restrictions.eq("misAuditBean.status", MISConstants.MASTER_STATUS_APPROVED));
			programBeans = getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e)
		{
			e.printStackTrace();
			throw e;
			
		}
		
		return programBeans.get(0);
	}
}
