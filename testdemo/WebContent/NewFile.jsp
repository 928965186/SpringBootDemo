<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="scripts/jquery-2.1.0.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
<table style="border-collapse:separate; border-spacing:0px 10px;">
 <tr>
  <td align="right">应收现金：</td>
  <td><input id="accountReceivable" ></td>
  <td>元，折扣：</td>
  <td><input id="vipdiscount"  ></td>
  <td align="right">实收：</td>
  <td><input id="receipts" ></td>
  <td>元 </td>
  <td>消费类型：<select id="conType"><option value="1" >报名费</option><option value="2" >酒水零食费</option></select></td>
  <td><a id="btnSend" class="buttom">提交</a></td>
									   
 </tr>
</table>

</body>
<script type="text/javascript">
$(function(){  
	 
	$('#accountReceivable').bind('input propertychange', function() {  
		var a = $('#accountReceivable').val();
		var b = $('#vipdiscount').val();
		var sum= a * b;
		
	    $('#receipts').val(sum);  
	}); 
  
})  

</script>

</html>