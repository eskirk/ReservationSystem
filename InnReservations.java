import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.time.LocalDate;

/**
 use this command when running:  
 java -cp mysql-connector-java-5.1.45-bin.jar:. InnReservations

 pre-compilation stuff:
 export CLASSPATH=$CLASSPATH:mysql-connector-java-5.1.45-bin.jar:.
 export APP_JDBC_URL=jdbc:mysql://csc365winter2018.webredirect.org/alam38?autoReconnect=true
 export APP_JDBC_USER=alam38
 export APP_JDBC_PW=365W18_010270594
 */

public class InnReservations {
   public static void main(String[] args) throws SQLException {
      Connection conn = startProgram();
      executeQuery(conn);
   }

   /*
      Acquires and returns a connection based on our environment variables
   */
   public static Connection startProgram() throws SQLException {
      String url = System.getenv("APP_JDBC_URL");
      String user = System.getenv("APP_JDBC_USER");
      String pass = System.getenv("APP_JDBC_PW");

      Connection connection = DriverManager.getConnection(url, user, pass);
      System.out.println("Database connection acquired - processing query");
      return connection;
   }

   /*
      Executes a query and returns a List<String[]> representing the result of
      the query
   */
   public static List<String[]> executeQuery(Connection connection) throws SQLException {
      String query = "SELECT Room, COUNT(Room) / 180 FROM lab6_reservations WHERE Checkout >= (CURDATE() - INTERVAL 180 DAY) GROUP BY Room ORDER BY COUNT(Room) DESC";
      Statement stmt = connection.createStatement();
      ResultSet res = stmt.executeQuery(query);

      // print out the columns
      try {
         while (res.next()) {
            return resultToArray(res);
         }
      } catch (SQLException e) {
         System.out.println("An exception occured");
         System.out.println(e);
      }
      return null;
   }

   /*
      Returns a List<String[]> representing the query results
   */
   public static List<String[]> resultToArray(ResultSet result) throws SQLException {
      int cols = result.getMetaData().getColumnCount();
      List<String[]> table = new ArrayList<>();

      do {
         String[] row = new String[cols];
         for (int col = 1; col <= cols; col++) {
            Object obj = result.getObject(col);
            row[col - 1] = (obj == null) ? null : obj.toString();
         }
         table.add(row);
      } while(result.next());

      // print result
      for (String[] row : table) {
         for (String s : row) {
            System.out.print(" " + s);
         }
         System.out.println();
      }

      return table;
   }
}