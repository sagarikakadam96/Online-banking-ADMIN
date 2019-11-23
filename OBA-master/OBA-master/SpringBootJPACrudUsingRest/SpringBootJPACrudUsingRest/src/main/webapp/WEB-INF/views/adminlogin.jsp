<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	<div style="margin: 50px">

		<form method="get" action="adminloginprocess">
			<span>User id</span>
			<input type="text" name="user_id" required />
			<span> Password<span>
			<input type="password" name="password" required />
			${error}
	</div>


	<input type="submit" value="login to admin view" />

	</form>

	</div>

</body>
</body>
</html>