

import java.io.IOException;


import java.util.Date;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import database.DB;

/**
 * Servlet implementation class CheckinServletBookDetails
 */
@WebServlet("/CheckinServletBookDetails")
public class CheckinServletBookDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckinServletBookDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		String itemId = request.getParameter("itemId");
		
		ResultSet rs = null;
		String bookTitle="";
		String authorName="";
		String dueDate="";
		String fineCalc="";
		double fine=0;
		try{
			 rs = DB.getdata("select t_book.book_title from t_book JOIN t_book_copy on t_book.isbn=t_book_copy.isbn WHERE t_book_copy.accession_no='"+itemId+"'");
		
			 if(rs.next())
			 
				 bookTitle=rs.getString(1);
				
			 		
			 rs=DB.getdata("SELECT t_author.author_name FROM t_author JOIN t_author_details ON t_author.author_id=t_author_details.author_id JOIN t_book_copy ON t_author_details.isbn=t_book_copy.isbn WHERE t_book_copy.accession_no='"+itemId+"'");

			if(rs.next())
			
				authorName=rs.getString(1);
			
			
			 Calendar cal1 = new GregorianCalendar();
		     Calendar cal2 = new GregorianCalendar();
			
			rs=DB.getdata("SELECT due_date FROM t_borrow WHERE accession_no='"+itemId+"'");
			
			if(rs.next())
				
				dueDate=rs.getString(1);
			
			    DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
			    Date date=new Date();
				String yDate=df.format(date);
				Date date1=df.parse(yDate);
				cal1.setTime(date1);
			    date1=df.parse(dueDate);
			    cal2.setTime(date1);
			Date day1=cal1.getTime();
			Date day2=cal2.getTime();	
			
			int difference= (int)( (day1.getTime() - day2.getTime()) / (1000 * 60 * 60 * 24));
			
			 if(difference<=0)
				 fineCalc="No FIne";
			 
			 else if(difference>0 && difference<15){
				 fine=difference*0.50;
			 fineCalc=Double.toString(fine);
			 }
			 
			 else{
				 fine=difference*2.50;
			 fineCalc=Double.toString(fine);
			 }
			 
			out.println(getResult(bookTitle,authorName,fineCalc ));
			//System.out.println(getResult(bookTitle,authorName,fineCalc)); 
			
		}
		
		catch(Exception e)
		{
			 e.getMessage();
			  e.printStackTrace();
		}
		
	}
	
		public String getResult(String bookTitle, String authorName,String fineCalc)
		{
			
		 //String diff=Integer.toString(difference);
		 String result="<bookdata>";
		result+="<book>";
		result+="<title>"+bookTitle+"</title>";
		result+="<name>"+authorName+"</name>";
		result+="<dif>"+fineCalc+"</dif>";
		result+="</book>";
		result+="</bookdata>";
		return result;
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
