package testdemo;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.Cell;
import weaver.soa.workflow.request.DetailTable;
import weaver.soa.workflow.request.DetailTableInfo;
import weaver.soa.workflow.request.Property;
import weaver.soa.workflow.request.RequestInfo;
import weaver.soa.workflow.request.Row;
/**
 * ������������</br>
 * <p>
 * ��������Ҫ������</br>
 * 1. ��������������� </br>
 * �ڵ㸽�Ӳ�������URL��/testdemo/demoreject.class</br>
 * </p>
 * @author xiehui
 * @date 2018��9��3��08:59:43
 * @version 1.0.1
 */
public class demoreject implements Action{
	private String info = "�ɹ�������������:";
	@Override
	public String execute(RequestInfo requestinfo) {
		String src = requestinfo.getRequestManager().getSrc();//��ǰ�������� submit:�ύ/reject:�˻�
		RecordSet rs = new RecordSet();//������jdbc�е�conn
		String Num="";
		String frozenNum="";
		String DBName="formtable_main_181";
		
		rs.writeLog("------------------����ڵ�����-------------------");
		System.out.println("��ǰ�������� submit:�ύ/reject:�˻�-------------"+src);
		
	
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
				  	rs.writeLog("��ϸ��:"+Mat);
				  	
				  	
				  	
				  	if (src.equals("submit")) {
						
						String sql = "update  '"+DBName+"' set frozenNum=0 where id='"+Mat+"'";

						boolean isSuc = rs.executeSql(sql);
						rs.writeLog(info+">>�������ݿ⣺"+isSuc+":"+sql);
						if(!isSuc){
							rs.writeLog(info+">>�������ݿ�ʧ�ܣ�"+isSuc+":"+sql);
							requestinfo.getRequestManager().setMessageid(info);
							requestinfo.getRequestManager().setMessagecontent(info+"ʧ�ܣ��������ݿ�ʧ��"+sql);
							return "0";
						}
					
					}else if (src.equals("reject")) {
						String selectsql="select Num,frozenNum from '\"+DBName+\"' where id='"+Mat+"'";
						rs.executeSql(selectsql);
						if (rs.next()) {
							Num=rs.getString("Num");
							frozenNum=rs.getString("frozenNum");
						}
						int n=Integer.valueOf(Num);
						int i=Integer.valueOf(frozenNum);
						
						int num=n+i;
						String sql = "update  '\"+DBName+\"' set frozenNum ='"+0+"',Num='"+num+"' where id='"+Mat+"'";
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
