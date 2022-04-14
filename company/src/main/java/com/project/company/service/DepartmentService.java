package com.project.company.service;

import com.project.company.entity.Department;
import com.project.company.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepo;

    public Department getDepartmentById(Long id) {
        LOGGER.debug("Searching department with id: " + id);
        Optional<Department> departmentRepoById = departmentRepo.findById(id);

        if(departmentRepoById.isEmpty()) {
            LOGGER.error("Department with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        LOGGER.info("Department with id: " + id + " was found successfully.");
        return departmentRepoById.get();
    }

    public List<Department> getAllDepartments() {
        LOGGER.warn("Searching all departments...");
        ArrayList<Department> departments = new ArrayList<>(departmentRepo.findAll());
        LOGGER.warn(departments.size() + " departments was found.");
        return departments;
    }

    public Department addDepartment(Department department) {
        LOGGER.warn("Adding new department to repository: " + department.getName());
        departmentRepo.save(department);
        LOGGER.info(department.getName() + " was saved successfully.");
        return department;
    }

    public Department updateDepartment(Long id, Department department) {
        LOGGER.warn("Updating department: " + department.getName());
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if(optionalDepartment.isEmpty()) {
            LOGGER.error("Department with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        Department updatedDepartment = departmentRepo.getById(id);
        updatedDepartment.setDescription(department.getDescription());
        updatedDepartment.setName(department.getName());
        updatedDepartment.setDirectorate(department.getDirectorate());
        updatedDepartment.setEmployees(department.getEmployees());
        LOGGER.info(department.getName() + " was updated successfully.");
        return departmentRepo.save(updatedDepartment);
    }

    public void deleteDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if(optionalDepartment.isEmpty()) {
            LOGGER.error("Department with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        LOGGER.info("Department with id: " + id + " was deleted successfully.");
        departmentRepo.deleteById(id);
    }
}
