

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IssueMemIDServlet
 */
@WebServlet("/IssueMemIDServlet")
public class IssueMemIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueMemIDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				response.setContentType("text");
			//	System.out.println("1111111111");
				PrintWriter out =response.getWriter();
				
				
				String memId = request.getParameter("memId");//NIC
				System.out.print(memId);
				
				try {
					//connect to database
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library","root","123");
					Statement stmt = conn.createStatement();
					
					ResultSet rs1 = stmt.executeQuery("SELECT mem_name_with_initials,city,membership_status  FROM t_member  WHERE NIC ='"+memId+"' ");
					
					
					if(rs1.next()){
						
						out.print(rs1.getString(1)+":"+rs1.getString(2)+":"+rs1.getString(3));//Get name,address,status
						
					}
						
				} catch(Exception e) {
					System.out.println("-------error----------");
					System.out.println(e.getMessage());
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
