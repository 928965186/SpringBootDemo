package com.westvalley.fk.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.westvalley.fk.service.ICreateK3Table;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
/**
 * 查找所有符合条件的K3数据并返回一个List
 * @author xieh
 * @date 2018年9月13日18:45:17
 * @version 1.0
 */
public class CreateK3TableImpl implements ICreateK3Table{
	//主表数据   共十二条
	String FBillNo="";//单据编号
	String FCREATORID="";//制单人
	String FBILLTYPEID="";//单据类型
	String FEXPECTPAYDATE="";//付款日期//TODO 是否换成Date类型
	String F_ZTE_FKGYS="";//供应商名称
	String FCURRENCYID="";//币别
	Double FEXCHANGERATE=0.00;//汇率 四位小数点
	String FEACHBANKNAME="";//供应商开户行
	String FEACHCCOUNTNAME="";//账号名称
	String FEACHBANKACCOUNT="";//账号
	String F_ZTE_Text="";//SWIFT CODE
	String F_ZTE_Remarks="";//备注
	//子表数据 共八条
	String FPAYPURPOSEID="";//付款用途
	String FCOSTID="";//费用项目
	String FSOURCETYPE="";//单源类型
	String FSRCBILLNO="";//源单单号
	String FSETTLETYPEID="";//结算方式
	Double FAPPLYAMOUNTFOR=0.00;//申请付款金额
	Double FPAYAMOUNTFOR=0.00;//应付金额
	String FDescription="";//备注
			
	//用一个list装数据
	List<Object> list=new ArrayList<Object>();
	//使用外部数据源
	RecordSetDataSource rs = new RecordSetDataSource("k3uat");
	//查询语句 查询k3所有的新建付款单数据
	String sql="select * from T_CN_PAYAPPLY where FDOCUMENTSTATUS='A'";
	//e-c数据源
	RecordSet rsw=new RecordSet();
	@Override
	public List<Object> QuesyK3() {
		rs.writeLog("查找K3数据开始");
		boolean execute = rs.execute(sql);
		if (execute) {
			rs.writeLog("查找K3数据成功");
		}else {
			rs.writeLog("查找K3数据失败请稍后重试");
		}
		while (rs.next()) {
			FBillNo=rs.getString("FBillNo");
			FCREATORID=rs.getString("FCREATORID");
			FBILLTYPEID=rs.getString("FBILLTYPEID");
			FEXPECTPAYDATE=rs.getString("FEXPECTPAYDATE");
			F_ZTE_FKGYS=rs.getString("F_ZTE_FKGYS");
			FCURRENCYID=rs.getString("FCURRENCYID");
			FEXCHANGERATE=rs.getDouble("FEXCHANGERATE");
			FEACHBANKNAME=rs.getString("FEACHBANKNAME");
			FEACHCCOUNTNAME=rs.getString("FEACHCCOUNTNAME");
			FEACHBANKACCOUNT=rs.getString("FEACHBANKACCOUNT");
			F_ZTE_Text=rs.getString("F_ZTE_Text");
			F_ZTE_Remarks=rs.getString("F_ZTE_Remarks");
			
			FPAYPURPOSEID=rs.getString("FPAYPURPOSEID");
			FCOSTID=rs.getString("FCOSTID");
			FSOURCETYPE=rs.getString("FSOURCETYPE");
			FSRCBILLNO=rs.getString("FSRCBILLNO");
			FSETTLETYPEID=rs.getString("FSETTLETYPEID");
			FAPPLYAMOUNTFOR=rs.getDouble("FAPPLYAMOUNTFOR");
			FPAYAMOUNTFOR=rs.getDouble("FPAYAMOUNTFOR");
			FDescription=rs.getString("FDescription");
			
			list.add(FBillNo);
			list.add(FCREATORID);
			list.add(FBILLTYPEID);
			list.add(FEXPECTPAYDATE);
			list.add(F_ZTE_FKGYS);
			list.add(FCURRENCYID);
			list.add(FEXCHANGERATE);
			list.add(FEACHBANKNAME);
			list.add(FEACHCCOUNTNAME);
			list.add(FEACHBANKACCOUNT);
			list.add(F_ZTE_Text);
			list.add(F_ZTE_Remarks);
			
			list.add(FPAYPURPOSEID);
			list.add(FCOSTID);
			list.add(FSOURCETYPE);
			list.add(FSRCBILLNO);
			list.add(FSETTLETYPEID);
			list.add(FAPPLYAMOUNTFOR);
			list.add(FPAYAMOUNTFOR);
			list.add(FDescription);
			if (list!=null) {
				rs.writeLog("获取K3数据成功");
			}else {
				rs.writeLog("获取K3数据失败请检查数据后重试");
			}
		}
		return list;
	}

}
