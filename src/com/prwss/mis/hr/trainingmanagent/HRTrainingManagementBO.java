package com.prwss.mis.hr.trainingmanagent;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.hr.trainingmanagent.struts.HRTrainingManagementForm;

public interface HRTrainingManagementBO {
	
	public List<HRTrainingManagementBean> findHRTrainingManagementBean(HRTrainingManagementForm hrTrainingManagementForm, List<String> statusList) throws MISException;
	public boolean updateHRTrainingManagementBean(HRTrainingManagementForm hrTrainingManagementForm, MISSessionBean misSessionBean) throws MISException;
	public boolean postHRTrainingManagementBean(HRTrainingManagementForm hrTrainingManagementForm, MISSessionBean misSessionBean) throws MISException;
	
}
