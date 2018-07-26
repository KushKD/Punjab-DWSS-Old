package com.prwss.mis.ccdu.issue.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;

public interface CCDUIssueDao {
	
	public Set<CCDUIssueBean> getDistinctCCDUIssueCodes() throws DataAccessException;

}
