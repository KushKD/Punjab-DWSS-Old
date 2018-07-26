package com.prwss.mis.pmm.village.phase;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.village.phase.dao.VillagePhaseStatusBean;
import com.prwss.mis.pmm.village.phase.struts.VillagePhaseStatusForm;

public interface VillagePhaseStatusBO {
	
    public List<VillagePhaseStatusBean> findVillagePhaseStatus(VillagePhaseStatusForm  villagePhaseStatusForm, List<String> statusList) throws MISException;
	
	public boolean saveVillagePhaseStatus(VillagePhaseStatusForm  villagePhaseStatusForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateVillagePhaseStatus(VillagePhaseStatusForm  villagePhaseStatusForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteVillagePhaseStatus(VillagePhaseStatusForm  villagePhaseStatusForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postVillagePhaseStatus(VillagePhaseStatusForm  villagePhaseStatusForm,  MISSessionBean misSessionBean) throws MISException;

}
