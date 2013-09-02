<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import=" java.sql.* " %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
System.out.println("dddddddddd");
response.setContentType("text/html");



String memId = request.getParameter("memId");
System.out.println(memId);


%>

<%
//connect to database
try{
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library","root","root");
Statement stmt = conn.createStatement();
//compare incoming username, password with the Database
	//username, password
	//String queryInsertBorrow="insert into t_borrow(borrow_id,borrow_date,accession_no,mem_id,emp_username,due_date) values('aa', 'aa','a1','1','ishara','aa' ) ";
String query="delete from t_member where mem_id='"+memId+"' ";
//ResultSet rs1 = stmt.executeQuery("SELECT mem_name_with_initials,NIC  FROM t_member  WHERE mem_id ='"+memId+"' ");
//int r=1;
int r = stmt.executeUpdate(query);
if(r>0){
	out.print("Deleted Successfully.........");
	//System.out.println("borrowed.......");
	//response.sendRedirect("borrowedResult.jsp");
}
}catch(Exception e){
	System.out.println("-------error----------");
	System.out.println(e.getMessage());
	e.printStackTrace();
}

%>
</body>
</html>