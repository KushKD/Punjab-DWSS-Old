package com.prwss.mis.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.masters.scheme.dao.SchemeVillageBean;
import com.prwss.mis.masters.scheme.struts.SchemeHeaderVillageForm;

public class MisUtility {
	
		
	
	public static boolean ifEmpty(Object obj){		
		if(obj == null)
			return false;
		
		return true;
	}
	/*public  static boolean waterWorksLocation(SchemeVillageBean schemeVillageBean) {
		
		List<SchemeVillageBean> schemeVillageBeans = new ArrayList<SchemeVillageBean>()  ;
		int count=0;
		boolean waterWorkLocation=true;
		System.out.println("MisUtility"+schemeVillageBeans);
		for(SchemeVillageBean villageBean:schemeVillageBeans ){
			if(villageBean.isWaterWorksLocation()==true){
				++count;
				System.out.println("in MISUtility-----------"+count);
			}
			if(count>1&&count<1){
				waterWorkLocation=false;
			}
			
		}
		return waterWorkLocation;
		
		
	}*/
	
	public static boolean ifEmpty(String str){
		if(str != null && !"".equals(str.trim()) && !"''".equals(str.trim()) &&!"null".equals(str.trim())&&!str.equalsIgnoreCase("select"))
			return true;
		
		return false;
	}
	
	public static boolean ifEmptyField(String str){
		if(str != null && !"".equals(str.trim()) && !"''".equals(str.trim()) &&!"null".equals(str.trim()) && !str.isEmpty())
			return true;
		
		return false;
	}
	
	public static boolean ifEmptyFieldNo(String str){
		if(str != null && !"".equals(str.trim()) && !"''".equals(str.trim()) &&!"null".equals(str.trim()) &&!str.equalsIgnoreCase("No")&&!str.equalsIgnoreCase("Select Village")&&!str.equalsIgnoreCase("0"))
			return true;
		
		return false;
	}
	public static boolean ifEmptyFieldYes(String str){
		if(str != null && !"".equals(str.trim()) && !"''".equals(str.trim()) &&!"null".equals(str.trim()) &&str.equalsIgnoreCase("Yes"))
			return true;
		
		return false;
	}
	public static boolean ifEmpty(Long var){
		if(var == null || var == 0)
			return false;
		
		return true;
	}
	
	public static boolean ifEmpty(int var){
		if(var == 0)
			return false;
		
		return true;
	}
	
	public static boolean ifEmpty(Double val){		
		if(val== null || val == 0.0)
			return false;
		
		return true;
	}
	
