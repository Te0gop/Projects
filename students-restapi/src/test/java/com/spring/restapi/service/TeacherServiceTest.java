package com.spring.restapi.service;

import com.spring.restapi.entity.Teacher;
import com.spring.restapi.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    private static Teacher teacher;

    @Mock
    TeacherRepository teacherRepository;
    TeacherService teacherService;

    @BeforeEach
    public void setUp() {
        teacherService = new TeacherService(teacherRepository);
    }


    @Test
    void getAllTeachers() {
    }

    @Test
    void getTeacherById() {
    }

    @Test
    void saveTeacher() {
    }

    @Test
    void updateTeacher() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void patchTeacher() {
    }
}