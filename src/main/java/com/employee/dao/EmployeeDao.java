package com.employee.dao;

import java.util.List;
import java.util.Map;

public interface EmployeeDao 
{
	public void batchInsert(List<Employee> employees);
	
	public List<Employee> getEmployees();
	
	public void batchUpdate(List<Employee> employees);
	
	public void batchDelete(List<Employee> employees);
	
	public void printDetailsWithDepartment();
	
	Map<String,Integer> getDeptWiseExpenditure();
	
	Map<String,Integer> getHodWiseEmpCount();
	
	List<Employee> getEmpBelowSalary(int salary);
	
	List<Employee> getEmpsBetweenDoj(String date1,String date2);
	
	int getEmployeeSalary(int employeeId);
	
	String getEmployeeDoj(int employeeId);

	String getHodName(int departmentId);
	
	Employee getEmployeeDetails(int employeeId);
	
}
