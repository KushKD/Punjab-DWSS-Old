package com.prwss.mis.common.util.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.util.MisUtility;

public class PublicReportController extends HttpServlet {
	private static final long serialVersionUID = -1738910969424259354L;
	private Logger log = Logger.getLogger(PublicReportController.class);

	private MISJdcDaoImpl misJdcDaoImpl;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		Connection connection = null;
		Statement statement = null;
		try {
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(req.getSession()
							.getServletContext());

			String op = null;
			if (MisUtility.ifEmpty(req.getParameter("op"))) {
				op = (String) req.getParameter("op");
			}
		
			if (op.equals("AllREPORTS")) {

				String where = " 1=1 ";
				String distID = req.getParameter("districtId");
				String blockId = req.getParameter("blockId");
				String divisionalOfficeId = req.getParameter("divisionalOfficeId");
				System.out.println("before"+divisionalOfficeId);

				if(divisionalOfficeId.equals("DIV01"))
				{divisionalOfficeId="Gurdaspur";}else if(divisionalOfficeId.equals("DIV02")){divisionalOfficeId="Batala";}else if(divisionalOfficeId.equals("DIV03")){divisionalOfficeId="Pathankot";}
				else if(divisionalOfficeId.equals("DIV05")){divisionalOfficeId="Amritsar,Div-1";}else if(divisionalOfficeId.equals("DIV06")){divisionalOfficeId="Amritsar,Div-2";}
				else if(divisionalOfficeId.equals("DIV07")){divisionalOfficeId="Amritsar,Div-3";}else if(divisionalOfficeId.equals("DIV09")){divisionalOfficeId="Tarn Taran";}
				else if(divisionalOfficeId.equals("DIV11")){divisionalOfficeId="Kapurthala";}else if(divisionalOfficeId.equals("DIV13")){divisionalOfficeId="Jalandhar,Div-1";}
				else if(divisionalOfficeId.equals("DIV14")){divisionalOfficeId="Jalandhar,Div-2";}else if(divisionalOfficeId.equals("DIV16")){divisionalOfficeId="Hoshiarpur,Div-1";}
				else if(divisionalOfficeId.equals("DIV17")){divisionalOfficeId="Hoshiarpur,Div-2";}else if(divisionalOfficeId.equals("DIV18")){divisionalOfficeId="Garh Shankar";}
				else if(divisionalOfficeId.equals("DIV19")){divisionalOfficeId="Talwara";}else if(divisionalOfficeId.equals("DIV21")){divisionalOfficeId="SBS Nagar, Div";}
				else if(divisionalOfficeId.equals("DIV23")){divisionalOfficeId="Anandpur Sahib";}else if(divisionalOfficeId.equals("DIV24")){divisionalOfficeId="Roopnagar, Div-2";}
				else if(divisionalOfficeId.equals("DIV26")){divisionalOfficeId="Ajitgarh, Div-1";}else if(divisionalOfficeId.equals("DIV27")){divisionalOfficeId="Ajitgarh, Div-2";}
				else if(divisionalOfficeId.equals("DIV28")){divisionalOfficeId="Ajitgarh, Div-3";}else if(divisionalOfficeId.equals("DIV30")){divisionalOfficeId="CCDU, DWSS";}
				else if(divisionalOfficeId.equals("DIV31")){divisionalOfficeId="Ajitgarh, QM&S Div-1";}else if(divisionalOfficeId.equals("DIV32")){divisionalOfficeId="Ajitgarh, QM&S Div-2";}
				else if(divisionalOfficeId.equals("DIV33")){divisionalOfficeId="Ajitgarh, QM&S Div-3";}else if(divisionalOfficeId.equals("DIV34")){divisionalOfficeId="Fatehgarh Sahib";}
				else if(divisionalOfficeId.equals("DIV36")){divisionalOfficeId="Ludhiana, Div-1";}else if(divisionalOfficeId.equals("DIV37")){divisionalOfficeId="Ludhiana, Div-2";}
				else if(divisionalOfficeId.equals("DIV38")){divisionalOfficeId="Ludhiana, Div-3";}else if(divisionalOfficeId.equals("DIV39")){divisionalOfficeId="Khanna";}
				else if(divisionalOfficeId.equals("DIV41")){divisionalOfficeId="Moga";}else if(divisionalOfficeId.equals("DIV43")){divisionalOfficeId="Ferozepur, Div-1";}
				else if(divisionalOfficeId.equals("DIV44")){divisionalOfficeId="Ferozepur, Div-2";}else if(divisionalOfficeId.equals("DIV45")){divisionalOfficeId="Fazilka";}
				else if(divisionalOfficeId.equals("DIV46")){divisionalOfficeId="Abohar";}else if(divisionalOfficeId.equals("DIV48")){divisionalOfficeId="Muktsar, Div-1";}
				else if(divisionalOfficeId.equals("DIV49")){divisionalOfficeId="Muktsar, Div-2";}else if(divisionalOfficeId.equals("DIV50")){divisionalOfficeId="Malout";}
				else if(divisionalOfficeId.equals("DIV52")){divisionalOfficeId="Faridkot";}else if(divisionalOfficeId.equals("DIV54")){divisionalOfficeId="Bathinda, Div-1";}
				else if(divisionalOfficeId.equals("DIV55")){divisionalOfficeId="Bathinda, Div-2";}else if(divisionalOfficeId.equals("DIV56")){divisionalOfficeId="Bathinda, Div-3";}
				else if(divisionalOfficeId.equals("DIV58")){divisionalOfficeId="Mansa, Div-1";}else if(divisionalOfficeId.equals("DIV59")){divisionalOfficeId="Mansa, Div-2";}
				else if(divisionalOfficeId.equals("DIV61")){divisionalOfficeId="Malerkotla";}else if(divisionalOfficeId.equals("DIV62")){divisionalOfficeId="Sangrur";}
				else if(divisionalOfficeId.equals("DIV64")){divisionalOfficeId="Barnala";}else if(divisionalOfficeId.equals("DIV66")){divisionalOfficeId="Patiala, Div-1";}
				else if(divisionalOfficeId.equals("DIV67")){divisionalOfficeId="Patiala, Div-2";}else if(divisionalOfficeId.equals("DIV68")){divisionalOfficeId="Rajpura";}
				else if(divisionalOfficeId.equals("DIV70")){divisionalOfficeId="Patiala, Mechnical Division";}else if(divisionalOfficeId.equals("DIV72")){divisionalOfficeId="Fazilka-FZR";}
				
				

				System.out.println("after"+divisionalOfficeId);
				String finyear = req.getParameter("finyear");

				if (finyear != "" && distID != "" && divisionalOfficeId != "") {
					//System.out.println("only year and dist and divisional");
					if (finyear.equals("2007-08")) {
							where += "and month_of_commissioning between '2007-04-01' and '2008-03-31' and district_id='"
									+ req.getParameter("districtId")
									+ "' and location_name='"
									+divisionalOfficeId+ "'";
					} else if (finyear.equals("2008-09")) {
						where += "and month_of_commissioning between '2008-04-01' and '2009-03-31' and district_id='"
								+ req.getParameter("districtId")
								+ "' and location_name='"
								+divisionalOfficeId+ "'";
					} else if (finyear.equals("2009-10")) {
						where += "and month_of_commissioning between '2009-04-01' and '2010-03-31' and district_id='"
								+ req.getParameter("districtId")
								+ "' and location_name='"
								+divisionalOfficeId+ "'";
					} else if (finyear.equals("2010-11")) {
						where += "and month_of_commissioning between '2010-04-01' and '2011-03-31' and district_id='"
								+ req.getParameter("districtId")
								+ "' and location_name='"
								+divisionalOfficeId+ "'";
					} else if (finyear.equals("2011-12")) {
						where += "and month_of_commissioning between '2011-04-01' and '2012-03-31' and district_id='"
								+ req.getParameter("districtId")
								+ "' and location_name='"
								+divisionalOfficeId+ "'";
					} else if (finyear.equals("2012-13")) {
						where += "and month_of_commissioning between '2012-04-01' and '2013-03-31' and district_id='"
								+ req.getParameter("districtId")
								+ "' and location_name='"
								+divisionalOfficeId+ "'";
					} else if (finyear.equals("2013-14")) {
						where += "and month_of_commissioning between '2013-04-01' and '2014-03-31' and district_id='"
								+ req.getParameter("districtId")
								+ "' and location_name='"
								+divisionalOfficeId+ "'";
					} else if (finyear.equals("2014-15")) {
						where += "and month_of_commissioning between '2014-04-01' and '2015-03-31' and district_id='"
								+ req.getParameter("districtId")
								+ "' and location_name='"
								+divisionalOfficeId+ "'";
					} else if (finyear.equals("2015-16")) {
						where += "and month_of_commissioning between '2015-04-01' and '2016-03-31' and district_id='"
								+ req.getParameter("districtId")
								+ "' and location_name='"
								+divisionalOfficeId+ "'";
					} else if (finyear.equals("2016-17")) {
						where += "and month_of_commissioning between '2016-04-01' and '2017-03-31' and district_id='"
								+ req.getParameter("districtId")
								+ "' and location_name='"
								+divisionalOfficeId+ "'";
					}

				} else if (finyear != "" && distID.equals(""))

				{ //System.out.println("only year");
					if (finyear.equals("2007-08")) {
						where += "and month_of_commissioning between '2007-04-01' and '2008-03-31'";
					} else if (finyear.equals("2008-09")) {
						where += "and month_of_commissioning between '2008-04-01' and '2009-03-31'";
					} else if (finyear.equals("2009-10")) {
						where += "and month_of_commissioning between '2009-04-01' and '2010-03-31'";
					} else if (finyear.equals("2010-11")) {
						where += "and month_of_commissioning between '2010-04-01' and '2011-03-31'";
					} else if (finyear.equals("2011-12")) {
						where += "and month_of_commissioning between '2011-04-01' and '2012-03-31'";
					} else if (finyear.equals("2012-13")) {
						where += "and month_of_commissioning between '2012-04-01' and '2013-03-31'";
					} else if (finyear.equals("2013-14")) {
						where += "and month_of_commissioning between '2013-04-01' and '2014-03-31'";
					} else if (finyear.equals("2014-15")) {
						where += "and month_of_commissioning between '2014-04-01' and '2015-03-31'";
					} else if (finyear.equals("2015-16")) {
						where += "and month_of_commissioning between '2015-04-01' and '2016-03-31'";
					} else if (finyear.equals("2016-17")) {
						where += "and month_of_commissioning between '2016-04-01' and '2017-03-31'";
					}
				} else if (finyear != "" && distID != "") {
					//System.out.println("only year and dist");
					if (finyear.equals("2007-08")) {
						where += "and month_of_commissioning between '2007-04-01' and '2008-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					} else if (finyear.equals("2008-09")) {
						where += "and month_of_commissioning between '2008-04-01' and '2009-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					} else if (finyear.equals("2009-10")) {
						where += "and month_of_commissioning between '2009-04-01' and '2010-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					} else if (finyear.equals("2010-11")) {
						where += "and month_of_commissioning between '2010-04-01' and '2011-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					} else if (finyear.equals("2011-12")) {
						where += "and month_of_commissioning between '2011-04-01' and '2012-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					} else if (finyear.equals("2012-13")) {
						where += "and month_of_commissioning between '2012-04-01' and '2013-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					} else if (finyear.equals("2013-14")) {
						where += "and month_of_commissioning between '2013-04-01' and '2014-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					} else if (finyear.equals("2014-15")) {
						where += "and month_of_commissioning between '2014-04-01' and '2015-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					} else if (finyear.equals("2015-16")) {
						where += "and month_of_commissioning between '2015-04-01' and '2016-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					} else if (finyear.equals("2016-17")) {
						where += "and month_of_commissioning between '2016-04-01' and '2017-03-31' and district_id='"
								+ req.getParameter("districtId") + "'";
					}
				}

				else if (distID != "" && divisionalOfficeId != "") {
					//System.out.println("only dist and division");
					where += " and district_id='"
							+ req.getParameter("districtId")
							+ "' and location_name='"
							+divisionalOfficeId+ "'";
				} else if (distID != "") {
					//System.out.println("only dist");
					where += " and district_id='"
							+ req.getParameter("districtId") + "'";
				} else {
					if (finyear.equals("2007-08")) {
						where += "and month_of_commissioning between '2007-04-01' and '2008-03-31'";
					} else if (finyear.equals("2008-09")) {
						where += "and month_of_commissioning between '2008-04-01' and '2009-03-31'";
					} else if (finyear.equals("2009-10")) {
						where += "and month_of_commissioning between '2009-04-01' and '2010-03-31'";
					} else if (finyear.equals("2010-11")) {
						where += "and month_of_commissioning between '2010-04-01' and '2011-03-31'";
					} else if (finyear.equals("2011-12")) {
						where += "and month_of_commissioning between '2011-04-01' and '2012-03-31'";
					} else if (finyear.equals("2012-13")) {
						where += "and month_of_commissioning between '2012-04-01' and '2013-03-31'";
					} else if (finyear.equals("2013-14")) {
						where += "and month_of_commissioning between '2013-04-01' and '2014-03-31'";
					} else if (finyear.equals("2014-15")) {
						where += "and month_of_commissioning between '2014-04-01' and '2015-03-31'";
					} else if (finyear.equals("2015-16")) {
						where += "and month_of_commissioning between '2015-04-01' and '2016-03-31'";
					} else if (finyear.equals("2016-17")) {
						where += "and month_of_commissioning between '2016-04-01' and '2017-03-31'";
					}
				}

				try {
					this.misJdcDaoImpl = (MISJdcDaoImpl) webApplicationContext
							.getBean("misJdcDaoImpl");
					connection = misJdcDaoImpl.getMISDataSource()
							.getConnection();
					connection.setAutoCommit(false);
					statement = connection.createStatement();

					ResultSet rs = statement
							.executeQuery("select district_name,location_name,block_name,program_name,scheme_name,swap_nonswap,water_works_existing_new,admin_approval_amount,village_commissioned_nc,village_commissioned_pc,village_commissioned_sb,village_commissioned_oh,month_of_commissioning,source_of_scheme from (select z.zone_name as zone,z.zone_id,c.circle_name as circle,c.circle_id,d.district_name,com.district_id,name_of_consituency ,b.block_name ,com.block_id ,com.location_name,p.program_name,com.program_id ,com.scheme_name,case when p.swap_nonswap='SWAP' and com.program_id='PROG15' then  'SWAP-IDA'when p.swap_nonswap='SWAP' and com.program_id<>'PROG15' then 'SWAP-NON IDA' else p.swap_nonswap end swap_nonswap,com.water_works_existing_new ,com.admin_approval_no,com.admin_approval_date ,com.admin_approval_amount,com.village_commissioned_nc,com.village_commissioned_pc,com.village_commissioned_sb ,com.village_commissioned_oh ,com.village_id ,com.month_of_commissioning,case when com.source_of_scheme='CANNAL' then 'CANAL' else com.source_of_scheme end source_of_scheme,case when month_of_commissioning between '2008-04-01' and '2009-03-31' then 'Financial Year (2008-2009)'	when month_of_commissioning between '2009-04-01' and '2010-03-31' then 'Financial Year (2009-2010)' when month_of_commissioning between '2010-04-01' and '2011-03-31' then 'Financial Year (2010-2011)' else 'Warning: Not in range, Please varify the month of commissioning.' end as fin_year,com.scheme_id,case when (month_of_commissioning between '2008-04-01' and '2009-03-31') and (com.village_commissioned_nc is not null) then 1 else 0 end fin_year_08_09_nc, case when (month_of_commissioning between '2009-04-01' and '2010-03-31') and (com.village_commissioned_nc is not null) then 1 else 0 end fin_year_09_10_nc,case when (month_of_commissioning between '2010-04-01' and '2011-03-31') and (com.village_commissioned_nc is not null) then 1 else 0 end fin_year_10_11_nc,case when (month_of_commissioning < '2008-04-01' or month_of_commissioning > '2011-03-31') and (com.village_commissioned_nc is not null) then 1 else 0 end fin_year_not_in_range_nc,case when (month_of_commissioning between '2008-04-01' and '2009-03-31') and (com.village_commissioned_pc is not null) then 1 else 0 end fin_year_08_09_pc,case when (month_of_commissioning between '2009-04-01' and '2010-03-31') and (com.village_commissioned_pc is not null) then 1 else 0 end fin_year_09_10_pc,case when (month_of_commissioning between '2010-04-01' and '2011-03-31') and (com.village_commissioned_pc is not null) then 1 else 0 end fin_year_10_11_pc,case when (month_of_commissioning < '2008-04-01' or month_of_commissioning > '2011-03-31') and (com.village_commissioned_pc is not null) then 1 else 0 end fin_year_not_in_range_pc,case when (month_of_commissioning between '2008-04-01' and '2009-03-31') and (com.village_commissioned_sb is not null) then 1 else 0 end fin_year_08_09_sb,case when (month_of_commissioning between '2009-04-01' and '2010-03-31') and (com.village_commissioned_sb is not null) then 1 else 0 end fin_year_09_10_sb,case when (month_of_commissioning between '2010-04-01' and '2011-03-31') and (com.village_commissioned_sb is not null) then 1 else 0 end fin_year_10_11_sb,case when (month_of_commissioning < '2008-04-01' or month_of_commissioning>'2011-03-31') and (com.village_commissioned_sb is not null) then 1 else 0 end fin_year_not_in_range_sb,case when (month_of_commissioning between '2008-04-01' and '2009-03-31') and (com.village_commissioned_oh is not null) then 1 else 0 end fin_year_08_09_oh,case when (month_of_commissioning between '2009-04-01' and '2010-03-31') and (com.village_commissioned_oh is not null) then 1 else 0 end fin_year_09_10_oh,case when (month_of_commissioning between '2010-04-01' and '2011-03-31') and (com.village_commissioned_oh is not null) then 1 else 0 end fin_year_10_11_oh,case when (month_of_commissioning < '2008-04-01' or month_of_commissioning > '2011-03-31') and (com.village_commissioned_oh is not null) then 1 else 0 end fin_year_not_in_range_oh, rank() over (partition by village_id order by month_of_commissioning desc, scheme_id desc ,admin_approval_date desc , rr asc) row_id from ( select *, case when water_works_existing_new = 'extended' then 1 when water_works_existing_new = 'NEW' then 2 when water_works_existing_new = 'EXISTING' then 3 else 4 end as rr from prwss_main.vw_commissioning_data_new_12_aug)com left outer join prwss_main.mst_program p on trim(p.program_id)=trim(com.program_id),prwss_main.mst_district d,prwss_main.mst_circle c,prwss_main.mst_zone z,prwss_main.mst_block b WHERE z.zone_id=c.zone_id and c.circle_id=d.circle_id and d.district_id=trim(com.district_id) and b.block_id=trim(com.block_id) and month_of_commissioning is not null)main where row_id = 1  and "
									+ where
									+ " order by zone,circle,district_name,location_name,scheme_id;");

					Result result = ResultSupport.toResult(rs);
					req.setAttribute("result", result);
					RequestDispatcher rd = req
							.getRequestDispatcher("/Reports/showReportsResult.jsp");
					rd.forward(req, resp);

				} catch (SQLException e) {
					if (connection != null) {
						try {
							connection.rollback();
						} catch (SQLException excep) {
							excep.printStackTrace();
						}
					}
				} finally {
					if (statement != null) {
						statement.close();
					}
					connection.setAutoCommit(true);
					connection.close();
				}
			}
		} catch (BeansException e) {
			log.error(e.getLocalizedMessage(), e);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

}