package com.prwss.mis.pmm.RoScreen;

import java.util.List;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.RoScreenStruts.RoScreenForm;

public interface RoScreenBo {

	public boolean saveData(RoScreenForm roScreenForm, Integer enteredBy)throws MISException;
	public List<RoScreenBean> find(RoScreenForm roScreenForm, List<String> statusList) throws MISException;

}
