<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="weaver.general.Util" %> 
<%@ include file="/systeminfo/init_wev8.jsp" %>
<%@ taglib uri="/WEB-INF/weaver.tld" prefix="wea"%>
<%@ taglib uri="/browserTag" prefix="brow"%>
<jsp:useBean id="rs" class="weaver.conn.RecordSet" scope="page"/>
<%
/**
if(!HrmUserVarify.checkUserRight("System:LabelManage", user)){
	response.sendRedirect("/notice/noright.jsp");
	return;
}**/
String imagefilename = "/images/hdMaintenance_wev8.gif";
String titlename = "新增OA部门与U8部门对照关系";
String needfav ="1";
String needhelp ="";
String id = Util.null2String(request.getParameter("id"));

String isclose = Util.null2String(request.getParameter("isclose"));
String error = Util.null2String(request.getParameter("error"));
String code = Util.null2String(request.getParameter("code"));
String name = Util.null2String(request.getParameter("name"));
String supdeptid = Util.null2String(request.getParameter("supdeptid"));
String supdeptname = Util.null2String(request.getParameter("supdeptname"));//变更内容
String cengji = Util.null2String(request.getParameter("cengji"));//变更内容
String remark = Util.null2String(request.getParameter("remark"));//变更内容
String depthead = Util.null2String(request.getParameter("depthead"));//变更内容
String deptheadname = Util.null2String(request.getParameter("deptheadname"));//变更内容
String leader = Util.null2String(request.getParameter("leader"));//变更内容
String leadername = Util.null2String(request.getParameter("leadername"));//变更内容
String costsys = Util.null2String(request.getParameter("costsys"));//变更内容
String ifdept = Util.null2String(request.getParameter("ifdept"));//变更内容
String sffc = Util.null2String(request.getParameter("sffc"));//变更内容

if(!"".equals(id)){
	rs.executeSql("select t.id,t.code,t.name,t.supdeptid,t.supdeptname,t.cengji,t.remark,t.depthead,"+
		"t.deptheadname,t.leader,t.leadername,t.costsys,t.ifdept,(case when t.ifdept=0 then '是' when t.ifdept=1 then '否' else '是' end ) isdept,t.sffc from (select a.*,h.lastname as deptheadname,h1.lastname as leadername,y.name as supdeptname  from uf_yusbm a left join hrmresource h on a.depthead=h.id left join hrmresource h1 on a.leader=h1.id left join uf_yusbm y on a.supdeptid=y.id) t where t.id='"+id+"'");
	if(rs.next()){
		 code = Util.null2String(rs.getString("code"));
		 name = Util.null2String(rs.getString("name"));
		 supdeptid = Util.null2String(rs.getString("supdeptid"));
		 supdeptname = Util.null2String(rs.getString("supdeptname"));//变更内容
		 cengji = Util.null2String(rs.getString("cengji"));//变更内容
		 remark = Util.null2String(rs.getString("remark"));//变更内容
		 depthead = Util.null2String(rs.getString("depthead"));//变更内容
		 deptheadname = Util.null2String(rs.getString("deptheadname"));//变更内容
		 leader = Util.null2String(rs.getString("leader"));//变更内容
		 leadername = Util.null2String(rs.getString("leadername"));//变更内容
		 costsys = Util.null2String(rs.getString("costsys"));//变更内容
		 ifdept = Util.null2String(rs.getString("ifdept"));//变更内容
		 sffc = Util.null2String(rs.getString("sffc"));//变更内容
	}
}
%>
<HTML><HEAD>
<LINK href="/css/Weaver_wev8.css" type=text/css rel=STYLESHEET>
<link rel="stylesheet" href="/wui/theme/ecology8/jquery/js/zDialog_e8_wev8.css" type="text/css" />
<script language=javascript src="/js/weaver_wev8.js"></script>
<script language="javascript" src="/wui/theme/ecology8/jquery/js/zDialog_wev8.js"></script>
<script language=javascript src="/js/ecology8/request/e8.browser_wev8.js"></script>

