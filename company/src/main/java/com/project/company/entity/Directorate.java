package com.project.company.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "directorates")
public class Directorate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chief_id", referencedColumnName = "id")
    private Employee directorateChief;

    @OneToMany(mappedBy = "directorate")
    private Set<Department> departments;

    public Directorate(Long id, String name, String description, Employee directorateChief, Set<Department> departments) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
