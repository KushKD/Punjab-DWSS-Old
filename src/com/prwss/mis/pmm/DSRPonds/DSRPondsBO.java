/**
 * 
 */
package com.prwss.mis.pmm.DSRPonds;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.DSRPonds.dao.DSRPondsBean;
import com.prwss.mis.pmm.DSRPonds.struts.DSRPondsForm;

/**
 * @author pjha
 *
 */
public interface DSRPondsBO {
public List<DSRPondsBean> findDSRPonds(DSRPondsForm dsrPondsForm, List<String> statusList) throws MISException;
	
	public boolean saveDSRPonds(DSRPondsForm dsrPondsForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean updateDSRPonds(DSRPondsForm dsrPondsForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteDSRPonds(DSRPondsForm dsrPondsForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postDSRPonds(DSRPondsForm dsrPondsForm,  MISSessionBean misSessionBean) throws MISException;
}
