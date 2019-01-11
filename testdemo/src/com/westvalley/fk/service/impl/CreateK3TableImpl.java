package com.westvalley.fk.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.westvalley.fk.service.ICreateK3Table;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
/**
 * �������з���������K3���ݲ�����һ��List
 * @author xieh
 * @date 2018��9��13��18:45:17
 * @version 1.0
 */
public class CreateK3TableImpl implements ICreateK3Table{
	//��������   ��ʮ����
	String FBillNo="";//���ݱ��
	String FCREATORID="";//�Ƶ���
	String FBILLTYPEID="";//��������
	String FEXPECTPAYDATE="";//��������//TODO �Ƿ񻻳�Date����
	String F_ZTE_FKGYS="";//��Ӧ������
	String FCURRENCYID="";//�ұ�
	Double FEXCHANGERATE=0.00;//���� ��λС����
	String FEACHBANKNAME="";//��Ӧ�̿�����
	String FEACHCCOUNTNAME="";//�˺�����
	String FEACHBANKACCOUNT="";//�˺�
	String F_ZTE_Text="";//SWIFT CODE
	String F_ZTE_Remarks="";//��ע
	//�ӱ����� ������
	String FPAYPURPOSEID="";//������;
	String FCOSTID="";//������Ŀ
	String FSOURCETYPE="";//��Դ����
	String FSRCBILLNO="";//Դ������
	String FSETTLETYPEID="";//���㷽ʽ
	Double FAPPLYAMOUNTFOR=0.00;//���븶����
	Double FPAYAMOUNTFOR=0.00;//Ӧ�����
	String FDescription="";//��ע
			
	//��һ��listװ����
	List<Object> list=new ArrayList<Object>();
	//ʹ���ⲿ����Դ
	RecordSetDataSource rs = new RecordSetDataSource("k3uat");
	//��ѯ��� ��ѯk3���е��½��������
	String sql="select * from T_CN_PAYAPPLY where FDOCUMENTSTATUS='A'";
	//e-c����Դ
	RecordSet rsw=new RecordSet();
	@Override
	public List<Object> QuesyK3() {
		rs.writeLog("����K3���ݿ�ʼ");
		boolean execute = rs.execute(sql);
		if (execute) {
			rs.writeLog("����K3���ݳɹ�");
		}else {
			rs.writeLog("����K3����ʧ�����Ժ�����");
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
				rs.writeLog("��ȡK3���ݳɹ�");
			}else {
				rs.writeLog("��ȡK3����ʧ���������ݺ�����");
			}
		}
		return list;
	}

}
