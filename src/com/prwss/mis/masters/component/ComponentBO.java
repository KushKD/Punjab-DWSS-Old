/**
 * 
 */
package com.prwss.mis.masters.component;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.component.dao.ComponentBean;
import com.prwss.mis.masters.component.struts.ComponentForm;

/**
 * @author vgadiraju
 *
 */
public interface ComponentBO {
	
	public List<ComponentBean> findComponent(ComponentForm componentForm, List<String> statusList) throws MISException;
	
	public boolean saveComponent(ComponentForm componentForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateComponent(ComponentForm componentForm, MISSessionBean misAuditBean) throws MISException;*/
	
	public boolean deleteComponent(ComponentForm componentForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postComponent(ComponentForm componentForm, MISSessionBean misAuditBean) throws MISException;

	boolean updateComponent(ComponentForm componentForm,
			MISSessionBean misSessionBean, List<String> statusList)
			throws MISException;
	
}
