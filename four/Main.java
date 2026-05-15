package four;

import java.sql.Connection;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Connection conn = CP.createC();
        
        if (conn != null) {
            CP.closeConnection(conn);
            new MainFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to connect to database. Application will exit.");
            System.exit(1);
        }
    }
}