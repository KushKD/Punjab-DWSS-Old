package com.prwss.mis.masters.village;

import org.springframework.dao.DataAccessException;

public interface ProposedProgramDao {

	public boolean saveProposedProgram(ProposedProgramBean proposedProgramBean) throws DataAccessException;

}
