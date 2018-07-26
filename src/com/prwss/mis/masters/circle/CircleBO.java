package com.prwss.mis.masters.circle;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.circle.dao.CircleBean;
import com.prwss.mis.masters.circle.struts.CircleForm;

public interface CircleBO {
	
	public List<CircleBean> findCircle(CircleForm circleForm, List<String> statusList) throws MISException;
	
	public boolean saveCircle(CircleForm circleForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateCircle(CircleForm circleForm, MISSessionBean misAuditBean) throws MISException;
	*/
	public boolean deleteCircle(CircleForm circleForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postCircle(CircleForm circleForm, MISSessionBean misAuditBean) throws MISException;

	boolean updateCircle(CircleForm circleForm, MISSessionBean misAuditBean,
			List<String> statusList) throws MISException;


}
