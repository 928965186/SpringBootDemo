package testdemo;


import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.DetailTableInfo;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;

public class TestAction extends BaseBean implements Action {
	public String p1; // �Զ������1
	public String p2; // �Զ������2

	public String execute(RequestInfo requestinfo) {
		
		System.out.println("����Action requestid=" + requestinfo.getRequestid());
		String requestid = requestinfo.getRequestid();// ����ID 
		String requestlevel = requestinfo.getRequestlevel();//��������̶�
		String src =requestinfo.getRequestManager().getSrc(); //��ǰ��������submit:�ύ/reject:�˻� 
		String workflowid =requestinfo.getWorkflowid();//����ID 
		String tablename =requestinfo.getRequestManager().getBillTableName();//������ 
		int billid = requestinfo.getRequestManager().getBillid();//������ID
		User usr = requestinfo.getRequestManager().getUser();//��ȡ��ǰ�����û����� 
		String requestname =requestinfo.getRequestManager().getRequestname();//�������
		String remark =requestinfo.getRequestManager().getRemark();//��ǰ�û��ύʱ��ǩ�����
		int formid =requestinfo.getRequestManager().getFormid();//��ID 
		int isbill= requestinfo.getRequestManager().getIsbill();//�Ƿ����Զ����
		
		//ȡ�������� 
		Property[] properties =requestinfo.getMainTableInfo().getProperty();// ��ȡ�����ֶ���Ϣ 
		for(int i = 0; i < properties.length; i++) { 
			String name =properties[i].getName();// ���ֶ����� 
			String value =Util.null2String(properties[i].getValue());// ���ֶζ�Ӧ��ֵ
			System.out.println(name + " " + value); } //ȡ��ϸ����
			weaver.soa.workflow.request.DetailTable[] detailtable =requestinfo.getDetailTableInfo().getDetailTable();// ��ȡ������ϸ�� 
			if(detailtable.length > 0) { 
				for (int i = 0; i <detailtable.length; i++) { 
					DetailTable dt = detailtable[i];//ָ����ϸ�� 
					Row[] s = ((weaver.soa.workflow.request.DetailTable) dt).getRow();// ��ǰ��ϸ�����������,���д洢 
					for (int j =0; j < s.length; j++) { 
						Row r = s[j];// ָ���� 
						Cell c[] =r.getCell();// ÿ�������ٰ��д洢 
						for (int k = 0; k < c.length; k++) {
								Cell c1 = c[k];// ָ���� 
								String name = c1.getName();// ��ϸ�ֶ�����
								String value = c1.getValue();// ��ϸ�ֶε�ֵ
								System.out.println(name + " " + value); 
								}
						}
					}
				}
	// ����������ת�������������У����̲���������ת��������ʾ���ص��Զ��������Ϣ
	requestinfo.getRequestManager().setMessagecontent("�����Զ���Ĵ�����Ϣ");
	requestinfo.getRequestManager().setMessageid("������Ϣ���");
	System.out.println("Actionִ����� �������p1="+this.getP1()+"p2="+this.getP2());
	return SUCCESS;// return���ع̶�����`SUCCESS`
																																																					// }
	}
	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}
}