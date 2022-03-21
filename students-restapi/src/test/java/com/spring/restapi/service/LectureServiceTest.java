package com.spring.restapi.service;

import com.spring.restapi.entity.Lecture;
import com.spring.restapi.entity.Student;
import com.spring.restapi.entity.Teacher;
import com.spring.restapi.repository.LectureRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class LectureServiceTest {

    private static Lecture lecture;
    private static Teacher teacher;

    @Autowired
    LectureService lectureService;

    @Autowired
    LectureRepository lectureRepository;

    @BeforeEach
    void setUp() {
//        teacher = new Teacher(1L, "Jake", "Jackson", 51, "PhD", new ArrayList<>());
        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setFirstName("Jake");
        teacher.setLastName("Jackson");
        teacher.setAge(51);
        teacher.setDegree("PhD");
        teacher.setLectures(new ArrayList<>());

        lecture = new Lecture();
        lecture.setId(1L);
        lecture.setLectureName("Unit testing");
        lecture.setDuration(2);
        lecture.setTeacher(teacher);
    }

    @Test
    void saveLecture() {
        lectureService.saveLecture(lecture);
        assertNotNull(lectureService.getLectureById(1L));
    }

    @Test
    void getLectureById() {
        assertEquals("Unit testing", lectureService.getLectureById(1L).getLectureName());
    }

    @Test
    void getAllLectures() {
        List<Lecture> allLectures = lectureService.getAllLectures();
        assertNotEquals(0, allLectures.size());
    }

    @Test
    void updateLecture() {
        Lecture updatedLecture =
                new Lecture(1L, "TDD", 18, teacher);

        lectureService.updateLecture(1L, updatedLecture);
        assertEquals(lectureService.getLectureById(1L).getLectureName(), "TDD");
    }

    @Test
    void deleteLectureById() {
        lectureService.deleteLectureById(1L);
        Optional<Lecture> optionalLecture = lectureRepository.findById(1L);
        Assertions.assertThat(optionalLecture).isNotPresent();
    }
}