package four;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CP {
    static Connection connect;
    
    public static Connection createC() {
        try {    
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String password = "123456789";
            String url = "jdbc:mysql://localhost:3306/studentdb";
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection established successfully!");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database connection failed!");
            JOptionPane.showMessageDialog(null, "Database Connection Error: " + e.getMessage());
            return null;
        }
    }
    
    public static void closeConnection(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error closing database connection!");
        }
    }
}