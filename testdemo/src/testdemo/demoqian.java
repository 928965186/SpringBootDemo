package testdemo;


import weaver.conn.RecordSet;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class demoqian implements Action{

	@Override
	public String execute(RequestInfo requestinfo) {
		// TODO Auto-generated method stub
		RecordSet rs = new RecordSet();
		
		rs.writeLog("------------------进入节点前操作aaaaaaa-------------------");
		System.out.println("1111111111111+++++节点前");
		requestinfo.getRequestManager().setMessageid(requestinfo.getRequestManager().getRequestname());
		requestinfo.getRequestManager().setMessagecontent("出现异常：");
		requestinfo.getRequestManager().setMessage("111100");
		
		return FAILURE_AND_CONTINUE;
	}

}
