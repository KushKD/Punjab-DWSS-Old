package com.prwss.mis.masters.district;

import java.util.List;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.masters.district.dao.DistrictBean;
import com.prwss.mis.masters.district.struts.DistrictForm;

public interface DistrictBO {
	
	public List<DistrictBean> findDistrict(DistrictForm districtForm, List<String> statusList) throws MISException;
	
	public boolean saveDistrict(DistrictForm districtForm, MISSessionBean misAuditBean) throws MISException;
	
	/*public boolean updateDistrict(DistrictForm districtForm, MISSessionBean misAuditBean) throws MISException;
	*/
	public boolean deleteDistrict(DistrictForm districtForm, MISSessionBean misAuditBean) throws MISException;
	
	public boolean postDistrict(DistrictForm districtForm, MISSessionBean misAuditBean) throws MISException;

	boolean updateDistrict(DistrictForm districtForm,
			MISSessionBean misAuditBean, List<String> statusList)
			throws MISException;

}
