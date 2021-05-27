package com.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class EmployeeResultSetExtractor implements ResultSetExtractor<List<Employee>>
{

	@Override
	public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException 
	{
		List<Employee> employees = new ArrayList<Employee>();
		while(rs.next())
		{
			Employee employee = new Employee();
			employee.setId(rs.getInt("Id"));
			employee.setName(rs.getString("name"));
			employee.setGender(rs.getString("gender"));
			employee.setDob(rs.getString("dob"));
			employee.setDoj(rs.getString("doj"));
			employee.setSalary(rs.getInt("salary"));
			employee.setDeptId(rs.getInt("dept_id"));
			
			employees.add(employee);
			
		}
		return employees;
	}
	
}
