package com.westvalley.fk.LeaveApply;

import java.util.Map;

import com.westvalley.fk.util.DataUtil;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;
/**
 * 日常费用报销流程申请节点
 * 
 * @author hpp
 *
 */
public class LeaveApplyEnd  extends BaseAction{

	public String execute(RequestInfo ri) {
		this.writeLog("请假申请审批归档节点," +
				"创建人�??"+ri.getCreatorid()+"�?" +
				"流程id�?"+ri.getWorkflowid()+"�?" +
				"流程请求id�?"+ri.getRequestid()+"�?" +
				"当前节点�?"+ri.getRequestManager().getNodeid()+"�?" +
				"请求标题�?"+ri.getRequestManager().getRequestname()+"�?");
		if (ri == null || ri.getMainTableInfo() == null || ri.getMainTableInfo().getPropertyCount() == 0)
			return "0";
		String flag="0";
		String ErrorMsg="";
		//流程id
		String workflowid=ri.getWorkflowid();
		String tablename=DataUtil.getTableName(workflowid);
		//主表
		Map<String,String> mid=DataUtil.getPropertyMap(ri.getMainTableInfo().getProperty());
		RecordSetDataSource rsd=new RecordSetDataSource("OAApi");
		RecordSet rs=new RecordSet();
		//申请�?
		String shenqr=Util.null2String(mid.get("shenqr"));
		//申请人工�?
		String StaffNumber="";
		rs.executeSql("select workcode from hrmresource where id='"+shenqr+"'");
		if(rs.next()){
			StaffNumber=Util.null2String(rs.getString("workcode"));
		}
		//请假类型描述
		String LevName= DataUtil.getSelN(workflowid, "", "jiaqlx",  Util.null2String(mid.get("jiaqlx"))) ;
		//请假类型编号
		String LevCode="";
		rsd.executeSql("select code from v_HRMS_LeaveType where description='"+LevName+"'");
		if(rsd.next()){
			LevCode=rsd.getString("code");
		}
		//�?始时�?
		String StartDate=Util.null2String(mid.get("kaissj"));
		//结束时间
		String EndDate=Util.null2String(mid.get("jiessj"));
		//�?始时间上下午
		String StartT=DataUtil.getSelN(workflowid, "", "shangxw",  Util.null2String(mid.get("shangxw"))) ;
		//结束时间上下�?
		String EndT=DataUtil.getSelN(workflowid, "", "shangxw1",  Util.null2String(mid.get("shangxw1"))) ;
		String Result="";
		rsd.executeSql("select * from [EfundHK_API].[dbo].[HRMS_LeaveApp] ('StartDate="+StartDate+"&EndDate="+EndDate+"&StaffNumber="+StaffNumber+"&LevCode="+LevCode+"&StartT="+StartT+"&EndT="+EndT+"' )");
		if(rsd.next()){
			Result=Util.null2String(rsd.getString("Result"));
			ErrorMsg=Util.null2String(rsd.getString("ErrorMsg"));
			String RText=Util.null2String(rsd.getString("RText"));
			this.writeLog("HR系统返回结果//"+ErrorMsg);
			rs.executeSql("update "+tablename+" set HRxtfhjg='"+RText+"' where requestid='"+ri.getRequestid()+"' ");
		}
		if("1".equals(Result)){
			flag="1";
		}
		if(!"1".equals(flag)){
			ri.getRequestManager().setMessageid(ri.getRequestManager().getRequestname());
			ri.getRequestManager().setMessagecontent(ErrorMsg);
			ri.getRequestManager().setMessage("111100");
		}
		this.writeLog("请假审批归档节点完成===================");
		return flag;
	}
}
