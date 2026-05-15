package four;

public class student {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String gender;
    private String dob;
    private String address;
    private String courses;
    
    public student(String id, String name, String email, String phone, String gender, 
                   String dob, String address, String courses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.courses = courses;
    }
    
    // Constructor for simplified cases (keep if needed)
    public student(String name, String id, String email, String phone) {
        this(name, id, email, phone, "", "", "", "");
    }
    
    // Getters and setters
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getDob() {
        return dob;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getCourses() {
        return courses;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setCourses(String courses) {
        this.courses = courses;
    }
}