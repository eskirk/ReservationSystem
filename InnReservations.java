import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.time.LocalDate;

/**
 use this command when running:  
 java -cp mysql-connector-java-5.1.45-bin.jar:. InnReservations

 pre-compilation stuff:
 export CLASSPATH=$CLASSPATH:mysql-connector-java-5.1.45-bin.jar:.
 export APP_JDBC_URL=<URL>
 export APP_JDBC_USER=<USER>
 export APP_JDBC_PW=<PASS>
 */

public class InnReservations {
   public static void main(String[] args) throws SQLException{
      Connection dbConnection = establishConnection();

      if (dbConnection != null) {
         System.out.println("Database connection acquired - processing query");
         // follow through with other query related shenanigans  
         
      }
      else 
         System.out.println("Database connection cannot be acquired - exiting");
   }

   public static Connection establishConnection() throws SQLException {
      String url = System.getenv("APP_JDBC_URL");
      String user = System.getenv("APP_JDBC_USER");
      String pass = System.getenv("APP_JDBC_PW");
      Connection conn = null;

      try (Connection connection = DriverManager.getConnection(url, user, pass)) {
         System.out.println("Established connection");
         conn = connection;
      }
      catch (SQLException e) {
         System.out.println("An exception occured while establishing a connection");
         System.out.println(e);
      }
      return conn;
   }

   // Executes the given query using the given database connection
   public static executeQuery(String query, Connection conn) {

   }
}