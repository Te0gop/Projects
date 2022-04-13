package com.project.company.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "directorates")
public class Directorate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chief_id", referencedColumnName = "id")
    private Employee directorateChief;

    @OneToMany(mappedBy = "directorate")
    private List<Department> departments;

    public Directorate(Long id, Employee directorateChief, List<Department> departments) {
        this.id = id;
        this.directorateChief = directorateChief;
        this.departments = departments;
    }

    public Directorate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getDirectorateChief() {
        return directorateChief;
    }

    public void setDirectorateChief(Employee directorateChief) {
        this.directorateChief = directorateChief;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
