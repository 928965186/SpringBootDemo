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
String sqlName = "hr";
RecordSetDataSource sds = new RecordSetDataSource("demo");

if("getHrToBumryyd".equals(cmd)){//获取中台数据
	try {
		String yjhkrq = Util.null2String(request.getParameter("yjhkrq"));//日期
		String msg = "对的";
		String jsonJbsc = "{msg:'"+yjhkrq+"'}";
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log("获取数据异常："+e);
	}

}
else if("getZT2".equals(cmd)){//获取中台数据
	try {
		rs.writeLog("获取科目数据");
		String code = Util.null2String(request.getParameter("code"));//科目
		String msg = "对的";
		rs.writeLog("select name from WV_T_BudgetSubject where  code = '"+code+"'");
		rs.executeSql("select name from WV_T_BudgetSubject where  code = '"+code+"'");
		
		if(rs.next()){
			msg = rs.getString("name");
		}
		rs.writeLog("msg:"+msg);
		String jsonJbsc = "{msg:'"+msg+"'}";
		rs.writeLog("jsonJbsc:"+jsonJbsc);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log("获取数据异常："+e);
	}

}
else if("getZT".equals(cmd)){//获取中台数据
	try {
		RecordSetDataSource rsds = new RecordSetDataSource("demo");
		rsds.writeLog("获取科目数据");
		String code = Util.null2String(request.getParameter("code"));//科目
		String msg = "对的";
		rsds.writeLog("2:select name from WV_T_BudgetSubject where  code = '"+code+"'");
		rsds.executeSql("select name from WV_T_BudgetSubject where  code = '"+code+"'");
		
		if(rsds.next()){
			msg = rsds.getString("name");
		}
		rsds.writeLog("2:msg:"+msg);
		String jsonJbsc = "{msg:'"+msg+"'}";
		rsds.writeLog("2:jsonJbsc:"+jsonJbsc);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log("获取数据异常："+e);
	}

}

%>
