package com.spring.restapi.controller;

import com.spring.restapi.entity.Lecture;
import com.spring.restapi.entity.Student;
import com.spring.restapi.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    @Autowired
    LectureService lectureService;

    @GetMapping
    public List<Lecture> getAllLectures() {
        return lectureService.getAllLectures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(lectureService.getLectureById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Lecture> saveLecture(@RequestBody Lecture lecture) {
        return new ResponseEntity<>(lectureService.saveLecture(lecture), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lecture> updateLecture(@PathVariable("id") Long id, @RequestBody Lecture lecture) {
        return new ResponseEntity<>(lectureService.updateLecture(id, lecture), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Lecture> deleteLectureById(@PathVariable("id") Long id) {
        lectureService.deleteLectureById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Lecture> patchLecture(@PathVariable("id") Long id, @RequestBody Lecture lecture) {
        return new ResponseEntity<>(lectureService.patchLecture(id, lecture), HttpStatus.OK);
    }
}
