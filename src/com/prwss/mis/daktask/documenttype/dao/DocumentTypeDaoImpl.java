package com.prwss.mis.daktask.documenttype.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class DocumentTypeDaoImpl extends HibernateDaoSupport implements DocumentTypeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDocumentType() throws DataAccessException {
		List<DocumentTypeBean> documentTypeBeans=null;
		DetachedCriteria criteria = DetachedCriteria.forClass(DocumentTypeBean.class);
		documentTypeBeans=getHibernateTemplate().findByCriteria(criteria);
		List<String> documentTypeList=new ArrayList<String>();
		for(DocumentTypeBean documentTypeBean:documentTypeBeans)
		{
			documentTypeList.add(documentTypeBean.getDocumentType());
			
		}
		return documentTypeList;
	}

}
