package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private RegForm regForm;
    
    public MainFrame() {
        setTitle("Student Management System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create control panel
        JPanel controlPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create buttons
        JButton btnAdd = new JButton("Add Student");
        JButton btnUpdate = new JButton("Update Student");
        JButton btnDelete = new JButton("Delete Student");
        JButton btnView = new JButton("View Student");
        JButton btnList = new JButton("List All Students");
        
        // Add buttons to panel
        controlPanel.add(btnAdd);
        controlPanel.add(btnUpdate);
        controlPanel.add(btnDelete);
        controlPanel.add(btnView);
        controlPanel.add(btnList);
        
        // Initialize registration form (hidden initially)
        regForm = new RegForm();
        regForm.setVisible(false);
        
        // Add components to frame
        add(controlPanel, BorderLayout.NORTH);
        add(regForm, BorderLayout.CENTER);
        
        // Set up button actions
        btnAdd.addActionListener(e -> {
            regForm.setOperation("ADD");
            regForm.setVisible(true);
            regForm.clearForm();
        });
        
        btnUpdate.addActionListener(e -> {
            regForm.setOperation("UPDATE");
            regForm.setVisible(true);
            regForm.clearForm();
        });
        
        btnDelete.addActionListener(e -> {
            regForm.setOperation("DELETE");
            regForm.setVisible(true);
            regForm.clearForm();
            regForm.tfId.requestFocus();
        });
        
        btnView.addActionListener(e -> {
            regForm.setOperation("VIEW");
            regForm.setVisible(true);
            regForm.clearForm();
            regForm.tfId.requestFocus();
        });
        
        btnList.addActionListener(e -> {
            regForm.setOperation("LIST");
            regForm.setVisible(true);
            regForm.listAllStudents();
        });
    }
}