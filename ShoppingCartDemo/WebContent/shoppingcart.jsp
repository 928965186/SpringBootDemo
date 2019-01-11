<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<script src="scripts/jquery-2.1.0.js" type="text/javascript"></script>
</head>
<body>
	商品名：<input id="search" name="search" type="text"><input type="button" value="模糊查询" onclick="onserch()">
	 <c:forEach var="c" items="${shoppingcarts}">
	<table >
		<tr>
		<td>
        <input type="checkbox" name="item" id="item" value="${c.shoppingcartid}" >
        </td>
        <td>${c.shoppingcartid}</td>
        <td>${c.shoppingcartname}</td>
		</tr>
		
	</table>
	</c:forEach> 
	
	<table class="table"> 
	<tr>
	
	<th>商品id</th>
	<th>商品名称</th>
	</tr> 
	</table>
	<input type="button" value="删除" onclick="ondelete()"><br>
	<a href="shopping.do">继续选购</a>
</body>

<script type="text/javascript">
onserch()
function ondelete() {
	obj = document.getElementsByName("item");
    check_val = [];
    for(k in obj){
        if(obj[k].checked)
            check_val.push(obj[k].value);
    }
   if (check_val==null || check_val=="") {
	alert('当前没有选中任何数据！')
}else{
	
	$.ajax({
		url : "deleteCart",// 请求的servlet地址
		type : "GET",// 请求方式
		data : "cid=" + check_val,// 发送到服务器的数据
		dataType : "text",// 设置返回数据类型
		success : function(data) {
			
			alert("操作成功!")
			reloadPage()
		},// 响应成功后执行的回调方法data响应文本
		complete : function(XMLHttpRequest, statusText) {

		},// 响应完成后执行的回调方法
		error : function(XMLHttpRequest, statusText) {
			alert("操作失败!")
		}// 响应失败后执行的回调方法
	})
}
}

function onserch() {
	var text=document.getElementById("search");
	
		/* if (text.value==null||text.value=="") {
			type: 'post',
			url: 'ShoppingCartServlet',
			data : "text=" + text.value,
			dataType: 'json',
			success:function(data){
			var item;
			$.each(data,function(i,result){
			item=
			"<tr><td>"+result['shoppingcartid']+"</td><td>"+result['shoppingcartname']+"</td></tr>"; 
			$('.table').append(item); 
			}); 
		}else{ */
		
		$.ajax({
			type: 'post',
			url: 'search',
			data : "text=" + text.value,
			dataType: 'json',
			success:function(data){
			var item;
			$.each(data,function(i,result){
			item=
			"<tr><td>"+result['shoppingcartid']+"</td><td>"+result['shoppingcartname']+"</td></tr>"; 
			$('.table').append(item); 
			}); 
			},

			});
	}


function reloadPage()
{
window.location.reload()
}
</script>
</html>