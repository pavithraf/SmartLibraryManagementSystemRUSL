package database;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	
	private static Connection con;
    public static Connection getconn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/slms","root","123");
        }
        catch(Exception e)
        {
        System.out.println(e);
        }
        return con;
    }
public static void change(String a)
{
  if(con==null){
    getconn();
  }
  try{
   con.createStatement().executeUpdate(a);
  }
  catch(Exception e)
  {
      System.out.println(e);
   }

}
public static ResultSet search(String sqlquery)throws SQLException
   {
  if(con==null){
    getconn();
  }

return con.createStatement().executeQuery(sqlquery);
}
public static ResultSet getdata(String sqlquery)throws SQLException
{
  if(con==null){
    getconn();
  }

return con.createStatement().executeQuery(sqlquery);

}

}