<script language=javascript >
jQuery(document).ready(function(){
	setBudgetHide();
});
//在被打开的页面中，使用如下语句获取父窗口对象：
 parentWin = parent.getParentWindow(window);
//在被打开的页面中，使用如下语句获取Dialog对象：
 dialog = parent.getDialog(window);


// var parentWin = parent.parent.getParentWindow(parent);
// var dialog = parent.parent.getDialog(parent);

if("<%=error%>"=="1"){
	top.Dialog.alert("<%=SystemEnv.getHtmlLabelName(31877,user.getLanguage())%>");
}
if("<%=isclose%>"=="1"){
	closeDialog();
}
function closeDialog(){
	try{
		parentWin._table.reLoad();
	}catch(e){}
	dialog.close();
	//parent.document.getElecmentById("#pp").window('close'); 
	 	//parent.parent.getDialog(parent).close();	
}
setTimeout(function(){
	setBudgetHide();
},1500);
function onSave(){
		if(checkMust()||checkDept()||equalsup()){
			return;
		}
		var param = $("#frmmain").serialize();
		$.ajax({
			type: "POST",
			data: param,
			url: '/westvalley/budget/budgetDept/edit/operation.jsp?cmd=save',
			dataType : "text",
			success: function(data){
				data= data.replace(/^\s*|\s*$/,'');
				data = eval("(" + data + ")");
 				if(data.result=='success'){
 					alert("保存成功");
 				}else{
					top.Dialog.alert(data.error);
				}
			},
			error: function(data){
			}
		});
	
}
function setBudgetHide(){
		var tiaoznf=jQuery("#nianf").val();
		var tiaozysbm=jQuery("#yusbm").val();
		var tiaozyskm=jQuery("#yuskm").val();
		if(tiaoznf =="" || tiaozysbm==""||tiaozyskm==""){
			return;
		}
		jQuery.ajax({
			type:"post",
			url:"/westvalley/workflow/workflowServlet.jsp?cmd=getBudgetDetail",
			data:"tiaoznf="+tiaoznf+"&tiaozysbm="+tiaozysbm+"&tiaozyskm="+tiaozyskm,
			dataType:"text",
			async:false,
			success:function(data){
				var returnJson=eval("("+data+")");
				if(returnJson.totalCount>0){
					var havedMonth=returnJson.detail[0].havedMonth;
					if(havedMonth!="" && havedMonth !=undefined){
						if(havedMonth.indexOf("1,")>-1){
							setHideBor("a1");
						};
						if(havedMonth.indexOf("2,")>-1){
							setHideBor("ery");
						};
						if(havedMonth.indexOf("3,")>-1){
							setHideBor("a3");
						};
						if(havedMonth.indexOf("4,")>-1){
							setHideBor("a4");
						};
						if(havedMonth.indexOf("5,")>-1){
							setHideBor("a5");
						};
						if(havedMonth.indexOf("6,")>-1){
							setHideBor("a6");
						};
						if(havedMonth.indexOf("7,")>-1){
							setHideBor("a7");
						};
						if(havedMonth.indexOf("8,")>-1){
							setHideBor("a8");
						};
						if(havedMonth.indexOf("9,")>-1){
							setHideBor("a9");
						};
						if(havedMonth.indexOf("10,")>-1){
							setHideBor("a10");
						};
						if(havedMonth.indexOf("11,")>-1){
							setHideBor("a11");
						};
						if(havedMonth.indexOf("12,")>-1){
							setHideBor("a12");
						};
					}
				}
			}
		});
}
//确认必填
function checkMust(){
	var must=false;
	var code=jQuery("#code").val();//预算部门
	var name=jQuery("#name").val();//预算科目
	var supdeptid=jQuery("#supdeptid").val();//预算年份
	var depthead=jQuery("#depthead").val();//预算年份
	var leader=jQuery("#leader").val();//预算年份
	var sffc=jQuery("#sffc").val();//是否封存
	if(code==""){
		alert("'预算部门编码'未填写");
		must=true;
		return must;
	}
	if(name==""){
		alert("'预算部门名称'未填写");
		must=true;
		return must;
	}
	if(depthead==""){
		alert("'部门负责人'未填写");
		must=true;
		return must;
	}
	if(leader==""){
		alert("'分管领导'未填写");
		must=true;
		return must;
	}
	if(sffc==""){
		alert("'是否封存'未填写");
		must=true;
		return must;
	}
	return must;
}
function equalsup(){
	var ifhave=false;//数据是否存在
	var supdeptid=jQuery("#supdeptid").val();
	var id=jQuery("#id").val();
	if(id==undefined || id==""){
		return ifhave;
	}
	if(id==supdeptid){
		alert("上级预算部门不可为本身");
		return true;
	}
}
function checkValue(){
	var ifhave=false;//数据是否存在
	var id=jQuery("#id").val();
	if(id==undefined || id==""){
		id="-1";
	}
	var yusbm=jQuery("#yusbm").val();//预算部门
	var yuskm=jQuery("#yuskm").val();//预算科目
	var nianf=jQuery("#nianf").val();//预算年份
	jQuery.ajax({
		type:"post",
		url:"/westvalley/budget/budgetOperation.jsp?cmd=checkValue",
		data:"yusbm="+yusbm+"&yuskm="+yuskm+"&nianf="+nianf+"&id="+id,
		dataType:"text",
		async:false,
		success:function(data){
			var returnJson=eval("("+data+")");
			var ifhave1=returnJson.check;
			if(ifhave1=="true"){
				ifhave=true;
				alert("数据已存在");
			}
		}
	});
	return ifhave;
}
function checkDept(){
	var ifhave=false;
	var yusbm=jQuery("#code").val();//预算部门
	var id=jQuery("#id").val();
	if(id==undefined || id==""){
		id="-1";
	}
	jQuery.ajax({
		type:"post",
		url:"/westvalley/budget/budgetOperation.jsp?cmd=checkDept",
		data:"yusbm="+yusbm+"&id="+id,
		dataType:"text",
		async:false,
		success:function(data){
			var returnJson=eval("("+data+")");
			var ifhave1=returnJson.check;
			if(ifhave1=="true"){
				ifhave=true;
				alert("部门编码已存在");
			}
		}
	});
	return ifhave;
}
function showyusbm(input,span,spanimg){
	var url="";	
	var yusbm= jQuery("#"+input).val();
	if(yusbm !=""){
		url="/systeminfo/BrowserMain.jsp?url=/formmode/tree/treebrowser/CustomTreeBrowser.jsp?type=3_256&selectedids="+yusbm;
	}else{
		url="/systeminfo/BrowserMain.jsp?url=/formmode/tree/treebrowser/CustomTreeBrowser.jsp?type=3_256";
	}
	url="/systeminfo/BrowserMain.jsp?url=/interface/CommonBrowser.jsp?type=browser.yusbm";
	open(url,input,span,"");
}

