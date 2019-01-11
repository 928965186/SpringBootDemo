<%@page import="weaver.hrm.HrmUserVarify"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %> 
<%@ page import="weaver.general.Util" %>
<%@ include file="/systeminfo/init_wev8.jsp" %>
<%@ taglib uri="/WEB-INF/weaver.tld" prefix="wea"%>
<%@ taglib uri="/browserTag" prefix="brow"%>
<jsp:useBean id="RecordSet" class="weaver.conn.RecordSet" scope="page"/>
<script language="javascript" src="/wui/theme/ecology8/jquery/js/zDialog_wev8.js"></script>
<link rel="stylesheet" href="/wui/theme/ecology8/jquery/js/zDialog_e8_wev8.css" type="text/css" />

<%


//引用权限
//if(!HrmUserVarify.checkUserRight("CE_CustomerSystemReportMaint", user) ) { 
//        response.sendRedirect("/notice/noright.jsp");         
//        return;     
//  }
	String perpage ="10";
	String imagefilename = "/images/hdReport_wev8.gif";
	String titlename = "物料信息维护";
	String needfav ="1";
	String needhelp ="";
	
	String id = Util.null2String(request.getParameter("id"));//日期
	String Name = Util.null2String(request.getParameter("Name"));//日期
	String Num = Util.null2String(request.getParameter("Num"));//科目名称
	String frozenNum = Util.null2String(request.getParameter("frozenNum"));//科目编码
	
	String shifdk = "";
