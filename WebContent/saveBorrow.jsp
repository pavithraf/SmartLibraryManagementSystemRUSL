<%@ page import=" java.sql.* " %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
response.setContentType("text/html");

//get parameter values
String bookId = request.getParameter("bookId");System.out.println(bookId);
String memId = request.getParameter("memId");System.out.println(memId);
String dueDate = request.getParameter("dueDate");System.out.println(dueDate);
String borrowDate = request.getParameter("borrowDate");System.out.println(borrowDate);

//get session value
String empUn=session.getAttribute("empUn").toString();

%>
<%
//connect to database
try{
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library","root","root");
Statement stmt = conn.createStatement();
String queryInsertBorrow="insert into t_borrow(borrow_date,accession_no,mem_id,emp_username,due_date) values('"+borrowDate+"', '"+bookId+"','"+memId+"','"+empUn+"','"+dueDate+"' ) ";

int r = stmt.executeUpdate(queryInsertBorrow);
if(r>0){
	out.print("Issuing Book Done Successfully..\nIssuing Details Successfully Saved..");
	System.out.println("borrowed.......");
	
}
}catch(Exception e){
	System.out.println("-------error----------");
	System.out.println(e.getMessage());
	e.printStackTrace();
}
%>


	
