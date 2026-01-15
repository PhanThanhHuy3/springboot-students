package com.example.demo1.controller;

import com.example.demo1.model.Student;
import com.example.demo1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students") // Thay đổi prefix để dễ quản lý
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. HIỂN THỊ DANH SÁCH: http://localhost:8080/students/view
    @GetMapping("/view")
    public String viewHomePage(Model model) {
        List<Student> list = studentRepository.findAll();
        model.addAttribute("students", list); 
        return "index"; 
    }

    // 2. THÊM HOẶC CẬP NHẬT (Lưu dữ liệu)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentRepository.save(student); // Hibernate tự hiểu: Nếu có ID rồi thì là Update, chưa có thì là Insert
        return "redirect:/students/view"; // Lưu xong quay về trang danh sách
    }

    // 3. SỬA: Lấy thông tin cũ và hiển thị lên form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable(value = "id") Integer id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "edit"; // Trả về file edit.html
    }

    // 4. XÓA: Xóa theo ID
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") Integer id) {
        studentRepository.deleteById(id);
        return "redirect:/students/view"; // Xóa xong quay về trang danh sách
    }
}