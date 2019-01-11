/**
 * 出差报销申请流程--创建节点JS
 * @author Xuehui
 * @date 2018年8月28日
 * @version 1.0.0
 * @encoding utf-8
 * @url <script type="text/javascript" src="/westvalley/workflow-tirp.js"></script>
 * <p>
 * 主表表单元素调用：wf_main_fields.字段名称
 * 明细一表单元素调用：wf_dt1_fields.字段名称
 * 方法集主要包括：
 * 1.
 */

//引用公共js
document.write("<script type='text/javascript' src='/westvalley/cw/cw.js'></script>");


//主表
//表的字段以及前台的id
  var t0 = { 
		applyPerson :'field10709' /*申请人 */
	   ,applyDate :'field10710' /*申请日期*/ 
	  
    } 
 

  //明细表1
  var t1 = {
	Type : 'field10711', /*费用类型*/ 
	City : 'field10712', /*出差地点*/ 
	Money : 'field10713', /*申请费用*/
	sMoney : 'field10714', /*费用标准*/
	beginDate : 'field10715', /*开始日期*/
	endDate : 'field10716', /*结束日期*/
	days : 'field10717',/*出差天数*/
}

//明细表2
var t2 = {
	applyPerson : 'field10718', /*申请人 */
	applyDate : 'field10719', /*申请日期*/
	Type : 'field10720', /*费用类型*/
	City : 'field10721', /*出差地点*/
	Money : 'field10722', /*申请费用*/
}

jQuery(document).ready(function(){	
	
	
	/*jQuery("#"+t1.endDate+"_"+index).bind("change", function () {
		var beginDate=jQuery("#"+t1.beginDate+"_"+index).val();
		var endDate=jQuery("#"+t1.endDate+"_"+index).val();
		
		//jQuery("#"+t1.days+"_"+index).val();
		
	})*/
	
	//监听明细行增加
       jQuery("#indexnum0").bindPropertyChange(function(){
           //获取到明细下标
		var index = jQuery("#indexnum0").val()-1;
		
           //给新增的明细行字段添加监听事件
               jQuery("#"+t1.Type+"_"+index).bind("change",function(){
            	   var Type=jQuery("#"+t1.Type+"_"+index).val();//获取类型
            	   alert("类型改变了");
            	   alert("Type为"+Type);
            		   var City=jQuery("#"+t1.City+"_"+index).val();//获取城市
            		   alert("城市改变了");
                	   alert("City为"+City);
                	   
                	   jQuery.ajax({
               			type:"post",
               			url:"/westvalley/workflowServlet2.jsp?cmd=gettype",
               			data:"Type="+Type+"&City="+City,
               			dataType:"text",
               			async:false,
               			success:function(data){
               				var returnJson=eval("("+data+")");//取到回调函数
               				if (returnJson.msg==1) {
               					alert('returnJson.msg:无此流程信息请维护');
               					jQuery("#"+t1.sMoney+"_"+index).val("");
							}else{
								jQuery("#"+t1.sMoney+"_"+index).val(returnJson.msg);
							}
               				
               			},
               			error : function(data) {
               				top.Dialog.alert("获取信息失败，数据异常！");
               			}
               		});
                		
               });
     	});

	      		   
       //申请费用不能大于费用标准*出差天数
       jQuery("#indexnum0").bindPropertyChange(function(){
           //获取到明细下标
		var index = jQuery("#indexnum0").val()-1;
		
           //给新增的明细行字段添加监听事件
               jQuery("#"+t1.days+"_"+index).bind("change",function(){
            	   var Money=jQuery("#"+t1.Money+"_"+index).val();//获取申请费用
            	   var sMoney=jQuery("#"+t1.sMoney+"_"+index).val();//获取费用标准
            	   var days=jQuery("#"+t1.days+"_"+index).val();//获取出差天数
            	  
            	   
            	   jQuery.ajax({
              			type:"post",
              			url:"/westvalley/workflowServlet2.jsp?cmd=getpd",
              			data:"Money="+Money+"&sMoney="+sMoney+"&days="+days,
              			dataType:"text",
              			async:false,
              			success:function(data){
              				var returnJson=eval("("+data+")");
              				if (returnJson.msg==1) {
              					alert('申请费用不能大于费用标准*出差天数');
              					jQuery("#"+t1.days+"_"+index).val("");
							}else{
								alert('通过');
								
							}
              				
              			},
              			error : function(data) {
              				top.Dialog.alert("获取信息失败，数据异常！");
              			}
              		});
                		
               });
     	});     	   
            	  
            	  
    
     //监听明细表2增加
       jQuery("#indexnum1").bindPropertyChange(function(){
           //获取到明细下标
		var index = jQuery("#indexnum1").val()-1;
		
           //根据申请人，自动带出本月出差历史明细表数据
               jQuery("#"+t2.applyPerson+"_"+index).bind("change",function(){
            	   var applyPerson=jQuery("#"+t0.applyPerson).val();
            	  
            	   alert("applyPerson"+applyPerson);
            	   jQuery("#"+t2.applyPerson+"_"+index).val(applyPerson);
                	   
                	   jQuery.ajax({
               			type:"post",
               			url:"/westvalley/workflowServlet2.jsp?cmd=getMX",
               			data:"applyPerson="+applyPerson,
               			dataType:"text",
               			async:false,
               			success:function(data){
               				var returnJson=eval("("+data+")");
               				
               				jQuery("#"+t2.applyDate+"_"+index).val(returnJson.applyDate);
               				jQuery("#"+t2.Type+"_"+index).val(returnJson.Type);
               				jQuery("#"+t2.City+"_"+index).val(returnJson.City);
               				jQuery("#"+t2.Money+"_"+index).val(returnJson.Money);
               			},
               			error : function(data) {
               				top.Dialog.alert("获取信息失败，数据异常！");
               			}
               		});
                		
               });
     	});
          
	
	
	
	

	
	
});
  


//转换数据
function parseFloat_(v){
	return isNaN(v)? 0 : parseFloat(v);
}

