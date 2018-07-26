package com.prwss.mis.pmm.village;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.village.struts.VillageDetailsForm;

public interface VillageDetailsBO {

	public VillageDetailsBean findVillageDetails(
			VillageDetailsForm villageDetailsForm, List<String> statusList)
			throws MISException;

	public boolean saveVillageDetails(VillageDetailsForm villageDetailsForm,
			MISSessionBean misSessionBean) throws MISException;

	public boolean updateVillageDetails(VillageDetailsForm villageDetailsForm,
			MISSessionBean misSessionBean) throws MISException;

	// public boolean deleteVillageDetails(VillageDetailsForm
	// villageDetailsForm, MISSessionBean misSessionBean) throws MISException;

	public boolean postVillageDetails(VillageDetailsForm villageDetailsForm,
			MISSessionBean misSessionBean) throws MISException;
}
