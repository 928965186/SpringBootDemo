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


if("save".equals(cmd)){
	
	
	String Name = Util.null2String(request.getParameter("Name"));
	String Num = Util.null2String(request.getParameter("Num"));
	String id = Util.null2String(request.getParameter("id"));
	
	System.out.println(id+"----------------"+Name+"-----"+Num+"---------");
	if(id.equals("")){
		String insertSql="insert into  formtable_main_181(Name,Num) values('"+Name+"','"+Num+"')";
		rs.writeLog("insert into  formtable_main_181(Name,Num) values('"+Name+"','"+Num+"'") ;
		rs.executeSql(insertSql);
	}else{
		String updateSql="update  formtable_main_181 set Num ='"+Num+"',Name= '"+Name+"' where id='"+id+"'";
		rs.writeLog("update  formtable_main_181 set Num ='"+Num+"',Name= '"+Name+"' where id='"+id+"'");
		rs.executeSql(updateSql);
	}
	String jsonJbsc = "{result:'"+"success"+"'}";
	response.setContentType("text/html;charset=UTF-8");
	response.getWriter().print(jsonJbsc.toString());
		
}else if("del".equals(cmd)){
	String id = Util.null2String(request.getParameter("id"));
	String deleteSql="delete from formtable_main_181  where id='"+id+"'";
	rs.executeSql(deleteSql);	
	String jsonJbsc = "{result:'"+"success"+"'}";
	response.setContentType("text/html;charset=UTF-8");
	response.getWriter().print(jsonJbsc.toString());
	
}else if("delAll".equals(cmd)){
	String ids = Util.null2String(request.getParameter("ids"));
	String[] split = ids.split(",");
	for (String string : split) {
		String deleteSql="delete from formtable_main_181  where id='"+string+"'";
		rs.executeSql(deleteSql);
	}
	String jsonJbsc = "{result:'"+"success"+"'}";
	response.setContentType("text/html;charset=UTF-8");
	response.getWriter().print(jsonJbsc.toString());
}


%>
