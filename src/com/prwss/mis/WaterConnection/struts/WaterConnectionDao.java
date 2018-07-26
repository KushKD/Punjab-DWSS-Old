package com.prwss.mis.WaterConnection.struts;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.tender.dao.TenderHeaderBean;


public interface WaterConnectionDao
{
	public String saveWaterConnections(WaterConnectionBean waterconnectionBean) throws DataAccessException;
	public List<WaterConnectionBean> getApplicationschangedStatus(String userid,String changedStaus) throws DataAccessException;	
	public List<WaterConnectionBean> validateAadhaar(String AadhaarNumber ) throws DataAccessException;
}
