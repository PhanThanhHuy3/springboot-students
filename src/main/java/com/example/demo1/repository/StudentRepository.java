package com.example.demo1.repository;
import com.example.demo1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // JpaRepository đã có sẵn các hàm: save(), findAll(), deleteById(),...
    // Tự động tạo câu lệnh: SELECT * FROM students WHERE name LIKE %keyword%
    List<Student> findByNameContainingIgnoreCase(String name);
}
