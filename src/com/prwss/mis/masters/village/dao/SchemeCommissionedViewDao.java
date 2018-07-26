package com.prwss.mis.masters.village.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface SchemeCommissionedViewDao {
	public List<SchemeCommissionedViewBean> findVillageSchemeFromView1(SchemeCommissionedViewBean villageSchemeViewBean1, List<String> statusList) throws DataAccessException;

}
