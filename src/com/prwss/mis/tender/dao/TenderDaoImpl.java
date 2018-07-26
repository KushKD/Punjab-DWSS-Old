package com.prwss.mis.tender.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.tender.responsive.NonResponsiveTenderBean;
import com.prwss.mis.tender.responsive.ResponsiveTenderBean;

public class TenderDaoImpl extends HibernateDaoSupport implements TenderDao {
	
	private Logger log = Logger.getLogger(TenderDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<TenderHeaderBean> findTender(TenderHeaderBean tenderBean, List<String> statusList)
			throws DataAccessException {
		List<TenderHeaderBean> tenderHeaderBeans = null;
		
		try {
			if(MisUtility.ifEmpty(tenderBean)){
			DetachedCriteria criteria = DetachedCriteria.forClass(TenderHeaderBean.class);
			//criteria.add(Restrictions.eq("tenderId", tenderBean.getTenderId()));
			
			if(MisUtility.ifEmpty(tenderBean.getLocationId())){
				System.out.println("Location id: "+tenderBean.getLocationId());
				criteria.add(Restrictions.eq("locationId", tenderBean.getLocationId()));			
			}
			if(MisUtility.ifEmpty(tenderBean.getPlanId())){
				System.out.println("getPlanId id: "+tenderBean.getPlanId());
				criteria.add(Restrictions.eq("planId", tenderBean.getPlanId()));
			}
			if(MisUtility.ifEmpty(tenderBean.getProcurementId())){
				System.out.println("getProcurementId id: "+tenderBean.getProcurementId());
				criteria.add(Restrictions.eq("procurementId", tenderBean.getProcurementId()));
			}
			if(MisUtility.ifEmpty(tenderBean.getSchemeCode())){
				System.out.println("getSchemeCode id: "+tenderBean.getSchemeCode());
				criteria.add(Restrictions.eq("schemeCode", tenderBean.getSchemeCode()));
			}
			if(MisUtility.ifEmpty(tenderBean.getPackageId())){
				System.out.println("getPackageId id: "+tenderBean.getPackageId());
				criteria.add(Restrictions.eq("packageId", tenderBean.getPackageId()));
			}
			if(MisUtility.ifEmpty(tenderBean.getTenderId())){
				System.out.println("getTenderId id: "+tenderBean.getTenderId());
				criteria.add(Restrictions.eq("tenderId", tenderBean.getTenderId()));
			}
			
			if(!MisUtility.ifEmpty(statusList)){
				System.out.println("statusList: "+statusList);
				criteria.add(Restrictions.in("misAuditBean.status", statusList));
			}
				
			log.debug("findTender\t\t"+criteria);
			tenderHeaderBeans = getHibernateTemplate().findByCriteria(criteria);
			System.out.println("----------Check Size "+tenderHeaderBeans.size());
			}
		} catch (DataAccessException e) {
			throw e;
		}		
		return tenderHeaderBeans;
	}
	/*@SuppressWarnings("unchecked")
	@Override
	public List<TenderHeaderBean> findTenderV()
			throws DataAccessException {
		List<TenderHeaderBean> tenderHeaderBeans = null;
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TenderHeaderBean.class);
			//criteria.add(Restrictions.eq("tenderId", tenderBean.getTenderId()));
			
			
			tenderHeaderBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		 catch (DataAccessException e) {
			throw e;
		}		
		return tenderHeaderBeans;
	}
*/
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TenderHeaderBean> findTender(List<String> tenderIdList) throws DataAccessException {
		List<TenderHeaderBean> tenderHeaderBeans = null;
		
		try {
			if(!MisUtility.ifEmpty(tenderIdList)){
				DetachedCriteria criteria = DetachedCriteria.forClass(TenderHeaderBean.class);
				criteria.add(Restrictions.in("tenderId", tenderIdList));	
				log.debug("findTender\t\t"+criteria);
				tenderHeaderBeans = getHibernateTemplate().findByCriteria(criteria);			
			}
		} catch (DataAccessException e) {
			throw e;
		}		
		
		return tenderHeaderBeans;
	}

	@Override
	public String saveTender(TenderHeaderBean tenderBean) throws DataAccessException {
		String tenderId = null;
		try {
			tenderId = (String)getHibernateTemplate().save(tenderBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return tenderId;
	}

	@Override
	public boolean updateTender(TenderHeaderBean tenderBean) throws DataAccessException {
		try {
			getHibernateTemplate().update(tenderBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Set<TenderHeaderBean> getDistinctTenderCodes(String locationId, List<String> statusList) throws DataAccessException {
		Set<TenderHeaderBean> tenderHeaderBeans = null;
		
		try {
				System.out.println("locaion"+locationId);
				DetachedCriteria criteria = DetachedCriteria.forClass(TenderHeaderBean.class);
				criteria.add(Restrictions.eq("locationId",locationId));
				criteria.add(Restrictions.in("misAuditBean.status",statusList));
				log.debug("findTender\t\t"+criteria);
				tenderHeaderBeans = new TreeSet(getHibernateTemplate().findByCriteria(criteria));			
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		
		}		
		System.out.println("Bean"+tenderHeaderBeans);
		return tenderHeaderBeans;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<ResponsiveTenderBean> getDistinctTenderCodesResponsive(String locationId) throws DataAccessException {
		Set<ResponsiveTenderBean> tenderHeaderBeans = null;
		
		try {
				System.out.println("locaion"+locationId);
				DetachedCriteria criteria = DetachedCriteria.forClass(ResponsiveTenderBean.class);
				criteria.add(Restrictions.eq("locationId",locationId));
				log.debug("findTender\t\t"+criteria);
				tenderHeaderBeans = new TreeSet( getHibernateTemplate().findByCriteria(criteria));
				
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		
		}	
		catch(Throwable e1){
			e1.printStackTrace();
		}
		return tenderHeaderBeans;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set<NonResponsiveTenderBean> getDistinctTenderCodesNONResponsive(String packageId) throws DataAccessException {
		Set<NonResponsiveTenderBean> tenderHeaderBeans = null;
		
		try {
				System.out.println("package"+packageId);
				DetachedCriteria criteria = DetachedCriteria.forClass(NonResponsiveTenderBean.class);
				criteria.add(Restrictions.eq("packageId",packageId));
				log.debug("findTender\t\t"+criteria);
				tenderHeaderBeans = new TreeSet( getHibernateTemplate().findByCriteria(criteria));
				System.out.println("-----------------Size"+tenderHeaderBeans.size());
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		
		}	
		catch(Throwable e1){
			e1.printStackTrace();
		}
		System.out.println("Bean"+tenderHeaderBeans);
		return tenderHeaderBeans;
	}
	
	@Override
	public Set<TenderHeaderBean> getDistinctTenders(TenderHeaderBean tenderBean, List<String> statusList) throws DataAccessException {
		Set<TenderHeaderBean> tenderHeaderBeans = null;
		
		try {
				if(MisUtility.ifEmpty(tenderBean)){
					DetachedCriteria criteria = DetachedCriteria.forClass(TenderHeaderBean.class);
					
					if(MisUtility.ifEmpty(tenderBean.getLocationId())){
						criteria.add(Restrictions.eq("locationId", tenderBean.getLocationId()));			
					}
					if(MisUtility.ifEmpty(tenderBean.getPlanId())){
						criteria.add(Restrictions.eq("planId", tenderBean.getPlanId()));
					}
					if(MisUtility.ifEmpty(tenderBean.getProcurementId())){
						criteria.add(Restrictions.eq("procurementId", tenderBean.getProcurementId()));
					}
					if(MisUtility.ifEmpty(tenderBean.getSchemeCode())){
						criteria.add(Restrictions.eq("schemeCode", tenderBean.getSchemeCode()));
					}
					if(MisUtility.ifEmpty(tenderBean.getPackageId())){
						criteria.add(Restrictions.eq("packageId", tenderBean.getPackageId()));
					}
					if(MisUtility.ifEmpty(tenderBean.getTenderId())){
						System.out.println("Tender NO is "+tenderBean.getTenderId());
						criteria.add(Restrictions.eq("tenderId", tenderBean.getTenderId()));
					}
					
					if(!MisUtility.ifEmpty(statusList)){
						criteria.add(Restrictions.in("misAuditBean.status", statusList));
					}
						
					log.debug("findTender\t\t"+criteria);
					tenderHeaderBeans = new TreeSet(getHibernateTemplate().findByCriteria(criteria));
					System.out.println("-----------------Size"+tenderHeaderBeans.size());
				}
		} catch (DataAccessException e) {
			throw e;
		}		
		
		return tenderHeaderBeans;
	}
	@Override
	public boolean updateTender(List<TenderHeaderBean> tenderBeans) throws DataAccessException {
		
		try {
			getHibernateTemplate().saveOrUpdateAll(tenderBeans);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}
	public boolean deleteUploadedTender(TenderUploadBean tenderUploadBean)
	throws DataAccessException {
		try {
			getHibernateTemplate().delete(tenderUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
		}
	@Override
	public boolean uploadTender(TenderUploadBean tenderUploadBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(tenderUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}
	@Override
	public boolean uploadEOI(EOIUploadBean eoiUploadBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(eoiUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}		
		return true;
	}
	@Override
	public boolean uploadAdv(AdvUploadBean advUploadBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(advUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}
	
	@Override
	public boolean updateAdv(AdvUploadBean advUploadBean)
		throws DataAccessException {
		try {
			getHibernateTemplate().update(advUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}
		
	
	
	@Override
	public List<TenderUploadBean> getTenderUploaded(
			TenderUploadBean tenderUploadBean) throws DataAccessException {
		List<TenderUploadBean> tenderUploadBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TenderUploadBean.class);
			if(MisUtility.ifEmpty(tenderUploadBean)){
				
			if(MisUtility.ifEmpty(tenderUploadBean.getTenderUploadId())){
				criteria.add(Restrictions.eq("tenderUploadId", tenderUploadBean.getTenderUploadId()));
			}
			
			if(MisUtility.ifEmpty(tenderUploadBean.getTenderId())){
				criteria.add(Restrictions.eq("tenderId", tenderUploadBean.getTenderId()).ignoreCase());
			}
			
			if(MisUtility.ifEmpty(tenderUploadBean.getLocationId())){
				criteria.add(Restrictions.eq("locationId", tenderUploadBean.getLocationId()));
			}
//		criteria.add(Restrictions.in("misAuditBean.status", statusList));
			log.debug("findTender\t\t"+criteria);
			tenderUploadBeans = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			throw e;
			
		}	
		
		return tenderUploadBeans;
	}

	@Override
	public List<TenderUploadBean> findTenderUploaded(TenderUploadBean tenderUploadBean, List<String> statusList)throws DataAccessException {
		List<TenderUploadBean> tenderUploadBeans= null;
		try {
			if(MisUtility.ifEmpty(tenderUploadBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(TenderUploadBean.class);
				if(MisUtility.ifEmpty(tenderUploadBean.getTenderUploadId())){
					criteria.add(Restrictions.eq("tenderUploadId", tenderUploadBean.getTenderUploadId()));
				}
				
				if(MisUtility.ifEmpty(tenderUploadBean.getTenderId())){
					criteria.add(Restrictions.eq("tenderId", tenderUploadBean.getTenderId()).ignoreCase());
				}
				
				if(MisUtility.ifEmpty(tenderUploadBean.getLocationId())){
					criteria.add(Restrictions.eq("locationId", tenderUploadBean.getLocationId()));
				}
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				log.debug("findTender\t\t"+criteria);
				
				
				tenderUploadBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}				
		return tenderUploadBeans;
	}

	@Override
	public List<EOIUploadBean> getEOIUploaded(
			EOIUploadBean eoiUploadBean) throws DataAccessException {
		List<EOIUploadBean> eoiUploadBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(EOIUploadBean.class);
			if(MisUtility.ifEmpty(eoiUploadBean)){
				
			if(MisUtility.ifEmpty(eoiUploadBean.getEoiUploadId())){
				criteria.add(Restrictions.eq("eoiUploadId", eoiUploadBean.getEoiUploadId()));
			}
			if(MisUtility.ifEmpty(eoiUploadBean.getLocationId())){
				criteria.add(Restrictions.eq("locationId", eoiUploadBean.getLocationId()));
			}
			log.debug("findTender\t\t"+criteria);
			eoiUploadBeans = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			throw e;
			
		}	
		
		return eoiUploadBeans;
	}

	@Override
	public List<EOIUploadBean> findEOIUploaded(EOIUploadBean eoiUploadBean, List<String> statusList)throws DataAccessException {
		List<EOIUploadBean> eoiUploadBeans= null;
		try {
			if(MisUtility.ifEmpty(eoiUploadBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(EOIUploadBean.class);
				if(MisUtility.ifEmpty(eoiUploadBean.getEoiUploadId())){
					criteria.add(Restrictions.eq("eoiUploadId", eoiUploadBean.getEoiUploadId()));
				}
				
				if(MisUtility.ifEmpty(eoiUploadBean.getLocationId())){
					criteria.add(Restrictions.eq("locationId", eoiUploadBean.getLocationId()));
				}
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				log.debug("findTender\t\t"+criteria);
				
				
				eoiUploadBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}				
		return eoiUploadBeans;
	}

	@Override
	public List<QuoatationUploadBean> getQuoatationUploaded(
			QuoatationUploadBean quoatationUploadBean)
			throws DataAccessException {
		List<QuoatationUploadBean> quoatationUploadBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(QuoatationUploadBean.class);
			if(MisUtility.ifEmpty(quoatationUploadBean)){
				
			if(MisUtility.ifEmpty(quoatationUploadBean.getQuoatationUploadId())){
				criteria.add(Restrictions.eq("quoatationUploadId", quoatationUploadBean.getQuoatationUploadId()));
			}
			if(MisUtility.ifEmpty(quoatationUploadBean.getLocationId())){
				criteria.add(Restrictions.eq("locationId", quoatationUploadBean.getLocationId()));
			}
			log.debug("findTender\t\t"+criteria);
			quoatationUploadBeans = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			throw e;
			
		}	
		
		return quoatationUploadBeans;
	}

	@Override
	public List<QuoatationUploadBean> findQuoatationUploaded(
			QuoatationUploadBean quoatationUploadBean, List<String> statusList)
			throws DataAccessException {
		List<QuoatationUploadBean> quoatationUploadBeans= null;
		try {
			if(MisUtility.ifEmpty(quoatationUploadBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(QuoatationUploadBean.class);
				if(MisUtility.ifEmpty(quoatationUploadBean.getQuoatationUploadId())){
					criteria.add(Restrictions.eq("quoatationUploadId", quoatationUploadBean.getQuoatationUploadId()));
				}
				
				if(MisUtility.ifEmpty(quoatationUploadBean.getLocationId())){
					criteria.add(Restrictions.eq("locationId", quoatationUploadBean.getLocationId()));
				}
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				log.debug("findTender\t\t"+criteria);
				
				
				quoatationUploadBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}				
		return quoatationUploadBeans;
	}
	@Override
	public List<AdvUploadBean> getAdvUploaded(
			AdvUploadBean advUploadBean)
			throws DataAccessException {
		List<AdvUploadBean> advUploadBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(AdvUploadBean.class);
			if(MisUtility.ifEmpty(advUploadBean)){
				
			if(MisUtility.ifEmpty(advUploadBean.getAdvUploadId())){
				criteria.add(Restrictions.eq("advUploadId", advUploadBean.getAdvUploadId()));
			}
			if(MisUtility.ifEmpty(advUploadBean.getLocationId())){
				criteria.add(Restrictions.eq("locationId", advUploadBean.getLocationId()));
			}
			log.debug("findTender\t\t"+criteria);
			advUploadBeans = getHibernateTemplate().findByCriteria(criteria);

			}
		} catch (DataAccessException e) {
			throw e;
			
		}	
		
		return advUploadBeans;
	}

	@Override
	public List<AdvUploadBean> findAdvUploaded(
			AdvUploadBean advUploadBean, List<String> statusList)
			throws DataAccessException {
		List<AdvUploadBean> advUploadBeans= null;
		try {
			if(MisUtility.ifEmpty(advUploadBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(QuoatationUploadBean.class);
				if(MisUtility.ifEmpty(advUploadBean.getAdvUploadId())){
					criteria.add(Restrictions.eq("advUploadId", advUploadBean.getAdvUploadId()));
				}
				
				if(MisUtility.ifEmpty(advUploadBean.getLocationId())){
					criteria.add(Restrictions.eq("locationId", advUploadBean.getLocationId()));
				}
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				log.debug("findTender\t\t"+criteria);
				
				
				advUploadBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}				
		return advUploadBeans;
	}

	@Override
	public boolean uploadQuoatation(QuoatationUploadBean quoatationUploadBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(quoatationUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}		
		return true;	
	}

	@Override
	public boolean uploadCorrigendum(CorrigendumUploadBean corrigendumUploadBean)
			throws DataAccessException {
		try {
			getHibernateTemplate().save(corrigendumUploadBean);
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	@Override
	public List<CorrigendumUploadBean> getCorrigendumUploaded(CorrigendumUploadBean corrigendumUploadBean) throws DataAccessException {
		List<CorrigendumUploadBean> corrigendumUploadBeans = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CorrigendumUploadBean.class);
			if(MisUtility.ifEmpty(corrigendumUploadBean)){
				
			if(MisUtility.ifEmpty(corrigendumUploadBean.getCorrigendumUploadId())){
				criteria.add(Restrictions.eq("corrigendumUploadId", corrigendumUploadBean.getCorrigendumUploadId()));
			}	
			if(MisUtility.ifEmpty(corrigendumUploadBean.getDocId())){
				criteria.add(Restrictions.eq("docId", corrigendumUploadBean.getDocId()).ignoreCase());
			}
			if(MisUtility.ifEmpty(corrigendumUploadBean.getLocationId())){
				criteria.add(Restrictions.eq("locationId", corrigendumUploadBean.getLocationId()));
			}
			log.debug("findTender\t\t"+criteria);
			corrigendumUploadBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;			
		}	
		
		return corrigendumUploadBeans;

	}

	@Override
	public List<CorrigendumUploadBean> findCorrigendumUploaded(
			CorrigendumUploadBean corrigendumUploadBean, List<String> statusList)
			throws DataAccessException {
		List<CorrigendumUploadBean> corrigendumUploadBeans= null;
		try {
			if(MisUtility.ifEmpty(corrigendumUploadBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(CorrigendumUploadBean.class);
				if(MisUtility.ifEmpty(corrigendumUploadBean.getCorrigendumUploadId())){
					criteria.add(Restrictions.eq("tenderUploadId", corrigendumUploadBean.getCorrigendumUploadId()));
				}
				
				if(MisUtility.ifEmpty(corrigendumUploadBean.getDocId())){
					criteria.add(Restrictions.eq("docId", corrigendumUploadBean.getDocId()).ignoreCase());
				}
				
				if(MisUtility.ifEmpty(corrigendumUploadBean.getLocationId())){
					criteria.add(Restrictions.eq("locationId", corrigendumUploadBean.getLocationId()));
				}
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				log.debug("findTender\t\t"+criteria);
				
				
				corrigendumUploadBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}				
		return corrigendumUploadBeans;
	}


	@Override
	public List<TenderHeaderBean> findTenderV() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
