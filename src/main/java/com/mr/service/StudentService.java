package com.mr.service;

import com.mr.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();

    Student findStudentById(Integer id);

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Integer id);
}
