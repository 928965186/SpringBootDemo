package testdemo;


import weaver.conn.RecordSet;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class demoqian implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		RecordSet rs = new RecordSet();
		
		rs.writeLog("------------------����ڵ�ǰ����aaaaaaa-------------------");
		System.out.println("1111111111111+++++�ڵ�ǰ");
		requestinfo.getRequestManager().setMessageid(requestinfo.getRequestManager().getRequestname());
		requestinfo.getRequestManager().setMessagecontent("�����쳣��");
		requestinfo.getRequestManager().setMessage("111100");
		
		return FAILURE_AND_CONTINUE;
	}

}
