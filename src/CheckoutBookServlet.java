


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
 * Servlet implementation class CheckoutBookServlet
 */
@WebServlet("/CheckoutBookServlet")
public class CheckoutBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				response.setContentType("text");
				PrintWriter out =response.getWriter();
				
				//get book id
				String bookId = request.getParameter("bookId");
				String queryBookDetails="SELECT book_title, t_author.author_name, t_book.isbn FROM t_book_copy inner join t_book on	t_book_copy.isbn=t_book.isbn inner join t_author_details on t_book.isbn=t_author_details.isbn 	inner join t_author on 	t_author_details.author_id=t_author.author_id where t_book_copy.accession_no='"+bookId+"' ";
							
				try {
					//connect to database
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library","root","root");
					Statement stmt = conn.createStatement();
					ResultSet rs2 = stmt.executeQuery(queryBookDetails);
					if(rs2.next()){
						out.print(rs2.getString(1)+":"+rs2.getString(2)+":"+rs2.getString(3) );//write three
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
