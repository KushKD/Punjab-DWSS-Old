/**
 * 
 */
package com.prwss.mis.pmm.instplants;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.pmm.instplants.struts.InstPlantsForm;

/**
 * @author pjha
 *
 */
public interface InstPlantsBO {

	public List<InstPlantsBean> findInstPlants(InstPlantsForm instPlantsForm,List<String> statusList) throws MISException;
	public boolean saveInstPlants(InstPlantsForm instPlantsForm,  MISSessionBean misSessionBean) throws MISException;
	public boolean updateInstPlants(InstPlantsForm instPlantsForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean deleteInstPlants(InstPlantsForm instPlantsForm,  MISSessionBean misSessionBean) throws MISException;
	
	public boolean postInstPlants(InstPlantsForm instPlantsForm,  MISSessionBean misSessionBean) throws MISException;
}
