package com.employee.dao;

public class DeptResultHolder 
{
	private int empId;
	private String empName;
	private	String deptName;
	private String hodName;
	
	@Override
	public String toString() {
		return "DeptResultHolder [empId=" + empId + ", empName=" + empName + ", deptName=" + deptName + ", hodName="
				+ hodName + "]";
	}
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getHodName() {
		return hodName;
	}
	public void setHodName(String hodName) {
		this.hodName = hodName;
	}
	
	
	
	

}