function showhrm(input,span,spanimg){
	var hrmid=jQuery("#"+input).val(); 
	var url="/systeminfo/BrowserMain.jsp?url=/formmode/browser/CommonSingleBrowser.jsp?customid=26";
	open(url,input,span,spanimg);
}
function open(url,inputname,spanname,spanimg){
		if(window.top.Dialog){
			diag_field = new window.top.Dialog();
		} else {
			diag_field = new Dialog();
		}
		diag_field.currentWindow = window;
		diag_field.Width = 600;
		diag_field.Height = 650;
		diag_field.Drag = true;
		diag_field.URL = url;
		//返回值
		diag_field.callback=function(v){
			jQuery("#"+inputname).val(v.id);
			jQuery("#"+spanname).html(v.name);
			if(v.id==""){
				jQuery("#"+spanimg).html("<img align='absmiddle' src='/images/BacoError_wev8.gif'>");
			}else{
				jQuery("#"+spanimg).html("");
			}
			datainput(inputname);
			diag_field.close();
		};
		diag_field.show();
}



/**
 * input设置为可编辑
 * 
 * @param id
 */
function setShow(id){
	jQuery("#" + id).css("border", '1px solid #e5e5e5');
	jQuery("#" + id).attr("readonly", false);
	
	jQuery("#"+id+"span_new").remove();
	jQuery("#"+id+"_browserbtn").show();//显示按钮
	jQuery("#out"+id+"div").show();
}

