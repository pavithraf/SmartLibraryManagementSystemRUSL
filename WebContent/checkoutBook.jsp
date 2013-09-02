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
response.setContentType("text/html");
String bookId = request.getParameter("bookId");
String queryBookDetails="SELECT book_title, t_author.author_name, t_book.isbn FROM t_book_copy inner join t_book on	t_book_copy.isbn=t_book.isbn inner join t_author_details on t_book.isbn=t_author_details.isbn 	inner join t_author on 	t_author_details.author_id=t_author.author_id where t_book_copy.accession_no='"+bookId+"' ";

try {
	//connect to database
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library","root","root");
	Statement stmt = conn.createStatement();
	//compare incoming username, password with the Database
		//username, password
	//ResultSet rs1 = stmt.executeQuery("SELECT mem_name_with_initials,NIC  FROM t_member  WHERE mem_id ='"+memId+"' ");
	ResultSet rs2 = stmt.executeQuery(queryBookDetails);
	//System.out.print(rs2.getInt("ct"));
	
	//if correct username, password
	//out.print(rs1.getString("mem_name_with_initials")+":"+rs1.getString("NIC")+":"+rs2.getString(1) );
	if(rs2.next()){
		//out.print("a"+":"+"b"+":"+rs2.getInt("ct") );
		out.print(rs2.getString(1)+":"+rs2.getString(2)+":"+rs2.getString(3) );//write three
	}
		
} catch(Exception e) {
	System.out.println("-------error----------");
	System.out.println(e.getMessage());
	e.printStackTrace();
}
%>
</body>
</html>