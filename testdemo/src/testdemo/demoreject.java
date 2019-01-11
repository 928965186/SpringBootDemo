package testdemo;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.DetailTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
/**
 * 报销申请流程</br>
 * <p>
 * 方法集主要包括：</br>
 * 1. 流程审批处理操作 </br>
 * 节点附加操作配置URL：/testdemo/demoreject.class</br>
 * </p>
 * @author xiehui
 * @date 2018年9月3日08:59:43
 * @version 1.0.1
 */
public class demoreject implements Action{
	private String info = "采购订单审批流程:";
	@Override
	public String execute(RequestInfo requestinfo) {
		String src = requestinfo.getRequestManager().getSrc();//当前操作类型 submit:提交/reject:退回
		RecordSet rs = new RecordSet();//类似于jdbc中的conn
		String Num="";
		String frozenNum="";
		String DBName="formtable_main_181";
		
		rs.writeLog("------------------进入节点后操作-------------------");
		System.out.println("当前操作类型 submit:提交/reject:退回-------------"+src);
		
	
		DetailTableInfo detail = requestinfo.getDetailTableInfo();//所有明细表	
		Row[] rows=null;
		Cell[] cells=null;
		
		try {
			if (detail != null && detail.getDetailTableCount() > 0) {
				DetailTable d_table = detail.getDetailTable(0);//第一个明细表
				rows = d_table.getRow();
				d_table = null;
				/*******循环明细表的所有行*******/
				 //借方明细
				  for (Row row : rows) {
				  	cells = row.getCell();//明细每一列的值
				  	//姓名
				  	String Mat = this.getCellByName(cells, "Mat");
				  	rs.writeLog("明细表:"+Mat);
				  	
				  	
				  	
				  	if (src.equals("submit")) {
						
						String sql = "update  '"+DBName+"' set frozenNum=0 where id='"+Mat+"'";

						boolean isSuc = rs.executeSql(sql);
						rs.writeLog(info+">>插入数据库："+isSuc+":"+sql);
						if(!isSuc){
							rs.writeLog(info+">>插入数据库失败："+isSuc+":"+sql);
							requestinfo.getRequestManager().setMessageid(info);
							requestinfo.getRequestManager().setMessagecontent(info+"失败！插入数据库失败"+sql);
							return "0";
						}
					
					}else if (src.equals("reject")) {
						String selectsql="select Num,frozenNum from '\"+DBName+\"' where id='"+Mat+"'";
						rs.executeSql(selectsql);
						if (rs.next()) {
							Num=rs.getString("Num");
							frozenNum=rs.getString("frozenNum");
						}
						int n=Integer.valueOf(Num);
						int i=Integer.valueOf(frozenNum);
						
						int num=n+i;
						String sql = "update  '\"+DBName+\"' set frozenNum ='"+0+"',Num='"+num+"' where id='"+Mat+"'";
						boolean isSuc = rs.executeSql(sql);
						rs.writeLog(info+">>插入数据库："+isSuc+":"+sql);
						if(!isSuc){
							rs.writeLog(info+">>插入数据库失败："+isSuc+":"+sql);
							requestinfo.getRequestManager().setMessageid(info);
							requestinfo.getRequestManager().setMessagecontent(info+"失败！插入数据库失败"+sql);
							return "0";
							
						}
					}
				  }
				  
				  
			}
			
			
		} catch (Exception e) {
			
		}finally {
			 detail=null; rows=null; cells=null;
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
	
	/**
	 * 获取明细表字段的值
	 * @param cells 明细某行中列的集合
	 * @param name 字段名
	 * @return value 值
	 */
	private String getCellByName(Cell[] cells, String name){
		for(Cell c : cells){
			if(Util.null2String(name).equalsIgnoreCase(c.getName())){
				return Util.null2String(c.getValue());
			}
		}
		return "";
	}
}
