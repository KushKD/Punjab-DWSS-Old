package com.prwss.mis.daktask.documenttype.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;
@Entity
@Table(name="sd_document_type", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DocumentTypeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2926046616473291950L;

	/**
	 * 
	 */
	
	
	@Id
	@Column(name="document_type", nullable=false)
	private String documentType;
	@Column(name="document_name", nullable=false)
	private String documentName;
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	@Override
	public String toString() {
		return "DocumentTypeBean [documentType=" + documentType
				+ ", documentName=" + documentName + "]";
	}
	
	
}
