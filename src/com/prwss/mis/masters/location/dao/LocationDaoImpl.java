package com.prwss.mis.masters.location.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.WaterConnection.struts.WaterConnectionBean;
import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.login.dao.LoginUserLocationBean;

public class LocationDaoImpl extends HibernateDaoSupport implements LocationDao {

	Statement stmt = null;
	DataSource db = null;
	Boolean status = false;

	private MISJdcDaoImpl misJdcDaoImpl;

	public MISJdcDaoImpl getMisJdcDaoImpl() {
		return misJdcDaoImpl;
	}

	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<LocationBean> getLocationIds(String locationType) throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);

			if (MisUtility.ifEmpty(locationType)) {
				criteria.add(Restrictions.eq("locationType", locationType).ignoreCase());
			}

			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	public Set<LocationBean> getLocationNameById(String locationId) throws DataAccessException {

		Set<LocationBean> locationBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);

			if (MisUtility.ifEmpty(locationId)) {
				criteria.add(Restrictions.eq("locationId", locationId).ignoreCase());
			}

			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<LocationBean> getChildLocationIds(String parentLocationId, String locationType)
			throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("parentLocation", parentLocationId));

				if (MisUtility.ifEmpty(locationType))
					criteria.add(Restrictions.eq("locationType", locationType).ignoreCase());

				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				// criteria.addOrder(Order.desc("locationName"));
				locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<LocationBean> getChildLocationIdss(String subdivisionId) throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(subdivisionId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("locationId", subdivisionId));

				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				// criteria.addOrder(Order.desc("locationName"));
				locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<LocationBean> getChildLocationIds(String parentLocationId, List<String> locationType)
			throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("parentLocation", parentLocationId));

				if (!MisUtility.ifEmpty(locationType))
					criteria.add(Restrictions.in("locationType", locationType));

				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				// criteria.addOrder(Order.desc("locationName"));
				locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationBean> getChildLocationListIds(String parentLocationId, String locationType)
			throws DataAccessException {
		List<LocationBean> locationBeans = null;
		try {
			if (MisUtility.ifEmpty(parentLocationId)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("parentLocation", parentLocationId));

				if (MisUtility.ifEmpty(locationType))
					criteria.add(Restrictions.eq("locationType", locationType).ignoreCase());

				List<String> statusList = new ArrayList<String>();
				statusList.add(MISConstants.MASTER_STATUS_APPROVED);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
				criteria.addOrder(Order.asc("locationName"));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@Override
	public boolean saveLocation(LocationBean locationBean) throws DataAccessException {
		try {
			getHibernateTemplate().save(locationBean);
		} catch (DataAccessException e) {
			throw e;
		}

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public LocationBean getLocation(LocationBean locationBean) throws DataAccessException {
		List<LocationBean> locationBeans = null;
		LocationBean locationBean2 = null;

		try {
			if (MisUtility.ifEmpty(locationBean)) {
				DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
				criteria.add(Restrictions.eq("locationId", locationBean.getLocationId()).ignoreCase());
				// criteria.addOrder(Order.asc("locationName"));
				locationBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}

		if (!MisUtility.ifEmpty(locationBeans))
			locationBean2 = locationBeans.get(0);

		return locationBean2;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<LocationBean> getLocationIdOnTypeList(List<String> locationTypeList) throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
			System.out.println("-----Search location for " + locationTypeList.get(0));
			if (!MisUtility.ifEmpty(locationTypeList))
				criteria.add(Restrictions.in("locationType", locationTypeList));

			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			criteria.addOrder(Order.asc("locationName"));
			locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<LocationBean> getLocationBeanOnLocationIdList(List<String> locationIds) throws DataAccessException {
		Set<LocationBean> locationBeans = null;
		List<String> locationTypes = new ArrayList<String>();
		locationTypes.add("DO");
		locationTypes.add("DPMC");
		locationTypes.add("SPMC");
		locationTypes.add("CIRCLE");
		locationTypes.add("ZONE");
		locationTypes.add("SPONSER");
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);

			if (!MisUtility.ifEmpty(locationIds))
				criteria.add(Restrictions.in("locationId", locationIds));

			criteria.add(Restrictions.in("locationType", locationTypes));

			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			locationBeans = new TreeSet<LocationBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LocationBean> getLocationBeanOnLocationIdList2(List<String> locationIds) throws DataAccessException {
		List<LocationBean> locationBeans = null;
		List<String> locationTypes = new ArrayList<String>();
		locationTypes.add("DO");
		locationTypes.add("DPMC");
		locationTypes.add("SPMC");
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LocationBean.class);
			if (!MisUtility.ifEmpty(locationIds))
				criteria.add(Restrictions.in("locationId", locationIds));
			criteria.add(Restrictions.in("locationType", locationTypes));
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			criteria.add(Restrictions.in("misAuditBean.status", statusList));
			locationBeans = getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			throw e;
		}

		return locationBeans;
	}

	@Override
	public WaterConnectionBean getApplicationName(String applicationId) throws DataAccessException {
		List<WaterConnectionBean> listapplicationId = null;
		WaterConnectionBean waterConnection = null;
		try {

			listapplicationId = new ArrayList<WaterConnectionBean>();
			waterConnection = new WaterConnectionBean();
			String name = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(WaterConnectionBean.class);

			criteria.add(Restrictions.eq("application_id", Integer.parseInt(applicationId)));

			listapplicationId = getHibernateTemplate().findByCriteria(criteria);
			waterConnection = listapplicationId.get(0);
			System.out.println(listapplicationId.get(0).getApplication_number());
			System.out.println(waterConnection.toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return waterConnection;
	}

	@Override
	public String getApplicationStatus(String applicationId) throws DataAccessException {
		List<WaterConnectionBean> listapplicationId = null;
		try {

			listapplicationId = new ArrayList<WaterConnectionBean>();
			String name = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(WaterConnectionBean.class);

			criteria.add(Restrictions.eq("application_number", applicationId));

			listapplicationId = getHibernateTemplate().findByCriteria(criteria);
			System.out.println(listapplicationId.get(0).getStatus());

		} catch (DataAccessException e) {
			throw e;
		}
		return listapplicationId.get(0).getStatus();
	}

	@Override
	public WaterConnectionBean getApplicationfromId(String applicationId) throws DataAccessException {
		List<WaterConnectionBean> listapplicationId = null;
		try {

			listapplicationId = new ArrayList<WaterConnectionBean>();
			String name = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(WaterConnectionBean.class);

			criteria.add(Restrictions.eq("application_number", applicationId));

			listapplicationId = getHibernateTemplate().findByCriteria(criteria);
			// System.out.println(listapplicationId.get(0).getStatus());

		} catch (DataAccessException e) {
			throw e;
		}
		return listapplicationId.get(0);
	}

	@Override
	public List<WaterConnectionBean> getApplications(String UserId) throws DataAccessException {
		List<WaterConnectionBean> listapplicationId = null;
		try {

			listapplicationId = new ArrayList<WaterConnectionBean>();
			String name = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(WaterConnectionBean.class);

			criteria.add(Restrictions.eq("islyingwith", UserId));
			//criteria.add(Restrictions.eq("status", "In Progress"));
			criteria.addOrder(Order.desc("receiveddate"));
			criteria.addOrder(Order.desc("application_number"));

			listapplicationId = getHibernateTemplate().findByCriteria(criteria);
			System.out.println(listapplicationId.toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return listapplicationId;
	}

	@Override
	public Boolean updateWaterConnectionData(String status, String appId, String comments, String snum)
			throws DataAccessException, SQLException {

		try {
			db = misJdcDaoImpl.getDataSource();
			Connection con = db.getConnection();

			stmt = con.createStatement();
			String ss = "UPDATE prwss_main.new_connection_application " + "set status = '" + status
					+ "',  remarks_Dept = '" + comments + "',  sanctioned_Number = '" + snum
					+ "' WHERE application_number='" + appId + "'";

			System.out.println(ss);
			String sql = "UPDATE prwss_main.new_connection_application " + "set status = '" + status
					+ "',  remarks_Dept = '" + comments + "',  sanctioned_Number = '" + snum
					+ "' WHERE application_number='" + appId + "'";
			System.out.println("error message" + sql);

			stmt.executeUpdate(sql);
			System.out.println("Success");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {

			if (stmt != null) {
				stmt.close();
			}

		}

	}

	@Override
	public String getApplicationStatusComments(String applicationId) throws DataAccessException {
		List<WaterConnectionBean> listapplicationId = null;
		try {

			listapplicationId = new ArrayList<WaterConnectionBean>();
			String name = null;
			DetachedCriteria criteria = DetachedCriteria.forClass(WaterConnectionBean.class);

			criteria.add(Restrictions.eq("application_number", applicationId));

			listapplicationId = getHibernateTemplate().findByCriteria(criteria);
			System.out.println("Comments ARe:- " + listapplicationId.get(0).getRemarks_Dept());

		} catch (DataAccessException e) {
			throw e;
		}
		return listapplicationId.get(0).getRemarks_Dept();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<LoginUserBean> getUserName(List<String> userId) throws DataAccessException {
		Set<LoginUserBean> loginUserBeans=null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserBean.class);
			criteria.add(Restrictions.in("userId", userId));
			criteria.add(Restrictions.eq("roleId", "XEN"));
			loginUserBeans = new HashSet<LoginUserBean>(getHibernateTemplate().findByCriteria(criteria));
		} catch (DataAccessException e) {
			Log.debug(e.getMessage());
			throw e;
		}
		return loginUserBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserId(String locationId) throws DataAccessException {
		List<String> loginUserBeans=null;
		try{
			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserLocationBean.class);
			criteria.add(Restrictions.eq("locationId", locationId));
			criteria.setProjection(Projections.property("userId"));
			loginUserBeans = getHibernateTemplate().findByCriteria(criteria);
		}catch(DataAccessException e){
			throw e;
		}
		return loginUserBeans;
	}
}