package four;

import java.sql.*;
import javax.swing.JOptionPane;

public class StudentCRUD {
    public static boolean addStudent(student student) {
        Connection con = null;
        try {
            System.out.println("[DB] Preparing to add student: " + student.getId());
            con = CP.createC();
            String query = "INSERT INTO students (id, name, email, phone, gender, dob, address, courses) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhone());
            pstmt.setString(5, student.getGender());
            pstmt.setString(6, student.getDob());
            pstmt.setString(7, student.getAddress());
            pstmt.setString(8, student.getCourses());
            
            pstmt.executeUpdate();
            System.out.println("[DB] Successfully added student: " + student.getId());
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("[DB] Student ID already exists: " + student.getId());
            JOptionPane.showMessageDialog(null, "Student ID already exists!");
            return false;
        } catch (Exception e) {
            System.out.println("[DB] Error adding student: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error adding student: " + e.getMessage());
            return false;
        } finally {
            CP.closeConnection(con);
        }
    }

    public static boolean updateStudent(student student) {
        Connection con = null;
        try {
            System.out.println("[DB] Preparing to update student: " + student.getId());
            con = CP.createC();
            String query = "UPDATE students SET name=?, email=?, phone=?, gender=?, dob=?, address=?, courses=? WHERE id=?";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPhone());
            pstmt.setString(4, student.getGender());
            pstmt.setString(5, student.getDob());
            pstmt.setString(6, student.getAddress());
            pstmt.setString(7, student.getCourses());
            pstmt.setString(8, student.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[DB] Successfully updated student: " + student.getId());
                return true;
            } else {
                System.out.println("[DB] No student found with ID: " + student.getId());
                return false;
            }
        } catch (Exception e) {
            System.out.println("[DB] Error updating student: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error updating student: " + e.getMessage());
            return false;
        } finally {
            CP.closeConnection(con);
        }
    }

    public static boolean deleteStudent(String studentId) {
        Connection con = null;
        try {
            System.out.println("[DB] Preparing to delete student: " + studentId);
            con = CP.createC();
            String query = "DELETE FROM students WHERE id=?";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, studentId);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[DB] Successfully deleted student: " + studentId);
                return true;
            } else {
                System.out.println("[DB] No student found with ID: " + studentId);
                return false;
            }
        } catch (Exception e) {
            System.out.println("[DB] Error deleting student: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error deleting student: " + e.getMessage());
            return false;
        } finally {
            CP.closeConnection(con);
        }
    }

    public static student getStudent(String studentId) {
        Connection con = null;
        try {
            System.out.println("[DB] Preparing to fetch student: " + studentId);
            con = CP.createC();
            String query = "SELECT * FROM students WHERE id=?";
            
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, studentId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                student student = new student(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getString("dob"),
                    rs.getString("address"),
                    rs.getString("courses")
                );
                System.out.println("[DB] Successfully fetched student: " + studentId);
                return student;
            }
            System.out.println("[DB] No student found with ID: " + studentId);
            return null;
        } catch (Exception e) {
            System.out.println("[DB] Error fetching student: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error getting student: " + e.getMessage());
            return null;
        } finally {
            CP.closeConnection(con);
        }
    }

    public static ResultSet getAllStudents() {
        Connection con = null;
        try {
            System.out.println("[DB] Preparing to fetch all students");
            con = CP.createC();
            String query = "SELECT * FROM students";
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            
            // We can't close the connection here because it would close the ResultSet
            // The caller must close the ResultSet when done
            System.out.println("[DB] Successfully fetched all students");
            return rs;
        } catch (Exception e) {
            System.out.println("[DB] Error fetching all students: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error getting all students: " + e.getMessage());
            try {
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("[DB] Error closing connection: " + ex.getMessage());
            }
            return null;
        }
        // Note: Connection will be closed when ResultSet is closed by the caller
    }
}