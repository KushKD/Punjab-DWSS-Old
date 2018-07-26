package com.prwss.mis.tender.biddersdetail.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;

public class BidderHeaderDaoImpl extends HibernateDaoSupport implements BidderHeaderDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BidderHeaderBean> findBidderHeader(BidderHeaderBean bidderHeaderBean, List<String> statusList)
			throws DataAccessException {
		List<BidderHeaderBean> bidderHeaderBeans = null;
		
		try {
			if(MisUtility.ifEmpty(bidderHeaderBean)){
				DetachedCriteria criteria = DetachedCriteria.forClass(BidderHeaderBean.class);
				
				if(MisUtility.ifEmpty(bidderHeaderBean.getBidInfoId()))
					criteria.add(Restrictions.eq("bidInfoId", bidderHeaderBean.getBidInfoId()));
				if(MisUtility.ifEmpty(bidderHeaderBean.getBiddingPhase()))
					criteria.add(Restrictions.eq("biddingPhase", bidderHeaderBean.getBiddingPhase()));
				if(MisUtility.ifEmpty(bidderHeaderBean.getLocationId()))
					criteria.add(Restrictions.eq("locationId", bidderHeaderBean.getLocationId()).ignoreCase());
				if(MisUtility.ifEmpty(bidderHeaderBean.getTenderId()))
					criteria.add(Restrictions.eq("tenderId", bidderHeaderBean.getTenderId()).ignoreCase());
				if(MisUtility.ifEmpty(bidderHeaderBean.getReferenceTenderId()))
					criteria.add(Restrictions.eq("referenceTenderId", bidderHeaderBean.getReferenceTenderId()).ignoreCase());
				if(MisUtility.ifEmpty(bidderHeaderBean.getBidOpeningDate()))
					criteria.add(Restrictions.eq("bidOpeningDate", bidderHeaderBean.getBidOpeningDate()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));
				
				bidderHeaderBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return bidderHeaderBeans;
	}

	@Override
	public long saveBidderHeader(BidderHeaderBean bidderHeaderBean) throws DataAccessException {
		long bidderInfoId = 0;
		
		try {
			getHibernateTemplate().flush();
			bidderInfoId = (Long) getHibernateTemplate().save(bidderHeaderBean);
			getHibernateTemplate().flush();
		} catch (DataAccessException e) {
			throw e;
		}
		
		return bidderInfoId;
	}

	@Override
	public boolean updateBidderHeader(BidderHeaderBean bidderHeaderBean) throws DataAccessException {
		
		try {
			getHibernateTemplate().flush();
			getHibernateTemplate().update(bidderHeaderBean);
			getHibernateTemplate().flush();
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

}
