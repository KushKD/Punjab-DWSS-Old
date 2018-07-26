package com.prwss.mis.WaterConnection.struts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserLocationBean;
import com.prwss.mis.masters.village.dao.VillageBean;

public class WaterConnectionDaoImpl extends HibernateDaoSupport implements WaterConnectionDao {

	private Logger log = Logger.getLogger(WaterConnectionDaoImpl.class);
	List<WaterConnectionSequence> watersequence = null;
	List<WaterConnectionUrbanSDOMappingBean> SODMapping = null;
	List<VillageBean> SubDivisions = null;
	List<LoginUserLocationBean> userIDSubDiv = null;
	Statement stmt = null;
	DataSource db = null;

	private MISJdcDaoImpl misJdcDaoImpl;

	public MISJdcDaoImpl getMisJdcDaoImpl() {
		return misJdcDaoImpl;
	}

	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	@Override
	public String saveWaterConnections(WaterConnectionBean waterconnectionBean) throws DataAccessException {
		System.out.println(waterconnectionBean.toString());
		Boolean flag = false;
		String applicationId = null;
		String applicationName = null;
		int seqnumber = 0;

		try {
			seqnumber = getRural_UrbanSeqNum(waterconnectionBean);
			System.out.println("Sequence is: " + seqnumber);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error in Getting the Sequence");
		}

		try {
			// Incrementing the value
			seqnumber += 1;
			System.out.println("Updated Sequence is: " + seqnumber);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error while incrementing the value of sequence");
		}

		try {
			// Creating the Application Name
			applicationName = generateApplicationName(waterconnectionBean, seqnumber);

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error while getting the Appliaction Name");
		}

		try {
			// Set the value to Bean
			waterconnectionBean.setApplication_number(applicationName);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error while setting the Appliaction Number in the bean");
		}

		try {
			// Update the Sequence
			flag = updateSequence(seqnumber);
			System.out.println(flag);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// GET and SET the S LYING Field From Other Table
		try {
			// Set the value to Bean
			String isLyingWith = null;
			isLyingWith = getIsLyingWith(waterconnectionBean);
			if (MisUtility.ifEmpty(isLyingWith)) {
				waterconnectionBean.setIslyingwith(isLyingWith);
			} else {
				waterconnectionBean.setIslyingwith("");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Error while setting the Appliaction Number in the bean");
		}

		try {
			if (flag) {
				getHibernateTemplate().save(waterconnectionBean);
			} else {
				System.out.println("Not Right");
			}
		} catch (DataAccessException e) {
			throw e;
		}

		return Integer.toString(waterconnectionBean.getApplication_id());
	}

	private String getIsLyingWith(WaterConnectionBean waterconnectionBean) {
		// TODO Auto-generated method stub
		String lyingWith = null;
		String SubDivision = null;
		try {
			if (waterconnectionBean.getConnection_type() == 1) {
				// Urban
				List<WaterConnectionUrbanSDOMappingBean> getvalue = null;
				getvalue = getIsLyingWithFromDB(waterconnectionBean);
				lyingWith = getvalue.get(0).getUser_id();
				System.out.println("User ID FROM DB" + lyingWith);
				return lyingWith;

			} else {
				// Rural
				// Get Subdivison on the Basis of Village from mst_village pass
				// SUB DIV is VARCHAR STRING
				List<VillageBean> getSubDivisionOnTheBasisofVillgae = null;
				getSubDivisionOnTheBasisofVillgae = getSubDivisonViaVillage(waterconnectionBean);
				SubDivision = getSubDivisionOnTheBasisofVillgae.get(0).getSubDiv();
				System.out.println("SubDivision ID FROM DB" + SubDivision);

				// Get user on the Basis of Sub Divison from sd_user location USERID ---- pass sub Division Here
				//LoginUserLocationBean
				
				List<LoginUserLocationBean> userOnTheBasisOfSubDivision  = null;
				userOnTheBasisOfSubDivision = getUserIDViaSubDivision(SubDivision);
				lyingWith = userOnTheBasisOfSubDivision.get(0).getUserId();
				
				if(!MisUtility.ifEmpty(lyingWith)){
					return " ";
				}else{
					return lyingWith;
				}
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return null;
		}

		// return null;
	}

	private List<LoginUserLocationBean> getUserIDViaSubDivision(String subDivision) {
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(LoginUserLocationBean.class);

			criteria.add(Restrictions.eq("locationId", subDivision));

			log.debug("inside Urban\t\t" + criteria);

			userIDSubDiv = getHibernateTemplate().findByCriteria(criteria);
			System.out.println(userIDSubDiv.get(0).toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return userIDSubDiv;
	}

	private List<VillageBean> getSubDivisonViaVillage(WaterConnectionBean waterconnectionBean) {
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(VillageBean.class);

			criteria.add(Restrictions.eq("villageId", waterconnectionBean.getVillage()));

			log.debug("inside Urban\t\t" + criteria);

			SubDivisions = getHibernateTemplate().findByCriteria(criteria);
			System.out.println(SubDivisions.get(0).toString());

		} catch (DataAccessException e) {
			throw e;
		}
		return SubDivisions;
	}

	@SuppressWarnings("unchecked")
	private List<WaterConnectionUrbanSDOMappingBean> getIsLyingWithFromDB(WaterConnectionBean waterconnectionBean) {
		// TODO Auto-generated method stub
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(WaterConnectionUrbanSDOMappingBean.class);

			criteria.add(Restrictions.eq("district_id", waterconnectionBean.getDistrict()));


			SODMapping = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return SODMapping;
	}

	private Boolean updateSequence(int seqnumber) throws SQLException {
		watersequence.get(0).setWater_qual_value(Integer.toString(seqnumber));
		WaterConnectionSequence waterseq = new WaterConnectionSequence();

		waterseq.setId(watersequence.get(0).getId());
		waterseq.setWater_qual_value(watersequence.get(0).getWater_qual_value());
		waterseq.setWater_qual_area(watersequence.get(0).getWater_qual_area());
		System.out.println(waterseq.toString());
		/*
		 * try { getHibernateTemplate().update(waterseq); } catch
		 * (DataAccessException e) { throw e; }
		 */

		try {
			db = misJdcDaoImpl.getDataSource();
			Connection con = db.getConnection();

			stmt = con.createStatement();
			String sql = "UPDATE prwss_main.waterqual_sequence_connection " + "SET water_qual_value = "
					+ waterseq.getWater_qual_value() + " WHERE id=" + waterseq.getId();
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
				watersequence = null;
			}

		}

	}

	private String generateApplicationName(WaterConnectionBean waterconnectionBean, int seqUpdated) {
		// TODO Auto-generated method stub
		String applicationName = null;
		try {
			if (waterconnectionBean.getConnection_type() == 1) {
				// Urban
				applicationName = waterconnectionBean.getDistrict() + "/" + seqUpdated;

				return applicationName.toString();

			} else {
				// Rural
				applicationName = waterconnectionBean.getBlock() + "/" + seqUpdated;
				return applicationName.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return applicationName.toString();
		}

		// return null;
	}

	private int getRural_UrbanSeqNum(WaterConnectionBean waterconnectionBean) {

		if (waterconnectionBean.getConnection_type() == 1) {
			// Get Urban Value
			List<WaterConnectionSequence> getvalue = null;
			getvalue = getValueFromDB();
			System.out.println("Getting Sequence" + getvalue.toString());
			return Integer.parseInt(getvalue.get(0).getWater_qual_value());

		} else {
			List<WaterConnectionSequence> getvalue = null;
			getvalue = getValueFromDB_Rural();
			System.out.println("Getting Sequence" + getvalue.toString());
			return Integer.parseInt(getvalue.get(0).getWater_qual_value());
		}

	}

	@SuppressWarnings("unchecked")
	private List<WaterConnectionSequence> getValueFromDB_Rural() {

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(WaterConnectionSequence.class);

			criteria.add(Restrictions.eq("water_qual_area", "Rural"));

			log.debug("inside Rural\t\t" + criteria);

			watersequence = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return watersequence;
	}

	@SuppressWarnings("unchecked")
	private List<WaterConnectionSequence> getValueFromDB() {

		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(WaterConnectionSequence.class);

			criteria.add(Restrictions.eq("water_qual_area", "Urban"));

			log.debug("inside Urban\t\t" + criteria);

			watersequence = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return watersequence;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WaterConnectionBean> validateAadhaar(String AadhaarNumber) throws DataAccessException {
		List<WaterConnectionBean> waterBean = null;
		try {
			System.out.println("DAO Code" + AadhaarNumber);

			DetachedCriteria criteria = DetachedCriteria.forClass(WaterConnectionBean.class);

			criteria.add(Restrictions.eq("consumer_adhaar", Long.parseLong(AadhaarNumber)));

			waterBean = new ArrayList<WaterConnectionBean>();
			log.debug("inside Urban\t\t" + criteria);

			waterBean = getHibernateTemplate().findByCriteria(criteria);

		} catch (DataAccessException e) {
			throw e;
		}
		return waterBean;
	}

	@Override
	public List<WaterConnectionBean> getApplicationschangedStatus(
			String userid, String changedStaus) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
