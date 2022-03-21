package com.spring.restapi.service;

import com.spring.restapi.entity.Teacher;
import com.spring.restapi.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LectureService.class);

    TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        LOGGER.warn("Searching all teachers...");
        List<Teacher> teachers = new ArrayList<>(teacherRepository.findAll());
        LOGGER.warn(teachers.size() + " teachers was found.");
        return teachers;
    }

    public Teacher getTeacherById(Long id) {
        LOGGER.debug("Searching teacher with id: " + id);
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if(teacher.isEmpty()) {
            LOGGER.error("Teacher with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        LOGGER.info("Teacher with id: " + id + " was found successfully.");
        return teacher.get();
    }

    public Teacher saveTeacher(Teacher teacher) {
        LOGGER.warn("Adding new teacher to repository: " + teacher.getFirstName());
        teacherRepository.save(teacher);
        LOGGER.info(teacher.getFirstName() + " was saved successfully.");
        return teacher;
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        LOGGER.warn("Updating teacher: " + teacher.getFirstName());
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);

        if(optionalTeacher.isEmpty()) {
            LOGGER.error("Teacher with id: " + id + " was not found.");
            throw new IllegalStateException();
        }

        Teacher updatedTeacher = teacherRepository.getById(id);
        updatedTeacher.setFirstName(teacher.getFirstName());
        updatedTeacher.setLastName(teacher.getLastName());
        updatedTeacher.setAge(teacher.getAge());
        updatedTeacher.setDegree(teacher.getDegree());
        teacherRepository.save(updatedTeacher);
        LOGGER.info(teacher.getFirstName() + " was updated successfully.");
        return updatedTeacher;
    }

    public void deleteById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if(teacher.isEmpty()) {
            LOGGER.error("Teacher with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        teacherRepository.deleteById(id);
        LOGGER.info("Teacher with id: " + id + " was deleted successfully.");
    }
}
