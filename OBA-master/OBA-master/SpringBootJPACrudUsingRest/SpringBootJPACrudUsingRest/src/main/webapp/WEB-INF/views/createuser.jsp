<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<body>

	<div style="margin: 50px">
		<h1>Create User Details</h1>

		<form method="get" action="createprocess">

			<span id="errorMsg"></span>
			<span>Account Number</span>
			<input type="text" name="acc_no_fk" required />
			<span> User Id </span>
			<input type="text" name="user_id" required />
			<h6>${error}</h6>
			<span> Password </span>
			<input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required />
			<span> Transaction Password </span>
			<input type="password" name="trans_pass" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required />
	</div>


	<input type="submit" value="Register for internet banking" />

	</form>

	</div>

</body>
</html>







<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
<script>

function checkpassword()
{
var p=f.password.value;
var cp=f.password2.value;
if(p!=cp)
alert("Password and Confirm Passwords Mismatch");
}

function checktranspassword()
{
var tp=f.trans_pass.value;
var tcp=f.trans_pass2.value;
if(tp!=tcp)
alert("Transaction Password and Transaction Confirm Passwords Mismatch");
} 
</script>

	<div style="margin: 50px">
		<h1>Create User Details</h1>

		<form name ="f" method="get" action="">

			<span id="errorMsg"></span>
			<span>Account Number</span>
			<input type="text" name="acc_no_fk" required />
			<span> User Id </span>
			<input type="text" name="user_id" required />
			<h6>${error}</h6>
			<span> Password </span>
			<input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required />
			<span>Confirm Password </span>
			<input type="password" name="password2"  required />
			<span> Transaction Password </span>
			<input type="password" name="trans_pass" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required />
	        <span>Confirm Transaction Password </span>
			<input type="password" name="trans_pass2"  required />
	
	</div>


	<input type="submit"  value="Register for internet banking" onclick="checkpassword();checktranspassword()"/>

	</form>

	</div>

</body>
</html> --%>