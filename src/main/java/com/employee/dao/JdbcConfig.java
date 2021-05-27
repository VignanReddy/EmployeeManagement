package com.employee.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class JdbcConfig 
{
	@Bean(name="ds")
	public DataSource getDataSource()
	{
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/EmployeeSpringJdbc?useSSL=false");
		ds.setUsername("root");
		ds.setPassword("12345678");
		return ds;
	}
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate getTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		
		return jdbcTemplate;
	}
	
	
	@Bean(name="employeeDao")
	public EmployeeDao getEmployeeDao()
	{
		EmployeeDaoImplement employeeDao = new EmployeeDaoImplement();
		employeeDao.setJdbcTemplate(getTemplate());
		
		return employeeDao;
		
	}
	
}
