package com.prwss.mis.tender.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class TestIreport {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
/*		JasperReportsUtils jasperReport;
		 JasperPrint jasperPrint;
		 JRResultSetDataSource obj ;
		 ResultSet rs3;
*/
		try {		
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/prwss_db_ver1","postgres","3xchange!");
/*			stmt1=(Statement) con1.createStatement();
		    rs3=(ResultSet) stmt1.executeQuery("select * from items where");

		    obj = new JRResultSetDataSource(rs3);
		    jasperReport = JasperCompileManager.compileReport("C:/items.jrxml");
		    jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),obj);
*/

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
