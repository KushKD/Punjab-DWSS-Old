package com.prwss.mis.masters.program;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.struts.ProgramForm;

public interface ProgramBO {

	public List<ProgramBean> findProgram(ProgramForm programForm,List<String> statusList) throws MISException;
	public boolean saveProgram(ProgramForm programForm,  MISSessionBean misSessionBean) throws MISException;
	/*public boolean updateProgram(ProgramForm programForm,  MISSessionBean misSessionBean) throws MISException;
	*/
	public boolean deleteProgram(ProgramForm programForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postProgram(ProgramForm programForm,  MISSessionBean misSessionBean) throws MISException;
	boolean updateProgram(ProgramForm programForm,
			MISSessionBean misSessionBean, List<String> statusList)
			throws MISException;
}
