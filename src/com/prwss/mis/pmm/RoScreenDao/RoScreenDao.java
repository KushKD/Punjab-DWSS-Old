package com.prwss.mis.pmm.RoScreenDao;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.pmm.RoScreen.RoScreenBean;
import com.prwss.mis.pmm.RoScreenStruts.RoScreenDto;

public interface RoScreenDao {

	public List<RoScreenDto> getDivisionNameAndId() throws DataAccessException;
	public List<RoScreenDto> getSubDivisionNameAndId(String divisionId) throws DataAccessException;
	public List<RoScreenDto> getVillageNameAndId(String divisionId, String subDivisionId) throws DataAccessException;
	public String getCapacityOfRO(String divisionId, String subDivisionId, String villageId) throws DataAccessException;
	public String getProgram(String divisionId, String subDivisionId, String villageId)throws DataAccessException;
	public String getExecutingAgency(String divisionId, String subDivisionId, String villageId) throws DataAccessException;
	//public String getOnMDate(String divisionId, String subDivisionId, String villageId)throws DataAccessException;
	//public String getAdminApproveCost(String divisionId, String subDivisionId, String villageId)throws DataAccessException;
	public List<RoScreenBean> fetchRoConnectionBean(String division, String subDivision, String village)throws DataAccessException;
	//public boolean disablePrevious(Integer roConnectionId)throws DataAccessException;
	public boolean saveRoConnectionDetails(RoScreenBean roScreenBeans)throws DataAccessException;
	public int update(RoScreenBean roBean, Integer enteredBy)throws DataAccessException;
	public String getSeasonalRO(String divisionId, String subDivisionId, String villageId)throws DataAccessException;
	public String getHouseholds(String villageId)throws DataAccessException;
	public List<RoScreenBean> find(RoScreenBean roScreenBean, List<String> statusList)throws DataAccessException;

}
