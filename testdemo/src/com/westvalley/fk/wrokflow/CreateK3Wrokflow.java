package com.westvalley.fk.wrokflow;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.general.BaseBean;

/**
 * 使用外部数据源，同步K3的T_CN_PAYAPPLY表至e-cology数据库
 * @author xieh
 * @date 2018年9月12日19:47:11
 * @version 1.0
 *
 */
public class CreateK3Wrokflow extends BaseBean{
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
		
		
}
