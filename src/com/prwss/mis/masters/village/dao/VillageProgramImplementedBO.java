package com.prwss.mis.masters.village.dao;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.village.VillageProgramImplementedBean;
import com.prwss.mis.masters.village.struts.VillageProgramImplForm;

public interface VillageProgramImplementedBO {

	public boolean updateVillageProgImpl(VillageProgramImplForm villageProgramImplForm,  MISSessionBean misSessionBean) throws MISException;
	
	public VillageProgramImplementedBean findVillageProgImpl(VillageProgramImplForm villageProgramImplForm,  List<String> statusList) throws MISException;
	
	public boolean saveVillageProgImpl(VillageProgramImplForm villageProgramImplForm,  MISSessionBean misSessionBean) throws MISException;

	public boolean postVillageProgImp(VillageProgramImplForm villageProgramImplForm,
			MISSessionBean misSessionBean) throws MISException;
	
}