	public static boolean ifEmpty(BigDecimal val){		
		if(val== null || val.doubleValue()==0.0)
			return false;
		
		return true;
	}
	
		
	@SuppressWarnings("rawtypes")
	public static boolean ifEmpty(Collection collection){
		
		if(collection != null && collection.size() > 0)
			return false;
		
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean ifEmpty(Map map){
		
		if(map != null && map.size() > 0)
			return false;
		
		return true;
	}
	
	public static boolean ifEmpty(Object[] obj){		
		if(obj == null || !(obj.length > 0))
			return false;
		
		return true;
	}
	
	public static String decorateLikeSearchCriteria(String property){
		
		if(property != null && property.trim().equalsIgnoreCase("")){
			property = "%"+property+"%";
		}
		return property;
	}
	
	public static Date convertStringToDate(String dateString){
		if(!MisUtility.ifEmpty(dateString))
			return null;
		
		Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
		StringTokenizer stringTokenizer = new StringTokenizer(dateString, "-");
		int year = 0 ;
		int month = 0;
		int date = 0;
		while(stringTokenizer.hasMoreTokens()){
			date = new Integer(stringTokenizer.nextToken());
			month = new Integer(stringTokenizer.nextToken()) - 1; // subtracting 1 because Calender.MONTH starts with 0(Jan) 
			year = new Integer(stringTokenizer.nextToken());
		}
		Calendar cal = GregorianCalendar.getInstance(aLocale);		
		cal.set(year, month, date);
		
		return new Date(cal.getTime().getTime());
	}
	public static Date convertStringToSqlDate(String dateString){
		if(!MisUtility.ifEmpty(dateString))
			return null;
		
		Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
		StringTokenizer stringTokenizer = new StringTokenizer(dateString, "-");
		int year = 0 ;
		int month = 0;
		int date = 0;
		while(stringTokenizer.hasMoreTokens()){
			year = new Integer(stringTokenizer.nextToken());
			month = new Integer(stringTokenizer.nextToken()) - 1; // subtracting 1 because Calender.MONTH starts with 0(Jan) 
			date = new Integer(stringTokenizer.nextToken());
		}
		Calendar cal = GregorianCalendar.getInstance(aLocale);		
		cal.set(year, month, date);
		
		return new Date(cal.getTime().getTime());
	}
	
	public static Date convertStringSqlDate(String dateString){
		if(!MisUtility.ifEmpty(dateString))
			return null;
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date parsed=null;
		
			try {
				parsed = format.parse(dateString);
			} catch (ParseException e) {
				
			}
		
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        return sql;
	}
	public static String getMonthFromStringDate(String dateString){
		StringTokenizer stringTokenizer = new StringTokenizer(dateString, "-");
		String year = null ;
		String month = null;
		String date = null;
		while(stringTokenizer.hasMoreTokens()){
			date = stringTokenizer.nextToken();
			month = stringTokenizer.nextToken(); 
			year = stringTokenizer.nextToken();
		}
		return month;
	}
	
	public static String getYearFromStringDate(String dateString){
		StringTokenizer stringTokenizer = new StringTokenizer(dateString, "-");
		String year = null ;
		String month = null;
		String date = null;
		while(stringTokenizer.hasMoreTokens()){
			date = stringTokenizer.nextToken();
			month = stringTokenizer.nextToken(); 
			year = stringTokenizer.nextToken();
		}
		return year;
	}
	
	public static String convertDateToString(java.util.Date date){
		if(date ==  null)
			return null;
		
		/*Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
		Calendar cal = GregorianCalendar.getInstance(aLocale);
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR) ;
		int month = cal.get(Calendar.MONTH)+ 1 ; // Adding 1 because Calender.MONTH starts with 0(Jan)
		int dateOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		return dateOfMonth+"-"+month+"-"+year;*/
		SimpleDateFormat dateformatJava = new SimpleDateFormat("dd-MM-yyyy");
		String date_to_string = dateformatJava.format(date);
		return date_to_string;
	}
	public static String convertDateString(java.sql.Date date){
		if(date ==  null)
			return null;
		
		/*Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
		Calendar cal = GregorianCalendar.getInstance(aLocale);
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR) ;
		int month = cal.get(Calendar.MONTH)+ 1 ; // Adding 1 because Calender.MONTH starts with 0(Jan)
		int dateOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		return dateOfMonth+"-"+month+"-"+year;*/
		SimpleDateFormat dateformatJava = new SimpleDateFormat("MM/dd/yyyy");
		String date_to_string = dateformatJava.format(date);
		return date_to_string;
	}
	
	public static String convertDateToStringForDisplay(java.sql.Date date){
		if(date ==  null)
			return null;
		
		Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
		Calendar cal = GregorianCalendar.getInstance(aLocale);
		cal.setTime(date);
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
	    return sdf.format(cal.getTime());
//		int year = cal.get(Calendar.YEAR) ;
//		int month = cal.get(Calendar.MONTH)+ 1 ; // Adding 1 because Calender.MONTH starts with 0(Jan)
//		int dateOfMonth = cal.get(Calendar.DAY_OF_MONTH);
//		return dateOfMonth+"-"+month+"-"+year;
	}
	
	public static String now(String dateFormat) {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());
	  }


	public static void dateConverter(Date date){
		DateConverter converter = new DateConverter(date);
		ConvertUtils.register(converter, java.sql.Date.class);
		
	}
	
	public static long getNumberOfDays(Date fromDate,Date toDate){
		
		if(!ifEmpty(fromDate)||!ifEmpty(toDate))
			return 0;
		
		long from = fromDate.getTime();
		long to=toDate.getTime();
		long diff=to-from;
		long noofdays = diff/(24*60*60*1000);
		
		return noofdays+1;
	}
	
	public static boolean isSameDate(Date firstDate, Date seconDate){
		if(ifEmpty(firstDate)&&ifEmpty(firstDate)){
			Locale aLocale = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
			Calendar cal = GregorianCalendar.getInstance(aLocale);
			cal.setTime(firstDate);
			int year = cal.get(Calendar.YEAR) ;
			int month = cal.get(Calendar.MONTH)+ 1 ; // Adding 1 because Calender.MONTH starts with 0(Jan)
			int dateOfMonth = cal.get(Calendar.DAY_OF_MONTH);
			
			Locale aLocale2 = new Locale(Locale.ENGLISH.getLanguage(), Locale.UK.getCountry());
			Calendar cal2 = GregorianCalendar.getInstance(aLocale);
			cal.setTime(seconDate);
			int year2 = cal.get(Calendar.YEAR) ;
			int month2 = cal.get(Calendar.MONTH)+ 1 ; // Adding 1 because Calender.MONTH starts with 0(Jan)
			int dateOfMonth2 = cal.get(Calendar.DAY_OF_MONTH);

			if(year==year2 && month==month2 && dateOfMonth==dateOfMonth2){
				return true;
			}
		}
		return false;
	}
	
	public static PrintWriter getPrintWriter(HttpServletResponse response) throws IOException{
		 response.setContentType("text/text;charset=utf-8");
		  response.setHeader("cache-control", "no-cache");
		  PrintWriter out = response.getWriter();
		  return out;
	}
	
	
}
