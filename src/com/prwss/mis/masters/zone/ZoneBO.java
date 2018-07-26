package com.prwss.mis.masters.zone;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.zone.dao.ZoneBean;
import com.prwss.mis.masters.zone.struts.ZoneForm;

public interface ZoneBO {
	
	public List<ZoneBean> findZone(ZoneForm zoneForm, List<String> statusList) throws MISException;
	
	public String saveZone(ZoneForm zoneForm, MISSessionBean misSessionBean) throws MISException;
	
	/*public boolean updateZone(ZoneForm zoneForm, MISSessionBean misSessionBean) throws MISException;
	*/
	public boolean deleteZone(ZoneForm zoneForm, MISSessionBean misSessionBean) throws MISException;
	
	public boolean postZone(ZoneForm zoneForm, MISSessionBean misSessionBean) throws MISException;

	boolean updateZone(ZoneForm zoneForm, MISSessionBean misSessionBean,
			List<String> statusList) throws MISException;

}
