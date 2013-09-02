
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
 * Servlet implementation class NoOfBorrowsServlet
 */
@WebServlet("/NoOfBorrowsServlet")
public class NoOfBorrowsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoOfBorrowsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				response.setContentType("text");
				PrintWriter out =response.getWriter();
				
				//get mem id
				String memId = request.getParameter("memId");
				String queryNoOfBorrows="select count(*) from t_borrow left outer join t_return on t_borrow.borrow_id=t_return.borrow_id where t_return.borrow_id is null and t_borrow.mem_id='"+memId+"' ";
				
				
				try {
					//connect to database
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library","root","root");
					Statement stmt = conn.createStatement();
					ResultSet rs2 = stmt.executeQuery(queryNoOfBorrows);
					if(rs2.next()){//simply move the pointer of the result rows set to the next row
						out.print(rs2.getString(1) );
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
