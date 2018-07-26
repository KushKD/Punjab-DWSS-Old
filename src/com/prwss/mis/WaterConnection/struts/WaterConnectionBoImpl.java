package com.prwss.mis.WaterConnection.struts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MisUtility;

public class WaterConnectionBoImpl implements WaterConnectionBo{
	
	private WaterConnectionDao waterConnectionDao;
	
	

	public WaterConnectionDao getWaterConnectionDao() {
		return waterConnectionDao;
	}




	public void setWaterConnectionDao(WaterConnectionDao waterConnectionDao) {
		this.waterConnectionDao = waterConnectionDao;
	}




	@Override
	public String saveWaterConnectionData(WaterConnectionForm waterconnectionForm) throws MISException {
		
		
		String status = null;
		WaterConnectionBean waterConnectionBeans = null;

		try {
			System.out.println("Water Commission form-------" + waterconnectionForm.toString());
			waterConnectionBeans = new WaterConnectionBean();
			 waterConnectionBeans = populateWaterConnectionBeanFromWaterConnectionForm(waterconnectionForm);
			if(MisUtility.ifEmpty(waterConnectionBeans)){
				System.out.println("inside if in bo before save ---------");
			
				
				status = waterConnectionDao.saveWaterConnections(waterConnectionBeans);
				System.out.println("after if in bo after save ---------");
				waterConnectionBeans = null;
			}

		} catch (DataAccessException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
			throw new MISException(e);
		}

		//return status;
		
		
		
		
		
		return status;
	}

	
	
	
	private WaterConnectionBean populateWaterConnectionBeanFromWaterConnectionForm(WaterConnectionForm waterconnectionForm){
			
		WaterConnectionBean waterConnectionBean  = new WaterConnectionBean();
			try {
				
				
				//application_number
				//CAtegory
				
				System.out.println(MisUtility.ifEmpty(waterconnectionForm.getRural()));
				System.out.println(MisUtility.ifEmpty(waterconnectionForm.getUrban()));
					
				if(MisUtility.ifEmpty(waterconnectionForm.getRural())){
					if(waterconnectionForm.getRural().equalsIgnoreCase("rural")){
						waterConnectionBean.setConnection_type(0);
					}
				}
				
				if(MisUtility.ifEmpty(waterconnectionForm.getUrban())){
					if(waterconnectionForm.getUrban().equalsIgnoreCase("urban")){
						waterConnectionBean.setConnection_type(1);
					}
				}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getDistrictId())){
						waterConnectionBean.setDistrict(waterconnectionForm.getDistrictId());
					}else{
						waterConnectionBean.setDistrict(waterconnectionForm.getDistrictIdurban());
					}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getBlockId())){
						waterConnectionBean.setBlock(waterconnectionForm.getBlockId());
					}else{
						waterConnectionBean.setBlock("");
					}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getVillageId())){
						waterConnectionBean.setVillage(waterconnectionForm.getVillageId());
					}else{
						waterConnectionBean.setVillage("");
					}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getWardno())){
						waterConnectionBean.setConnection_ward(waterconnectionForm.getWardno());
					}else{
						waterConnectionBean.setConnection_ward("");
					}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getFirstname())){
						waterConnectionBean.setConsumer_name(waterconnectionForm.getFirstname());
					}else{
						waterConnectionBean.setConsumer_name("");
					}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getFh_name())){
						waterConnectionBean.setFather_husband_name(waterconnectionForm.getFh_name());
					}else{
						waterConnectionBean.setFather_husband_name("");
					}
					
					
					
					if(MisUtility.ifEmpty(waterconnectionForm.getMobile())){
						System.out.println(Long.parseLong(waterconnectionForm.getMobile())); 
						waterConnectionBean.setMobile_number(Long.parseLong(waterconnectionForm.getMobile().trim()));
					}else{
						waterConnectionBean.setMobile_number(Long.parseLong("0"));
					}
					if(MisUtility.ifEmpty(waterconnectionForm.getCategory())){
						waterConnectionBean.setConsumer_category(waterconnectionForm.getCategory());
					}else{
						waterConnectionBean.setConsumer_category("NA");
					}
					if(MisUtility.ifEmpty(waterconnectionForm.getPoiType())){
						waterConnectionBean.setProof_of_address((waterconnectionForm.getPoiType()));
					}else{
						waterConnectionBean.setProof_of_address("");
					}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getPoi_number())){
						waterConnectionBean.setProof_number((waterconnectionForm.getPoi_number()));
					}else{
						waterConnectionBean.setProof_number("");
					}
					
					
					if(MisUtility.ifEmpty(waterconnectionForm.getProofOfIdentity())){
						waterConnectionBean.setProof_of_identity((waterconnectionForm.getProofOfIdentity()));
					}else{
						waterConnectionBean.setProof_of_identity("");
					}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getPoiNumber())){
						waterConnectionBean.setProof_of_identity_number((waterconnectionForm.getPoiNumber()));
					}else{
						waterConnectionBean.setProof_of_identity_number("");
					}
					
					
					
					
					
					if(MisUtility.ifEmpty(waterconnectionForm.getAddress())){
						waterConnectionBean.setConnection_address((waterconnectionForm.getAddress()));
					}else{
						waterConnectionBean.setConnection_address("NA");
					}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getAadhaarNumber())){
						waterConnectionBean.setConsumer_adhaar(Long.parseLong(waterconnectionForm.getAadhaarNumber()));
					}else{
						waterConnectionBean.setConsumer_adhaar(Long.parseLong("0"));
					}
					
					
					
					if(MisUtility.ifEmpty(waterconnectionForm.getPincode())){
						waterConnectionBean.setConnection_pincode((Integer.parseInt(waterconnectionForm.getPincode())));
					}else{
						waterConnectionBean.setConnection_pincode(0);
					}
					
					
					
					waterConnectionBean.setStatus("In Progress");
					
					// Create an instance of SimpleDateFormat used for formatting 
					// the string representation of date (month/day/year)
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
					// Get the date today using Calendar object.
					Date today = Calendar.getInstance().getTime();        
					// Using DateFormat format method we can create a string 
					// representation of a date with the defined format.
					String reportDate = df.format(today);

					// Print what date is today!
					System.out.println("Report Date: " + reportDate);
					waterConnectionBean.setReceiveddate(MisUtility.convertStringSqlDate(reportDate));
					

					if(MisUtility.ifEmpty(waterconnectionForm.getRemarks_Dept())){
						waterConnectionBean.setRemarks_Dept(waterconnectionForm.getRemarks_Dept());
					}else{
						waterConnectionBean.setRemarks_Dept(" ");
					}
					
					if(MisUtility.ifEmpty(waterconnectionForm.getSanctioned_Number())){
						waterConnectionBean.setSanctioned_Number(waterconnectionForm.getSanctioned_Number());
					}else{
						waterConnectionBean.setSanctioned_Number(" ");
					}
					return waterConnectionBean;
					
				}
			catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
			}
			
			return waterConnectionBean;
		}



	@Override
	public Boolean validateAadhaar(String AadhaarNumber) throws MISException {
		// TODO Auto-generated method stub
		System.out.println("Aadhar Number inside BO is:- " + AadhaarNumber  );
		List<WaterConnectionBean> AadharList;
	
			
			AadharList = waterConnectionDao.validateAadhaar(AadhaarNumber);
			
			if(AadharList.isEmpty()){
				return true;
				
			}else{
				return false;
			}
		
		
	}




}
