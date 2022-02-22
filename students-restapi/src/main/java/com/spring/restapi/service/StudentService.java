package com.spring.restapi.service;

import com.spring.restapi.entity.Student;
import com.spring.restapi.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    //Instead @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
       return studentRepository.save(student);
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.getById(id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student, Long id) {
        Student existingStudent = studentRepository.findById(id).orElseThrow();
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());
        studentRepository.save(existingStudent);
        return existingStudent;

    }

    public Student patchStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow();

        BeanUtils.copyProperties(student, existingStudent, "id");
        return studentRepository.save(existingStudent);
    }
}
