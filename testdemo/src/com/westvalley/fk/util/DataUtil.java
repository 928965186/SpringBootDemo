package com.westvalley.fk.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.Row;

/**
 * 
 * @ClassName: DataUtil 
 * @Description: TODO
 * 常用数据工具�?
 */
public class DataUtil {
	private static final int DEF_DIV_SCALE = 10;  
	/**
	 * 必须放在归档节点前才可以取到，判断下�?个节点是否归档节�?
	 * @param v 流程id
	 * @param v2 下一个节点id
	 * @return String 归档  在�??
	 *
	 */
	public static String nextNodeIsEND(String v,String v2) {
		RecordSet rs=new RecordSet();
		rs.executeSql("select n.isend,n.isstart from workflow_nodebase n left join workflow_flownode f on n.id=f.nodeid where f.workflowid='"+v+"' and n.id='"+v2+"'");
		if(rs.next()){
			//1归档节点  0其他在�??
			if("1".equals(Util.null2String(rs.getString("isend")))){
				return "归档";
			}
		}
		return "在�??";
	}
	
	
	/**
	 * 主表 property转到map
	 * @param property
	 * @return
	 */
	public static Map<String, String> getPropertyMap(Property[] property) {
		Map<String, String> m = new HashMap<String, String>();
		for(Property p : property){
			m.put( p.getName(), p.getValue());
		}
		return m;
	}
	
	/**
	 * 获取子表的所有行
	 * @param DetailTable 第几个明细表
	 * @return
	 */
	public static List<Map<String, String>> getDetailTableRows(DetailTable detailTable){
		List<Map<String, String>> listMap = new ArrayList<Map<String,String>>();
		for (Row row : detailTable.getRow()){
			Map<String, String> map = new HashMap<String, String>();
			for (Cell cell : row.getCell()){
				map.put(cell.getName(), cell.getValue());
			}
			map.put("id", row.getId());
			listMap.add(map);
		}
		return listMap;
	}
	
	
	/** 
	* 提供精确的减法运算�?? 
	* @param v1 被减�? 
	* @param v2 减数 
	* @return 两个参数的差 
	*/  
	  
	public static String sub(String v1, String v2) {  
		v1 =nullToZero(v1);
		v2 =nullToZero(v2).replaceAll("-", "");
	   BigDecimal b1 = new BigDecimal(v1);  
	   BigDecimal b2 = new BigDecimal(v2);  
	   return b1.subtract(b2).toString();  
	} 
	
	/** 
	* 提供精确的加法运算�?? 
	* @param v1 被加�? 
	* @param v2 加数 
	* @return 两个参数的和 
	*/  
	  
	public static String add(String v1, String v2) {
		v1 =nullToZero(v1);
		v2 =nullToZero(v2);
		
	   BigDecimal b1 = new BigDecimal(v1);  
	   BigDecimal b2 = new BigDecimal(v2);  
	   return b1.add(b2).toString();  
	}
	
	
	/** 
	* 提供精确的小数位四舍五入处理�? 
	* @param v �?要四舍五入的数字 
	* @param scale 小数点后保留几位   3
	* @return 四舍五入后的结果 
	*/  
	public static String round(String v,int scale) {
	   v =nullToZero(v);
	   BigDecimal b = new BigDecimal(v);  
	   BigDecimal ne = new BigDecimal("1");  
	   return b.divide(ne, scale, BigDecimal.ROUND_HALF_UP).toString();  
	}
	
	
	/**
	 * 数字String 空转换为"0"
	 * 
	 * */
	public static String nullToZero(String str){
		if(str ==null){
			return "0";
		}
		String str1 =str.trim();
		if("".equals(str1)){
			return "0";
		}
		return str1;
	}
	
	/**
	 * �?0
	 * @param num
	 * @param length
	 * @return
	 */
	public static String addZero(String num,int length){
		String str = num+"";
		String zero = "";
		int zeroNum = length-str.length();
		for(int i=0;i<zeroNum;i++){
			zero += "0";
		}
		return zero+str;
	}
	
	
	/**
	 * 获取季度
	 * @param month
	 * @return
	 */
	public static Map<Integer,Integer> getQuarterDate(int month){
		Map<Integer,Integer> mapQuarter = new HashMap<Integer,Integer>();
		switch (month) {
			case 1:
			case 2:
			case 3:
				mapQuarter.put(1,1);
				mapQuarter.put(2,2);
				mapQuarter.put(3,3);
				break;
			case 4:
			case 5:
			case 6:
				mapQuarter.put(4,4);
				mapQuarter.put(5,5);
				mapQuarter.put(6,6);
				break;
			case 7:
			case 8:
			case 9:
				mapQuarter.put(7,7);
				mapQuarter.put(8,8);
				mapQuarter.put(9,9);
				break;
			case 10:
			case 11:
			case 12:
				mapQuarter.put(10,10);
				mapQuarter.put(11,11);
				mapQuarter.put(12,12);
				break;
		}
		return mapQuarter;
	}
	
