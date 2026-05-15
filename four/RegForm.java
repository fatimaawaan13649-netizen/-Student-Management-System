package four;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class RegForm extends JPanel {
    // UI components
    JTextField tfId, tfName, tfEmail, tfPhone, tfDOB;
    JRadioButton rbMale, rbFemale;
    JTextArea taAddress;
    JCheckBox cbJava, cbPython, cbCpp;
    JTable studentTable;
    JButton btnAdd, btnUpdate, btnView, btnDelete, btnClear;
    String currentOperation;
    
    public RegForm() {
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add form components
        formPanel.add(new JLabel("Student ID:"));
        tfId = new JTextField();
        formPanel.add(tfId);
        
        formPanel.add(new JLabel("Name:"));
        tfName = new JTextField();
        formPanel.add(tfName);
        
        formPanel.add(new JLabel("Email:"));
        tfEmail = new JTextField();
        formPanel.add(tfEmail);
        
        formPanel.add(new JLabel("Phone:"));
        tfPhone = new JTextField();
        formPanel.add(tfPhone);
        
        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        ButtonGroup genderGroup = new ButtonGroup();
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        formPanel.add(genderPanel);
        
        formPanel.add(new JLabel("Date of Birth:"));
        tfDOB = new JTextField();
        formPanel.add(tfDOB);
        
        formPanel.add(new JLabel("Address:"));
        taAddress = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(taAddress));
        
        formPanel.add(new JLabel("Courses:"));
        JPanel coursesPanel = new JPanel();
        cbJava = new JCheckBox("Java");
        cbPython = new JCheckBox("Python");
        cbCpp = new JCheckBox("C++");
        coursesPanel.add(cbJava);
        coursesPanel.add(cbPython);
        coursesPanel.add(cbCpp);
        formPanel.add(coursesPanel);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnView = new JButton("View");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnView);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        
        // Table for listing students
        studentTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(studentTable);
        
        // Add components to panel
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);
        
        // Set up event handlers
        btnAdd.addActionListener(e -> {
            currentOperation = "ADD";
            addStudent();
        });
        
        btnUpdate.addActionListener(e -> {
            currentOperation = "UPDATE";
            updateStudent();
        });
        
        btnView.addActionListener(e -> {
            currentOperation = "VIEW";
            viewStudent();
        });
        
        btnDelete.addActionListener(e -> {
            currentOperation = "DELETE";
            deleteStudent();
        });
        
        btnClear.addActionListener(e -> clearForm());
    }
    
    public void setOperation(String operation) {
        this.currentOperation = operation;
    }
    
    private student createStudentFromForm() {
        String courses = "";
        if (cbJava.isSelected()) courses += "Java ";
        if (cbPython.isSelected()) courses += "Python ";
        if (cbCpp.isSelected()) courses += "C++";
        
        return new student(
            tfId.getText(),
            tfName.getText(),
            tfEmail.getText(),
            tfPhone.getText(),
            rbMale.isSelected() ? "Male" : "Female",
            tfDOB.getText(),
            taAddress.getText(),
            courses.trim()
        );
    }

    private void populateFormFromStudent(student student) {
        tfId.setText(student.getId());
        tfName.setText(student.getName());
        tfEmail.setText(student.getEmail());
        tfPhone.setText(student.getPhone());
        
        if ("Male".equals(student.getGender())) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        }
        
        tfDOB.setText(student.getDob());
        taAddress.setText(student.getAddress());
        
        String courses = student.getCourses();
        cbJava.setSelected(courses.contains("Java"));
        cbPython.setSelected(courses.contains("Python"));
        cbCpp.setSelected(courses.contains("C++"));
    }
    
    private boolean validateInputs() {
        if (tfId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student ID is required");
            return false;
        }
        if (currentOperation.equals("ADD") || currentOperation.equals("UPDATE")) {
            if (tfName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name is required");
                return false;
            }
            if (tfEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email is required");
                return false;
            }
            if (tfPhone.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phone is required");
                return false;
            }
            if (tfDOB.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Date of Birth is required");
                return false;
            }
        }
        return true;
    }
    
    public void clearForm() {
        tfId.setText("");
        tfName.setText("");
        tfEmail.setText("");
        tfPhone.setText("");
        tfDOB.setText("");
        taAddress.setText("");
        rbMale.setSelected(true);
        cbJava.setSelected(false);
        cbPython.setSelected(false);
        cbCpp.setSelected(false);
    }
    
    private void addStudent() {
        if (!validateInputs()) return;
        
        student student = createStudentFromForm();
        boolean success = StudentCRUD.addStudent(student);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            clearForm();
            listAllStudents();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add student. ID might already exist.");
        }
    }

    private void updateStudent() {
        if (!validateInputs()) return;
        
        student student = createStudentFromForm();
        boolean success = StudentCRUD.updateStudent(student);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
            listAllStudents();
        } else {
            JOptionPane.showMessageDialog(this, "No student found with ID: " + tfId.getText());
        }
    }

    private void deleteStudent() {
        String studentId = tfId.getText().trim();
        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID to delete");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete student with ID: " + studentId + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = StudentCRUD.deleteStudent(studentId);
            
            if (success) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                clearForm();
                listAllStudents();
            } else {
                JOptionPane.showMessageDialog(this, "No student found with ID: " + studentId);
            }
        }
    }

    private void viewStudent() {
        String studentId = tfId.getText().trim();
        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID to view");
            return;
        }
        
        student student = StudentCRUD.getStudent(studentId);
        
        if (student != null) {
            populateFormFromStudent(student);
            JOptionPane.showMessageDialog(this, "Student details loaded!");
        } else {
            JOptionPane.showMessageDialog(this, "No student found with ID: " + studentId);
        }
    }

    public void listAllStudents() {
        try {
            ResultSet rs = StudentCRUD.getAllStudents();
            if (rs == null) {
                JOptionPane.showMessageDialog(this, "No students found in database");
                return;
            }
            
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Email");
            model.addColumn("Phone");
            model.addColumn("Gender");
            model.addColumn("DOB");
            model.addColumn("Courses");
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("gender"),
                    rs.getString("dob"),
                    rs.getString("courses")
                });
            }
            
            studentTable.setModel(model);
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error listing students: " + e.getMessage());
            e.printStackTrace();
        }
    }
}