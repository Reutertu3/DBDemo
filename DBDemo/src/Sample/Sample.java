package Sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sample
{
  public static void main(String[] args)
  {
    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:inventory.db");		//Create inventory.db
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      statement.executeUpdate("drop table if exists inventory");				//table name = inventory
      statement.executeUpdate("create table inventory (productid integer, productname string, productbrand string, productprice double, amount integer)");
      statement.executeUpdate("insert into inventory values(1, 'nudeln', 'barilla', 1.49, 10)");
      statement.executeUpdate("insert into inventory values(2, 'butter', 'rama', 2.19, 15)");
      statement.executeUpdate("insert into inventory values(3, 'kaese', 'frau_antje', 3.99, 50)");
      statement.executeUpdate("insert into inventory values(4, 'cola', 'coca_cola', 1.29, 125)");
      ResultSet rs = statement.executeQuery("select * from inventory");
      while(rs.next())
      {
        // read the result set
    	System.out.println("id = " + rs.getInt("productid") + " / " + "name = " + rs.getString("productname") + " / " + "marke = " + rs.getString("productbrand") + " / " + "preis = " + rs.getDouble("productprice") + " / " + "menge = " + rs.getInt("amount"));
       
      }
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e.getMessage());
      }
    }
  }
}