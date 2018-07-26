package com.prwss.mis.sd.document.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.mis.sd.document.dao.DocumentNumberBean;

public interface DocumentNumberDAO {
	public List<DocumentNumberBean> getDocumentNumberBeans(DocumentNumberBean DocumentNumberBean) throws DataAccessException;
	
	public List<DocumentNumberBean> getDocumentNumber(DocumentNumberBean DocumentNumberBean) throws DataAccessException;
	
	public boolean saveOrUpdateDocumentNumberBeans(DocumentNumberBean DB) throws DataAccessException;

}
