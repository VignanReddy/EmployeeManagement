<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.container 
	{
	    position: absolute;
	    top: 50%;
	    left: 50%;
	    transform : translateX(-50%) translateY(-80%);
	}
</style>

</head>
<body>
<div class = container>
	<h1>Add Employee </h1>
	<form action = "AddEmployeeToDatabase">
		<label>Name</label><input type = "text" name = "name">
		<br>
		
		
		<label>Gender</label>
		<br>
		<input type="radio" id="male" name="gender" value="M">
		<label for="male">Male</label><br>
		<input type="radio" id="female" name="gender" value="F">
		<label for="female">Female</label><br>
		
		
		<label>Date of Birth</label>
		<input type = "date" name = "dob">
		<br>
		
		
		<label>Date of Joining</label>
		<input type = "date" name = "doj">
		<br>
		
		<label>Salary</label>
		<input type = "number" name = "salary">
		<br>
		
		
		
		<label for="department">Choose a Department:</label>

		<select name="department" id="department">
		  <option value="1">Software</option>
		  <option value="2">Accounts</option>
		  <option value="3">CallCenter</option>
		  <option value="4">HR</option>
		</select>
		
		<br>
		
		
		
		<input type = "submit" value = "submit">
		
		<br>
		<%
			String message = "";
			if(request.getAttribute("result")!=null)
				message = (String)request.getAttribute("result");
		%>
		<h4><%= message %></h4>
		
	</form>
	
</div>
</body>
</html>