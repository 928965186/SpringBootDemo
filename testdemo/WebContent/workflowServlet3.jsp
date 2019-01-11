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
	String Money = Util.null2String(request.getParameter("Money"));//报销金额合计
	String loanMoney = Util.null2String(request.getParameter("loanMoney"));//借款金额
	String sMoney = Util.null2String(request.getParameter("sMoney"));//本次冲销金额
	
	System.out.println("Money"+Money+"SMoney"+sMoney+"loanMoney"+loanMoney);
	
	Double i=Double.valueOf(Money);
	Double j=Double.valueOf(sMoney);
	Double v=Double.valueOf(loanMoney);
	Double sum=i*v;
	
	
	System.out.println("Money"+i+"SMoney"+j+"loanMoney"+v+"sum"+i*v); 
	
	if(j>i||j>v){
		String jsonJbsc = "{msg:'"+1+"'}";
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	}else{
		String jsonJbsc = "{msg:'"+2+"'}";
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
	}
	

}else if("gettype".equals(cmd)){
	try {
		String applyPerson = Util.null2String(request.getParameter("applyPerson"));
		
		
		int i=Integer.valueOf(applyPerson);
		
		
		String msg="";
		
		rs.executeSql("select money from formtable_main_176 where  Hrm = '"+i+"'");
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
		
	}

}else if("getfl".equals(cmd)){
	String num = Util.null2String(request.getParameter("num"));
	System.out.println("num-------------"+num);
	String[] split = num.split(";");
	Double n=0.00;
	if(num!=null&&num!=""){
		for (int j = 0; j < split.length; j++) {
				Double i=Double.valueOf(split[j]);
				n+=i;
		}
		
		String jsonJbsc = "{n:'"+n+"'}";
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonJbsc.toString());
		}else{
			String jsonJbsc = "{n:'"+1+"'}";
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonJbsc.toString());
		}
	
	
}


%>
