package com.example.demo1.model; // Thay đổi theo package của bạn

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "students") // Tên bảng trong SQL Server
public class Student {

    @Id // Đánh dấu đây là khóa chính (Primary Key)
    private Integer id;

    @Column(name = "name") // Ánh xạ với cột 'name'
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    // --- CONSTRUCTOR (Hàm khởi tạo) ---
    public Student() {
    }

    public Student(Integer id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // --- GETTERS AND SETTERS (Để truy xuất dữ liệu) ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}