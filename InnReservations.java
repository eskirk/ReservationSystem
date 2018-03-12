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
 export APP_JDBC_URL=jdbc:mysql://csc365winter2018.webredirect.org/alam38?autoReconnect=true
 export APP_JDBC_USER=<USERNAME>
 export APP_JDBC_PW=<PASSWORD>
 */

public class InnReservations {
   public static void main(String[] args) throws SQLException {
      establishConnection();
   }

   public static void establishConnection() throws SQLException {
      String url = System.getenv("APP_JDBC_URL");
      String user = System.getenv("APP_JDBC_USER");
      String pass = System.getenv("APP_JDBC_PW");

      try (Connection conn = DriverManager.getConnection(url, user, pass)) {
         System.out.println("Established connection");
      }
   }
}