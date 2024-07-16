package com.codegym.c0324h1_2.services;

import com.codegym.c0324h1_2.models.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    void save(Student student);

    Student findById(Long id);

    void update(Student student);

    void deleteStudent(Long id);
}
