package com.spring.restapi.service;

import com.spring.restapi.entity.Lecture;
import com.spring.restapi.repository.LectureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LectureService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LectureService.class);

    @Autowired
    LectureRepository lectureRepository;

    public List<Lecture> getAllLectures() {
        LOGGER.warn("Searching all lectures...");
        List<Lecture> lecture = new ArrayList<>(lectureRepository.findAll());
        LOGGER.warn(lecture.size() + " lectures was found.");
        return lecture;
    }

    public Lecture getLectureById(Long id) {
        LOGGER.debug("Searching drink with id: " + id);

        Optional<Lecture> lecture = lectureRepository.findById(id);

        if(lecture.isEmpty()) {
            LOGGER.error("Lecture with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        LOGGER.info("Lecture with id: " + id + " was found successfully.");
        return lecture.get();
    }

    public Lecture saveLecture(Lecture lecture) {
        LOGGER.warn("Adding new lecture to repository: " + lecture.getLectureName());
        lectureRepository.save(lecture);
        LOGGER.info(lecture.getLectureName() + " was saved successfully.");
        return lecture;
    }

    public Lecture updateLecture(Long id, Lecture lecture) {
        LOGGER.warn("Updating lecture: " + lecture.getLectureName());
        Optional<Lecture> existingLecture = lectureRepository.findById(id);

        if(existingLecture.isEmpty()) {
            LOGGER.error("Lecture with id: " + id + " was not found.");
            throw new IllegalStateException();
        }

        lectureRepository.save(lecture);
        LOGGER.info(lecture.getLectureName() + " was updated successfully.");
        return lecture;
    }

    public void deleteLectureById(Long id) {
        Optional<Lecture> lecture = lectureRepository.findById(id);

        if(lecture.isEmpty()) {
            LOGGER.error("Lecture with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        lectureRepository.deleteById(id);
        LOGGER.info("Lecture with id: " + id + " was deleted successfully.");
    }
}
