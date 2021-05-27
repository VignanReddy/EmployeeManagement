package com.employee.controllers;


import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.employee.dao.Employee;
import com.employee.service.EmployeeService;

@Controller
public class EmployeeController 
{

	
	//commented 
	
	//developer changes 

	@RequestMapping("/AddEmployee")
	public ModelAndView addEmployee()
	{
		//System.out.println("Reaching add employee");
		//EmployeeService employeeService = new EmployeeService();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AddEmployeeView");
		
		return mv;
	}
	
	@RequestMapping("/AddEmployeeToDatabase")
	public ModelAndView addEmployeeToDatabase(@RequestParam("name") String name,@RequestParam("gender") String gender,@RequestParam("dob") String dob,@RequestParam("doj") String doj,@RequestParam("salary") int salary,@RequestParam("department") String department)
	{
//		System.out.println("Reachig AddEmployeeToDatabase");
//		System.out.println(name+" "+gender+""+" "+dob+" "+" "+doj+" "+salary+" "+department);
		EmployeeService employeeService = new EmployeeService();
		employeeService.AddEmployee(name, gender, dob, doj, salary, department);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AddEmployeeView");
		mv.addObject("result","Employee is added Successfully");
		return mv;
	}
	
	@RequestMapping("/ListEmployees")
	public ModelAndView listEmployees()
	{
		EmployeeService employeeService = new EmployeeService();
		List<Employee> employees  = employeeService.getEmployees();
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("employeeList",employees);
		mv.setViewName("ListEmployees");
		return mv;
	}
	
	@RequestMapping("/EditSalary")
	public ModelAndView editSalary()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("EditSalary");
	
		
		
		return mv;
	}

	
	
	
}
