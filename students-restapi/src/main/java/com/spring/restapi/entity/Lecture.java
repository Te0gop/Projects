package com.spring.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "lectures")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lectureName;
    private int duration;

    @ManyToOne
    @JsonIgnoreProperties("lectures")
    private Teacher teacher;

    public Lecture(Long id, String lectureName, int duration, Teacher teacher) {
        this.id = id;
        this.lectureName = lectureName;
        this.duration = duration;
        this.teacher = teacher;
    }

    public Lecture() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
