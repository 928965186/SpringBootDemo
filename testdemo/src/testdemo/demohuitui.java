package testdemo;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
/**
 * ������������</br>
 * <p>
 * ��������Ҫ������</br>
 * 1. ��������������� </br>
 * �ڵ㸽�Ӳ�������URL��/testdemo/demohuitui.class</br>
 * </p>
 * @author xiehui
 * @date 2018��9��3��08:59:43
 * @version 1.0.1
 */
public class demohuitui implements Action{
	private String info = "������������:";
	@Override
	public String execute(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		String src = requestinfo.getRequestManager().getSrc();//��ǰ�������� submit:�ύ/reject:�˻�
		String usingMoney="";//����������
		String money="";//�����
		RecordSet rs = new RecordSet();//������jdbc�е�conn
		
		
		rs.writeLog("------------------����ڵ�����-------------------");
		System.out.println("��ǰ�������� submit:�ύ/reject:�˻�-------------"+src);
		
		Property[] properties =requestinfo.getMainTableInfo().getProperty();// ��ȡ�����ֶ���Ϣ
		String applyPerson = Util.null2String(this.getPropertyByName(properties, "applyPerson"));//��ȡ������
		String sMoney = Util.null2String(this.getPropertyByName(properties, "sMoney"));//���γ������
		
		int a=Integer.valueOf(applyPerson);
		
		
		Double s=Double.valueOf(sMoney);
			
			if (src.equals("submit")) {
				String selectsql="select usingMoney from formtable_main_176 where Hrm='"+a+"'";
				rs.executeSql(selectsql);
				if (rs.next()) {
					 usingMoney=rs.getString("usingMoney");
				}
				System.out.println("usingMoney-----------------------------"+usingMoney);
				Double u=Double.valueOf(usingMoney);
				String sql = "update  formtable_main_176 set usedMoney ='"+u+"',usingMoney=0 where Hrm='"+a+"'";

				boolean isSuc = rs.executeSql(sql);
				rs.writeLog(info+">>�������ݿ⣺"+isSuc+":"+sql);
				if(!isSuc){
					rs.writeLog(info+">>�������ݿ�ʧ�ܣ�"+isSuc+":"+sql);
					requestinfo.getRequestManager().setMessageid(info);
					requestinfo.getRequestManager().setMessagecontent(info+"ʧ�ܣ��������ݿ�ʧ��"+sql);
					return "0";
				}
			
			}else if (src.equals("reject")) {
				String selectsql="select money from formtable_main_176 where Hrm='"+a+"'";
				rs.executeSql(selectsql);
				if (rs.next()) {
					money=rs.getString("money");
				}
				System.out.println("money-----------------------------"+money);
				Double u=Double.valueOf(money);
				
				double num=u+s;
				String sql = "update  formtable_main_176 set usingMoney ='"+0+"',money='"+num+"' where Hrm='"+a+"'";
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
