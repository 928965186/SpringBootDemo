<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品页面</title>
<script src="scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script type="text/javascript" src="scripts/product_view.js"></script>

</head>
<body>
	<div>
	<c:forEach items="${list}" var="p" varStatus="status">
	
	<table>
    <tr>
        <td>
        <input type="checkbox" name="item" id="item" value="${p.shoppingid}" >
        </td>
        <td>
            ${p.shoppingid}
        </td>
        <td>
            ${p.shoppingname}
        </td>
    </tr>
    </table>
	<br/>
	
	</c:forEach>
	<input type="button" value="加入购物车" onclick="show()"><br>
	
	<a href="ShoppingCartServlet">打开购物车</a>
	</div>
</body>
	<script type="text/javascript">
	
	
	function show(){
	    obj = document.getElementsByName("item");
	    check_val = [];
	    for(k in obj){
	        if(obj[k].checked)
	            check_val.push(obj[k].value);
	    }
	   if (check_val==null || check_val=="") {
		alert('当前没有选中任何数据！')
	}else{
		alert('感谢选择'+check_val)
		
	    $.ajax({
			url : "addToCart",// 请求的servlet地址
			type : "GET",// 请求方式
			data : "pidNum=" + check_val,// 发送到服务器的数据
			dataType : "text",// 设置返回数据类型
			
			
			success : function(total) {
				$("#msg").html(total);
				
				//alert("成功添加到购物车!")
			},// 响应成功后执行的回调方法data响应文本
			complete : function(XMLHttpRequest, statusText) {
				
			},// 响应完成后执行的回调方法
			error : function(XMLHttpRequest, statusText) {
				alert("添加到购物车失败!")
			}// 响应失败后执行的回调方法
		})
		
	}
	}

	</script>
</html>