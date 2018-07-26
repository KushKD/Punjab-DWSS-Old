package com.prwss.mis.daktask.documenttype.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface DocumentTypeDao {
	
	public List<String> getDocumentType()throws DataAccessException;

}
