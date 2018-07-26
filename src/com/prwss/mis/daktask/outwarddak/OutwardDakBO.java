package com.prwss.mis.daktask.outwarddak;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.daktask.outwarddak.dao.OutwardDakBean;
import com.prwss.mis.daktask.outwarddak.struts.OutwardDakForm;
import com.prwss.mis.sd.document.dao.DocumentNumberBean;


public interface OutwardDakBO {
	public List<OutwardDakBean> findOutwardDak(OutwardDakForm outwardDakForm , List<String> statusList) throws MISException;
	public long saveOutwardDak(OutwardDakForm outwardDakForm, MISSessionBean misSessionBean, DocumentNumberBean documentNumberBean) throws MISException;
	public boolean updateOutwardDak(OutwardDakForm outwardDakForm, MISSessionBean misSessionBean) throws MISException;

}
