package com.prwss.mis.common.notification.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class NotificationJdcDaoImpl extends JdbcDaoSupport {
	
	public List<NotificationBean> getNotificationById(String userId) throws DataAccessException, SQLException {
		List<NotificationBean> notificationBeans = new ArrayList<NotificationBean>();
		NotificationBean notificationBean = null;
		String queryString = "{?=call prwss_main.usersnotification(?)}";
		int user= 125;
		Connection  connection = null;
		CallableStatement proc = null;
			try {
				connection = getJdbcTemplate().getDataSource().getConnection();
				connection.setAutoCommit(false);
				proc = connection.prepareCall(queryString);
				proc.registerOutParameter(1, Types.OTHER);
				proc.setInt(2, user);
				proc.execute();
				ResultSet rs = (ResultSet)proc.getObject(1);
				while(rs.next()){
					notificationBean = new NotificationBean();
					notificationBean.setNotificationId(rs.getInt(1));					
					System.out.println(rs.getInt("notification_id"));
					System.out.println(rs.getString("short_message"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				proc.close();
				if(connection !=  null){
					connection.close();
				}
			}
		
/*		BasicDataSource ds =(BasicDataSource) getJdbcTemplate().getDataSource();
		ds.setDefaultAutoCommit (false);

		DemoStoredProcedure proc = new DemoStoredProcedure(ds);
		Map params = new HashMap();
		proc.execute(params);

		ds.close();
*/
		return notificationBeans;
	}
/*	private class DemoStoredProcedure extends StoredProcedure {
		public static final String SQL = "prwss_main.usersnotificationSet";
		public DemoStoredProcedure(DataSource ds) {
		setDataSource(ds);
		setSql(SQL);
		declareParameter(new SqlReturnResultSet("refcur", new DemoRowMapper()));
		compile();
		}
		}
	private class DemoRowMapper implements RowCallbackHandler {
		public void processRow(ResultSet rs) throws SQLException {
		//get our ref cursor
		ResultSet cur = (ResultSet) rs.getObject(1);
		while (cur.next()) {
		System.out.println(cur.getInt(1) + " - " + cur.getString(2));
		}
		cur.close();
		}
		}
*/
}



