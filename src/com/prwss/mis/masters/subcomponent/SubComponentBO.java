/**
 * 
 */
package com.prwss.mis.masters.subcomponent;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.subcomponent.dao.SubComponentBean;
import com.prwss.mis.masters.subcomponent.struts.SubComponentForm;

/**
 * @author vgadiraju
 *
 */
public interface SubComponentBO {
	
	public  List<SubComponentBean> findSubComponent(SubComponentForm subComponent, List<String> statusList) throws MISException;
	
	/*public boolean saveSubComponent(SubComponentForm subComponent, MISSessionBean misAuditBean) throws MISException;*/
	
	public boolean updateSubComponent(SubComponentForm subComponent, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postSubComponent(SubComponentForm subComponent, MISSessionBean misAuditBean) throws MISException;
	
	public boolean deleteSubComponent(SubComponentForm subComponent, MISSessionBean misAuditBean) throws MISException;

	boolean saveSubComponent(SubComponentForm subComponent,
			MISSessionBean misAuditBean, List<String> statusList)
			throws MISException;

}
