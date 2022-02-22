package com.spring.restapi.service;

import com.spring.restapi.entity.Lecture;
import com.spring.restapi.entity.Student;
import com.spring.restapi.repository.LectureRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {

    @Autowired
    LectureRepository lectureRepository;

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public Lecture getLectureById(Long id) {
        return lectureRepository.getById(id);
    }

    public Lecture saveLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    public Lecture updateLecture(Long id, Lecture lecture) {
        Lecture existingLecture = lectureRepository.findById(id).orElseThrow();

        existingLecture.setLectureName(lecture.getLectureName());
        existingLecture.setDuration(lecture.getDuration());

        lectureRepository.save(existingLecture);
        return existingLecture;
    }

    public void deleteLectureById(Long id) {
        lectureRepository.findById(id).orElseThrow();
        lectureRepository.deleteById(id);
    }

    public Lecture patchLecture(Long id, Lecture lecture) {
        Lecture existingLecture = lectureRepository.findById(id).orElseThrow();

        BeanUtils.copyProperties(lecture, existingLecture, "id");
        return lectureRepository.save(existingLecture);
    }
}
