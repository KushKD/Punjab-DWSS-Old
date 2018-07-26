package com.prwss.mis.pmm.schemeupload.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.scheme.dao.SchemeBean;
import com.prwss.mis.pmm.schemeupload.struts.SchemeUploadForm;
import com.prwss.mis.tender.dao.TenderDao;

public class SchemeUploadDaoImpl extends HibernateDaoSupport implements
		SchemeUpdateDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean uploadScheme(SchemeUploadBean schemeUploadBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(schemeUploadBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return true;

	}

	@Override
	@Transactional
	public List<SchemeUploadBean> getUploadScheme(
			SchemeUploadForm schemeuploadFom) throws DataAccessException {
		List<SchemeUploadBean> schemeUploadBeans = new  ArrayList<SchemeUploadBean>();
		try {
			if(MisUtility.ifEmpty(schemeuploadFom)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(SchemeUploadBean.class);
				if(MisUtility.ifEmpty(schemeuploadFom.getScheme_id()))
					criteria.add(Restrictions.eq("scheme_id", schemeuploadFom.getScheme_id()));
				
				if(MisUtility.ifEmpty(schemeuploadFom.getLocation_id()))
					criteria.add(Restrictions.eq("location_id", schemeuploadFom.getLocation_id()));
				
				if(MisUtility.ifEmpty(schemeuploadFom.getScheme_type()))
					criteria.add(Restrictions.eq("scheme_type", schemeuploadFom.getScheme_type()));
				
				
				
				schemeUploadBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeUploadBeans;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<SchemeUploadDto> getAttachmentData(String name, String id , String columnName)
			throws DataAccessException {
		
		List<SchemeUploadDto> updateRTIDto = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(SchemeUploadBean.class);
			
				criteria.add(Restrictions.eq("id", Integer.parseInt(id)));
				
				if(columnName.equalsIgnoreCase("digitalSurvey_name_pdf")){
					
					criteria.setProjection(Projections.projectionList()
						      .add(Projections.property("digitalSurvey_name_pdf"), "attachmentName")
						      .add(Projections.property("digitalSurvey_name_pdf_File"), "attachmentFile"))
						    .setResultTransformer(Transformers.aliasToBean(SchemeUploadDto.class));

					
					
				}
				
				if(columnName.equalsIgnoreCase("digitalSurvey_name_cdr")){
					criteria.setProjection(Projections.projectionList()
						      .add(Projections.property("digitalSurvey_name_cdr"), "attachmentName")
						      .add(Projections.property("digitalSurvey_name_cdr_File"), "attachmentFile"))
						    .setResultTransformer(Transformers.aliasToBean(SchemeUploadDto.class));

				}
				
				if(columnName.equalsIgnoreCase("schemeEstimate_name")){
					criteria.setProjection(Projections.projectionList()
						      .add(Projections.property("schemeEstimate_name"), "attachmentName")
						      .add(Projections.property("schemeEstimateFile"), "attachmentFile"))
						    .setResultTransformer(Transformers.aliasToBean(SchemeUploadDto.class));
				}
				
				if(columnName.equalsIgnoreCase("adminAproval_name")){
					criteria.setProjection(Projections.projectionList()
						      .add(Projections.property("adminAproval_name"), "attachmentName")
						      .add(Projections.property("adminAprovalFile"), "attachmentFile"))
						    .setResultTransformer(Transformers.aliasToBean(SchemeUploadDto.class));

					
				}
				
				if(columnName.equalsIgnoreCase("strataChart_name")){
					criteria.setProjection(Projections.projectionList()
						      .add(Projections.property("strataChart_name"), "attachmentName")
						      .add(Projections.property("strataChartFile"), "attachmentFile"))
						    .setResultTransformer(Transformers.aliasToBean(SchemeUploadDto.class));

					
				}
				
			
				updateRTIDto = getHibernateTemplate().findByCriteria(criteria);
			
				
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return updateRTIDto;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<SchemeUploadBean> checkSchemeExistsOrNot(
			SchemeUploadForm schemeUploadFindBean) throws DataAccessException {
		List<SchemeUploadBean> schemeUploadBeans = new  ArrayList<SchemeUploadBean>();
		try {
			if(MisUtility.ifEmpty(schemeUploadFindBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(SchemeUploadBean.class);
				if(MisUtility.ifEmpty(schemeUploadFindBean.getScheme_id()))
					criteria.add(Restrictions.eq("scheme_id", schemeUploadFindBean.getScheme_id()));
				
				if(MisUtility.ifEmpty(schemeUploadFindBean.getLocation_id()))
					criteria.add(Restrictions.eq("location_id", schemeUploadFindBean.getLocation_id()));
				
				if(MisUtility.ifEmpty(schemeUploadFindBean.getScheme_type()))
					criteria.add(Restrictions.eq("scheme_type", schemeUploadFindBean.getScheme_type()));
				
				
				
				schemeUploadBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeUploadBeans;
	}

	@Override
	@Transactional
	public boolean uploadSchemeUpdate(SchemeUploadBean schemeUploadFindBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().saveOrUpdate(schemeUploadFindBean);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}

		return true;
	}

	@Override
	@Transactional
	public SchemeUploadBean getId(SchemeUploadForm schemeuploadFom)
			throws DataAccessException {
		List<SchemeUploadBean> schemeUploadBeans = new  ArrayList<SchemeUploadBean>();
		try {
			if(MisUtility.ifEmpty(schemeuploadFom)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(SchemeUploadBean.class);
				if(MisUtility.ifEmpty(schemeuploadFom.getScheme_id()))
					criteria.add(Restrictions.eq("scheme_id", schemeuploadFom.getScheme_id()));
				
				if(MisUtility.ifEmpty(schemeuploadFom.getLocation_id()))
					criteria.add(Restrictions.eq("location_id", schemeuploadFom.getLocation_id()));
				
				if(MisUtility.ifEmpty(schemeuploadFom.getScheme_type()))
					criteria.add(Restrictions.eq("scheme_type", schemeuploadFom.getScheme_type()));
				
				
				
				schemeUploadBeans =  getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		return schemeUploadBeans.get(0);
	}
}
