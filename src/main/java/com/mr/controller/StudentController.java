package com.mr.controller;

import com.mr.interfaces.TrackPoint;
import com.mr.model.Student;
import com.mr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生管理接口
 */
@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public Map<String, Object> getAllStudents() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Student> students = studentService.findAllStudents();
            result.put("code", 200);
            result.put("message", "查询成功");
            result.put("data", students);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "查询失败: " + e.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getStudentById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            Student student = studentService.findStudentById(id);
            if (student != null) {
                result.put("code", 200);
                result.put("message", "查询成功");
                result.put("data", student);
            } else {
                result.put("code", 404);
                result.put("message", "学生不存在");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "查询失败: " + e.getMessage());
        }
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> addStudent(@RequestBody Student student) {
        Map<String, Object> result = new HashMap<>();
        try {
            studentService.addStudent(student);
            result.put("code", 200);
            result.put("message", "添加成功");
            result.put("data", student);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "添加失败: " + e.getMessage());
        }
        return result;
    }

    @PutMapping("/update")
    public Map<String, Object> updateStudent(@RequestBody Student student) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (student.getId() == null) {
                result.put("code", 400);
                result.put("message", "学生ID不能为空");
                return result;
            }
            studentService.updateStudent(student);
            result.put("code", 200);
            result.put("message", "更新成功");
            result.put("data", student);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "更新失败: " + e.getMessage());
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteStudent(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            studentService.deleteStudent(id);
            result.put("code", 200);
            result.put("message", "删除成功");
        } catch (Exception e) {
            result.put("code", 500);
            result.put("message", "删除失败: " + e.getMessage());
        }
        return result;
    }
}
