

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DB;
import java.sql.ResultSet;

/**
 * Servlet implementation class CheckinServlet
 */
@WebServlet("/CheckinServlet")
public class CheckinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		String uid = request.getParameter("userid");
		
		ResultSet rs = null;
		String numberOfBooks="";
		String memberName="";
		try{
			 rs = DB.getdata("select count(* ) from t_borrow left outer join t_return on t_borrow.borrow_id=t_return.borrow_id where t_return.borrow_id is null and t_borrow.mem_id='"+uid+"'");
		
			 if(rs.next())
			 
				 numberOfBooks=rs.getString(1);
				
			 		
			 rs=DB.getdata("select mem_name FROM t_member WHERE mem_id='"+uid+"'");
			if(rs.next())
			
				memberName=rs.getString(1);
			
			out.println(getResult(numberOfBooks,memberName ));
			 System.out.println(getResult(numberOfBooks,memberName)); 
			
		}
		
		catch(Exception e)
		{
			 e.getMessage();
			  e.printStackTrace();
		}
		
	}
	
		public String getResult(String numberOfBooks, String memberName)
		{
			
		
		 String result="<userdata>";
		result+="<user>";
		result+="<id>"+numberOfBooks+"</id>";
		result+="<name>"+memberName+"</name>";
		result+="</user>";
		result+="</userdata>";
		return result;
		}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
