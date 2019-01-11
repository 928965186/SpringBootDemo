package testdemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetTrans;
import weaver.general.Util;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.DetailTableInfo;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;



/**
 * 年度人力资源计划流程（汇总）流程归档节点附加操作</br>
 * <p>
 * 方法集主要包括：</br>
 * 1. 流程申请处理操作 </br>
 * 节点附加操作配置URL：/servicesetting/actionsetting.jsp</br>
 * 节点附加操作配置文件：/WEB-INF/service/action.xml</br>
 * </p>
 * @author LIJ
 * @date 2017-05-09 10:03:06
 * @version 1.0.1
 */
public class YearHrPlan extends weaver.interfaces.workflow.action.BaseAction{

	private String info = "部门年度人力需求计划表流程:";
	
	/**
	 * 流程归档处理操作
	 * @return “1”：流程继续往下提交，其他值：流程不往下提交
	 */
	@Override
	public String execute(RequestInfo request) {
		if(request == null || request.getMainTableInfo() == null || request.getMainTableInfo().getPropertyCount() == 0 ){ 
			request.getRequestManager().setMessageid("YearHrPlan");
			request.getRequestManager().setMessagecontent(info+"：未获取到表单信息！");
			return "0";
		}
		String flag = "0";
		MainTableInfo main = request.getMainTableInfo();//主表
		DetailTableInfo detail = request.getDetailTableInfo();//所有明细表
		Property[] property = main.getProperty();//主表字段
		Row[] rows=null;
		Cell[] cells=null;
		
		String danh = Util.null2String(this.getPropertyByName(property, "danh"));//单号
		String shenqr = Util.null2String(this.getPropertyByName(property, "tiar"));//提案人
		RecordSet rs = new RecordSet();
		rs.executeSql("select workcode from hrmresource where id = "+shenqr);
		String workCode = "";
		if(rs.next()){
			workCode = rs.getString("workcode");
		}
		if("".equals(workCode)){
			request.getRequestManager().setMessageid(info);
			request.getRequestManager().setMessagecontent(info+"失败！申请人无编号");
			return "0";
		}
		String shenqbm = Util.null2String(this.getPropertyByName(property, "tiabm"));//提案部门
		String departmentCode = "";
		if(!"".equals(shenqbm)){
			rs.executeSql("select departmentcode from hrmdepartment where id = "+shenqbm);
			if(rs.next()){
				departmentCode = rs.getString("departmentcode");
			}
		}
		String tiarq = Util.null2String(this.getPropertyByName(property, "tiarq"));//提案日期
		String gongh = Util.null2String(this.getPropertyByName(property, "gongh"));//工号
																				//5
		String zhiw = Util.null2String(this.getPropertyByName(property, "zhiw"));//职务 岗位
		String jobtitleCodeZhiw = "";
		if(!"".equals(zhiw)){
			rs.executeSql("select jobtitlecode from HrmJobTitles where id = "+zhiw);
			if(rs.next()){
				jobtitleCodeZhiw = rs.getString("jobtitlecode");
			}
		}
		String suosgs = Util.null2String(this.getPropertyByName(property, "suosgs"));//所属公司 分部
		String subcompanyCode = "";
		if(!"".equals(suosgs)){
			rs.executeSql("select subcompanycode from hrmsubcompany where id = "+suosgs);
			if(rs.next()){
				subcompanyCode = rs.getString("subcompanycode");
			}
		}
		String shenqnd = Util.null2String(this.getPropertyByName(property, "shenqnd"));//申请年度
		String xianggwd = Util.null2String(this.getPropertyByName(property, "xianggwd"));//相关文档
		String gonsxygws = Util.null2String(this.getPropertyByName(property, "gonsxygws"));//公司现有岗位数
																						//10
		String gonsxyrys = Util.null2String(this.getPropertyByName(property, "gonsxyrys"));//公司现有人员数
		String xiangglc = Util.null2String(this.getPropertyByName(property, "xiangglc"));//相关流程
		String xianggfj = Util.null2String(this.getPropertyByName(property, "xianggfj"));//相关附件
		
		//新增
		String renzzg = Util.null2String(this.getPropertyByName(property, "renzzg"));//任职资格	文本
		String jihzjrs = Util.null2String(this.getPropertyByName(property, "jihzjrs"));//计划增加人数	整数
		String zengjhzrs = Util.null2String(this.getPropertyByName(property, "zengjhzrs"));//增加后总人数	整数
		String table = "formtable_main_69";
		String zhihqy = Util.null2String(this.getPropertyByName(property, "zhihqy"));//知会区域 下拉  表单中隐藏做条件判断
		String zhihqyName = "";
		if(!"".equals(zhihqy)){
			rs.executeSql("select selectname from workflow_SelectItem where fieldid= (select id from workflow_billfield where fieldname = 'zhihqy' and billid = (select id from workflow_bill where tablename = '"+table+"')) and selectvalue = "+zhihqy);
			if(rs.next()){
				zhihqyName = rs.getString("selectname");
			}
		}
		

		RecordSetTrans rst = new RecordSetTrans();
		
		try{
			
			rst.setAutoCommit(false);
			String sql = "insert into WV_YearHrPlan(requestid, danh, tiar, tiabm, tiarq, gongh, " +
														"zhiw,  suosgs, shenqnd, xianggwd,  gonsxygws," +
														"gonsxyrys, xiangglc, xianggfj, title," +
														"renzzg, jihzjrs, zengjhzrs, zhihqy) values ("+
										request.getRequestid()+", '"+danh+"', '"+workCode+"', '"+departmentCode+"', '"+tiarq+"', '"+gongh+"', '"+
										jobtitleCodeZhiw+"', '"+subcompanyCode+"','"+shenqnd+"','"+xianggwd+"',"+gonsxygws+"," +
										gonsxyrys+", '"+xiangglc+"', '"+xianggfj+"','"+request.getDescription()+"'," +
										"'"+renzzg+"','"+jihzjrs+"','"+zengjhzrs+"','"+zhihqyName+"')";
			boolean isSuc = rst.executeSql(sql);
			
			if(!isSuc){
				rst.rollback();
				request.getRequestManager().setMessageid("YearHrPlan");
				request.getRequestManager().setMessagecontent(info+"插入SQL：" +isSuc+":"+sql );
				return "0";
			}
		
			if(detail != null && detail.getDetailTableCount() > 0){
				DetailTable d_table = detail.getDetailTable(0);//第一个明细表
				rows = d_table.getRow();
				d_table = null;
				/*******循环明细表的所有行*******/
				 //借方明细
				  for (Row row : rows) {
				  	cells = row.getCell();//明细每一列的值
				  	//部门
				  	String bum = this.getCellByName(cells, "bum");
				  	String departmentCodeBum = "";
					if(!"".equals(bum)){
						rs.executeSql("select departmentcode from hrmdepartment where id = "+bum);
						if(rs.next()){
							departmentCodeBum = rs.getString("departmentcode");
						}
					}
				  	//负责人
				  	String fuzr = this.getCellByName(cells, "fuzr");
				  	rs.executeSql("select workcode from hrmresource where id = "+fuzr);
					String workCodeFuzr = "";
					if(rs.next()){
						workCodeFuzr = rs.getString("workcode");
					}
					if("".equals(workCodeFuzr)){
						rst.rollback();
						request.getRequestManager().setMessageid(info);
						request.getRequestManager().setMessagecontent(info+"失败！负责人无编号");
						return "0";
					}
				  	//计划增员职位
				  	String jihzyzw = this.getCellByName(cells, "jihzyzw");
				  	String jobtitleCodeJihzyzw = "";
				  	//多岗位
				  	String[] jihzyzws = jihzyzw.split(",");
					for (int i = 0; i < jihzyzws.length; i++) {
//						rs.executeSql("select jobtitlename from HrmJobTitles where id = "+jihzyzws[i]);
						rs.executeSql("select jobtitlecode from HrmJobTitles where id = "+jihzyzws[i]);
						if(rs.next()){
//							jobtitleCodeJihzyzw = jobtitleCodeJihzyzw + rs.getString("jobtitlename") +",";
							jobtitleCodeJihzyzw = jobtitleCodeJihzyzw + rs.getString("jobtitlecode") +",";
						}
					}
					if(!"".equals(jobtitleCodeJihzyzw)){
						jobtitleCodeJihzyzw = jobtitleCodeJihzyzw.substring(0, jobtitleCodeJihzyzw.length()-1);
					}
					//单岗位
					/**
				  	if(!"".equals(jihzyzw)){
						rs.executeSql("select jobtitlecode from HrmJobTitles where id = "+jihzyzw);
						if(rs.next()){
							jobtitleCodeJihzyzw = rs.getString("jobtitlecode");
						}
					}
					*/
				  	//职级
				  	String zhij = this.getCellByName(cells, "zhij");
				  	//现有人数
				  	String xianyrs = this.getCellByName(cells, "xianyrs");
				    //计划增加人数
				  	String jihzjrsMx = this.getCellByName(cells, "jihzjrs");
				  	//批准人员数量
				  	String pizrysl = this.getCellByName(cells, "pizrysl");
				  	//主要工作职责
				  	String zhuygzzz = this.getCellByName(cells, "zhuygzzz");
				    //计划到岗时间
				  	String jihdgsj = this.getCellByName(cells, "jihdgsj");
				  	//新增
				  	//zengyly	增员理由	多行文本
				  	String zengyly = this.getCellByName(cells, "zengyly");
				    //新增20170613 任职资格
					String renzzgMX = this.getCellByName(cells, "renzzg");//任职资格	文本
				  	
					sql = "insert into WV_YearHrPlan_dt1(requestid, bum, fuzr, jihzyzw, zhij, xianyrs, jihzjrs, pizrysl, zhuygzzz, jihdgsj, zengyly, renzzg) values(" +
				  							request.getRequestid()+", '"+departmentCodeBum+"', '"+workCodeFuzr+"', '"+jobtitleCodeJihzyzw+"', '"+zhij+"', "+xianyrs+", "+jihzjrsMx+", "+pizrysl+", '"+zhuygzzz+"', '"+jihdgsj+"', '"+zengyly+"', '"+renzzgMX+"')";
				   
				  	isSuc = rst.executeSql(sql);
				  	
				  	if(!isSuc){
						rst.rollback();
						request.getRequestManager().setMessageid("YearHrPlan");
						request.getRequestManager().setMessagecontent(info+"插入SQL：" +isSuc+":"+sql );
						return "0";
					}
				  }
			}
			isSuc = rst.commit();
			if(!isSuc){
				request.getRequestManager().setMessageid("YearHrPlan");
				request.getRequestManager().setMessagecontent(info+"：提交事务错误：" +isSuc);
				return "0";
			}
			flag = "1";
			
		}catch(Exception e){
			rst.rollback();
			flag = "0";
			request.getRequestManager().setMessageid("YearHrPlan");
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
	
	
	private Map jsonToMap(String str){
		try {
			JSONObject jsonObject = new JSONObject(str);
	        Map result = new HashMap();
	        Iterator iterator = jsonObject.keys();
	        String key = null;
	        String value = null;
	        
	        while (iterator.hasNext()) {
	            key = (String) iterator.next();
				value = jsonObject.getString(key);
	            result.put(key, value);
	        }
	        return result;
		} catch (JSONException e) {
			e.printStackTrace();
			
			return new HashMap(); 
		}
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
		System.out.println();
		while (rs.next()) {
			strTableName=Util.null2String(rs.getString("TABLENAME"));
		}
		return strTableName;
	}
	
	public static void main(String[] args) {
		String dd= "14,61,33,30,20";
		String[] dds = dd.split(",");
		for (int i = 0; i < dds.length; i++) {
			System.out.println(dds[i]);
		}
	}
	
}
