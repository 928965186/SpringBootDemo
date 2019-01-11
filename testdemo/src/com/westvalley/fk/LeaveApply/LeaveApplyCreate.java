package com.westvalley.fk.LeaveApply;

import java.util.Map;

import com.westvalley.fk.util.DataUtil;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;
/**
 * 请假申请审批节点
 * 
 * @author hpp
 *
 */
public class LeaveApplyCreate  extends BaseAction{

	public String execute(RequestInfo ri) {
		this.writeLog("请假申请审批申请节点," +
				"创建人�??"+ri.getCreatorid()+"�?" +
				"流程id�?"+ri.getWorkflowid()+"�?" +
				"流程请求id�?"+ri.getRequestid()+"�?" +
				"当前节点�?"+ri.getRequestManager().getNodeid()+"�?" +
				"请求标题�?"+ri.getRequestManager().getRequestname()+"�?");
		if (ri == null || ri.getMainTableInfo() == null || ri.getMainTableInfo().getPropertyCount() == 0)
			return "0";
		String flag="1";
		String ErrorMsg="";
		//流程id
		String workflowid=ri.getWorkflowid();
		//流程请求id
		String requestid=ri.getRequestid();
		//流程�?
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
		//查询对应可用天数
		double AvailableBalance=0;
		rsd.executeSql("SELECT * from [EfundHK_API].[dbo].[HRMS_LeaveBalance] ('asOfDate="+StartDate+"&StaffNumber="+StaffNumber+"&leaveCode="+LevCode+"')");
		while(rsd.next()){
			AvailableBalance=DataUtil.toDouble(Util.null2String(rsd.getString("AvailableBalance"))) ;
		}
		//查询对应总天�?
		double WorkDays=0;
		rsd.executeSql("select * from [EfundHK_API].[dbo].[HRMS_CountWorkDays] ('startDate="+StartDate+"&endDate="+EndDate+"&StartT="+StartT+"&EndT="+EndT+"' )");
		while(rsd.next()){
			WorkDays=DataUtil.toDouble(Util.null2String(rsd.getString("WorkDays")));
		}
		//更新数据
		rs.executeSql("update "+tablename+" set keyjqts="+AvailableBalance+", zongts="+WorkDays+" where requestid='"+requestid+"'");
		rs.writeLog("update "+tablename+" set keyjqts="+AvailableBalance+", zongts="+WorkDays+" where requestid='"+requestid+"'");
		/**
		if("1".equals(flag)){
			//验证超假�?
			if(AvailableBalance<WorkDays){
				flag="0";
				ErrorMsg="总天数不可大于累计可用假期天�?";
			}
		}
		**/
		//验证是否时间冲突
		if("1".equals(flag)){
			String Result="";
			rsd.executeSql("select * from [EfundHK_API].[dbo].[HRMS_LeaveAppValidate] ('StartDate="+StartDate+"&EndDate="+EndDate+"&StaffNumber="+StaffNumber+"&LevCode="+LevCode+"&StartT="+StartT+"&EndT="+EndT+"' )");
			rsd.writeLog("select * from [EfundHK_API].[dbo].[HRMS_LeaveAppValidate] ('StartDate="+StartDate+"&EndDate="+EndDate+"&StaffNumber="+StaffNumber+"&LevCode="+LevCode+"&StartT="+StartT+"&EndT="+EndT+"' )");
			while(rsd.next()){
				Result=rsd.getString("Result");
				ErrorMsg=rsd.getString("ErrorMsg");
			}
			if(!"1".equals(Result)){
				flag="0";
			}
		}
		if(!"1".equals(flag)){
			ri.getRequestManager().setMessageid(ri.getRequestManager().getRequestname());
			ri.getRequestManager().setMessagecontent(ErrorMsg);
			ri.getRequestManager().setMessage("111100");
		}
		this.writeLog("请假审批申请节点完成//===================");
		return flag;
	}
}
