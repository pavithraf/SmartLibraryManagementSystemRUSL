

import java.io.IOException;



import database.DB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class memServlet
 */
@WebServlet("/memServlet")
public class memServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String name=request.getParameter("txtfname");
		String nic=request.getParameter("txtnic");
		String email=request.getParameter("txtmail");
		String dob=request.getParameter("txtdob");
		String ad1=request.getParameter("txtaddress1");
		String ad2=request.getParameter("txtaddress2");
		String city=request.getParameter("txtcity");
		String mob=request.getParameter("txtmobile");
		String fix=request.getParameter("txtfixed");
		
		String msg="";
		
		 
		try{
			
			DB.change("insert into t_member(mem_name,nic,email,dob,first_address,second_address,city,mobile_no,telephone_no,membership_status) values('"+name+"','"+nic+"','"+email+"','"+dob+"','"+ad1+"','"+ad2+"','"+city+"','"+mob+"','"+fix+"','pending')");
			msg="Member Request successfully sent";
			pw.println(msg);
			
		}
		
		catch (Exception e)
		{
			
			pw.println("error");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
