<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="weaver.general.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="weaver.conn.RecordSet" %>
<%@ page import="weaver.conn.RecordSetDataSource" %>
<%@ page import="weaver.file.Prop" %>
<%@ page import="java.math.BigDecimal" %>
<%@page import="java.net.URLEncoder"%>
<%

String cmd = Util.null2String(request.getParameter("cmd"));
RecordSet rs=new RecordSet();
//类似于jdbc中获取conn
String sqlName = "hr";
RecordSetDataSource sds = new RecordSetDataSource("demo");

if("gettype".equals(cmd)){//获取中台数据
	try {
		String city = Util.null2String(request.getParameter("City"));//城市
		String type = Util.null2String(request.getParameter("Type"));//类型
		
		int i=Integer.valueOf(city);
		int j=Integer.valueOf(type);
		String msg="";
		rs.writeLog("select money from formtable_main_279 where  city = '"+city+"' and type='"+type+"'");
		rs.executeSql("select money from formtable_main_279 where  city = '"+i+"' and type='"+j+"'");
		if(rs.next()){
			msg = rs.getString("money");
			
		}
		if(msg==null||msg==""){
			String jsonJbsc = "{msg:'"+1+"'}";
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonJbsc.toString());
			
		}else{
			String jsonJbsc = "{msg:'"+msg+"'}";
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonJbsc.toString());
		}
			
			
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log("获取数据异常："+e);
	}

}else if("getpd".equals(cmd)){
	String Money = Util.null2String(request.getParameter("Money"));//城市
	String sMoney = Util.null2String(request.getParameter("sMoney"));//类型
	String days = Util.null2String(request.getParameter("days"));//城市
	
	System.out.println("Money"+Money+"SMoney"+sMoney+"days"+days);
	
	Double i=Double.valueOf(Money);
	Double j=Double.valueOf(sMoney);
	Double v=Double.valueOf(days);
	Double sum=i*v;
	
	
	System.out.println("Money"+i+"SMoney"+j+"days"+v+"sum"+i*v); 
	
	if(sum>j){
		String jsonJbsc = "{msg:'"+1+"'}";
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	}else{
		String jsonJbsc = "{msg:'"+2+"'}";
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	}
	

} else if("getMX".equals(cmd)){//获取明细表2
	try {
		
		rs.writeLog("获取明细表2");
		String applyPerson = Util.null2String(request.getParameter("applyPerson"));//申请人
		int i=Integer.valueOf(applyPerson);
		String msg = "";
		System.out.println("applyPerson"+i);
		
		String applyDate="";
		String Type="";
		String City="";
		String Money="";
		
		
		rs.writeLog("2:select applyDate,Type,City,Money from formtable_main_278_dt2 where  applyPerson = '"+applyPerson+"'");
		rs.executeSql("select applyDate,Type,City,Money from formtable_main_278_dt2 where  applyPerson = '"+i+"'");
	
		if(rs.next()){
			applyDate = rs.getString("applyDate");
			Type = rs.getString("Type");
			City = rs.getString("City");
			Money = rs.getString("Money");
			
		}
		System.out.println("applyPerson"+i+"applyDate"+applyDate+"Type"+Type+"City"+City+"Money"+Money);
		//String json=JSON.toJSONString(total);
		//rsds.writeLog("2:total:"+total);
		//String jsonJbsc = "{applyDate:'"+applyDate+"'},{Type:'"+Type+"'},{City:'"+City+"'},{Money:'"+Money+"'}";
		//rsds.writeLog("2:jsonJbsc:"+jsonJbsc);
		String jsonJbsc = "{applyDate:'"+applyDate+"',Type:'"+Type+"',City:'"+City+"',Money:'"+Money+"'}";

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log("获取数据异常："+e);
	}
 
}

%>
