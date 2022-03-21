package com.spring.restapi.service;

import com.spring.restapi.entity.Student;
import com.spring.restapi.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LectureService.class);

    StudentRepository studentRepository;

    //Instead @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        LOGGER.warn("Adding new student to repository: " + student.getFirstName());
        studentRepository.save(student);
        LOGGER.info(student.getFirstName() + " was saved successfully.");
        return student;
    }

    public List<Student> getStudents() {
        LOGGER.warn("Searching all students...");
        List<Student> student = new ArrayList<>(studentRepository.findAll());
        LOGGER.warn(student.size() + " students was found.");
        return student;
    }

    public Student getStudentById(Long id) {
        LOGGER.debug("Searching student with id: " + id);
        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty()) {
            LOGGER.error("Student with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        LOGGER.info("Student with id: " + id + " was found successfully.");
        return student.get();
    }

    public void deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty()) {
            LOGGER.error("Student with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        studentRepository.deleteById(id);
        LOGGER.info("Student with id: " + id + " was deleted successfully.");
    }

    public Student updateStudent(Student student, Long id) {
        LOGGER.warn("Updating student: " + student.getFirstName());
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isEmpty()) {
            LOGGER.error("Student with id: " + id + " was not found.");
            throw new IllegalStateException();
        }

        Student updatedStudent = studentRepository.getById(id);
        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setAge(student.getAge());
        updatedStudent.setEmail(student.getEmail());
        studentRepository.save(updatedStudent);
        LOGGER.info(student.getFirstName() + " was updated successfully.");
        return updatedStudent;
    }
}
