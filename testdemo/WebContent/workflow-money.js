/**
 * 出差报销申请流程--创建节点JS
 * 
 * @author Xuehui
 * @date 2018年8月28日
 * @version 1.0.0
 * @encoding utf-8
 * @url <script type="text/javascript" src="/westvalley/workflow-money.js"></script>
 *      <p>
 *      主表表单元素调用：wf_main_fields.字段名称 明细一表单元素调用：wf_dt1_fields.字段名称 方法集主要包括： 1.
 */

// 引用公共js


// 主表
// 表的字段以及前台的id
var t0 = {
	applyPerson : 'field9026',/* 申请人 */
	applyDate : 'field9010',/* 申请日期 */
	Money : 'field9011',/* 报销金额合计 */
	loanMoney : 'field9012',/* 借款金额*/
	sMoney : 'field9013',/* 本次冲销金额 */

}

// 明细表1
var t1 = {
	Type : 'field9014', /* 费用类型 */
	Money : 'field9015', /* 报销费用 */
}

jQuery(document).ready(function(){	
	
	//监听明细行增加
       jQuery("#indexnum0").bindPropertyChange(function(){
           //获取到明细下标
		var index = jQuery("#indexnum0").val()-1;
		
           //给新增的明细行字段添加监听事件
               jQuery("#"+t1.Money+"_"+index).bind("change",function(){
            	   var Money=jQuery("#"+t1.Money+"_"+index).val();//获取明细表输入
            	   
            	  
            	   var num="";//定义一个var
            	   if (index==0) {
            		   jQuery("#"+t0.Money).val(Money);//如果明细表只有一行的时候，直接处理
   					}else{
   						for (var i = 0; i <= index; i++) {
   							var Money=jQuery("#"+t1.Money+"_"+i).val();//如果明细表不止一行，将数据一同传去后台处理
   							
							num+=Money+";";
							 
						}
   						jQuery.ajax({
	              			type:"post",
	              			url:"/westvalley/workflowServlet3.jsp?cmd=getfl",//定义的url
	              			data:"num="+num,//传参
	              			dataType:"text",
	              			async:false,//同步
	              			success:function(data){
	              				var returnJson=eval("("+data+")");//取到回调函数
	              				if (returnJson==1) {
									alert("输入有误请重新再试")
								}else{
									jQuery("#"+t0.Money).val(returnJson.n);
								}
	           					
	              			},
	              			error : function(data) {
	              				top.Dialog.alert("获取信息失败，数据异常！");
	              			}
	              		});  
   					}
            	   
            	  
                		
               });
     	});

	      		   
       //本次冲销金额不能大于报销金额合计、不能大于借款金额
       jQuery("#indexnum0").bindPropertyChange(function(){
           //获取到明细下标
		var index = jQuery("#indexnum0").val()-1;
		
           //给新增的明细行字段添加监听事件
               jQuery("#"+t0.sMoney).bind("change",function(){
            	   var Money=jQuery("#"+t0.Money).val();
            	   var sMoney=jQuery("#"+t0.sMoney).val();
            	   var loanMoney=jQuery("#"+t0.loanMoney).val();
            	   
            	  
            	   
            	   jQuery.ajax({
              			type:"post",
              			url:"/westvalley/workflowServlet3.jsp?cmd=getpd",
              			data:"Money="+Money+"&sMoney="+sMoney+"&loanMoney="+loanMoney,
              			dataType:"text",
              			async:false,
              			success:function(data){
              				var returnJson=eval("("+data+")");
              				if (returnJson.msg==1) {
              					alert('本次冲销金额不能大于报销金额合计、不能大于借款金额');
              					jQuery("#"+t0.sMoney).val("");
							}else{
								
								
							}
              				
              			},
              			error : function(data) {
              				top.Dialog.alert("获取信息失败，数据异常！");
              			}
              		});
                		
               });
     	});     	   
            	  
            	  
    
       jQuery("#"+t0.applyPerson).bindPropertyChange(function(){
    	   alert("申请人改变了")
    	   var applyPerson=jQuery("#"+t0.applyPerson).val();
    	   
    	   jQuery.ajax({
   			type:"post",
   			url:"/westvalley/workflowServlet3.jsp?cmd=gettype",
   			data:"applyPerson="+applyPerson,
   			dataType:"text",
   			async:false,
   			success:function(data){
   				var returnJson=eval("("+data+")");//取到回调函数
   				if (returnJson.msg==1) {
   					alert('returnJson.msg:无此流程信息请维护');
   					jQuery("#"+t0.loanMoney).val("");
				}else{
					jQuery("#"+t0.loanMoney).val(returnJson.msg);
				}
   				
   			},
   			error : function(data) {
   				top.Dialog.alert("获取信息失败，数据异常！");
   			}
   		});
       }); 
       
   
	
	
});
  


//转换数据
function parseFloat_(v){
	return isNaN(v)? 0 : parseFloat(v);
}

