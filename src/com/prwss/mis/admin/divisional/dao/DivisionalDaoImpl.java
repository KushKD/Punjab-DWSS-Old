package com.prwss.mis.admin.divisional.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class DivisionalDaoImpl extends HibernateDaoSupport implements
		DivisionalDao {

	private Logger log = Logger.getLogger(DivisionalDaoImpl.class);

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<DivisionalBean> findDivisional(DivisionalBean divisionalBean,
			List<String> statusList) throws DataAccessException {
		List<DivisionalBean> divisionalBeans = null;
		List<String> locationType = new ArrayList<String>();
		locationType.add("DO");
		locationType.add("DPMC");
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(DivisionalBean.class);
			if (MisUtility.ifEmpty(divisionalBean)) {
				if (MisUtility.ifEmpty(divisionalBean.getDivisionalId())) {
					System.out.println("--------DivisionalId"
							+ divisionalBean.getDivisionalId());
					criteria.add(Restrictions.eq("divisionalId",
							divisionalBean.getDivisionalId()));
				}
				if (MisUtility.ifEmpty(divisionalBean.getDivisionalName()))
					criteria.add(Restrictions.eq("divisionalName",
							divisionalBean.getDivisionalName()));
				if (MisUtility.ifEmpty(divisionalBean.getDistrict())
						&& MisUtility.ifEmpty(divisionalBean.getDistrict()
								.getDistrictId())) {
					System.out.println("-------------CircleId"
							+ divisionalBean.getDistrict());
					criteria.add(Restrictions.eq("district.districtId",
							divisionalBean.getDistrict().getDistrictId()));
				}
				criteria.add(Restrictions.in("isSPMC_DPMC", locationType));
				if (!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("status", statusList));
				criteria.addOrder(Order.asc("divisionalId"));
				divisionalBeans = getHibernateTemplate().findByCriteria(
						criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return divisionalBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionalBean> findDivisional(List<String> divisionalIds)
			throws DataAccessException {
		List<DivisionalBean> divisionalBeans = null;

		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(DivisionalBean.class);

			if (!MisUtility.ifEmpty(divisionalIds)) {
				criteria.add(Restrictions.in("divisionalId", divisionalIds));
				divisionalBeans = getHibernateTemplate().findByCriteria(
						criteria);
			}
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return divisionalBeans;
	}

	@Override
	public boolean saveDivisional(DivisionalBean divisionalBean)
			throws DataAccessException {

		try {
			getHibernateTemplate().save(divisionalBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return true;
	}

	@Override
	public boolean updateDivisional(DivisionalBean divisionalBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().update(divisionalBean);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return true;
	}

	@Override
	public boolean updateDivisional(List<DivisionalBean> divisionalBeans)
			throws DataAccessException {

		try {
			getHibernateTemplate().saveOrUpdateAll(divisionalBeans);
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<DivisionalBean> getDistinctDivisionalCodes()
			throws DataAccessException {
		Set<DivisionalBean> divisionalBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(DivisionalBean.class);
			Criterion cr1 = Restrictions.eq("isSPMC_DPMC", "DO");
			Criterion cr2 = Restrictions.eq("isSPMC_DPMC", "DPMC");
			Conjunction disjunction = Restrictions.conjunction();
			disjunction.add(cr2);
			disjunction.add(cr1);
			criteria.add(disjunction);

			divisionalBeans = new TreeSet<DivisionalBean>(
					getHibernateTemplate().findByCriteria(criteria));
			System.out.println("-------Divisional Bean-Size"
					+ divisionalBeans.size());
		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return divisionalBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DivisionalBean> getDivisions(String districtId,
			List<String> locationType) {
		List<DivisionalBean> divisionalBeans = null;
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		try {
			DetachedCriteria criteria = DetachedCriteria
					.forClass(DivisionalBean.class);
			criteria.add(Restrictions.eq("district.districtId", districtId));

			criteria.add(Restrictions.in("isSPMC_DPMC", locationType));
			if (!MisUtility.ifEmpty(statusList))
				criteria.add(Restrictions.in("status", statusList));
			criteria.addOrder(Order.asc("divisionalId"));
			divisionalBeans = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			log.error(e);
			throw e;
		}
		return divisionalBeans;
	}

}
