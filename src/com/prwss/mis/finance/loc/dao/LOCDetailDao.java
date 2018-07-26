/**
 * 
 */
package com.prwss.mis.finance.loc.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author PJHA
 *
 */
public interface LOCDetailDao {

	public List<LOCDetailBean> findLOCtDetails(LOCDetailBean locDetailBean, List<String> statusList) throws DataAccessException;

	public boolean saveLOCDetails(Collection<LOCDetailBean> locDetailBeans) throws DataAccessException;

	public boolean updateLOCDetails(Collection<LOCDetailBean> locDetailBeans) throws DataAccessException;

}
