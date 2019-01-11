/**
 * 出差报销申请流程--创建节点JS
 * 
 * @author Xuehui
 * @date 2018年8月28日
 * @version 1.0.0
 * @encoding utf-8
 * @url <script type="text/javascript" src="/westvalley/workflow-buy.js"></script>
 *      <p>
 *      主表表单元素调用：wf_main_fields.字段名称 明细一表单元素调用：wf_dt1_fields.字段名称 方法集主要包括： 1.
 */

// 引用公共js


// 主表
// 表的字段以及前台的id
var t0 = {
	applyPerson : 'field9034',/* 申请人 */
	applyDate : 'field9035',/* 申请日期 */
	

}

// 明细表1
var t1 = {
	Mat : 'field9036', /* 物料 */
	Num : 'field9037', /* 库存 */
	applyNum: 'field9038',/* 申请数量 */
}

jQuery(document).ready(function(){	
	
	 //提单时，根据选择的物料信息自动带出库存数量（考虑冻结）。
	//监听明细行增加
       jQuery("#indexnum0").bindPropertyChange(function(){
           //获取到明细下标
		var index = jQuery("#indexnum0").val()-1;
		
           //给新增的明细行字段添加监听事件
               jQuery("#"+t1.Mat+"_"+index).bindPropertyChange(function(){
            	   var Mat=jQuery("#"+t1.Mat+"_"+index).val();//获取明细表输入
            	   alert("物料改变了")
   						jQuery.ajax({
	              			type:"post",
	              			url:"/westvalley/workflowServlet4.jsp?cmd=getfl",//定义的url
	              			data:"Mat="+Mat,//传参
	              			dataType:"text",
	              			async:false,//同步
	              			success:function(data){
	              				var returnJson=eval("("+data+")");//取到回调函数
	              				if (returnJson.msg==1) {
									alert("无此物料明细请维护")
								}else{
									jQuery("#"+t1.Num+"_"+index).val(returnJson.msg);
								}
	           					
	              			},
	              			error : function(data) {
	              				top.Dialog.alert("获取信息失败，数据异常！");
	              			}
	              		});  
   					
            	   
            	  
                		
               });
     	});

	    
       
       //申请数量不能大于库存剩余数量；
       jQuery("#indexnum0").bindPropertyChange(function(){
           //获取到明细下标
		var index = jQuery("#indexnum0").val()-1;
		
           //给新增的明细行字段添加监听事件
               jQuery("#"+t1.applyNum+"_"+index).bind("change",function(){
            	   var Num=jQuery("#"+t1.Num+"_"+index).val();
            	   var applyNum=jQuery("#"+t1.applyNum+"_"+index).val();
            	  
            	   
            	   jQuery.ajax({
              			type:"post",
              			url:"/westvalley/workflowServlet4.jsp?cmd=getpd",
              			data:"Num="+Num+"&applyNum="+applyNum,
              			dataType:"text",
              			async:false,
              			success:function(data){
              				var returnJson=eval("("+data+")");
              				if (returnJson.msg==1) {
              					alert('申请数量不能大于库存剩余数量');
              					jQuery("#"+t1.applyNum+"_"+index).val("");
							}else{
								
								
							}
              				
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

