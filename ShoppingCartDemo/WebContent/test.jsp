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
	<div>
	<input type="checkbox" name="test" value="1"/>
	<span>1</span><input type="checkbox" name="test" value="2"/>
	<span>2</span><input type="checkbox" name="test" value="3"/>
	<span>3</span><input type="checkbox" name="test" value="4"/>
	<span>4</span><input type="checkbox" name="test" value="5"/>
	</div>
	<div>
	<span>5</span><input type='button' value='提交' onclick="show()"/>
	</div>
	
	${shoppingcarts}
</body>
<script type="text/javascript">
function show(){
    obj = document.getElementsByName("test");
    check_val = [];
    for(k in obj){
        if(obj[k].checked)
            check_val.push(obj[k].value);
    }
    alert(check_val);
}

</script>
</html>