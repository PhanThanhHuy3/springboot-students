package com.example.demo1.controller;

import com.example.demo1.model.Student;
import com.example.demo1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. HIỂN THỊ DANH SÁCH
    @GetMapping("/view")
    public String viewHomePage(Model model) {
        List<Student> list = studentRepository.findAll();
        model.addAttribute("students", list);
        return "index";
    }

    // 2. THÊM HOẶC CẬP NHẬT
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        // Nếu id bị trống (do form thêm mới không nhập), tạo ID mới
        if (student.getId() == null || student.getId().trim().isEmpty()) {
            student.setId(UUID.randomUUID().toString());
        }
        studentRepository.save(student);
        return "redirect:/students/view";
    }

    // 3. SỬA: Lấy thông tin cũ dựa trên UUID (String)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable(value = "id") String id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên với ID: " + id));
        model.addAttribute("student", student);
        return "edit";
    }

    // 4. XÓA: Xóa dựa trên UUID (String)
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable(value = "id") String id) {
        studentRepository.deleteById(id);
        return "redirect:/students/view";
    }

    // 5. API: Trả về JSON cho một sinh viên cụ thể
    @GetMapping("/api/{id}")
    @ResponseBody
    public Student getStudentApi(@PathVariable String id) {
        return studentRepository.findById(id).orElse(null);
    }

    // 6. TÌM KIẾM THEO TÊN
    @GetMapping("/search")
    public String searchStudents(@RequestParam("keyword") String keyword, Model model) {
        List<Student> list = studentRepository.findByNameContainingIgnoreCase(keyword);
        model.addAttribute("students", list);
        model.addAttribute("keyword", keyword);
        return "index";
    }
}