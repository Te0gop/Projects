package com.project.company.service;

import com.project.company.entity.Department;
import com.project.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepo;

    public Department getDepartmentById(Long id) {
        Optional<Department> departmentRepoById = departmentRepo.findById(id);

        if(departmentRepoById.isEmpty()) {
            throw new IllegalStateException();
        }

        return departmentRepoById.get();
    }

    public List<Department> getAllDepartments() {
        return new ArrayList<>(departmentRepo.findAll());
    }

    public Department addDepartment(Department department) {
       return departmentRepo.save(department);
    }

    public Department updateDepartment(Long id, Department department) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if(optionalDepartment.isEmpty()) {
            throw new IllegalStateException();
        }
        Department updatedDepartment = departmentRepo.getById(id);
        updatedDepartment.setDescription(department.getDescription());
        updatedDepartment.setName(department.getName());
        updatedDepartment.setDirectorate(department.getDirectorate());
        updatedDepartment.setEmployees(department.getEmployees());
        return departmentRepo.save(updatedDepartment);
    }

    public void deleteDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if(optionalDepartment.isEmpty()) {
            throw new IllegalStateException();
        }

        departmentRepo.deleteById(id);
    }
}
