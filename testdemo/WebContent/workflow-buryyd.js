/**
 * 请假申请流程--创建节点JS
 * @author Lij
 * @date 2017-05-24
 * @version 1.0.0
 * @encoding utf-8
 * @url <script type="text/javascript" src="/westvalley/workflow-buryyd.js"></script>
 * <p>
 * 主表表单元素调用：wf_main_fields.字段名称
 * 明细一表单元素调用：wf_dt1_fields.字段名称
 * 方法集主要包括：
 * 1.
 */

//引用公共js
document.write("<script type='text/javascript' src='/westvalley/cw/cw.js'></script>");
//要解决兼容性的id集合  格式：#field7865,#field7865。。。。
//var excutMID="#"+wf_main_fields.kaistxrq+",#"+wf_main_fields.kaistxsj+
	//",#"+wf_main_fields.jiestxtq+",#"+wf_main_fields.jiestxsj;
//要解决兼容性的id集合  格式：[id,id,id]
//var excutDID=[wf_dt1_fields.kaissj,wf_dt1_fields.jiessj];

//主表
//表的字段以及前台的id
  var t0 = { 
		bianh : '' /*编号 */
	   ,code : '' /*科目*/ 
	   ,xm : '' /*申请人*/ 
	   ,shqrq : '' /*申请日期*/ 
	   ,bcjkje : ''/*本次借款金额*/ 
	   ,yjhkrq : ''/*预计还款日期*/
    } 
  

  //明细表1
  var t1 = { 
		remark : '' /*备注*/ 
		,name : '' /*姓名*/ 
	   ,hrm : '' /*人员*/ 
   }
  
//明细表2
  var t1 = { 
			remark : '' /*备注*/ 
			,name : '' /*姓名*/ 
		   ,hrm : '' /*人员*/ 
	   }

 jQuery
 
jQuery(document).ready(function(){
	

	jQuery.ajax({
		type:"POST",
		url: "/westvalley/cw/getfieldid.jsp",
		data: 'wid='+jQuery("input[name=workflowid]").val(),
		dataType:"json",
		async: false,
		success: function(data){	
		t0 = data.mid;
		t1 = data.did1;
		t2 = data.did2;
		}
	});
	
	
	var of = window.doSubmit;
	window.doSubmit = function(p){
		var index = jQuery("#indexnum0").val() * 1.0;
		for (var i = 0; i < index; i++) {
			alert(jQuery("#"+t1.name+"_"+i).val());
		}
		return false;
			of(p);
	}
	
	//监听明细行增加
       jQuery("#indexnum0").bindPropertyChange(function(){
           //获取到明细下标
		var index = jQuery("#indexnum0").val()-1;
           //给新增的明细行字段添加监听事件
		
               jQuery("#"+t1.name+"_"+index).bind("change",function(){
            	   alert("1111");
               		
            	   jQuery.ajax({
            			type:"POST",
            			url: "/demo/getData.jsp",
            			data: 'code='+this.value,
            			dataType:"text",
            			success: function(data){
            			  $("#"+did1.QG010+"_"+index).val(data);
            			}
            		});
            		
              });
	});
		
		
		//绑定人员
	_C.run2(t1.name, function(p){
//		alert('绑定异动前部门');
		var iscreate = jQuery("input[name=iscreate]").val();
		if (p.v.o == undefined && iscreate == 0){
			return;
		}
		alert("#"+t1.name+p.r);
		var hrm = jQuery("#"+t1.name+p.r).val();
		alert("name:"+name);
		//jQuery("#"+t1.name+p.r).val("A");
	});
	

	
	var addRowsFun = window.addRow0;//绑定到相应的明细表的添加按钮 onclick="addRow0(0)"
	window.addRow0 = function(){
		//addRowsFun();		
		var j= jQuery("#indexnum0").val() * 1.0 - 1;
		addRowsFun(0);
		jQuery("#"+t1.name+"_"+j).val("MM");
	}
	
	
	/**
	jQuery("#"+t0.bianh).bind("change",function(){
		alert("t0.bianh:"+t0.bianh);
		var remark = jQuery("#"+t0.bianh).val();
		alert(remark);
		//jQuery("#"+t0.code+"_browserbtn").hide();
		//jQuery("#"+t0.code+"_browserbtn").show();
	});
	*/
	
	jQuery("#"+t0.bianh).bindPropertyChange(function(){
		var yjhkrq = jQuery("#"+t0.bianh).val();
		alert(yjhkrq);
		
		//获取人力资源信息
		jQuery.ajax({
			type:"post",
			url:"/westvalley/workflowServlet.jsp?cmd=getZT",
			data:"code="+code,
			dataType:"text",
			async:false,
			success:function(data){
				var returnJson=eval("("+data+")");
				alert('returnJson.msg:'+returnJson.msg);
				jQuery("#"+t0.remark).val(returnJson.msg);
			},
			error : function(data) {
				//alert("获取信息失败");
				top.Dialog.alert("获取信息失败，数据异常！");
			}
		});
		
		
	});
	
	
	
	//绑定日期
	_C.run2(t0.yjhkrq, function(p){
		//alert('绑定姓名');
		var iscreate = jQuery("input[name=iscreate]").val();
		if (p.v.o == undefined && iscreate == 0){
			return;
		}
		var yjhkrq = jQuery("#"+t0.yjhkrq).val();
		alert("xm:"+yjhkrq);
//		var gongh = _C.v(t1.gongh+p.r);
//		alert("gongh="+gongh);
		
		//获取人力资源信息
		jQuery.ajax({
			type:"post",
			url:"/westvalley/workflowServlet.jsp?cmd=getHrToBumryyd",
			data:"yjhkrq="+yjhkrq,
			dataType:"text",
			async:false,
			success:function(data){
				var returnJson=eval("("+data+")");
				alert('returnJson.yjhkrq:'+returnJson.msg);
//				_C.v(t1.nianl+p.r, returnJson.nianl);
//				_C.v(t1.ruzsj+p.r, returnJson.ruzrq);
//				_C.v(t1.yidqzj+p.r, returnJson.yidqzj);
			},
			error : function(data) {
				//alert("获取信息失败");
				top.Dialog.alert("获取信息失败，数据异常！");
			}
		});
		
		
	});
	

	
	
	
	
	
});

//转换数据
function parseFloat_(v){
	return isNaN(v)? 0 : parseFloat(v);
}

