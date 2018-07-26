package com.prwss.mis.common;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class MISJdcDaoImpl extends JdbcDaoSupport{

	public DataSource getMISDataSource() throws SQLException{	
		return getJdbcTemplate().getDataSource();
	}
}
