package com.mr.mapper;

import com.mr.model.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    Student selectStudentById(Integer id);

    List<Student> selectAllStudents();

    void insertStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Integer id);
}
