/**
 * 
 */
package com.prwss.mis.reports.performa.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.reports.performa.PerformaFiveYearPlanBean;
import com.prwss.mis.reports.performa.PerformaMasterForm;

/**
 * @author bhsingh
 *
 */
public class PerformaMasterDaoImpl extends HibernateDaoSupport implements PerformaMasterDao {

	private Logger log = Logger.getLogger(PerformaMasterDaoImpl.class);
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PerformaMasterBean> findPerformaMaster(PerformaMasterForm performaMasterForm, List<String> statusList)
			throws DataAccessException {

		List<PerformaMasterBean> performaMasterBeans = null;
		try {
			if (MisUtility.ifEmpty(performaMasterForm)) {
				System.out.println(performaMasterForm.getSchemeId());
				DetachedCriteria criteria = DetachedCriteria.forClass(PerformaMasterBean.class);
				criteria.add(Restrictions.eq("schemeId", performaMasterForm.getSchemeId()));
				/*if (!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.eq("misAuditBean.status", statusList));
				*/
				performaMasterBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("--------after findByCriteria in PerformaMasterDaoImpl------");
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return performaMasterBeans;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PerformaMasterBean> findPerforma(PerformaMasterForm performaMasterForm, List<String> statusList)
			throws DataAccessException {

		List<PerformaMasterBean> performaMasterBeans = null;
		try {
			if (MisUtility.ifEmpty(performaMasterForm)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(PerformaMasterBean.class);
				criteria.add(Restrictions.eq("schemeId", performaMasterForm.getSchemeId()));
				criteria.add(Restrictions.eq("villageId", performaMasterForm.getVillgId()));

				/*if (!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.eq("misAuditBean.status", statusList));
				*/
				performaMasterBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("--------after findByCriteria in PerformaMasterDaoImpl------");
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return performaMasterBeans;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<PerformaMasterBean> findVillage(PerformaMasterBean performaMasterBean, List<String> statusList)
			throws DataAccessException {

		List<PerformaMasterBean> performaMasterBeans = null;
		try{
			if (MisUtility.ifEmpty(performaMasterBean)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(PerformaMasterBean.class);
				criteria.add(Restrictions.eq("schemeId", performaMasterBean.getSchemeId()));
				/*if (!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));*/
					performaMasterBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return performaMasterBeans;
	}

	@Override
	public boolean upadtePerformaHeader(List<VillagePerformaBean> performaBean)
			throws DataAccessException {
		System.out.println("inside dao---upadtePerformaHeader-------------->");
		try{
			System.out.println("length--->"+performaBean.size());
				getHibernateTemplate().saveOrUpdateAll(performaBean);
		} catch (DataAccessException e) {
			e.printStackTrace();	
			System.out.println(e.getLocalizedMessage());
			log.error(e.getLocalizedMessage(), e);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean upadtePerformaSource(List<SchemeSourcePerformaBean> performaBean) throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println("inside dao----------------->");
		try{
			System.out.println("length--->"+performaBean.size());
			for(SchemeSourcePerformaBean villagePerformaBean:performaBean){
				System.out.println("village beans----->"+villagePerformaBean.toString());
			}
				getHibernateTemplate().saveOrUpdateAll(performaBean);
				

		} catch (DataAccessException e) {
			e.printStackTrace();	
			System.out.println(e.getLocalizedMessage());
			log.error(e.getLocalizedMessage(), e);
		}
		return true;	
		
	}

	@Override
	public boolean saveSchemeBean(List<SchemeBean1> schemeBean) throws DataAccessException {
		System.out.println("inside dao--saveSchemeBean--------------->");
		try{
			System.out.println("schemeBean data--->"+schemeBean.toString());
				getHibernateTemplate().saveOrUpdateAll(schemeBean);

		} catch (DataAccessException e) {
			e.printStackTrace();	
			System.out.println(e.getLocalizedMessage());
			log.error(e.getLocalizedMessage(), e);
		}
		return true;
	}

	@Override
	public boolean saveFiveYearPlanBean(List<PerformaFiveYearPlanBean> performaFiveYearPlanBeans)
			throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println("inside dao---saveFiveYearPlanBean-------------->");
		try{
			System.out.println("length--->"+performaFiveYearPlanBeans.size());
				getHibernateTemplate().saveOrUpdateAll(performaFiveYearPlanBeans);
		} catch (DataAccessException e) {
			e.printStackTrace();	
			System.out.println(e.getLocalizedMessage());
			log.error(e.getLocalizedMessage(), e);
		}
		return true;	
	}
	
	@Override
	public List<PerformaMasterBean> findSchemeFromVillage(
			PerformaMasterBean performaBean) throws DataAccessException {
		List<PerformaMasterBean> performaMasterBeans = null;
		try {
			if (MisUtility.ifEmpty(performaBean)) {
				System.out.println(performaBean.getVillageId());
				DetachedCriteria criteria = DetachedCriteria.forClass(PerformaMasterBean.class);
				criteria.add(Restrictions.eq("villageId", performaBean.getVillageId()));
				/*if (!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.eq("misAuditBean.status", statusList));
				*/
				performaMasterBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("--------after findByCriteria in PerformaMasterDaoImpl------");
			}
		} /*catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}*/catch(Exception e){
			e.printStackTrace();
		}
		return performaMasterBeans;
	}

	@Override
	public List<SchemeSourcePerformaBean> findFiveYearPlanData(PerformaMasterForm performaMasterForm)
			throws DataAccessException {
		
		List<SchemeSourcePerformaBean> performaMasterBeans = null;
		try {
			if (MisUtility.ifEmpty(performaMasterForm)) {
				System.out.println(performaMasterForm.getSchemeId());
				DetachedCriteria criteria = DetachedCriteria.forClass(SchemeSourcePerformaBean.class);
				criteria.add(Restrictions.eq("schemeId", performaMasterForm.getSchemeId()));
				criteria.add(Restrictions.eq("villageId", performaMasterForm.getVillageId()));
				
				performaMasterBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("--------after findByCriteria in PerformaMasterDaoImpl------");
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return performaMasterBeans;
		}

	@Override
	public List<SchemeBean1> findSchemeMaster(PerformaMasterForm performaMasterForm) throws DataAccessException {
		// TODO Auto-generated method stub

		List<SchemeBean1> schemeBeans = null;
		try {
			if (MisUtility.ifEmpty(performaMasterForm)) {
				System.out.println(performaMasterForm.getSchemeId());
				DetachedCriteria criteria = DetachedCriteria.forClass(SchemeBean1.class);
				criteria.add(Restrictions.eq("schemeId", performaMasterForm.getSchemeId()));
				schemeBeans = getHibernateTemplate().findByCriteria(criteria);
				System.out.println("--------after findByCriteria in PerformaMasterDaoImpl------");
			}
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			throw e;
		}
		return schemeBeans;
	}

	

	

	@Override
	public boolean savePerformaMaster(FiveYearPerformaMaster fiveYearPerformaMaster) throws DataAccessException {
	
		try{
			if(!MisUtility.ifEmpty(fiveYearPerformaMaster.getPerformaBean())){
				getHibernateTemplate().saveOrUpdateAll(fiveYearPerformaMaster.getPerformaBean());
			}
			if(!MisUtility.ifEmpty(fiveYearPerformaMaster.getPerformaSrcBeans())){
				getHibernateTemplate().saveOrUpdateAll(fiveYearPerformaMaster.getPerformaSrcBeans());
			}
			if(!MisUtility.ifEmpty(fiveYearPerformaMaster.getSchemeBean())){
				getHibernateTemplate().saveOrUpdateAll(fiveYearPerformaMaster.getSchemeBean());
			}
			if(!MisUtility.ifEmpty(fiveYearPerformaMaster.getPerformaFiveYearPlanBeans())){
				getHibernateTemplate().saveOrUpdateAll(fiveYearPerformaMaster.getPerformaFiveYearPlanBeans());
			}
			
		}catch(DataAccessException e){
			log.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
			throw e;
		}
		return true;
	}
	
}
