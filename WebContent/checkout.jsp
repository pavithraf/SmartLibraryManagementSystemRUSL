<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
//response.setContentType("text/html");
response.setContentType("text/plain");
String memId = request.getParameter("memId");
System.out.println(memId);
%>
<%
try {
	//connect to database
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library","root","root");
	Statement stmt = conn.createStatement();
	//compare incoming username, password with the Database
		//username, password
	ResultSet rs1 = stmt.executeQuery("SELECT mem_name_with_initials,NIC  FROM t_member  WHERE mem_id ='"+memId+"' ");
	//ResultSet rs2 = stmt.executeQuery(queryNoOfBorrows);
	//System.out.print(rs2.getInt("ct"));
	
	//if correct username, password
	//out.print(rs1.getString("mem_name_with_initials")+":"+rs1.getString("NIC")+":"+rs2.getString(1) );
	//if(rs1.next() && rs2.next()){
	if(rs1.next()){
		//out.print("a"+":"+"b"+":"+rs2.getInt("ct") );
		//out.print(rs1.getString("mem_name_with_initials")+":"+rs1.getString("NIC") );//write two
		String s=rs1.getString(1)+":"+rs1.getString(2);
		out.print(s);//write two
		//out.print(rs1.getString(1)+":"+rs1.getString(2)+":"+rs2.getString(1) );//cant write 3 at once
		//out.print(rs2.getString(1) );
	}
		
} catch(Exception e) {
	System.out.println("-------error----------");
	System.out.println(e.getMessage());
	e.printStackTrace();
}
%>
</body>
</html>