	/**
	 * 获取季度
	 * @param month
	 * @param se 1 季度�?始月�?  2 季度结束月份
	 * @return
	 */
	public static String getQuarterStartEndDate(int month,String se){
		String math="";
		switch (month) {
			case 1:
			case 2:
			case 3:
				if("1".equals(se)){
					math= "01";
				}else {
					math= "03";
				}
				break;
			case 4:
			case 5:
			case 6:
				if("1".equals(se)){
					math= "04";
				}else {
					math= "06";
				}
				break;
			case 7:
			case 8:
			case 9:
				if("1".equals(se)){
					math= "07";
				}else {
					math= "09";
				}
				break;
			case 10:
			case 11:
			case 12:
				if("1".equals(se)){
					math= "10";
				}else {
					math= "12";
				}
				break;
		}
		return math;
	}
	
	
	/**
	 * 获取季度
	 * @param month
	 * @return 季度集合  123  456  789 101112
	 * 
	 */
	public static String getQuarterDateSum(int month){
		String tem="";
		switch (month) {
			case 1:
			case 2:
			case 3:
				tem="123";
				break;
			case 4:
			case 5:
			case 6:
				tem="456";
				break;
			case 7:
			case 8:
			case 9:
				tem="789";
				break;
			case 10:
			case 11:
			case 12:
				tem="101112";
				break;
		}
		return tem;
	}
	/**
	 * 获取上个季度�?后一个月
	 * @param month
	 * @param se 1 季度�?始月�?  2 季度结束月份
	 * @return
	 */
	public static Map<String,String> getQuarterPre(int year,int month){
		Map<String,String>	map=new HashMap<String, String>();
		String qm="";
		int qn=0;
		switch (month) {
			case 1:
			case 2:
			case 3:
				qn=year-1;
				qm="12";
				break;
			case 4:
			case 5:
			case 6:
				qn=year;
				qm="03";
				break;
			case 7:
			case 8:
			case 9:
				qn=year;
				qm="06";
				break;
			case 10:
			case 11:
			case 12:
				qn=year;
				qm="09";
				break;
		}
		map.put("year", qn+"");
		map.put("month", qm);
		return map;
	}
	
