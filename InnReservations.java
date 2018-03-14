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
import java.util.*;
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

   public static void reservations() {
      System.out.print("First Name : ");
      String first = System.console().readLine();

      while (true) {
         if (first.matches("[a-zA-Z]+")) {
            break;
         }
         System.out.println("Only letters are allowed");
         System.out.print("First Name : ");
         first = System.console().readLine();
      }

      System.out.print("Last Name : ");
      String last = System.console().readLine();

      while (true) {
         if (last.matches("[a-zA-Z]+")) {
            break;
         }
         System.out.println("Only letters are allowed");
         System.out.print("Last Name : ");
         last = System.console().readLine();
      }

      System.out.print("Room Code (Input 'Any' if no preference) : ");
      String code = System.console().readLine();
      String[] roomCodeArray = new String[] { "AOB", "CAS", "FNA", "HBB", "IBD", "IBS", "MWC", "RND", "RTE", "TAA"};
      Set<String> roomCodeSet = new HashSet<>(Arrays.asList(roomCodeArray));

      while (true) {//add a real check to see if the code actually exists in the database
         if (roomCodeSet.contains(code.toUpperCase()) || "ANY".equals(code.toUpperCase())) {
            break;
         }
         System.out.println("Code must consist only of exactly 5 letters");
         System.out.print("Room Code (Input 'Any' if no preference) : ");
         code = System.console().readLine();
      }

      System.out.print("Bed Type ('Twin', 'Queen', 'King', 'Any' if no preference) : ");
      String bedType = System.console().readLine();

      while (true) {
         if ("TWIN".equals(bedType.toUpperCase()) || "QUEEN".equals(bedType.toUpperCase()) || "KING".equals(bedType.toUpperCase()) || "ANY".equals(bedType.toUpperCase())) {
            break;
         }
         System.out.print("Bed Type ('Twin', 'Queen', 'King', 'Any' if no preference) : ");
         bedType = System.console().readLine();
      }

      LocalDate today = LocalDate.now();
      System.out.println(today);

      System.out.print("Beginning Date of Stay (year-month-day): ");
      String begin = System.console().readLine();

      while (true) {
         if (begin.matches("(\\d\\d\\d\\d-\\d\\d-\\d\\d)")) {
            break;
         }
         System.out.print("Beginning Date of Stay (year-month-day): ");
         begin = System.console().readLine();
      }

      System.out.print("Ending Date of Stay (year-month-day): ");
      String end = System.console().readLine();

      while (true) {
         if (end.matches("(\\d\\d\\d\\d-\\d\\d-\\d\\d)")) {
            break;
         }
         System.out.print("Ending Date of Stay (year-month-day): ");
         end = System.console().readLine();
      }

      System.out.print("Number of Children : ");
      String chilluns = System.console().readLine();

      while (true) {
         if (chilluns.matches("[0-9]+")) {
            int children = Integer.parseInt(chilluns);
            break;
         }
         System.out.println("Invalid Input");
         System.out.print("Number of Children : ");
         chilluns = System.console().readLine();
      }

      System.out.print("Number of Adults : ");
      String bigguns = System.console().readLine();

      while (true) {
         if (bigguns.matches("[0-9]+")) {
            int adults = Integer.parseInt(bigguns);
            break;
         }
         System.out.println("Invalid Input");
         System.out.print("Number of Adults : ");
         bigguns = System.console().readLine();
      }

   }

   public static void revenue() {
      System.out.println("The Rooms and Rates are");
   }

   public static void detailedReservationInformation() {
      System.out.println("The Rooms and Rates are");
   }

   public static bool todayOrLater(str date) {
      return true;
   }

}

