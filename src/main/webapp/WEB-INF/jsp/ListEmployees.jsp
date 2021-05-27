<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList,com.employee.dao.Employee" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
	<%List<Employee> employees = (List<Employee>) request.getAttribute("employeeList");%>
	<%-- <%= employees %> --%>
	<h2 align = "center" >Employees</h2>
	<table class = "table table-striped" >
	<thead class = "table-dark">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Gender</th>
			<th>Date of Birth</th>
			<th>Date of Joining</th>
			<th>Salary</th>
			<th colspan = "2" align = "center">Action</th>
		</tr>
	</thead>
		
	<tbody>
	
		<%for(Employee employee : employees) { %>
		<tr>
			<td><%=employee.getId() %></td>
			<td><%=employee.getName() %></td>
			<td><%=employee.getGender() %></td>
			<td><%=employee.getDob() %></td>
			<td><%=employee.getDoj() %></td>
			<td><%=employee.getSalary() %></td>
			
			<td><form action = "EditSalary"><input type = "submit" value = "Edit"></form></td>
			
			<td><form action = "DeleteSalary"><input type = "submit" value = "Delete"></form></td>

		</tr>
		
		<% } %>
		
	</tbody>
	
	</table>
	
	
	
</body>
</html>