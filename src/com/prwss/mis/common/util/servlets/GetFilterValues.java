package com.prwss.mis.common.util.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.prwss.mis.WaterConnection.struts.WaterConnectionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.login.dao.LoginUserLocationBean;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.masters.item.dao.ItemBean;
import com.prwss.mis.masters.item.dao.ItemDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.store.dao.StoreBean;
import com.prwss.mis.masters.store.dao.StoreDao;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.masters.village.dao.VillageDao;
import com.prwss.mis.tender.contract.dao.ContractDao;
import com.prwss.mis.tender.contract.dao.ContractHeaderBean;

public class GetFilterValues extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1738910969424259354L;
	private LocationDao locationDao;
	private VillageDao villageDao;
	private StoreDao storeDao;
	private ContractDao contractDao;
	private ItemDao itemDao;
	// private UnitOfMeasurementDao unitOfMeasurementDao;
	private EmployeeDao employeeDao;
	private Logger log = Logger.getLogger(GetFilterValues.class);

	private StringBuffer getBlock(String locationId) {
		StringBuffer buffer = new StringBuffer();
		try {

			LocationBean locationBean = new LocationBean();
			locationBean.setLocationId(locationId);
			locationBean = locationDao.getLocation(locationBean);
			buffer.append("<option value='' selected>");
			buffer.append("Select Block");
			buffer.append("</option>");
			Set<LocationBean> blockList = locationDao.getChildLocationIds(
					locationBean.getParentLocation(), "BLOCK");
			for (LocationBean locationBean2 : blockList) {
				buffer.append("<option value=\"")
						.append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName()).append(" - (")
						.append(locationBean2.getLocationId()).append(")");
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer getBlockFromDistrict(String locationId) {
		StringBuffer buffer = new StringBuffer();
		try {
			// System.out.println("DistrictId :"+locationId);
			buffer.append("<option value='' selected>");
			buffer.append("Select Block");
			buffer.append("</option>");
			Set<LocationBean> blockList = locationDao.getChildLocationIds(
					locationId, "BLOCK");
			for (LocationBean locationBean2 : blockList) {
				buffer.append("<option value=\"")
						.append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName()).append(" - (")
						.append(locationBean2.getLocationId()).append(")");
				buffer.append("</option>");
			}
			// System.out.println("Buffer: "+buffer.toString());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer getVillage1(String id) {
		StringBuffer buffer = new StringBuffer();
		try {
			/*
			 * LocationBean locationBean = new LocationBean();
			 * locationBean.setLocationId(id); locationBean =
			 * locationDao.getLocation(locationBean);
			 * if(locationBean.getLocationType().equalsIgnoreCase("BLOCK")){
			 * buffer.append("<option value='' selected>");
			 * buffer.append("Select"); buffer.append("</option>");
			 * List<LocationBean> villageList =
			 * locationDao.getChildLocationListIds(locationBean.getLocationId(),
			 * "VILLAGE"); Collections.sort(villageList); for (LocationBean
			 * locationBean2 : villageList) {
			 * buffer.append("<option value=\"").append
			 * (locationBean2.getLocationId()).append("\">");
			 * buffer.append(locationBean2
			 * .getLocationName()).append(" - (").append
			 * (locationBean2.getLocationId()).append(")");
			 * buffer.append("</option>"); }
			 * 
			 * }
			 */

			VillageBean villageBean = new VillageBean();
			villageBean.setBlockId(id);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			List<VillageBean> villageList = villageDao.findVillage1(
					villageBean, statusList);
			// Collections.sort(villageList);
			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");
			for (VillageBean villageBean2 : villageList) {
				buffer.append("<option value=\"")
						.append(villageBean2.getVillageId()).append("\">");
				if (villageBean2.getHabitationType().equals("MH")) {
					buffer.append(villageBean2.getVillageName()).append(" - (")
							.append(villageBean2.getVillageId()).append(")");
				} else {
					buffer.append(villageBean2.getVillageName())
							.append(" - (")
							.append(villageBean2.getVillageId())
							.append(")"
									+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
									+ villageBean2.getParentHabitationName()
									+ "[" + "MH" + "]");
				}
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		System.out.println("before return ---->" + buffer.toString());
		return buffer;
	}

	private StringBuffer getBlocks(String locationId) {

		System.out.println("inside");
		StringBuffer buffer = new StringBuffer();
		try {

			LocationBean locationBean = new LocationBean();
			locationBean.setLocationId(locationId);
			// locationBean = locationDao.getLocation(locationBean);
			buffer.append("<option value='' selected>");
			buffer.append("Select Block");
			buffer.append("</option>");
			Set<LocationBean> blockList = locationDao.getChildLocationIds(
					locationId, "block");
			for (LocationBean locationBean2 : blockList) {
				buffer.append("<option value=\"")
						.append(locationBean2.getLocationId()).append("\">");
				buffer.append(locationBean2.getLocationName()).append(" - (")
						.append(locationBean2.getLocationId()).append(")");
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;
	}

	private StringBuffer getVillage(String id) {
		StringBuffer buffer = new StringBuffer();
		try {
			/*
			 * LocationBean locationBean = new LocationBean();
			 * locationBean.setLocationId(id); locationBean =
			 * locationDao.getLocation(locationBean);
			 * if(locationBean.getLocationType().equalsIgnoreCase("BLOCK")){
			 * buffer.append("<option value='' selected>");
			 * buffer.append("Select"); buffer.append("</option>");
			 * List<LocationBean> villageList =
			 * locationDao.getChildLocationListIds(locationBean.getLocationId(),
			 * "VILLAGE"); Collections.sort(villageList); for (LocationBean
			 * locationBean2 : villageList) {
			 * buffer.append("<option value=\"").append
			 * (locationBean2.getLocationId()).append("\">");
			 * buffer.append(locationBean2
			 * .getLocationName()).append(" - (").append
			 * (locationBean2.getLocationId()).append(")");
			 * buffer.append("</option>"); }
			 * 
			 * }
			 */

			VillageBean villageBean = new VillageBean();
			villageBean.setBlockId(id);
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			List<VillageBean> villageList = villageDao.findVillage(villageBean,
					statusList);
			// Collections.sort(villageList);
			buffer.append("<option value='' selected>");
			buffer.append("Select Village");
			buffer.append("</option>");
			for (VillageBean villageBean2 : villageList) {
				buffer.append("<option value=\"")
						.append(villageBean2.getVillageId()).append("\">");
				if (villageBean2.getHabitationType().equals("MH")) {
					buffer.append(villageBean2.getVillageName()).append(" - (")
							.append(villageBean2.getVillageId()).append(")");
				} else {
					buffer.append(villageBean2.getVillageName())
							.append(" - (")
							.append(villageBean2.getVillageId())
							.append(")"
									+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
									+ villageBean2.getParentHabitationName()
									+ "[" + "MH" + "]");
				}
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		System.out.println("before return ---->" + buffer.toString());
		return buffer;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(req.getSession()
							.getServletContext());
			this.locationDao = (LocationDao) webApplicationContext
					.getBean("locationDao");
			this.villageDao = (VillageDao) webApplicationContext
					.getBean("villageDao");
			this.itemDao = (ItemDao) webApplicationContext.getBean("itemDao");
			StringBuffer buffer = new StringBuffer();
			System.out.println("In GetFilterValues");
			if (MisUtility.ifEmpty(req.getParameter("locationId"))) {
				buffer = getBlock((String) req.getParameter("locationId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("blockId"))) {
				buffer = getVillage((String) req.getParameter("blockId"));
			}
			// getVillage1

			if (MisUtility.ifEmpty(req.getParameter("blockId1"))) {
				System.out
						.println("req.getParameter('blockId1')------------------->"
								+ req.getParameter("blockId1"));
				buffer = getVillage1((String) req.getParameter("blockId1"));
			}
			if (MisUtility.ifEmpty(req.getParameter("zoneId"))) {
				buffer = getCircleId((String) req.getParameter("zoneId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("circleId"))) {
				buffer = getDistrictId((String) req.getParameter("circleId"));
			}

			if (MisUtility.ifEmpty(req.getParameter("district_Id"))) {
				buffer = getDivisonalId((String) req
						.getParameter("district_Id"));
			}

			if (MisUtility.ifEmpty(req.getParameter("districtId11"))) {

				// if()
				System.out.println("inside distruicr23784t3");
				buffer = getDivisonalName((String) req
						.getParameter("districtId11"));
			}
			
			if (MisUtility.ifEmpty(req.getParameter("locationIdsss"))) {
				buffer = getUser((String) req
						.getParameter("locationIdsss"));
			}
			
			// districtId
			if (MisUtility.ifEmpty(req.getParameter("divisionId"))) {
				buffer = getSubDivisonalId((String) req
						.getParameter("divisionId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("getSubDivisonalId"))) {
				buffer = getSubDivisonalId2((String) req
						.getParameter("getSubDivisonalId"));
			}
			if(MisUtility.ifEmpty(req.getParameter("locationIds"))){
				buffer = getBlocks((String)req.getParameter("locationIds"));				
			}

			// getSubDivisonalId
			// Water Connection
			if (MisUtility.ifEmpty(req.getParameter("applicationId"))) {
				System.out
						.println(req.getParameter("applicationId").toString());
				buffer = fetchapplicationname(req.getParameter("applicationId")
						.toString().trim());
			}
			
			//LyingWithSubDiv  Bhrama Shankar Code Starts
			/*String lyingWithDepartment = new String();
			if (MisUtility.ifEmpty(req.getParameter("LyingWithSubDiv"))) {
				System.out
						.println(req.getParameter("LyingWithSubDiv").toString());
				lyingWithDepartment = fetchapplicationLyingWithname(req.getParameter("LyingWithSubDiv"));
				System.out
				.println("LyingWithSubDivision:::"+lyingWithDepartment);
			}
			*/
			//LyingWithSubDiv  Bhrama Shankar Code Ends
			
			if (MisUtility.ifEmpty(req.getParameter("applicationIdStatus"))) {
				System.out.println(req.getParameter("applicationIdStatus")
						.toString());
				buffer = fetchapplicationstatus(req
						.getParameter("applicationIdStatus").toString().trim());
			}
			
			if(MisUtility.ifEmpty(req.getParameter("applicationIdStatusComments"))){
				System.out.println(req.getParameter("applicationIdStatusComments").toString());
				buffer = fetchapplicationstatusComments(req.getParameter("applicationIdStatusComments").toString().trim());
			}
			if (MisUtility.ifEmpty(req.getParameter("poa"))) {
				System.out.println(req.getParameter("poa").toString());
				buffer = fetchapplicationstatuspoa(req.getParameter("poa")
						.toString().trim());
			}
			//poi
			if (MisUtility.ifEmpty(req.getParameter("poi"))) {
				System.out.println(req.getParameter("poi").toString());
				buffer = fetchapplicationstatuspoi(req.getParameter("poi")
						.toString().trim());
			}
			
			
			
			if (MisUtility.ifEmpty(req
					.getParameter("applicationIdStatusChanged"))) {
				System.out.println(req.getParameter(
						"applicationIdStatusChanged").toString());
				System.out.println(req.getParameter("comments").toString());
				String appId = null;
				String comments = null;
				String snum = null;
				snum = req.getParameter("snum").toString();
				appId = req.getParameter("appID").toString().trim();
				comments = req.getParameter("comments").toString();
				buffer = changeApplicationStatus(req.getParameter("applicationIdStatusChanged"),appId,comments,snum);
			}

			if (MisUtility.ifEmpty(req.getParameter("districtId"))) {
				if (MisUtility.ifEmpty(req.getParameter("type"))) {
					String type = req.getParameter("type");
					if (type.equals("withDPMC")) {
						buffer = getDivisionalOfficeDPMCId((String) req
								.getParameter("districtId"));
					} else if (type.equals("website")) {
						// System.out.println("In website");
						buffer = getBlockFromDistrict((String) req
								.getParameter("districtId"));
					} else if (type.equals("publicreport")) {
						buffer = getDivisionalOfficeDPMCIdForReport((String) req
								.getParameter("districtId"));

					}
				} else {
					buffer = getDivisionalOfficeId((String) req
							.getParameter("districtId"));
				}
			}
			if (MisUtility.ifEmpty(req.getParameter("locationStoreId"))) {
				this.storeDao = (StoreDao) webApplicationContext
						.getBean("storeDao");
				buffer = getStore((String) req.getParameter("locationStoreId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("locationContractId"))) {
				this.contractDao = (ContractDao) webApplicationContext
						.getBean("contractDao");
				buffer = getContracts((String) req
						.getParameter("locationContractId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("groupId"))) {
				buffer = getItem((String) req.getParameter("groupId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("itemId"))) {
				buffer = getUOM((String) req.getParameter("itemId"));
			}
			if (MisUtility.ifEmpty(req.getParameter("issuedType"))) {
				this.employeeDao = (EmployeeDao) webApplicationContext
						.getBean("employeeDao");
				buffer = getIssuedTo((String) req.getParameter("issuedType"),
						(String) req.getParameter("locationId"));
			}

			PrintWriter out = resp.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				// out.print(buffer.substring(0, buffer.length() - 1));
				out.print(buffer.toString());
			}
		} catch (BeansException e) {
			log.error(e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

	/*
	 * private StringBuffer getBlockfromSubDivisonalId(String parameter) {
	 * System.out.println("Say My name 1" + parameter); Set<LocationBean>
	 * locationBeans = null; StringBuffer buffer = new StringBuffer(); try {
	 * if(MisUtility.ifEmpty(parameter)){
	 * 
	 * locationBeans = locationDao.getChildLocationIds(parameter, "BLOCK");
	 * buffer.append("<option value='' selected>");
	 * buffer.append("Select Block"); buffer.append("</option>"); for
	 * (LocationBean locationBean : locationBeans) {
	 * buffer.append("<option value=\""
	 * ).append(locationBean.getLocationId()).append("\">");
	 * buffer.append(locationBean
	 * .getLocationName()+MISConstants.LABEL_VALUE_BEAN_SEPARATOR
	 * +locationBean.getLocationId()); buffer.append("</option>"); } } } catch
	 * (DataAccessException e) { log.error(e); e.printStackTrace(); } catch
	 * (Exception e) { log.error(e); e.printStackTrace(); } return buffer; }
	 */

	private StringBuffer changeApplicationStatus(String trim ,String ppId ,String comments ,String snum) throws DataAccessException, SQLException {
		// TODO Auto-generated method stub
		System.out.println(trim);
		System.out.println(ppId);
				Boolean status = null;
				StringBuffer response = null;
				response = new StringBuffer();
				
					status = locationDao.updateWaterConnectionData(trim , ppId ,comments, snum);
				
					if(status){
						return response.append("Application Status Updated Successfully");
					}else{
						return response.append("Something Wrong happened . Please Check Application Logs ");
					}
				
				
	}
	private StringBuffer fetchapplicationstatusComments(String trim) {
		// TODO Auto-generated method stub
				String status = null;
				StringBuffer response = null;
				response = new StringBuffer();
				
					status = locationDao.getApplicationStatusComments(trim);
				
				System.out.println("Buffer Value"+status);
				
				return response.append(status);
	}
	
	private StringBuffer fetchapplicationstatus(String trim) {
		// TODO Auto-generated method stub
		String status = null;
		StringBuffer response = null;
		response = new StringBuffer();

		status = locationDao.getApplicationStatus(trim);

		System.out.println("Buffer Value" + status);

		return response.append(status);
	}

	private StringBuffer fetchapplicationstatuspoa(String trim) {
		// TODO Auto-generated method stub
		WaterConnectionBean status = null;
		StringBuffer response = null;
		response = new StringBuffer();

		status = locationDao.getApplicationName(trim);

		System.out.println("Buffer Value" + status.getProof_of_address());

		return response.append(status.getProof_of_address());
	}
	
	
	//fetchapplicationstatuspoi
	private StringBuffer fetchapplicationstatuspoi(String trim) {
		// TODO Auto-generated method stub
		WaterConnectionBean status = null;
		StringBuffer response = null;
		response = new StringBuffer();

		status = locationDao.getApplicationName(trim);

		System.out.println("Buffer Value" + status.getProof_of_identity());

		return response.append(status.getProof_of_identity());
	} 

	@SuppressWarnings("null")
	private StringBuffer fetchapplicationname(String parameter) {
		// TODO Auto-generated method stub
		WaterConnectionBean status = null;
		StringBuffer response = null;
		response = new StringBuffer();

		status = locationDao.getApplicationName(parameter);

		System.out.println("Buffer Value" + status.getApplication_number());

		return response.append(status.getApplication_number());
	}
	
	/**
	 * Bhrama Shankar Code
	 * @param issuedType
	 * @param locationId
	 * @return
	 */
	/*@SuppressWarnings("null")
	private String fetchapplicationLyingWithname(String parameter) {
		// TODO Auto-generated method stub
		
		WaterConnectionBean status = null;
		String lyingWithDiv = new String();
		WaterConnectionBoImpl wconnBO = new WaterConnectionBoImpl();
		
		try {
			lyingWithDiv = wconnBO.getLyingwithSubDivision(parameter);
		} catch (MISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return lyingWithDiv;
		return lyingWithDiv;
	}*/
	
	private StringBuffer getIssuedTo(String issuedType, String locationId) {
		System.out.println("i am here::: " + issuedType + ":: " + locationId);
		StringBuffer buffer = new StringBuffer();
		Set<LocationBean> locationBeans = null;
		Set<EmployeeBean> employeeBeans = null;
		List<String> statusList = new ArrayList<String>();
		List<String> typeList = new ArrayList<String>();
		typeList.add(MISConstants.MIS_LOCATION_TYPE_DPMC);
		typeList.add(MISConstants.MIS_LOCATION_TYPE_DO);
		typeList.add(MISConstants.MIS_LOCATION_TYPE_SPMC);
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);

		try {

			if ((issuedType.equalsIgnoreCase(MISConstants.ISSUE_TO_EMPLOYEE))) {
				if (MisUtility.ifEmpty(locationId)) {
					employeeBeans = employeeDao.getDistinctEmployeeIds(
							locationId, statusList);
					if (!MisUtility.ifEmpty(employeeBeans)) {
						buffer.append("<option value='' selected>");
						buffer.append("Select");
						buffer.append("</option>");
						for (EmployeeBean bean : employeeBeans) {

							buffer.append("<option value=\"")
									.append(bean.getEmployeeId()).append("\">");
							buffer.append(bean.getEmployeeName() + "-" + "("
									+ bean.getEmployeeId() + ")");
							buffer.append("</option>");
						}
					}

				}
			} else if ((issuedType
					.equalsIgnoreCase(MISConstants.ISSUE_TO_OFFICE))) {
				locationBeans = locationDao.getLocationIdOnTypeList(typeList);
				if (!MisUtility.ifEmpty(locationBeans)) {
					buffer.append("<option value='' selected>");
					buffer.append("Select");
					buffer.append("</option>");
					for (LocationBean bean : locationBeans) {
						buffer.append("<option value=\"")
								.append(bean.getLocationId()).append("\">");
						buffer.append(bean.getLocationName() + "-" + "("
								+ bean.getLocationId() + ")");
						buffer.append("</option>");
					}

				}

			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			log.error(e);
		}

		return buffer;
	}

	private StringBuffer getUOM(String itemId) {
		StringBuffer buffer = new StringBuffer();
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");

			ItemBean itemBean = new ItemBean();
			itemBean.setItemId(itemId);

			List<String> statusList = new ArrayList<String>();

			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);

			List<ItemBean> itemBeans = itemDao.findItem(itemBean, statusList);

			for (ItemBean itemBean1 : itemBeans) {
				buffer.append("<option value=\"")
						.append(itemBean1.getUnitOfMeasurement()
								.getMeasurementId()).append("\">");
				System.out.println("UOM " + itemBean1.getUnitOfMeasurement());
				buffer.append(itemBean1.getUnitOfMeasurement()
						.getMeasurementId());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
		return buffer;

	}

	public StringBuffer getCircleId(String zoneId) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(zoneId)) {
				locationBeans = locationDao.getChildLocationIds(zoneId,
						"CIRCLE");
				buffer.append("<option value='' selected>");
				buffer.append("Select Circle");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName()
							+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		return buffer;
	}

	public StringBuffer getDistrictId(String circleId) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(circleId)) {
				locationBeans = locationDao.getChildLocationIds(circleId,
						"DISTRICT");
				buffer.append("<option value='' selected>");
				buffer.append("Select District");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName()
							+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	// KD WOrking

	public StringBuffer getDivisonalId(String distictID) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(distictID)) {
				locationBeans = locationDao
						.getChildLocationIds(distictID, "DO");
				buffer.append("<option value='' selected>");
				buffer.append("Select Division");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName()
							+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getDivisonalName(String distictID) throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(distictID)) {
				locationBeans = locationDao
						.getChildLocationIds(distictID, "DO");
				buffer.append("<option value='' selected>");
				buffer.append("Select Division");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationName())
							.append("\">");
					buffer.append(locationBean.getLocationName());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getSubDivisonalId(String divisionalId)
			throws MISException {
		System.out.println("inside getSubDivisonalId------->");
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(divisionalId)) {
				locationBeans = locationDao.getChildLocationIds(divisionalId,
						"Sub-Division");
				buffer.append("<option value=''>");
				buffer.append("Select Sub Division");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName()
							+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
			System.out.println("buffer---->" + buffer.toString());
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getSubDivisonalId2(String divisionalId)
			throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(divisionalId)) {
				locationBeans = locationDao.getChildLocationIds(divisionalId,
						"Sub-Division");
				buffer.append("<option value=''>");
				buffer.append("Select Sub Division");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName()
							+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
			System.out.println("buffer---->" + buffer.toString());
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getDivisionalOfficeId(String districtId)
			throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(districtId)) {
				locationBeans = locationDao.getChildLocationIds(districtId,
						"DO");
				buffer.append("<option value='' selected>");
				buffer.append("Select Division/SPMC");
				buffer.append("</option>");
				buffer.append("<option value='SPMC'>");
				buffer.append("SPMC");
				buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName()
							+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getDivisionalOfficeDPMCId(String districtId)
			throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(districtId)) {
				List<String> locationTypeList = new ArrayList<String>();
				locationTypeList.add("DO");
				locationTypeList.add("DPMC");
				locationTypeList.add("SPMC");
				locationBeans = locationDao.getChildLocationIds(districtId,
						locationTypeList);
				buffer.append("<option value='' selected>");
				buffer.append("Select Division/SPMC/DPMC");
				buffer.append("</option>");
				// buffer.append("<option value='SPMC'>");
				// buffer.append("SPMC");
				// buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName()
							+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
							+ locationBean.getLocationId());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getDivisionalOfficeDPMCIdForReport(String districtId)
			throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (MisUtility.ifEmpty(districtId)) {
				List<String> locationTypeList = new ArrayList<String>();
				locationTypeList.add("DO");
				locationBeans = locationDao.getChildLocationIds(districtId,
						locationTypeList);
				buffer.append("<option value='' selected>");
				buffer.append("Select Division");
				buffer.append("</option>");
				// buffer.append("<option value='SPMC'>");
				// buffer.append("SPMC");
				// buffer.append("</option>");
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName());
					buffer.append("</option>");
				}
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return buffer;
	}

	public StringBuffer getStore(String locationId) throws MISException {
		StringBuffer buffer = new StringBuffer();
		try {

			// System.out.println("store------------------");

			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");

			Set<StoreBean> storeBeans = storeDao
					.getDistinctStoreCodes(locationId);
			for (StoreBean storeBean : storeBeans) {
				buffer.append("<option value=\"")
						.append(storeBean.getStoreId()).append("\">");
				buffer.append(storeBean.getStoreId())
						.append(MISConstants.LABEL_VALUE_BEAN_SEPARATOR)
						.append(storeBean.getStoreName());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;

	}

	public StringBuffer getContracts(String locationId) throws MISException {
		StringBuffer buffer = new StringBuffer();
		try {

			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");

			Set<ContractHeaderBean> contractHeaderBeans = contractDao
					.getContractCodes(locationId);
			for (ContractHeaderBean contractHeaderBean : contractHeaderBeans) {
				buffer.append("<option value=\"")
						.append(contractHeaderBean.getContractId())
						.append("\">");
				buffer.append(contractHeaderBean.getContractId());
				buffer.append("</option>");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;

	}

	public StringBuffer getItem(String itemGroupId) throws MISException {
		StringBuffer buffer = new StringBuffer();
		try {

			// System.out.println("contract------------------");
			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");

			Set<ItemBean> itemBeans = itemDao.getDistinctItemCodes(itemGroupId,
					null);
			for (ItemBean itemBean : itemBeans) {
				buffer.append("<option value=\"").append(itemBean.getItemId())
						.append("\">");
				buffer.append(itemBean.getItemId()).append("-(")
						.append(itemBean.getItemName()).append(")");
				buffer.append("</option>");
			}
			// System.out.println(buffer);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;

	}
	public StringBuffer getUser(String locationId) throws MISException {
		StringBuffer buffer = new StringBuffer();
		try {

			// System.out.println("contract------------------");
			buffer.append("<option value='' selected>");
			buffer.append("Select");
			buffer.append("</option>");
			List<String> loginUserLocationBeans=locationDao.getUserId(locationId);
			if(!MisUtility.ifEmpty(loginUserLocationBeans)){
				Set<LoginUserBean> loginUserBeans = locationDao.getUserName(loginUserLocationBeans);
				for (LoginUserBean loginUserBean : loginUserBeans) {
					buffer.append("<option value=\"").append(loginUserBean.getUserId())
							.append("\">");
					buffer.append(loginUserBean.getUserId()).append("-(")
							.append(loginUserBean.getUserName()).append(")");
					buffer.append("</option>");
				}
			}
			// System.out.println(buffer);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return buffer;

	}
	
	
}