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



//if(!HrmUserVarify.checkUserRight("CE_CustomerSystemReportMaint", user) ) { 
//        response.sendRedirect("/notice/noright.jsp");         
//        return;     
//  }
	String perpage ="10";
	String imagefilename = "/images/hdReport_wev8.gif";
	String titlename = "报销流程";
	String needfav ="1";
	String needhelp ="";
	
	String riqi = Util.null2String(request.getParameter("riqi"));//日期
	String name = Util.null2String(request.getParameter("name"));//科目名称
	String code = Util.null2String(request.getParameter("code"));//科目编码
	String code1 = Util.null2String(request.getParameter("code1"));//科目编码
	String code2 = Util.null2String(request.getParameter("code2"));//科目编码
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
				var url = "/westvalley/Add.jsp?id="+v;
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
			
			//批量删除
			function delAll(){
				var ids = _xtable_CheckedCheckboxId();
				if(ids != ""){
					var isDelete=confirm("确认要删除所选项吗？");
					if(isDelete==true){
						jQuery.ajax({
							type: "POST",
							url: "/westvalley/report/ccqy/operation.jsp",
							data:"id="+ids,
							dataType: "text",
							success: function(data){
								if(jQuery.trim(data)=="y"){
									//alert("删除成功！");
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
					url: "/westvalley/report/ccqy/operation.jsp",
					data:"id="+v,
					dataType: "text",
					success: function(data){
						if(jQuery.trim(data)=="y"){
							//alert("删除成功！");
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
			RCMenu += "{删除,javascript:delAll(),_self} " ;
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
		<form id="frmmain" name=frmmain method=post action="DemoList.jsp" >
			<wea:layout  type="4col" attributes="{expandAllGroup:true}">
				<wea:group context="查询条件">
					<wea:item>申请人</wea:item>
					<wea:item>
					<input name='code1' value="<%=code1 %>" class="InputStyle" >
					</wea:item>
					 
					<wea:item>借款金额</wea:item>
					<wea:item>
					<input name='name' value="<%=name %>" class="InputStyle" >
					</wea:item>
					
					<wea:item>部门</wea:item>
					<wea:item>
						<brow:browser viewType="0"  name="code2" browserValue="<%=code2 %>"
							 browserurl="/systeminfo/BrowserMain.jsp?url=/hrm/company/DepartmentBrowser.jsp"  
						   hasInput="true" isSingle="false" hasBrowser = "true" isMustInput='1'
						   completeUrl="/data.jsp?type=4" width="165px"
						   browserSpanValue="<%=code2 %>">
						 </brow:browser>
					</wea:item>
					
					<wea:item>科目</wea:item>
					<wea:item>
						<brow:browser viewType="0"  name="code" browserValue="<%=code %>"
							 browserurl="/systeminfo/BrowserMain.jsp?url=/interface/CommonBrowser.jsp?type=browser.code2"  
						   hasInput="true" isSingle="true" hasBrowser = "true" isMustInput='1'
						   completeUrl="/data.jsp?type=4" width="165px"
						   browserSpanValue="<%=code %>">
						 </brow:browser>
					</wea:item>
					
					<wea:item>日期</wea:item>
					<wea:item>
						<button type=button  class=Calendar id=selectenddate onClick="getDate('ShuikuanMonthspan','ShuikuanMonth')"></button>
		                <span id=ShuikuanMonthspan ><%=riqi %></span>
		            	<input type="hidden" name="ShuikuanMonth" id="ShuikuanMonth" value="<%=riqi %>">
					</wea:item>
					
					<wea:item>是否可抵扣</wea:item>
					<wea:item>
						<select name="shifdk">
							<option value="" <% if(shifdk.equals("") ){%>selected<%}%>>全部</option>
							<option value="1" <% if(shifdk.equals("1") ){%>selected<%}%>>是</option>
							<option value="0" <% if(shifdk.equals("0") ){%>selected<%}%>>否</option>
						</select>
					</wea:item>
					
					
				</wea:group>
				<wea:group context="">
					<wea:item attributes="{'colspan':'full','isTableList':'true'}">
							<%
								String sqlWhere = " WHERE 1=1 ";
							/**
								if(!"".equals(name)){
									sqlWhere+=" and name = '"+name+"' ";
								}
								
								if(!"".equals(code)){
									sqlWhere+=" and code = '"+code+"' ";
								}
								
								if(!"".equals(code1)){
									sqlWhere+=" and code1 = '"+code1+"' ";
								}
								
								if(!"".equals(code2)){
									sqlWhere+=" and code2 = '"+code2+"' ";
								}
								*/
								
								
								String backfields = " id, Hrm, money, usingMoney, usedMoney ";
									//" ,convert(char(10),timer.third_adjust_in_time,108) third_adjust_in_time, convert(char(10),timer.third_adjust_out_time,108) third_adjust_out_time "+
								String fromSql  = "  formtable_main_176 ";
								String orderby = " id "; //
								out.print(backfields+" "+fromSql+" "+sqlWhere);
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
										"				<col width=\"10%\"  text=\"申请人\" column=\"Hrm\" orderkey=\"Hrm\"/>"+
				                         //"				<col width=\"20%\"  text=\"部门名称\" column=\"workflowname\"  orderkey=\"wb.id\" href=\"/lj/zhzf/ProcessFilingToArchiveEditInfo.jsp?1=1\" linkkey=\"wb.id\" linkvaluecolumn=\"id\" target=\"_self\" />"+
				                        "				<col width=\"10%\"  text=\"借款金额\" column=\"money\"  orderkey=\"money\"/>"+
				                        "				<col width=\"10%\"  text=\"冻结金额\" column=\"usingMoney\"  orderkey=\"usingMoney\"/>"+
				                        "				<col width=\"10%\"  text=\"未冻结金额\" column=\"usedMoney\"  orderkey=\"usedMoney\"/>"+
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