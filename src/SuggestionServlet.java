

import java.io.IOException;



import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

import database.DB;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/SuggestionServlet")
public class SuggestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SuggestionServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");	//Response content type
		PrintWriter out=response.getWriter();	
		
		
		String Title=request.getParameter("txtTitle1");
		String Author=request.getParameter("txtTitle2");
		String Publisher=request.getParameter("txtTitle3");
		String PublishedDate=request.getParameter("txtTitle4");
		String Language=request.getParameter("txtTitle5");
		String msg=Title+":"+Author+":"+Publisher+":"+PublishedDate+":"+Language;
		
		
		
		
		
		
		
		
try{
			
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
		String currentDate=df.format(date);
		
		HttpSession  session=request.getSession();//Create session
		
	    String uname=session.getAttribute("memUn").toString();
	    //System.out.println(uname);
	    
	   ResultSet rs= DB.search("SELECT mem_id FROM t_member WHERE mem_username ='"+uname+"'");
		
	   
	   rs.next();
	   String id=rs.getString(1);
	    DB.change("insert into t_user_suggestions(mem_id,message,date) values('"+id+"','"+msg+"','"+currentDate+"')");
	    out.println("<b>Your Suggestion is successful!!!</b>");

			
		}
		
		catch (Exception e)
		{
			
			out.println("<b>Suggestion failed</b>");
			out.println("<b>Error:</b>" +e);
			
		}
	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
