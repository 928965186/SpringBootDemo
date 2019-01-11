<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="" name="ff" method="post"> 
 <input type="text" name="input1" onKeyUp="keydown()">
 <br> 
  <input type="text" name="input2">ujhhjn
  </form>
</body>
<script language="JavaScript">
function keydown()
{ 

if(document.ff.input1.value.substring(3,6)=="074")
{
document.ff.input2.value="计算机学院";
}
}
</script>
</html>