%>
<HTML>
	<HEAD>
		<LINK href="/css/Weaver_wev8.css" type=text/css rel=STYLESHEET>
		<SCRIPT language="javascript" src="/js/weaver_wev8.js"></script>
		<SCRIPT language="javascript" defer="defer" src="/js/datetime_wev8.js"></script>
		<SCRIPT language="javascript" defer="defer" src="/js/JSDateTime/WdatePicker_wev8.js"></script>
		<script language=javascript>
			function onSubmit() {
				document.frmmain.submit();
			}
			//重置搜索条件
			function returnV(){
				jQuery("input").val("");
				jQuery("select").val("");
				document.frmmain.submit();
			}
			//跳转修改或者新增页面
			function optionUrl(v){
				if(v==undefined||v=="undefined"){
					v="";
				}
				
				var url = "/westvalley/TestAdd.jsp?id="+v;
				//window.location=url;
				var title = '编辑';
				var dlg=new window.top.Dialog();//定义Dialog对象     
				dlg.currentWindow = window;　　　
				dlg.Model=true;
				dlg.Width=500;//定义长度　　　
				dlg.Height=400;　　　
				dlg.URL=url;　　　
				dlg.Title=title;　　　
				dlg.show();　
			}

			function openDialog(title,url) {
				var dlg=new window.top.Dialog();//定义Dialog对象     
				dialog.currentWindow = window;
				dlg.Model=true;
				dlg.Width=500;//定义长度　　　
				dlg.Height=400;
				dlg.URL=url;
				dlg.Title=title;
				dlg.show();
			}
			
			
			function delAll(){
				var ids = _xtable_CheckedCheckboxId();
				if(ids != ""){
					var isDelete=confirm("确认要删除所选项吗？");
					if(isDelete==true){
						jQuery.ajax({
							type: "POST",
							url: "/westvalley/test.jsp?cmd=delAll",
							data:"ids="+ids,
							dataType: "text",
							success: function(data){
								var returnJson=eval("("+data+")");
								if(returnJson.result=="success"){
									alert("删除成功！");
									document.frmmain.submit();
								}else{
									alert("删除失败！");	
								}
							},
							error: function(msg){
								var t = msg;
								alert("删除异常！");
							}
						});
					} 
				} else {
					alert("请选择记录！");
				}
			}
			//删除
			function del(v){
			    if(!confirm("确定要删除吗?")){
			       return false;
			    }
				jQuery.ajax({
					type: "POST",
					url: "/westvalley/test.jsp?cmd=del",
					data:"id="+v,
					dataType: "text",
					success: function(data){
					var returnJson=eval("("+data+")");
						if(returnJson.result=="success"){
							alert("删除成功！");
							document.frmmain.submit();
						}else{
							alert("删除失败！");	
						}
					},
					error: function(msg){
						var t = msg;
						alert("删除异常！");
					}
				});
			}
			//导出
			function excelAll(){
				_xtable_getAllExcel();
			}
		</script>
	</head>
	<BODY>
		<%@ include file = "/systeminfo/TopTitle_wev8.jsp" %>
		<%@ include file="/systeminfo/RightClickMenuConent_wev8.jsp" %>
		<%
			RCMenu += "{"+SystemEnv.getHtmlLabelName(82529,user.getLanguage())+",javascript:onSubmit(),_self} " ;
			RCMenuHeight += RCMenuHeightStep ;
			RCMenu += "{重置,javascript:returnV(),_self} " ;
			RCMenuHeight += RCMenuHeightStep ;
			RCMenu += "{新增,javascript:optionUrl(),_self} " ;
			RCMenuHeight += RCMenuHeightStep ;
			RCMenu += "{批量删除,javascript:delAll(),_self} " ;
			RCMenuHeight += RCMenuHeightStep ;
			RCMenu += "{导出,javascript:excelAll(),_self} " ;
			RCMenuHeight += RCMenuHeightStep ;
		%>	
		<%@ include file="/systeminfo/RightClickMenu_wev8.jsp" %>
		<jsp:include page="/systeminfo/commonTabHead.jsp">
		   <jsp:param name="mouldID" value="assest"/>
		   <jsp:param name="navName" value="<%=titlename %>"/>
		</jsp:include>
		<table id="topTitle" cellpadding="0" cellspacing="0">
			<tr>
				<td>
				</td>
				<td class="rightSearchSpan" style="text-align:right;">
					<input type="button" value="<%=SystemEnv.getHtmlLabelNames("197",user.getLanguage())%>" class="e8_btn_top"  onclick="onSubmit()"/>
					<!--   <input type="button" value="新增" class="e8_btn_top"  onclick="optionUrl()"/>  -->
					<span title="<%=SystemEnv.getHtmlLabelName(23036,user.getLanguage())%>" class="cornerMenu"></span>
				</td>
			</tr>
		</table>
		<div>
		<form id="frmmain" name=frmmain method=post action="TestList.jsp" >
			<wea:layout  type="4col" attributes="{expandAllGroup:true}">
				<wea:group context="查询条件">
					<wea:item>物料名称</wea:item>
					<wea:item>
					<input name='Name' value="<%=Name %>" class="InputStyle" >
					</wea:item>
					
					
					<wea:item> 库存数量</wea:item>
					<wea:item>
					<input name='Num' value="<%=Num %>" class="InputStyle" >
					</wea:item>
					
					
					
					
				</wea:group>
				<wea:group context="">
					<wea:item attributes="{'colspan':'full','isTableList':'true'}">
							<%
								String sqlWhere = " WHERE 1=1 ";
							
								if(!"".equals(Name)){
									sqlWhere+=" and Name = '"+Name+"' ";
								}
								
								if(!"".equals(Num)){
									sqlWhere+=" and Num = '"+Num+"' ";
								}
								
								String backfields = " id, Name, Num, frozenNum ";
									//" ,convert(char(10),timer.third_adjust_in_time,108) third_adjust_in_time, convert(char(10),timer.third_adjust_out_time,108) third_adjust_out_time "+
								String fromSql  = "  formtable_main_181 ";
								String orderby = " id "; //
								
								//String poolname = "HRTEST";
								//out.print("select "+backfields+" from "+fromSql+sqlWhere+" orderby "+orderby);
								String tableString =" <table pagesize=\""+perpage+"\"  tabletype=\"checkbox\">"+
										" <sql backfields=\""+Util.toHtmlForSplitPage(backfields)+"\" "+
										//"  poolname=\""+poolname+"\" "+
										"  sqlform=\""+Util.toHtmlForSplitPage(fromSql)+"\" "+
										"  sqlwhere=\""+Util.toHtmlForSplitPage(sqlWhere)+"\"  "+
										"  sqlorderby=\""+orderby+"\" "+
										"  sqlprimarykey=\" id \" "+
										"  sqlsortway=\"desc\" sqlisdistinct=\"false\"/>"+
										"	<head>"+
										"		<col width=\"100%\" text=\"id\" column=\"id\" hide=\"true\" />"+
										"				<col width=\"10%\"  text=\"物料名称\" column=\"Name\" orderkey=\"Name\"/>"+
				                        "				<col width=\"10%\"  text=\"库存数量\" column=\"Num\"  orderkey=\"Num\"/>"+
				                        "				<col width=\"10%\"  text=\"库存冻结数量\" column=\"frozenNum\"  orderkey=\"frozenNum\"/>"+
										"	</head>"+
										"	<operates>"+
		        						"    	<operate href=\"javascript:del();\"  otherpara=\"column:id\" text=\"删除\" index=\"0\"/>"+
		        						"     	<operate href=\"javascript:optionUrl();\"  otherpara=\"column:id\" text=\"编辑\" index=\"1\"/>"+
			 	       					"	</operates>"+
										"</table>";
							%>
							<wea:SplitPageTag isShowTopInfo="false" tableString='<%=tableString%>' mode="run" />
					</wea:item>
				</wea:group>
			</wea:layout>
		</form>	
		</div>
	</body>

<SCRIPT language="javascript" defer="defer" src="/js/JSDateTime/WdatePicker_wev8.js"></script>
<SCRIPT language="javascript" defer="defer" src="/js/datetime_wev8.js"></script>
<SCRIPT language="javascript" src="/js/selectDateTime_wev8.js"></script>
</HTML>