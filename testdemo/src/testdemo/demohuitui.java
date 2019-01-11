package testdemo;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
/**
 * 报销申请流程</br>
 * <p>
 * 方法集主要包括：</br>
 * 1. 流程审批处理操作 </br>
 * 节点附加操作配置URL：/testdemo/demohuitui.class</br>
 * </p>
 * @author xiehui
 * @date 2018年9月3日08:59:43
 * @version 1.0.1
 */
public class demohuitui implements Action{
	private String info = "报销审批流程:";
	@Override
	public String execute(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		String src = requestinfo.getRequestManager().getSrc();//当前操作类型 submit:提交/reject:退回
		String usingMoney="";//冻结冲销金额
		String money="";//借款金额
		RecordSet rs = new RecordSet();//类似于jdbc中的conn
		
		
		rs.writeLog("------------------进入节点后操作-------------------");
		System.out.println("当前操作类型 submit:提交/reject:退回-------------"+src);
		
		Property[] properties =requestinfo.getMainTableInfo().getProperty();// 获取表单主字段信息
		String applyPerson = Util.null2String(this.getPropertyByName(properties, "applyPerson"));//获取申请人
		String sMoney = Util.null2String(this.getPropertyByName(properties, "sMoney"));//本次冲销金额
		
		int a=Integer.valueOf(applyPerson);
		
		
		Double s=Double.valueOf(sMoney);
			
			if (src.equals("submit")) {
				String selectsql="select usingMoney from formtable_main_176 where Hrm='"+a+"'";
				rs.executeSql(selectsql);
				if (rs.next()) {
					 usingMoney=rs.getString("usingMoney");
				}
				System.out.println("usingMoney-----------------------------"+usingMoney);
				Double u=Double.valueOf(usingMoney);
				String sql = "update  formtable_main_176 set usedMoney ='"+u+"',usingMoney=0 where Hrm='"+a+"'";

				boolean isSuc = rs.executeSql(sql);
				rs.writeLog(info+">>插入数据库："+isSuc+":"+sql);
				if(!isSuc){
					rs.writeLog(info+">>插入数据库失败："+isSuc+":"+sql);
					requestinfo.getRequestManager().setMessageid(info);
					requestinfo.getRequestManager().setMessagecontent(info+"失败！插入数据库失败"+sql);
					return "0";
				}
			
			}else if (src.equals("reject")) {
				String selectsql="select money from formtable_main_176 where Hrm='"+a+"'";
				rs.executeSql(selectsql);
				if (rs.next()) {
					money=rs.getString("money");
				}
				System.out.println("money-----------------------------"+money);
				Double u=Double.valueOf(money);
				
				double num=u+s;
				String sql = "update  formtable_main_176 set usingMoney ='"+0+"',money='"+num+"' where Hrm='"+a+"'";
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
