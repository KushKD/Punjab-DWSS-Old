package com.prwss.mis.masters.iecactivity;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.iecactivity.dao.IECActivityBean;
import com.prwss.mis.masters.iecactivity.struts.IecActivityForm;

public interface IECActivityBO {
	
	public List<IECActivityBean> findIECActivity(IecActivityForm iecActivityForm, List<String> statusList) throws MISException;
	
	public String saveIECActivity(IecActivityForm iecActivityForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateIECActivity(IecActivityForm iecActivityForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteIECActivity(IecActivityForm iecActivityForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postIECActivity(IecActivityForm iecActivityForm, MISSessionBean misSessionBean) throws MISException;


}
