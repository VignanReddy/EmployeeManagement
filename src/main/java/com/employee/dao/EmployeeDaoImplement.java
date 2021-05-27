package com.employee.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDaoImplement implements EmployeeDao
{
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void batchInsert(final List<Employee> employees) 
	{
		// Batch insertion
		try
		{
			System.out.println("Reaching in batchInsert");
			String query = "insert into employee(name,gender,dob,doj,salary,dept_id) values(?,?,?,?,?,?)";
			int updateCounts[] = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
	
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					// TODO Auto-generated method stub
					ps.setString(1, employees.get(i).getName());
					ps.setString(2, employees.get(i).getGender()+"");
					ps.setString(3, employees.get(i).getDob());
					ps.setString(4, employees.get(i).getDoj());
					ps.setInt(5, employees.get(i).getSalary());
					ps.setInt(6, employees.get(i).getDeptId());
					
				}
	
				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return employees.size();
				}
			
			});
			
	
			for(int i : updateCounts)
			{
				System.out.println(i);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
		
		
	}

	@Override
	public List<Employee> getEmployees() 
	{
		String query = "select * from employee";
		ResultSetExtractor<List<Employee>> rse = new EmployeeResultSetExtractor();
		List<Employee> employees = jdbcTemplate.query(query, rse);
		return employees;
	}

	@Override
	public void batchUpdate(final List<Employee> employees) 
	{
		String query = "update employee set salary = ? where id = ?";
		int updateCounts[] = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() 
		{

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
				ps.setInt(1, employees.get(i).getSalary());
				ps.setInt(2, employees.get(i).getId());
			}

			@Override
			public int getBatchSize() 
			{
				// TODO Auto-generated method stub
				return employees.size();
			}
		});
		
		for(int i:updateCounts)
			System.out.println(i);
		
	}

	@Override
	public void batchDelete(final List<Employee> employees) {
		//deleting the employees
		
		String query = "delete from employee where id = ?";
		int updateCounts[] = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter()
				{

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1,employees.get(i).getId());
						
					}

					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						return employees.size();
					}
						
				});
		
		for(int i:updateCounts)
			System.out.println(i);
		
	}

	@Override
	public void printDetailsWithDepartment() 
	{
		String queryForDeptDetails = "select employee.id ,employee.name, department.name,department.hod_id from employee \n"
				+ "inner join department \n"
				+ "on employee.dept_id = department.dept_id order by employee.id";
		
		
		String queryForHodDetails = "select employee.id,employee.name from employee \n"
				+ "inner join department on department.hod_id = employee.id;";
		
		
		final Map<Integer,String> hodMap = new TreeMap<Integer,String>();
		
		
		final Map<Integer,String> hodDetails = jdbcTemplate.query(queryForHodDetails, new ResultSetExtractor<Map<Integer,String>>(){

			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {

				while(rs.next())
				{
					
					hodMap.put(rs.getInt(1),rs.getString(2));
				}
				return hodMap;
			}
			
		});
		
		
		List<DeptResultHolder> result = jdbcTemplate.query(queryForDeptDetails,new ResultSetExtractor<List<DeptResultHolder>>() {

			@Override
			public List<DeptResultHolder> extractData(ResultSet rs) throws SQLException, DataAccessException 
			{
				// TODO Auto-generated method stub
				
				List<DeptResultHolder> employees = new ArrayList<DeptResultHolder>();
				
				
				
				while(rs.next())
				{  
					DeptResultHolder deptResultHolder = new DeptResultHolder();
					
					deptResultHolder.setEmpId(rs.getInt(1));
					deptResultHolder.setEmpName(rs.getString(2));  
					deptResultHolder.setDeptName(rs.getString(3));
					String hodName = hodDetails.get(rs.getInt(4));
					deptResultHolder.setHodName(hodName);
					
					employees.add(deptResultHolder);
					

				}
			
				
				return employees;
			}
			
			
			
			
			
		});
		
		
		for(DeptResultHolder i : result)
		{
			System.out.println(i);
		}
	}

	@Override
	public Map<String, Integer> getDeptWiseExpenditure() 
	{
		String query = "select employee.dept_id,department.name,sum(employee.salary) from employee \n"
				+ "inner join department\n"
				+ "on employee.dept_id = department.dept_id group by dept_id;";
		
		Map<String,Integer> deptWiseExpenditure = jdbcTemplate.query(query, new ResultSetExtractor<Map<String,Integer>>() {

			@Override
			public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException 
			{
				Map<String,Integer> result =  new TreeMap<String,Integer>();
				
				while(rs.next())
				{
					

					result.put(rs.getString(2), rs.getInt(3));
				
				}
				
				return result;
			}
			
		});
		
		return deptWiseExpenditure;
	}

	@Override
	public Map<String, Integer> getHodWiseEmpCount() 
	{
		String query = "select department.hod_id,department.name,count(*) from department\n"
				+ "inner join employee\n"
				+ "on employee.dept_id = department.dept_id group by department.name;";
		
		
		String queryForHodDetails = "select employee.id,employee.name from employee \n"
				+ "inner join department on department.hod_id = employee.id;";
		
		
		
		
		final Map<Integer,String> hodDetails = jdbcTemplate.query(queryForHodDetails, new ResultSetExtractor<Map<Integer,String>>(){

			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Map<Integer,String> result = new TreeMap<Integer,String>();
				
				while(rs.next())
				{
					
					result.put(rs.getInt(1),rs.getString(2));
				}
				return result;
			}
			
		});
		
		
		
		Map<String,Integer> hodWiseEmpCount =  jdbcTemplate.query(query,new ResultSetExtractor<Map<String,Integer>>() 
		{

			@Override
			public Map<String, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Map<String,Integer> result = new TreeMap<String,Integer>();
				while(rs.next())
				{
					String hodName = hodDetails.get(rs.getInt(1));
					
					result.put(hodName,rs.getInt(3));
				}
				return result;
			}
			
		});
		return hodWiseEmpCount;
	}

	@Override
	public List<Employee> getEmpBelowSalary(int salary) 
	{
		String queryForHodDetails = "select employee.id,employee.name from employee \n"
				+ "inner join department on department.hod_id = employee.id;";

		final Map<Integer,String> hodDetails = jdbcTemplate.query(queryForHodDetails, new ResultSetExtractor<Map<Integer,String>>(){

			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Map<Integer,String> result = new TreeMap<Integer,String>();
				
				while(rs.next())
				{
					
					result.put(rs.getInt(1),rs.getString(2));
				}
				return result;
			}
			
		});
		
		
		
		String query = "select employee.id ,employee.name, department.name,department.hod_id from employee \n"
				+ "inner join department \n"
				+ "on employee.dept_id = department.dept_id where employee.salary>? order by employee.id ;";
		
		
		List<Employee> employees = jdbcTemplate.query(query, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Employee employee= new Employee();
				employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setDepartment(rs.getString(3));
				employee.setHodName(hodDetails.get(rs.getInt(4)));
				
				
				return employee;
			}
			
		},salary);
		
		
		
		return employees;
	}

	@Override
	public List<Employee> getEmpsBetweenDoj(String date1, String date2) 
	{
		String queryForHodDetails = "select employee.id,employee.name from employee \n"
				+ "inner join department on department.hod_id = employee.id;";

		final Map<Integer,String> hodDetails = jdbcTemplate.query(queryForHodDetails, new ResultSetExtractor<Map<Integer,String>>(){

			@Override
			public Map<Integer, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Map<Integer,String> result = new TreeMap<Integer,String>();
				
				while(rs.next())
				{
					
					result.put(rs.getInt(1),rs.getString(2));
				}
				return result;
			}
			
		});
		
		String query = "select employee.id,employee.name,department.name,department.hod_id  from employee \n"
				+ "inner join department\n"
				+ "on employee.dept_id = department.dept_id\n"
				+ "where doj between ? and ?;";
		
		
		List<Employee> employees = jdbcTemplate.query(query, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				
				Employee employee= new Employee();
				employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setDepartment(rs.getString(3));
				employee.setHodName(hodDetails.get(rs.getInt(4)));
				
				
				return employee;
			}
			
		},date1,date2);
		
		
		return employees;
	}

	@Override
	public int getEmployeeSalary(int employeeId) 
	{
		String query = "select salary from employee where id = ?";
		
		Employee employee = jdbcTemplate.queryForObject(query,new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Employee employee = new Employee();
				employee.setSalary(rs.getInt(1));
				return employee;
			}
			
		},employeeId);

		return employee.getSalary();
	}

	@Override
	public String getEmployeeDoj(int employeeId) 
	{
		String query = "select doj from employee where id = ?";
		
		Employee employee = jdbcTemplate.queryForObject(query,new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Employee employee = new Employee();
				employee.setDoj(rs.getString(1));
				return employee;
			}
			
		},employeeId);

		return employee.getDoj();
	}

	@Override
	public String getHodName(int departmentId) 
	{
		// TODO Auto-generated method stub
		String query = "select employee.name from department \n"
				+ "inner join employee\n"
				+ "on employee.id = department.hod_id where department.dept_id = ?;";
		
		Employee employee = jdbcTemplate.queryForObject(query,new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Employee employee = new Employee();
				employee.setHodName(rs.getString(1));
				return employee;
			}
			
		},departmentId);
		
		
		
		
		return employee.getHodName();
	}

	@Override
	public Employee getEmployeeDetails(int employeeId) {
		// TODO Auto-generated method stub
		
		String query = "select id,name,gender,dob,doj,salary,dept_id from employee where id = ?;";
		
		Employee employee = jdbcTemplate.queryForObject(query,new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Employee employee = new Employee();
				employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setGender(rs.getString(3));
				employee.setDob(rs.getString(4));
				employee.setDoj(rs.getString(5));
				employee.setSalary(rs.getInt(6));
				employee.setDeptId(rs.getInt(7));
				
				return employee;
			}
			
		},employeeId);
		
		
		return employee;
	}

}
