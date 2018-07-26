package com.prwss.mis.WaterConnection.struts;



import com.prwss.mis.common.exception.MISException;

public interface WaterConnectionBo {
	
	public String saveWaterConnectionData(WaterConnectionForm waterconnectionForm) throws MISException;
	public Boolean validateAadhaar(String AadhaarNumber) throws MISException;
	

}
