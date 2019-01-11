package testdemo;


import weaver.conn.RecordSet;
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
/**
 * ������������</br>
 * <p>
 * ��������Ҫ������</br>
 * 1. �������봦����� </br>
 * �ڵ㸽�Ӳ�������URL��/testdemo/demosubmit.class</br>
 * </p>
 * @author xiehui
 * @date 2018��9��3��08:59:43
 * @version 1.0.1
 */
public class demosubmit implements Action{
	private String info = "�ɹ�������������:";
	@Override
	public String execute(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		RecordSet rs = new RecordSet();
		String DBName="formtable_main_181";
		rs.writeLog("------------------�����ᵥ�ڵ�����-------------------");
		//ȡ�������� 
		
			
		DetailTableInfo detail = requestinfo.getDetailTableInfo();//������ϸ��	
		Row[] rows=null;
		Cell[] cells=null;
		
		try {
			if (detail != null && detail.getDetailTableCount() > 0) {
				DetailTable d_table = detail.getDetailTable(0);//��һ����ϸ��
				rows = d_table.getRow();
				d_table = null;
				/*******ѭ����ϸ���������*******/
				 //�跽��ϸ
				  for (Row row : rows) {
				  	cells = row.getCell();//��ϸÿһ�е�ֵ
				  	//����
				  	String Mat = this.getCellByName(cells, "Mat");
				  	String Num = this.getCellByName(cells, "Num");
				  	String applyNum = this.getCellByName(cells, "applyNum");
				  	rs.writeLog("��ϸ��:"+Mat+Num+applyNum);
				  	
				  	int i=Integer.valueOf(Num);
				  	int a=Integer.valueOf(applyNum);
				  	
				  	int num=i-a;
				  	
				  	System.out.println("------------"+Mat+"---"+Num+"-------"+applyNum+"-----------");
				  	String sql = "update  '"+DBName+"' set Num ='"+num+"',frozenNum= '"+applyNum+"' where id='"+Mat+"'";//�ᵥ�ڵ��ύʱ������������
					
					boolean isSuc = rs.executeSql(sql);
					rs.writeLog(info+">>�������ݿ⣺"+isSuc+":"+sql);
					if(!isSuc){
						rs.writeLog(info+">>�������ݿ�ʧ�ܣ�"+isSuc+":"+sql);
						requestinfo.getRequestManager().setMessageid(info);
						requestinfo.getRequestManager().setMessagecontent(info+"ʧ�ܣ��������ݿ�ʧ��"+sql);
						return "0";
					}
				  }
				  
				  
			}
			
			
		} catch (Exception e) {
			
		}finally {
			 detail=null; rows=null; cells=null;
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
	
	/**
	 * ��ȡ��ϸ���ֶε�ֵ
	 * @param cells ��ϸĳ�����еļ���
	 * @param name �ֶ���
	 * @return value ֵ
	 */
	private String getCellByName(Cell[] cells, String name){
		for(Cell c : cells){
			if(Util.null2String(name).equalsIgnoreCase(c.getName())){
				return Util.null2String(c.getValue());
			}
		}
		return "";
	}
}
