package testdemo;

import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

public class pagetest implements Action{
	
	public  String getWFDegree() {
		
		return "true";
		
	}
	
	public String getCanOperation() {
		return "0";
		
	}

	@Override
	public String execute(RequestInfo arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
