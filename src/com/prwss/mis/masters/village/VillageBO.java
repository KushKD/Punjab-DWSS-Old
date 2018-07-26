package com.prwss.mis.masters.village;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.village.dao.VillageBean;
import com.prwss.mis.masters.village.dao.VillageViewBean;
import com.prwss.mis.masters.village.struts.VillageForm;

public interface VillageBO {
public List<VillageBean> findVillage(VillageForm villageForm, List<String> statusList) throws MISException;

public List<VillageViewBean> findVillageView(VillageForm villageForm, List<String> statusList) throws MISException;
	
	public String saveVillage(VillageForm villageForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateVillage(VillageForm villageForm,  MISSessionBean misSessionBean,List<String> statusList) throws MISException;
	
	public boolean deleteVillage(VillageForm villageForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postVillage(VillageForm villageForm,  MISSessionBean misSessionBean) throws MISException;

	public List<VillageViewBean> findOtherHabitation(VillageForm villageForm, List<String> statusList)
			throws MISException;
}