	/**
	 * 获取当前系统时间
	 * @param type  获取时间格式
	 * @return String  返回时间字符�?
	 *
	 */
	public static String getNewDate(String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);
		try {
			return df.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * 查找流程主表�?
	 * @param workflowid  流程ID
	 * @return String  流程主表
	 *
	 */
	public static String getTableName(String workflowid) {
		RecordSet rs=new RecordSet();
		rs.executeSql("select formid from workflow_base where id='"+workflowid+"'");
		if(rs.next()){
			String formid=Util.null2String(rs.getString("formid")).replaceAll("-", "");
			return "formtable_main_"+formid;
		}
		return "";
	}
	/**
	 * 查找流程主表�?
	 * @param requestmark  流程编号
	 * @return String  流程主表
	 *
	 */
	public static String getTableNameByMark(String requestmark) {
		RecordSet rs=new RecordSet();
		rs.executeSql("select formid from workflow_base where id=(SELECT workflowid FROM workflow_requestbase where requestmark='"+requestmark+"')");
		if(rs.next()){
			String formid=Util.null2String(rs.getString("formid")).replaceAll("-", "");
			return "formtable_main_"+formid;
		}
		return "";
	}
	/**
	 * 查找流程主表�?
	 * @param requestid  请求
	 * @return String  流程主表
	 *
	 */
	public static String getTableNameByRequestid(String requestid) {
		RecordSet rs=new RecordSet();
		rs.executeSql("select formid from workflow_base where id=(SELECT workflowid FROM workflow_requestbase where requestid='"+requestid+"')");
		if(rs.next()){
			String formid=Util.null2String(rs.getString("formid")).replaceAll("-", "");
			return "formtable_main_"+formid;
		}
		return "";
	}

	public static String  toNullString(String v) {
		if (v==null||v==""||"".equals(v)) {
			return null;
		}else{
			return v;
		}		
	}
	
	
	/**
	 * 生成�?个uuid
	 * @return String
	 *
	 */
	public static String getUUID(){
		String uuid = java.util.UUID.randomUUID().toString();
		uuid = uuid.toUpperCase().replaceAll("-", "");
		return uuid;
	}
	
	
	/**
	 * 把字符串转为特定的日期类�?
	 * @param type  原来的日期类�?
	 * @param type2  要转到日期类�?
	 * @return String
	 *
	 */
	public static String strToDate(String type,String type2,String date) {
		SimpleDateFormat df = new SimpleDateFormat(type);
		SimpleDateFormat df2 = new SimpleDateFormat(type2);
		try {
			return df2.format(df.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * 把字符串对象转换为double类型，自动去除千分位
	 * @param v 被转换对�?
	 * @return Double 返回结果
	 *
	 */
	public static Double toDouble(String v) {
		if (v==null||v=="") {
			return 0.0;
		}
		try {
			return Double.parseDouble(v.replaceAll(",", ""));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	/**
	 * 格式化千分位，保�?2位小�?
	 * @return String
	 *
	 */
	public static String qfw(String v1) {
		v1 =nullToZero(v1);
		DecimalFormat d1 =new DecimalFormat(",##0.00");
		return d1.format(new BigDecimal(v1));
	}
	public static void main(String[] args) {
		System.out.println(div("9","1",2));
	}
	  
	/** 
	* 提供精确的乘法运算�?? 
	* @param v1 被乘�? 
	* @param v2 乘数 
	* @return 两个参数的积 
	*/  
	public static String mul(String v1, String v2) {  
		v1 =nullToZero(v1);
		v2 =nullToZero(v2);
	   BigDecimal b1 = new BigDecimal(v1);  
	   BigDecimal b2 = new BigDecimal(v2);  
	   return b1.multiply(b2).toString();  
	}  
	  
	/** 
	* 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 
	* 小数点以�?10位，以后的数字四舍五入�?? 
	* @param v1 被除�? 
	* @param v2 除数 
	* @return 两个参数的商 
	*/  
	  
	public static String div(String v1, String v2) { 
		v1 =nullToZero(v1);
		v2 =nullToZero(v2);
	   return div(v1, v2, DEF_DIV_SCALE);  
	}  
	  
	/** 
	* 提供（相对）精确的除法运算�?�当发生除不尽的情况时，由scale参数�? 
	* 定精度，以后的数字四舍五入�?? 
	* @param v1 被除�? 
	* @param v2 除数 
	* @param scale 表示表示�?要精确到小数点以后几位�?? 
	* @return 两个参数的商 
	*/  
	public static String div(String v1, String v2, int scale) {  
	   if (scale < 0) {  
	    throw new IllegalArgumentException(  
	      "保留小数点位置必须大于等�?0");  
	   }  
	   v1 =nullToZero(v1);
	   v2 =nullToZero(v2);
	   BigDecimal b1 = new BigDecimal(v1);  
	   BigDecimal b2 = new BigDecimal(v2);  
	   return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();  
	}  
	  
	
	
	/**
	 * 字符为空，则转换�?0,保留两位小数
	 * 
	 * */
	public static String getNullToString(String num){
		if("".equals(num) || num==null){
			return "0.00";
		}
		BigDecimal   b   =   new   BigDecimal(num);  
		
		return b.setScale(2,  BigDecimal.ROUND_HALF_UP)+"";
	}
	
	/**
	 * 补位 0
	*  @param l  �?求长�?
	*  @param num 
	*  @return      
	*  String     
	*  
	 */
	public static String getTenNum(int l,String num){
		String str ="";
		if(num.length()<10){
			for(int i=1;i<=l-num.length();i++){
				str ="0"+str;
			}
		}
		return str+num;
	}
	/**
	* 
	*  截取 字符 utf-8
	*  @param s
	*  @param num
	*  @return
	*  @throws Exception      
	*  String     
	*  
	 */
	public static String idgui(String s,int num)throws Exception{
        int changdu = s.getBytes("UTF-8").length;
        if(changdu > num){
            s = s.substring(0, s.length() - 1);
            s = idgui(s,num);
        }
        return s;
    }
	/** 
	 * SQL语句查询结果集转换成Json字符�?
	 * @param sql sql语句
	 * @return
	 */
	public static JSONObject recordSetToJson(String sql){
		JSONObject json = new JSONObject();
		try {
			RecordSet rs= new RecordSet();
			rs.executeSql(sql);
			String[] keySet = rs.getColumnName();
			int num=0;
			while (rs.next()) {
				JSONObject dajson = new JSONObject();
				for (String key : keySet) {
					dajson.put(key, Util.null2String(rs.getString(key)));
				}
				
				json.put(num+"", dajson);
				num++;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 取下拉框�?
	 * @param workflowid
	 * @param detailtable
	 * @param fieldname
	 * @param selectvalue
	 * @return
	 */
	public static String getSelN(String workflowid,String detailtable,String fieldname,String selectvalue){
		String selectname="";
		if("".equals(selectvalue)){
			return selectname;
		}
		RecordSet	rs=new RecordSet();
		String sql="select  selectname from workflow_selectitem where fieldid= " +
				"( select id from workflow_billfield where billid=" +
				"(select formid from workflow_base where id='"+workflowid+"') and detailtable='"+detailtable+"'" +
						" and fieldname='"+fieldname+"') and selectvalue='"+selectvalue+"'";
		rs.executeSql(sql);
		if(rs.next()){
			selectname=Util.null2String(rs.getString("selectname"));
		}
		if(!"".equals(selectvalue)&&"".equals(detailtable)&&"".equals(selectname)){
			sql="select  selectname from workflow_selectitem where fieldid= " +
					"( select id from workflow_billfield where billid=" +
					"(select formid from workflow_base where id='"+workflowid+"') and detailtable is null" +
							" and fieldname='"+fieldname+"') and selectvalue='"+selectvalue+"'";
			rs.executeSql(sql);
			if(rs.next()){
				selectname=Util.null2String(rs.getString("selectname"));
			}
		}
		return selectname;
	}
	
	/**
	 * 获取流程主表�?
	 * @Title: tableNameByWorkflowid
	 * @param workflowid
	 * @return:String  
	 * @throws
	 */
	public static String tableNameByWorkflowid(String workflowid){
		String tablename="";
		RecordSet rs= new RecordSet();
		rs.executeSql("select formid from workflow_base where id ='"+workflowid+"'");
		if(rs.next()){
			String formid = Util.null2String(rs.getString("formid")).replaceAll("-", "");
			tablename = "formtable_main_"+formid;
		}
		
		return tablename;

	}
	

	/**
	* 字符串转换成日期
	* @param str
	* @param formatStr 
	* @return date
	*/
	public static Date StrToDate(String str,String formatStr) {
	  
	   SimpleDateFormat format = new SimpleDateFormat(formatStr);
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	public static void main2(String[] args) {
		System.out.println(getTenNum(12,"244"));
	}
	/**
	 * 获取出纳操作日期
	 * @Title: getCNDate
	 * @param workflowid 
	 * @param requestid 
	 * @return:String  
	 * @throws
	 */
	public static String getCNDate(String workflowid,String requestid){
		String operatedate="";
		RecordSet rs= new RecordSet();
		rs.executeSql("SELECT operatedate FROM workflow_requestLog WHERE nodeid IN " +
				"( SELECT id FROM workflow_nodebase WHERE id IN " +
				"(SELECT nodeid FROM workflow_flownode  WHERE workflowid='"+workflowid+"') " +
						"AND nodename LIKE '%出纳%') AND  requestid='"+requestid+"'");
		if(rs.next()){
			operatedate = Util.null2String(rs.getString("operatedate"));
		}
		return operatedate;
	}
	/**
	 * 人员id
	 * @param userid
	 * @return
	 */
	public static String getWorkcodeByid(int userid){
		String workcode="";
		RecordSet rs= new RecordSet();
		rs.executeSql("select workcode from hrmresource where id='"+userid+"'");
		if(rs.next()){
			workcode = Util.null2String(rs.getString("workcode"));
		}
		return workcode;
	}
	/**
	 * 费用申请流程是否已被占用
	 * @param requestid_gl 关联流程请求id
	 * @param requestid 当前流程请求id
	 * @return 占用关联流程的流程编�?
	 */
	public static String getGllc(String requestid_gl,String requestid){
		String requestmark="";
		RecordSet rs= new RecordSet();
		rs.executeSql("SELECT requestid,requestmark FROM wv_v_gllc WHERE ','+gllc+',' LIKE '%,"+requestid_gl+",%' and requestid !='"+requestid+"'");
		if(rs.next()){
			if(!"".equals( Util.null2String(rs.getString("requestmark")))){
				requestmark = Util.null2String(rs.getString("requestmark"));
			}else{
				requestmark = Util.null2String(rs.getString("requestid"));
			}
		}
		return requestmark;
	}
}
