<%@ page import=" java.sql.* " %>

<%
String empUn = request.getParameter("empUn");
String empPw = request.getParameter("empPw");

//get name from db and check the availability of above un and pw
//connect to database
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library","root","root");
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM t_employee WHERE emp_username='"+empUn+"' and emp_password='"+empPw+"' ");
	
%>

<% 
if(rs.next()){
	session.setAttribute("empUn",empUn);//make a session
	response.sendRedirect("staffMemberLoginHome1.jsp");
%>

<script type="text/javascript">
alert("You Successfully Logged In As An Employee...");
</script>

<%
}else{
%>
<script type="text/javascript">
alert("Incorrect Username or Password....");
</script>

<%
response.sendRedirect("staffMemberLogin.html");
} %>	



  