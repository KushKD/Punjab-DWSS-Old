package com.prwss.mis.pmm.DSRBuilding;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.DSRBuilding.struts.DSRBuildingForm;
import com.prwss.mis.pmm.DSRBuilding.dao.DSRBuildingBean;

public interface DSRBuildingBO {

public List<DSRBuildingBean> findDSRBuilding(DSRBuildingForm dsrBuildingForm, List<String> statusList) throws MISException;
	
	public boolean saveDSRBuilding( DSRBuildingForm dsrBuildingForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateDSRBuilding(DSRBuildingForm dsrBuildingForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteDSRBuilding(DSRBuildingForm dsrBuildingForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postDSRBuilding(DSRBuildingForm dsrBuildingForm,  MISSessionBean misSessionBean) throws MISException;
}
