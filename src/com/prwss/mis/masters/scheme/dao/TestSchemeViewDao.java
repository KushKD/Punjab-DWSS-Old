package com.prwss.mis.masters.scheme.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface TestSchemeViewDao {
	public List<TestSchemeViewBean> finTestView(TestSchemeViewBean testSchemeViewBean ,List<String> statusList) throws DataAccessException;
}
