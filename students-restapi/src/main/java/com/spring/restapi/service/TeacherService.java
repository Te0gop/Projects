package com.spring.restapi.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.spring.restapi.entity.Student;
import com.spring.restapi.entity.Teacher;
import com.spring.restapi.repository.TeacherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.getById(id);
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow();
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setAge(teacher.getAge());
        existingTeacher.setDegree(teacher.getDegree());
        teacherRepository.save(existingTeacher);
        return existingTeacher;
    }

    public void deleteById(Long id) {
        teacherRepository.findById(id).orElseThrow();
        teacherRepository.deleteById(id);
    }

    public Teacher patchTeacher(Long id, Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow();
        BeanUtils.copyProperties(teacher, existingTeacher, "id");
        return teacherRepository.save(existingTeacher);
    }
}
