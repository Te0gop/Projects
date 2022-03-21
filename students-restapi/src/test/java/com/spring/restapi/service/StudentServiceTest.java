package com.spring.restapi.service;
import com.spring.restapi.entity.Student;
import com.spring.restapi.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private static Student student;

    @Mock
    StudentRepository studentRepository;
    StudentService studentService;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Johnson");
        student.setEmail("john.j@gmail.com");
        student.setAge(33);

        studentService = new StudentService(studentRepository);
    }

    @Test
    void testIfStudentIsAddedCorrectly() {
        studentService.addStudent(student);
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        //Capturing actual student that is passed inside save method
        verify(studentRepository).save(studentArgumentCaptor.capture());

        //Verifying that captured student is the same student
        Student capturedStudent = studentArgumentCaptor.getValue();
        Assertions.assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void testListAllStudents() {
        studentService.getStudents();
        verify(studentRepository).findAll();
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
}