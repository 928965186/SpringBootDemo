package testdemo;


import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
/**
 * 报销申请流程</br>
 * <p>
 * 方法集主要包括：</br>
 * 1. 流程申请处理操作 </br>
 * 节点附加操作配置URL：/testdemo/demohou.class</br>
 * </p>
 * @author xiehui
 * @date 2018年9月3日08:59:43
 * @version 1.0.1
 */
public class demohou implements Action{
	private String info = "报销申请流程:";
	@Override
	public String execute(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		String src = requestinfo.getRequestManager().getSrc();//当前操作类型 submit:提交/reject:退回
		RecordSet rs = new RecordSet();
		rs.writeLog("------------------进入提单节点后操作-------------------");
		System.out.println("当前操作类型 submit:提交/reject:退回-------------"+src);
		//取主表数据 
		Property[] properties =requestinfo.getMainTableInfo().getProperty();// 获取表单主字段信息
		for (int i = 0; i < properties.length; i++) {
			String value=Util.null2String(properties[0].getValue());
			
			String Money = Util.null2String(this.getPropertyByName(properties, "Money"));//报销金额合计
			String loanMoney = Util.null2String(this.getPropertyByName(properties, "loanMoney"));//借款金额
			String sMoney = Util.null2String(this.getPropertyByName(properties, "sMoney"));//本次冲销金额
			
			Double m=Double.valueOf(Money);
			Double l=Double.valueOf(loanMoney);
			Double s=Double.valueOf(sMoney);
			
			double num=l-s;
			System.out.println("Money"+m+"LoanMoney"+l+"sMoney"+s);
			
			String sql = "update  formtable_main_176 set usingMoney ='"+s+"',money= '"+num+"' where Hrm='"+value+"'";//提单节点提交时，冻结冲销借款
			
			boolean isSuc = rs.executeSql(sql);
			rs.writeLog(info+">>插入数据库："+isSuc+":"+sql);
			if(!isSuc){
				rs.writeLog(info+">>插入数据库失败："+isSuc+":"+sql);
				requestinfo.getRequestManager().setMessageid(info);
				requestinfo.getRequestManager().setMessagecontent(info+"失败！插入数据库失败"+sql);
				return "0";
			}
			
		}
		
		return SUCCESS;
	}
	/**
	 * 获取主表字段的值
	 * @param property 主表Property数组
	 * @param name 字段名
	 * @return value 值
	 */
	private String getPropertyByName(Property[] property, String name){
		for(Property p : property){
			if(Util.null2String(name).equalsIgnoreCase(p.getName()))
				return Util.null2String(p.getValue());
		}
		return "";
	}
}
