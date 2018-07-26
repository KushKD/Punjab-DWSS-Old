/**
 * 
 */
package com.prwss.mis.pmm.DSRPonds.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author pjha
 *
 */
public interface DSRPondsDao {
	public List<DSRPondsBean> findDSRPonds(DSRPondsBean dsrPondsBean, List<String> statusList) throws DataAccessException;

	public boolean saveDSRPonds(DSRPondsBean dsrPondsBean) throws DataAccessException;

	public boolean saveOrUpdateDSRPonds(DSRPondsBean dsrPondsBean)	throws DataAccessException;

}
