package testdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.DetailTableInfo;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;




/**
 * 请假申请流程申请和审批节点附加操作</br>
 * <p>
 * 方法集主要包括：</br>
 * 1. 流程归档处理操作 </br>
 * 节点附加操作配置URL：/servicesetting/actionsetting.jsp</br>
 * 节点附加操作配置文件：/WEB-INF/service/action.xml</br>
 * </p>
 * @author LIJ
 * @date 2016-11-07 10:03:06
 * @version 1.0.1
 */
public class leaveAction extends weaver.interfaces.workflow.action.BaseAction{

	private String info = "请假申请流程:";
	
	BaseBean baseLog = new BaseBean();
	
	RecordSet sss=new RecordSet();
	
	
	/**
	 * 流程归档处理操作
	 * @return “1”：流程继续往下提交，其他值：流程不往下提交
	 */
	@Override
	public String execute(RequestInfo request) {
		if(request == null || request.getMainTableInfo() == null || request.getMainTableInfo().getPropertyCount() == 0 ){ 
			request.getRequestManager().setMessageid("leaveAction");
			request.getRequestManager().setMessagecontent(info+"：未获取到表单信息！");
			return "0";
		}
		//log.info("日志");
		//baseLog.writeLog("3333");
		
		String flag = "0";
		MainTableInfo main = request.getMainTableInfo();//主表
		Property[] property = main.getProperty();//主表字段
		DetailTableInfo detail = request.getDetailTableInfo();//所有明细表
		
		Row[] rows=null;
		Cell[] cells=null;
		
		String requestid = request.getRequestid();//请求ID        
		String requestlevel = request.getRequestlevel();//请求紧急程度        
		String src = request.getRequestManager().getSrc();        
		//当前操作类型 submit:提交/reject:退回        
		String workflowid = request.getWorkflowid();//流程ID        
		User usr = request.getRequestManager().getUser();//获取当前操作用户对象 
		String requestname = request.getRequestManager().getRequestname();//请求标题        
		String remark2 = request.getRequestManager().getRemark();//当前用户提交时的签字意见        
		
		String bcjkje = Util.null2String(this.getPropertyByName(property, "bcjkje"));//备注
		String jkyy = Util.null2String(this.getPropertyByName(property, "jkyy"));//申请人
		String code = Util.null2String(this.getPropertyByName(property, "code"));//科目
		//本地数据源
		RecordSet rs = new RecordSet();
		
		//rs.writeLog("日志");
		//外部数据源
		RecordSetDataSource rs2 = new RecordSetDataSource("demo");
		
		
		rs.writeLog("bcjkje:"+bcjkje+";jkyy:"+jkyy+";code:"+code);
		
		try{
			
			if(detail != null && detail.getDetailTableCount() > 0){
				DetailTable d_table = detail.getDetailTable(0);//第一个明细表
				rows = d_table.getRow();
				d_table = null;
				/*******循环明细表的所有行*******/
				 //借方明细
				  for (Row row : rows) {
				  	cells = row.getCell();//明细每一列的值
				  	//姓名
				  	String name = this.getCellByName(cells, "name");
				  	rs.writeLog("明细表:"+name);
				  }
			}
			
			/**
			String sql = "insert into WV_T_BudgetSubject(code,name) values('"+code+"','"+remark+"') ";
			boolean isSuc = rs.executeSql(sql);
			rs.writeLog(info+">>插入数据库："+isSuc+":"+sql);
			if(!isSuc){
				rs.writeLog(info+">>插入数据库失败："+isSuc+":"+sql);
				request.getRequestManager().setMessageid(info);
				request.getRequestManager().setMessagecontent(info+"失败！插入数据库失败"+sql);
				return flag;
			}
			*/
			flag = "1";
		}catch(Exception e){
			flag = "0";
			request.getRequestManager().setMessageid(info);
			request.getRequestManager().setMessagecontent("执行节点附加操作失败!"+e.getLocalizedMessage());
			
		}finally{
			//释放资源
			main=null; detail=null; property=null; rows=null; cells=null; 
		}
		return flag;
	}
	
	/**
	 * property转到map（不建议使用，,Map消耗资源，注意回收）
	 * @param property
	 * @return
	 */
	@Deprecated
	private Map<String, String> getPropertyMap(Property[] property){
		Map<String, String> m = new HashMap<String, String>();
		for(Property p : property){
			m.put( p.getName(), Util.null2String(p.getValue()) );
		}
		return m;
	}
	/**
	 * Cell转到map（不建议使用，,Map消耗资源，注意回收）
	 * @param cells
	 * @return
	 */
	@Deprecated
	private Map<String, String> getCellMap(Cell[] cells){
		Map<String, String> m = new HashMap<String, String>();
		for(Cell c : cells){
			m.put(c.getName( ), Util.null2String(c.getValue()) );
		}
		return m;
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
	
	
	/**
	 * 根据request获取主表
	 * @param requestid
	 * @param rs
	 * @return
	 */
	public  String getRequestTableName(String requestid,RecordSet rs){
		//方法返回值
		String strTableName="";
		String strSql="SELECT A.TABLENAME FROM WORKFLOW_BILL A LEFT JOIN WORKFLOW_BASE B ON A.ID = B.FORMID WHERE B.ID= " +
				"(select r.workflowid from workflow_requestbase r where r.requestid= '"+requestid+ "') ";
		rs.execute(strSql);
		while (rs.next()) {
			strTableName=Util.null2String(rs.getString("TABLENAME"));
		}
		return strTableName;
	}
	
	
}
