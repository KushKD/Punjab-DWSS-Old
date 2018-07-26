/**
 * 
 */
package com.prwss.mis.masters.program.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;

/**
 * @author pjha
 *
 */
public interface ProgramDao {

public  List<ProgramBean> findProgram(ProgramBean programBean ,List<String> statusList) throws DataAccessException;

public boolean saveScheme(ProgramBean programBean) throws DataAccessException;

public boolean saveOrUpdateScheme(ProgramBean programBean) throws DataAccessException;

public Set<ProgramBean>  getDistinctPrograms()throws DataAccessException;

public ProgramBean getProgramById(String Id) throws DataAccessException;


}