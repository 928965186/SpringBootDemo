package testdemo;


import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.MainTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
/**
 * ������������</br>
 * <p>
 * ��������Ҫ������</br>
 * 1. �������봦����� </br>
 * �ڵ㸽�Ӳ�������URL��/testdemo/demohou.class</br>
 * </p>
 * @author xiehui
 * @date 2018��9��3��08:59:43
 * @version 1.0.1
 */
public class demohou implements Action{
	private String info = "������������:";
	@Override
	public String execute(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		String src = requestinfo.getRequestManager().getSrc();//��ǰ�������� submit:�ύ/reject:�˻�
		RecordSet rs = new RecordSet();
		rs.writeLog("------------------�����ᵥ�ڵ�����-------------------");
		System.out.println("��ǰ�������� submit:�ύ/reject:�˻�-------------"+src);
		//ȡ�������� 
		Property[] properties =requestinfo.getMainTableInfo().getProperty();// ��ȡ�����ֶ���Ϣ
		for (int i = 0; i < properties.length; i++) {
			String value=Util.null2String(properties[0].getValue());
			
			String Money = Util.null2String(this.getPropertyByName(properties, "Money"));//�������ϼ�
			String loanMoney = Util.null2String(this.getPropertyByName(properties, "loanMoney"));//�����
			String sMoney = Util.null2String(this.getPropertyByName(properties, "sMoney"));//���γ������
			
			Double m=Double.valueOf(Money);
			Double l=Double.valueOf(loanMoney);
			Double s=Double.valueOf(sMoney);
			
			double num=l-s;
			System.out.println("Money"+m+"LoanMoney"+l+"sMoney"+s);
			
			String sql = "update  formtable_main_176 set usingMoney ='"+s+"',money= '"+num+"' where Hrm='"+value+"'";//�ᵥ�ڵ��ύʱ������������
			
			boolean isSuc = rs.executeSql(sql);
			rs.writeLog(info+">>�������ݿ⣺"+isSuc+":"+sql);
			if(!isSuc){
				rs.writeLog(info+">>�������ݿ�ʧ�ܣ�"+isSuc+":"+sql);
				requestinfo.getRequestManager().setMessageid(info);
				requestinfo.getRequestManager().setMessagecontent(info+"ʧ�ܣ��������ݿ�ʧ��"+sql);
				return "0";
			}
			
		}
		
		return SUCCESS;
	}
	/**
	 * ��ȡ�����ֶε�ֵ
	 * @param property ����Property����
	 * @param name �ֶ���
	 * @return value ֵ
	 */
	private String getPropertyByName(Property[] property, String name){
		for(Property p : property){
			if(Util.null2String(name).equalsIgnoreCase(p.getName()))
				return Util.null2String(p.getValue());
		}
		return "";
	}
}
