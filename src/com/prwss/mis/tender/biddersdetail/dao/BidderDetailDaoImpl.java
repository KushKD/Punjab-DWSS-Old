package com.prwss.mis.tender.biddersdetail.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.tender.contract.dao.ContractDetailBean;

public class BidderDetailDaoImpl extends HibernateDaoSupport implements BidderDetailDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BidderDetailBean> getBidderDetailBeans(BidderDetailBean bidderDetailBean, List<String> statusList) throws DataAccessException {
		List<BidderDetailBean> bidderDetailBeans = null;
		
		try {
			if(MisUtility.ifEmpty(bidderDetailBean)){
				
				DetachedCriteria criteria = DetachedCriteria.forClass(BidderDetailBean.class);
				if(MisUtility.ifEmpty(bidderDetailBean.getBidInfoId()))
					criteria.add(Restrictions.eq("bidInfoId", bidderDetailBean.getBidInfoId()));
				if(MisUtility.ifEmpty(bidderDetailBean.getBidderName()))
					criteria.add(Restrictions.eq("bidderName", bidderDetailBean.getBidderName()).ignoreCase());
				if(MisUtility.ifEmpty(bidderDetailBean.getBankName()))
					criteria.add(Restrictions.eq("bankName", bidderDetailBean.getBankName()).ignoreCase());
				if(MisUtility.ifEmpty(bidderDetailBean.getContactNumber()))
					criteria.add(Restrictions.eq("contactNumber", bidderDetailBean.getContactNumber()).ignoreCase());
				if(MisUtility.ifEmpty(bidderDetailBean.getBidSaleDate()))
					criteria.add(Restrictions.eq("bidSaleDate", bidderDetailBean.getBidSaleDate()));
				if(!MisUtility.ifEmpty(statusList))
					criteria.add(Restrictions.in("misAuditBean.status", statusList));

				bidderDetailBeans = getHibernateTemplate().findByCriteria(criteria);
			}
		} catch (DataAccessException e) {
			throw e;
		}
		
		return bidderDetailBeans;
	}

	@Override
	public boolean saveOrUpdateBidderDetailBeans(Collection<BidderDetailBean> bidderDetailBeans) throws DataAccessException {
			
		try {
			System.out.println("bidderDetailBeans----in dao===="+bidderDetailBeans.toString());
			getHibernateTemplate().saveOrUpdateAll(bidderDetailBeans);
		
		} catch (DataAccessException e) {
			throw e;
		}
		
		return true;
	}
	@Override
	public boolean saveOrUpdateBidderDetailBeans1(Collection<BidderDetailBean> bidderDetailBeans) throws DataAccessException {
try {      
	Iterator<BidderDetailBean> iterator = bidderDetailBeans.iterator();
	while(iterator.hasNext()){
		BidderDetailBean bidderDetailBean = (BidderDetailBean)iterator.next();
		getHibernateTemplate().merge(bidderDetailBean);
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
	        
		}
}
			
		 catch (DataAccessException e) {
			throw e;
		}
		return true;
	}
	@Override
	public boolean saveOrUpdateBidderDetailBeans3(Collection<BidderDetailBean> bidderDetailBeans) throws DataAccessException {
try {
			String a=bidderDetailBeans.iterator().next().getMisAuditBean().getStatus();
			if(a.equals("D"))
			{	
				Iterator<BidderDetailBean> iterator = bidderDetailBeans.iterator();
				while(iterator.hasNext()){
					BidderDetailBean bidderDetailBean = (BidderDetailBean)iterator.next();
					getHibernateTemplate().merge(bidderDetailBean);
					getHibernateTemplate().flush();
					getHibernateTemplate().clear();
				
				}
			}
			
			
		} catch (DataAccessException e) {
			throw e;
		}
		return true;
	}
	@Override
	public boolean saveOrUpdateBidderDetailBeans2(Collection<BidderDetailBean> bidderDetailBeans) throws DataAccessException {
		try {
	
			getHibernateTemplate().saveOrUpdateAll(bidderDetailBeans);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();
			}
			
		 catch (DataAccessException e) {
			throw e;
		}
		return true;
	}

	

}
