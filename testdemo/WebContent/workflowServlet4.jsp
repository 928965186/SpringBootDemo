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


if("getpd".equals(cmd)){
	//本次冲销金额不能大于报销金额合计、不能大于借款金额
	String Num = Util.null2String(request.getParameter("Num"));//报销金额合计
	String applyNum = Util.null2String(request.getParameter("applyNum"));//借款金额
	
	
	System.out.println("Num"+Num+"applyNum"+applyNum);
	
	Double i=Double.valueOf(Num);
	Double j=Double.valueOf(applyNum);
	
	
	
	
	
	if(j>i){
		String jsonJbsc = "{msg:'"+1+"'}";
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	}else{
		String jsonJbsc = "{msg:'"+2+"'}";
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	}
}else if("getfl".equals(cmd)){
	String Mat = Util.null2String(request.getParameter("Mat"));
	System.out.println("Mat-------------"+Mat);
	String msg="";
	String sql="select Num from formtable_main_181 where id ='"+Mat+"'";
	rs.writeLog("select Num from formtable_main_181 where id ='"+Mat+"'");
	rs.executeSql(sql);	
	if(rs.next()){
		msg = rs.getString("Num");
		
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
		
}


%>
