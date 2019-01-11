package com.westvalley.fk.wrokflow.impl;

import java.util.List;

import com.westvalley.fk.service.ICreateK3Table;
import com.westvalley.fk.service.impl.CreateK3TableImpl;
import com.westvalley.fk.wrokflow.ICreateK3Wrokflow;

import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.RequestService;

/**
 * ���ݲ��ҵ�K3���ݴ���OA����
 * @author xieh
 * @date 2018��9��13��18:46:35
 * @version 1.0
 *
 */
public class CreateK3WrokflowImpl implements ICreateK3Wrokflow{
	//e-c����Դ
	RecordSet rs=new RecordSet();
	//�Զ��巵�ز����ڷ����и�ֵ
	String flag="";
	//��������
	String workflowName="";
	//�Ƿ��Զ���ת��һ���ڵ�  0���Զ�  1�Զ�
	String isNext="1";
	//ʹ���Զ���ӿ�ʵ�����еķ�����ȡK3�����ݲ�����List
	ICreateK3Table k3Table=new CreateK3TableImpl();
	
	
	@Override
	public String create() {
		rs.writeLog("���봴�����۵�����");
		//��ȡȫ������������K3����
		List<Object> list = k3Table.QuesyK3();
		//��ȡ����Դ
		RecordSet rs=new RecordSet();
		//--------------------------------------------------------------------
		// ���̷��������
		RequestService reqService = new RequestService();
		// ����������Ϣ����
		RequestInfo reqInfo = new RequestInfo();
		// ���̷�����ID
		reqInfo.setCreatorid((String) list.get(1));
		// workflowid/��������ID=5
		reqInfo.setWorkflowid("120");
		// �����̶�
		reqInfo.setRequestlevel("0");
		// ��������
		reqInfo.setRemindtype("1");
		// �Ƿ��Զ���ת���¸��ڵ㣨0�����Զ��ύ��1:�������Զ��ύ��
		reqInfo.setIsNextFlow(isNext);
		// ���̱���  ���̱���=����-���̷�����-ʱ�䣨YYYY-mm-dd��
		//reqInfo.setDescription("������������"+"-"+);
		//-----------------------------------------------------------------------
		MainTableInfo mtInfo = new MainTableInfo();// ��������������Ϣ����
		Property[] mProperty = new Property[1];// ���������������Լ���
		
		try {
			flag = reqService.createRequest(reqInfo);
		} catch (Exception e) {
			rs.writeLog(TimeUtil.getCurrentTimeString()+"���������쳣"+e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

}
