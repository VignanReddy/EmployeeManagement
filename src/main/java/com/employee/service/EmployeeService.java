package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.employee.dao.Employee;
import com.employee.dao.EmployeeDao;
import com.employee.dao.JdbcConfig;



public class EmployeeService 
{
	ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
	
	EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");
	
	public void AddEmployee(String name,String gender,String dob,String doj,int salary,String department)
	{
		
		
		System.out.println("In EmployeeService "+name+" "+gender+""+" "+dob+" "+" "+doj+" "+salary+" "+department);

		
		
		Employee employee = new Employee();
		employee.setName(name);
		employee.setGender(gender);
		employee.setDob(dob);
		employee.setDoj(doj);
		employee.setSalary(salary);
		employee.setDeptId(Integer.parseInt(department));
		
	
		
		List<Employee> employeeList = new ArrayList<Employee>();
		
		employeeList.add(employee);
		
		System.out.println(employeeList);
		
		
		//System.out.println(employeeDao.getEmployeeDetails(1));
		
		employeeDao.batchInsert(employeeList);	
		
		System.out.println("Entered employee into database successfully");

	}
	
	
	public List<Employee> getEmployees()
	{
		List<Employee> employees = employeeDao.getEmployees();
		return employees;
	}
	

}
