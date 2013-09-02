<HTML>
<HEAD>
</HEAD>
<BODY >
<%
session.invalidate(); //discard entire session


response.sendRedirect("staffMemberLogin.html");



%>

</BODY>
</HTML> 