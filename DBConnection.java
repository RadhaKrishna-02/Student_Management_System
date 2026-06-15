import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static final String URL =
            "jdbc:mysql://localhost:3306/studentdb";

    static final String USER = "root";

    static final String PASSWORD = "root";

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (Exception e) {

            System.out.println("Connection Failed");
            return null;
        }
    }
}