<HTML>
<HEAD>
</HEAD>
<BODY >
<%
session.invalidate(); //discard entire session


response.sendRedirect("index.html");



%>

</BODY>
</HTML> 