package com.mr.service.impl;

import com.mr.mapper.StudentMapper;
import com.mr.model.Student;
import com.mr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAllStudents() {
        return studentMapper.selectAllStudents();
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentMapper.selectStudentById(id);
    }

    @Override
    public void addStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentMapper.deleteStudent(id);
    }
}
