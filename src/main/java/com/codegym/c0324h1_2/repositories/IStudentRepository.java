package com.codegym.c0324h1_2.repositories;

import com.codegym.c0324h1_2.models.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();

    void save(Student student);

    Student findById(Long id);

    void update(Student student);

    void deleteStudent(Long id);
}
