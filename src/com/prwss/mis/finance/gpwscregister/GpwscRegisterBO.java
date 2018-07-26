package com.prwss.mis.finance.gpwscregister;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.finance.gpwscregister.dao.GpwscRegisterBean;
import com.prwss.mis.finance.gpwscregister.struts.GpwscRegisterForm;

public interface GpwscRegisterBO {
	public List<GpwscRegisterBean> findGpwsc(GpwscRegisterForm gpwscRegisterForm, List<String> statusList) throws MISException;
	public long saveGpwsc(GpwscRegisterForm gpwscRegisterForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateGpwsc(GpwscRegisterForm gpwscRegisterForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean deleteGpwsc(GpwscRegisterForm gpwscRegisterForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean postGpwsc(GpwscRegisterForm gpwscRegisterForm,  MISSessionBean misSessionBean) throws MISException;
}
