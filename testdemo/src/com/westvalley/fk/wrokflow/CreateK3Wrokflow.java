package com.westvalley.fk.wrokflow;

import java.util.ArrayList;
import java.util.List;

import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.general.BaseBean;

/**
 * ʹ���ⲿ����Դ��ͬ��K3��T_CN_PAYAPPLY����e-cology���ݿ�
 * @author xieh
 * @date 2018��9��12��19:47:11
 * @version 1.0
 *
 */
public class CreateK3Wrokflow extends BaseBean{
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
		
		
}
