package com.spring.restapi.service;

import com.spring.restapi.entity.Student;
import com.spring.restapi.repository.StudentRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class StudentServiceTest {

    private static Student student;

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Johnson");
        student.setEmail("john.j@gmail.com");
        student.setAge(33);
    }


    @Test
    void testIfStudentIsAddedCorrectly() {
        studentService.addStudent(student);
        assertNotNull(studentService.getStudentById(1L));
    }

    @Test
    void testListAllStudents() {
        List<Student> studentsList = studentService.getStudents();
        assertNotEquals(0, studentsList.size());
    }

    @Test
    void testIfGetStudentByIdWorkCorrectly() {
        assertEquals("John", studentService.getStudentById(1L).getFirstName());
    }

    @Test
    void testDeleteStudentById() {
        studentService.deleteStudent(1L);
        Optional<Student> optionalStudent = studentRepository.findById(1L);
        Assertions.assertThat(optionalStudent).isNotPresent();
    }

    @Test
    void testUpdateStudentById() {
        Student updatedStudent =
                new Student(1L, "Ivan", "Ivanov", 18, "ivan.i@gmail.com");

        studentService.updateStudent(updatedStudent, 1L);
        assertEquals(studentService.getStudentById(1L).getFirstName(), "Ivan");
    }

    @Test
    void testPatchStudentById() {
        Student patchedStudent = new Student();
        patchedStudent.setFirstName("Mike");
        studentService.patchStudent(1L, patchedStudent);

        assertEquals(studentService.getStudentById(1L).getFirstName(), "Mike");
    }






}