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
String titlename = "新建:物料信息";
String needfav ="1";
String needhelp ="";
String id = Util.null2String(request.getParameter("id"));

String Name = "";
String Num = "";
String frozenNum = "";


if(!"".equals(id)){
	rs.executeSql("select Name,Num,frozenNum from formtable_main_181 where id='"+id+"'");
	if(rs.next()){
		Name = rs.getString("Name");
		Num = rs.getString("Num");
		frozenNum = rs.getString("frozenNum");
		 
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

<%-- if("<%=error%>"=="1"){
	top.Dialog.alert("<%=SystemEnv.getHtmlLabelName(31877,user.getLanguage())%>");
} --%>
<%-- if("<%=isclose%>"=="1"){
	closeDialog();
} --%>
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
		
		var param = $("#frmmain").serialize();
		var id=jQuery("#id").val();
		var Num=jQuery("#Num").val();
		var Name=jQuery("#Name").val();
		$.ajax({
			type: "POST",
			data: "Num="+Num+"&Name="+Name+"&id="+id,
			url: '/westvalley/test.jsp?cmd=save',
			dataType : "text",
			success: function(data){
				data= data.replace(/^\s*|\s*$/,'');
				data = eval("(" + data + ")");
 				if(data.result=='success'){
 					alert("保存成功");
 					closeDialog();
 					window.location.reload();
 				}else{
					top.Dialog.alert(data.error);
				}
			},
			error: function(data){
				alert("保存失败")
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
	var Num=jQuery("#Num").val();//预算部门
	var Name=jQuery("#Name").val();//预算科目
	
	if(Num==""){
		alert("'库存数量'未填写");
		must=true;
		return must;
	}
	if(Name==""){
		alert("'物料名称'未填写");
		must=true;
		return must;
	}
	return must;
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
<form id="frmmain" name="frmmain" action="/westvalley/test.jsp" method=post>
<input type=hidden value="save" name="cmd">
<input id="id" name="id" type=hidden value="<%=id %>">
<wea:layout type="4col">
		<wea:group context="<%= SystemEnv.getHtmlLabelName(1361,user.getLanguage())%>">
			<wea:item>物料名称</wea:item>
			<wea:item>
					<INPUT  class="InputStyle"  value="<%=Name %>" width="100px"  name="Name" id="Name" temptitle="物料名称">
			</wea:item>
			<wea:item>库存数量</wea:item>
			<wea:item>
					<INPUT  class="InputStyle"   value="<%=Num %>" width="200px"  name="Num" id="Num" temptitle="库存数量"
	          		>      		
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