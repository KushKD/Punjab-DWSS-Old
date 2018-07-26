/**
 * 
 */
package com.prwss.mis.masters.scheme.dao;

import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

/**
 * @author pjha
 * 
 */
public class SchemeVillageDaoImpl extends HibernateDaoSupport implements
		SchemeVillageDao {
	private HibernateTemplate hibernateTemplate;
	private Logger log = Logger.getLogger(SchemeVillageDaoImpl.class);
	BatchUpdateException bt = new BatchUpdateException();
	int count;

	// private HibernateTemplate hibernateTemplate;
	@Override
	@SuppressWarnings("unchecked")
	public List<SchemeVillageBean> findVillage(String villageId,
			List<String> status) throws DataAccessException {

		List<SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		try {
			if (MisUtility.ifEmpty(villageId)) {

				DetachedCriteria criteria = DetachedCriteria
						.forClass(SchemeVillageBean.class);

				criteria.add(Restrictions.eq("villageId", villageId));
				criteria.add(Restrictions.in("misAuditBean.status", status));
				schemeVillageBeans = getHibernateTemplate().findByCriteria(
						criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeVillageBeans;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SchemeVillageBean> findWaterWorks(String schemeId,
			String villageId, List<String> status) throws DataAccessException {

		List<SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		try {
			if (MisUtility.ifEmpty(villageId)) {

				DetachedCriteria criteria = DetachedCriteria
						.forClass(SchemeVillageBean.class);
				criteria.add(Restrictions.eq("schemeId", schemeId));

				criteria.add(Restrictions.eq("villageId", villageId));
				criteria.add(Restrictions.in("misAuditBean.status", status));
				schemeVillageBeans = getHibernateTemplate().findByCriteria(
						criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeVillageBeans;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SchemeVillageBean> findSchemeVillage(
			SchemeVillageBean schemeVillageBean, List<String> statusList,
			String[] arr) throws DataAccessException {

		List<SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		try {
			if (MisUtility.ifEmpty(schemeVillageBean)) {

				DetachedCriteria criteria = DetachedCriteria
						.forClass(SchemeVillageBean.class);
				if (MisUtility.ifEmpty(schemeVillageBean.getSchemeId())) {

					criteria.add(Restrictions.eq("schemeId",
							schemeVillageBean.getSchemeId()));
				}

				if (MisUtility.ifEmpty(schemeVillageBean.getVillageId())) {

					criteria.add(Restrictions.eq("villageId",
							schemeVillageBean.getVillageId()));
				}

				if (!MisUtility.ifEmpty(schemeVillageBean.getSchemeUpgraded())) {

					criteria.add(Restrictions.in("schemeUpgraded", arr));
				}
				if (!MisUtility.ifEmpty(statusList))

					criteria.add(Restrictions.in("misAuditBean.status",
							statusList));

				schemeVillageBeans = getHibernateTemplate().findByCriteria(
						criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeVillageBeans;
	}

	@Override
	public boolean saveSchemeVillage(List<SchemeVillageBean> schemeVillageBean)
			throws DataAccessException, MISException {

		try {

			System.out.println("in dao ++++schemeVillageBean+++++++"
					+ schemeVillageBean.toString());

			hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.flush();

			getHibernateTemplate().saveOrUpdateAll(schemeVillageBean);
			hibernateTemplate.flush();
			hibernateTemplate.clear();

		} catch (DataAccessException e) {
			// e.printStackTrace();
			System.out.println(e.getLocalizedMessage());

			log.error(e.getLocalizedMessage(), e);
			throw e;
			// throw new MISException(e);
			// throw new MISException(e);

		}

		return true;
	}

	@Override
	public boolean updateSchemeVillages(
			List<SchemeVillageBean> schemeVillageBean)
			throws DataAccessException, MISException {

		try {

			System.out.println("in dao ++++schemeVillageBean+++++++"
					+ schemeVillageBean.toString());

			Iterator<SchemeVillageBean> iterator = schemeVillageBean.iterator();
			while (iterator.hasNext()) {
				SchemeVillageBean schemeVillageBeans = (SchemeVillageBean) iterator
						.next();
				getHibernateTemplate().merge(schemeVillageBeans);
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();

			}

		} catch (DataAccessException e) {
			// e.printStackTrace();
			System.out.println(e.getLocalizedMessage());

			log.error(e.getLocalizedMessage(), e);
			throw e;
		}

		return true;
	}

	@Override
	public boolean updateSchemeVillage(SchemeVillageBean schemeVillageBean)
			throws DataAccessException {
		try {
			hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.flush();
			getHibernateTemplate().saveOrUpdate(schemeVillageBean);
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
			throw e;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeVillageBean> findSchemeVillage(
			SchemeVillageBean schemeVillageBean, List<String> statusList)
			throws DataAccessException {
		System.out.println("--------------findSchemeVillage--------------");
		List<SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		try {
			if (MisUtility.ifEmpty(schemeVillageBean)) {
				DetachedCriteria criteria = DetachedCriteria
						.forClass(SchemeVillageBean.class);
				if (MisUtility.ifEmpty(schemeVillageBean.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId",
							schemeVillageBean.getSchemeId()));
				if (MisUtility.ifEmpty(schemeVillageBean.getVillageId()))
					criteria.add(Restrictions.eq("villageId",
							schemeVillageBean.getVillageId()));
				if (MisUtility.ifEmpty(schemeVillageBean.getSchemeUpgraded())) {
					criteria.add(Restrictions.eq("schemeUpgraded",
							schemeVillageBean.getSchemeUpgraded()));
				}
				if (!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status",
							statusList));

				schemeVillageBeans = getHibernateTemplate().findByCriteria(
						criteria);

				// System.out.println("in scheme find village dao-----------------"+schemeVillageBeans);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeVillageBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchemeVillageBean> findSchemevillage(SchemeVillageBean form,
			List<String> statusList) throws DataAccessException {
		System.out.println("--------------findSchemeVillage--------------");

		List<SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>();
		try {
			if (MisUtility.ifEmpty(form)) {
				DetachedCriteria criteria = DetachedCriteria
						.forClass(SchemeVillageBean.class);
				if (MisUtility.ifEmpty(form.getSchemeId()))
					criteria.add(Restrictions.eq("schemeId", form.getSchemeId()));
				/*
				 * if(MisUtility.ifEmpty(schemeVillageBean.getVillageId()))
				 * criteria.add(Restrictions.eq("villageId",
				 * schemeVillageBean.getVillageId()));
				 * if(MisUtility.ifEmpty(schemeVillageBean
				 * .getSchemeUpgraded())){
				 * criteria.add(Restrictions.eq("schemeUpgraded",
				 * schemeVillageBean.getSchemeUpgraded())); }
				 */
				if (!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status",
							statusList));

				schemeVillageBeans = getHibernateTemplate().findByCriteria(
						criteria);

				// System.out.println("in scheme find village dao-----------------"+schemeVillageBeans);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeVillageBeans;
	}

}
