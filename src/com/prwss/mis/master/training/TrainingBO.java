package com.prwss.mis.master.training;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.master.training.dao.TrainingBean;
import com.prwss.mis.master.training.struts.TrainingMasterForm;

public interface TrainingBO {

	public List<TrainingBean> findTrainingMaster(TrainingMasterForm trainingMasterForm, List<String> statusList) throws MISException;
	
	public String saveTrainingMaster(TrainingMasterForm trainingMasterForm, MISSessionBean misSessionBean) throws MISException;
	
	/*public boolean updateTrainingMaster(TrainingMasterForm trainingMasterForm, MISSessionBean misSessionBean) throws MISException;
	*/
	public boolean deleteTrainingMaster(TrainingMasterForm trainingMasterForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postTrainingMaster(TrainingMasterForm trainingMasterForm, MISSessionBean misSessionBean) throws MISException;

	boolean updateTrainingMaster(TrainingMasterForm trainingMasterForm,
			MISSessionBean misSessionBean, List<String> statusList)
			throws MISException;
	
}
