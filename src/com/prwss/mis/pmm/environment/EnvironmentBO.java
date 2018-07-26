/**
 * 
 */
package com.prwss.mis.pmm.environment;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.environment.struts.EnvironmentForm;

/**
 * @author pjha
 *
 */
public interface EnvironmentBO {


	public List<EnvironmentBean> findEnvironment(EnvironmentForm environmentForm,List<String> statusList) throws MISException;
	public boolean saveEnvironment(EnvironmentForm environmentForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateEnvironment(EnvironmentForm environmentForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteEnvironment(EnvironmentForm environmentForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postEnvironment(EnvironmentForm environmentForm,  MISSessionBean misSessionBean) throws MISException;
}
