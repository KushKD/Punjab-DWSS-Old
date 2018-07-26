package com.prwss.mis.masters.scheme.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.village.dao.PrwssVillageViewBeanWithMhOh;

public class SchemeDaoImpl extends HibernateDaoSupport implements SchemeDao {

	private Logger log = Logger.getLogger(SchemeDaoImpl.class);

	

	@Override
	public boolean saveScheme(SchemeBean schemeDetailBean) throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(schemeDetailBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		
		return true;
	}

	@Override
	public boolean saveOrUpdateScheme(SchemeBean schemeDetailBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(schemeDetailBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeBean> findScheme(SchemeBean schemeBean, List<String> statusList)
			throws DataAccessException {
		List<SchemeBean> schemeBeans = new  ArrayList<SchemeBean>();
		try {
			if(MisUtility.ifEmpty(schemeBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(SchemeBean.class);
				if(MisUtility.ifEmpty(schemeBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", schemeBean.getSchemeId()));
				
				if(MisUtility.ifEmpty(schemeBean.getSchemeSource()))
					criteria.add(Restrictions.eq("schemeSource", schemeBean.getSchemeSource()));
				
				if(MisUtility.ifEmpty(schemeBean.getResolutionDate()))
					criteria.add(Restrictions.eq("resolutionDate", schemeBean.getResolutionDate()));
				
				if(MisUtility.ifEmpty(schemeBean.getConstitutionDateSLC()))
					criteria.add(Restrictions.eq("constitutionDateSLC", schemeBean.getConstitutionDateSLC()));
				
				if(MisUtility.ifEmpty(schemeBean.getSchemeUpgraded()))
					criteria.add(Restrictions.eq("schemeUpgraded", schemeBean.getSchemeUpgraded()));
				
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				schemeBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeBeans;
	}


	
	
/*	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeBean> getAllData(SchemeBean abc)
			throws DataAccessException {
		System.out.println("i m in daoimp");
		List<SchemeBean> checkbean = new ArrayList<SchemeBean>();
		try {
			System.out.println("i m in  schme Dao Imp dao -rrrrrr----"+abc.getSchemeId());
			String schemecode=abc.getSchemeId();
			DetachedCriteria criteria = DetachedCriteria.forClass(SchemeBean.class);
			 if(MisUtility.ifEmpty(schemecode)){
				 	
				 System.out.println("come in MisUtility.ifEmpty");
				 criteria.add(Restrictions.eq("schemeCode", schemecode));
				 System.out.println("after where Condition");
			 }
			 checkbean = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			 throw e;
		}
		 System.out.println("---------------------------------------------");
		 System.out.println(checkbean);
		 return checkbean;
	}
	*/
	//rohit
	
/*	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeBean> findAllData(SchemeBean schemeBean)
			throws DataAccessException {
		System.out.println("DAO-------------------------------------");
		List<SchemeBean> schemeBeans = new  ArrayList<SchemeBean>();
		try {
			if(MisUtility.ifEmpty(schemeBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(SchemeBean.class);
				if(MisUtility.ifEmpty(schemeBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", schemeBean.getSchemeId()));
				
				schemeBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeBeans;
	}
	*/
	//rohit

}
