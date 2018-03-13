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
   public static void main(String[] args) throws SQLException{
      Connection dbConnection = establishConnection();

      if (dbConnection != null) {
         System.out.println("Database connection acquired - processing query");
         // follow through with other query related shenanigans  
         
      }
      else 
         System.out.println("Database connection cannot be acquired - exiting");

      mainPrompt();

   }

   public static Connection establishConnection() throws SQLException {
      String url = System.getenv("APP_JDBC_URL");
      String user = System.getenv("APP_JDBC_USER");
      String pass = System.getenv("APP_JDBC_PW");
      Connection conn = null;

      Connection connection = DriverManager.getConnection(url, user, pass); 
      return conn;
   }

   // Executes the given query using the given database connection
   //public static executeQuery(String query, Connection conn) {

   //}

   public static void mainPrompt() {

      System.out.println("Welcome to our database software.\n");
      System.out.println("R1: Rooms and Rates. The system will output a list of rooms sorted by popularity (highest to lowest)\n");
      System.out.println("R2: Reservations. Select this option to book a reservation\n");
      System.out.println("R3: Revenue. a month-by-month overview of revenue for an entire year.\n");
      System.out.println("D: Detailed Reservation Information. Presents a search prompt or form that allows a user to enter any combination"); 
      System.out.println("                                  of the fields listed below (a blank entry should indicate 'Any'). For all fields except dates, partial values"); 
      System.out.println("                                  using SQL LIKE wildcards are permitted(for example: GL% allowed as a last name search value)\n");
      System.out.println("Q: Quit the program.");

      while (true) {

         System.out.print("Input Command : ");
         String input = System.console().readLine();

         if ("Q".equals(input.toUpperCase())) {
            System.out.println("Exit!");
            System.exit(0);
         }

         if ("R1".equals(input.toUpperCase())) {
            roomsAndRates();
         }
         if ("R2".equals(input.toUpperCase())) {
            reservations();
         }
         if ("R3".equals(input.toUpperCase())) {
            System.out.println("Revenue");
         }
         if ("D".equals(input.toUpperCase())) {
            System.out.println("Detailed Reservation Information");
         }

         System.out.println("Invalid command: " + input);
      }

   }

   public static void roomsAndRates() {
      System.out.println("The Rooms and Rates are");
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

