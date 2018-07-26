package com.prwss.mis.ccdu.cb;

import java.util.List;

import com.prwss.mis.ccdu.cb.dao.TrainingProgressBean;
import com.prwss.mis.ccdu.cb.struts.CBTrainingProgressForm;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;

public interface TrainingProgressBO {
	
	public List<TrainingProgressBean> findCBTrainingProgress(CBTrainingProgressForm capacityBuildingTrainingProgressForm, List<String> statusList) throws MISException;
	
	public long saveCBTrainingProgress(CBTrainingProgressForm capacityBuildingTrainingProgressForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean updateCBTrainingProgress(CBTrainingProgressForm capacityBuildingTrainingProgressForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean deleteCBTrainingProgress(CBTrainingProgressForm capacityBuildingTrainingProgressForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postCBTrainingProgress(CBTrainingProgressForm capacityBuildingTrainingProgressForm, MISSessionBean misAuditBean) throws MISException;

}
