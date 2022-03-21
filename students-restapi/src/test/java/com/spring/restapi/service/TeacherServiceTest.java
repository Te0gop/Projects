package com.spring.restapi.service;
import com.spring.restapi.entity.Teacher;
import com.spring.restapi.repository.TeacherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    private static Teacher teacher;

    @Mock
    TeacherRepository teacherRepository;
    TeacherService teacherService;

    @BeforeEach
    public void setUp() {
        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("Steven");
        teacher.setLastName("Steward");
        teacher.setAge(55);
        teacher.setDegree("PhD");
        teacher.setLectures(new ArrayList<>());

        teacherService = new TeacherService(teacherRepository);
    }


    @Test
    void testIfTeacherIsAddedCorrectly() {
        teacherService.saveTeacher(teacher);
        ArgumentCaptor<Teacher> teacherArgumentCaptor = ArgumentCaptor.forClass(Teacher.class);

        verify(teacherRepository).save(teacherArgumentCaptor.capture());

        Teacher capturedTeacher = teacherArgumentCaptor.getValue();
        Assertions.assertThat(capturedTeacher).isEqualTo(teacher);
    }

    @Test
    void testListAllTeachers() {
        teacherService.getAllTeachers();
        verify(teacherRepository).findAll();
    }

    @Test
    void testIfGetTeachersByIdWorkCorrectly() {
        assertEquals("Steven", teacherService.getTeacherById(1L).getFirstName());
    }

    @Test
    void testUpdateTeacher() {
        Teacher updatedTeacher =
                new Teacher(1L, "Ivan", "Ivanov", 44, "Professor", new ArrayList<>());

        teacherService.updateTeacher(1L, updatedTeacher);
        assertEquals(teacherService.getTeacherById(1L).getFirstName(), "Ivan");
    }

    @Test
    void testIfTeacherIsdeletedById() {
        teacherService.deleteById(1L);
        Optional<Teacher> optionalTeacher = teacherRepository.findById(1L);
        Assertions.assertThat(optionalTeacher).isNotPresent();
    }
}