function onreturn(){
	document.frmmain.action="/westvalley/budget/budgetValue/budgetEdit/budgetList.jsp?hasTab=1";
	document.frmmain.submit();
}

//显示 不可编辑 只读
function setHideBor(id){
	jQuery("#" + id).attr("readonly", true);
	jQuery("#" + id).css("border", '0px solid #e5e5e5');
	
	jQuery("#"+id+"_browserbtn").hide();//隐藏按钮

	jQuery("#"+id+"span_new").remove();
	jQuery("#out"+id+"div").before("<span id='"+id+"span_new'>"+ jQuery("#"+id+"span").html()+"</span>");
	jQuery("#out"+id+"div").hide();
}
</script>


</head>
<%

%>
<BODY>
<div class="zDialog_div_content" id="pp" >
<%@ include file="/systeminfo/TopTitle_wev8.jsp" %>

<%@ include file="/systeminfo/RightClickMenuConent_wev8.jsp" %>
<%
RCMenu += "{"+SystemEnv.getHtmlLabelName(86,user.getLanguage())+",javascript:onSave(),_top} " ;
RCMenuHeight += RCMenuHeightStep ;
RCMenu += "{返回,/westvalley/budget/budgetDept/edit/budgetList.jsp?hasTab=1,_self} ";
RCMenuHeight += RCMenuHeightStep ;
%>
<%@ include file="/systeminfo/RightClickMenu_wev8.jsp" %>
<table id="topTitle" cellpadding="0" cellspacing="0">
	<tr>
		<td>
		</td>
		<td class="rightSearchSpan" style="text-align:right;">
			<input type="button" value="<%=SystemEnv.getHtmlLabelName(30986,user.getLanguage())%>" class="e8_btn_top" onclick="onSave();">
			<span title="<%=SystemEnv.getHtmlLabelName(23036,user.getLanguage())%>" class="cornerMenu"></span>
		</td>
	</tr>
