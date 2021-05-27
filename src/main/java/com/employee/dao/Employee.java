package com.employee.dao;

public class Employee 
{
	int id;
	String name;
	String gender;
	String dob;
	String doj;
	int salary;
	int deptId;
	
	int departmentId;
	String department;
	int hodId;
	String hodName;
	
	
	
	public String getHodName() {
		return hodName;
	}


	public void setHodName(String hodName) {
		this.hodName = hodName;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public int getHodId() {
		return hodId;
	}


	public void setHodId(int hodId) {
		this.hodId = hodId;
	}


	public Employee(int id, String name, String gender, String dob, String doj, int salary, int deptId) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.doj = doj;
		this.salary = salary;
		this.deptId = deptId;
	}


	public Employee() {
		// TODO Auto-generated constructor stub
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getDoj() {
		return doj;
	}


	public void setDoj(String doj) {
		this.doj = doj;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public int getDeptId() {
		return deptId;
	}


	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", doj=" + doj
				+ ", salary=" + salary + ", deptId=" + deptId + "]";
	}

	
	
	
	
	
	
	
	
}
