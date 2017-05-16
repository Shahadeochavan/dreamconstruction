<!-- <html>
<body>
<h2>Hello World!</h2>
</body>
</html> -->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC PDF Download Example</title>
</head>
<body>
	<form:form action="createmultiple" method="post" id="createmultiple">
		<h3>Spring MVC PDF Download Example</h3>
		<input id="submitId" type="submit" value="Downlaod PDF">
	</form:form>
</body>
</html>