</table>
<form id="frmmain" name="frmmain" action="/westvalley/budget/budgetValue/budgetEdit/operation.jsp" method=post>
<input type=hidden value="save" name="cmd">
<input id="id" name="id" type=hidden value="<%=id %>">
<wea:layout type="4col">
		<wea:group context="<%= SystemEnv.getHtmlLabelName(1361,user.getLanguage())%>">
			<wea:item>借款金额</wea:item>
			<wea:item>
				<wea:required id="codespan" required="true" value="<%=code %>">
					<INPUT onChange="checkinput('code','codespan');"  class="InputStyle"  value="<%=code %>" width="100px"  name="code" id="code" temptitle="预算部门编码">
				</wea:required>
			</wea:item>
			<wea:item>预算部门名称</wea:item>
			<wea:item>
				<wea:required id="namespan" required="true" value="<%=name %>">
					<INPUT onChange="checkinput('name','namespan');"  class="InputStyle"  value="<%=name %>" width="100px"  name="name" id="name" temptitle="预算部门名称">
				</wea:required>
			</wea:item>
			<wea:item>上级预算部门</wea:item>
			<wea:item>
	          	<brow:browser viewType="0" id="supdeptid" name="supdeptid" browserValue="<%=supdeptid %>"
                browserOnClick="showyusbm('supdeptid','supdeptidspan','supdeptidspanimg')" 
                hasInput="false" isSingle="true" hasBrowser="true" isMustInput='1' width="200px" 
               	completeUrl="/data.jsp" linkUrl="" arguments="12" browserSpanValue="<%=supdeptname %>"></brow:browser>
			</wea:item>
			<wea:item>备注</wea:item>
			<wea:item>
					<INPUT  class="InputStyle"  value="<%=remark %>" width="100px"  name="remark" id="remark" temptitle="备注">
			</wea:item>
			
			<wea:item>部门负责人</wea:item>
			<wea:item>
	          	<brow:browser viewType="0" id="depthead" name="depthead" browserValue="<%=depthead %>"
                browserOnClick="showhrm('depthead','deptheadspan','deptheadspanimg')" 
                hasInput="false" isSingle="true" hasBrowser="true" isMustInput='2' width="200px" 
               	completeUrl="/data.jsp" linkUrl="" arguments="12" browserSpanValue="<%=deptheadname %>"></brow:browser>
			</wea:item>
			
			<wea:item>分管领导</wea:item>
			<wea:item>
	          	<brow:browser viewType="0" id="leader" name="leader" browserValue="<%=leader %>"
                browserOnClick="showhrm('leader','leaderspan','leaderspanimg')" 
                hasInput="false" isSingle="true" hasBrowser="true" isMustInput='2' width="200px" 
               	completeUrl="/data.jsp" linkUrl="" arguments="12" browserSpanValue="<%=leadername %>"></brow:browser>
			</wea:item>
			<wea:item>是否预算部门</wea:item>
			<wea:item>
				<wea:required id="ifdeptspan" required="true" value="<%=ifdept %>">
					<select id="ifdept" name="ifdept" onChange="checkinput('kuanian','kuanianspan');" value="<%= ifdept%>">
						<option value=''></option>
						<option value='0' <% if("0".equals(ifdept)){%>selected<%} %>>是</option>
						<option value='1' <% if("1".equals(ifdept)){%>selected<%} %>>否</option>
					</select>
				</wea:required>
			</wea:item>
			<wea:item>费用体系</wea:item>
			<wea:item>
					<INPUT  class="InputStyle"   value="<%=costsys %>" width="200px"  name="costsys" id="costsys" temptitle="费用体系"
	          		>      		
			</wea:item>
			<wea:item>是否封存</wea:item>
			<wea:item>
				<wea:required id="sffcspan" required="true" value="<%=sffc %>">
					<select id="sffc" name="sffc" onChange="checkinput('kuanian','kuanianspan');" value="<%= sffc%>">
						<option value=''></option>
						<option value='0' <% if("0".equals(sffc)){%>selected<%} %>>是</option>
						<option value='1' <% if("1".equals(sffc)){%>selected<%} %>>否</option>
					</select>
				</wea:required>
			</wea:item>
		</wea:group>
	</wea:layout>
</FORM>
	</div>
	<div id="zDialog_div_bottom" class="zDialog_div_bottom">
		<wea:layout needImportDefaultJsAndCss="false">
			<wea:group context=""  attributes="{\"groupDisplay\":\"none\"}">
				<wea:item type="toolbar">
			    	<%--<input type="button" value="保存" class="zd_btn_submit" onclick="onSave();"/> --%>
			    	<input type="button" value="<%=SystemEnv.getHtmlLabelName(309,user.getLanguage())%>" id="zd_btn_cancle"  class="zd_btn_cancle" onclick="dialog.closeByHand()"/>
				</wea:item>
			</wea:group>
		</wea:layout>
	</div>
	<script type="text/javascript">
		jQuery(document).ready(function(){
			resizeDialog(document);
		});
	</script>
</BODY></HTML>