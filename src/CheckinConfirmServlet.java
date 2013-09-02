

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DB;

/**
 * Servlet implementation class CheckinConfirmServlet
 */
@WebServlet("/CheckinConfirmServlet")
public class CheckinConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckinConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		String itemId = request.getParameter("itemId");
		ResultSet rs=null;
		String borrowId="";
		String msg="";
		
		try{
			
		rs=DB.getdata("SELECT borrow_id FROM t_borrow WHERE accession_no='"+itemId+"'");	
			if(rs.next())
				borrowId=rs.getString(1);
			
			  DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
			    Date date=new Date();
				String cDate=df.format(date);
			DB.change("insert into t_return(return_date,borrow_id ) values('"+cDate+"','"+borrowId+"')");
			msg="Successfully Checkedin the item";
			out.println(msg);
			System.out.println(msg);
		}
		
		catch(Exception e)
		{
			 e.getMessage();
			  e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
