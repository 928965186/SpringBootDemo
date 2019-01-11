package com.westvalley.department.pojo;

public class Department {
	private long ID;
	private String departmentid;
	private String departmentname;
	private String DEPTCODE;
	private String DEPTNAME;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getDEPTCODE() {
		return DEPTCODE;
	}
	public void setDEPTCODE(String dEPTCODE) {
		DEPTCODE = dEPTCODE;
	}
	public String getDEPTNAME() {
		return DEPTNAME;
	}
	public void setDEPTNAME(String dEPTNAME) {
		DEPTNAME = dEPTNAME;
	}
	public Department(String departmentid, String departmentname, String dEPTCODE, String dEPTNAME) {
		super();
		this.departmentid = departmentid;
		this.departmentname = departmentname;
		DEPTCODE = dEPTCODE;
		DEPTNAME = dEPTNAME;
	}
	
	public Department() {
		
	}
	@Override
	public String toString() {
		return "Department [ID=" + ID + ", departmentid=" + departmentid + ", departmentname=" + departmentname
				+ ", DEPTCODE=" + DEPTCODE + ", DEPTNAME=" + DEPTNAME + "]";
	}
	
}
