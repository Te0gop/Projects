package com.project.company.service;
import com.project.company.entity.Employee;
import com.project.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeRepoById = employeeRepo.findById(id);

        if(employeeRepoById.isEmpty()) {
            throw new IllegalStateException();
        }

        return employeeRepoById.get();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRepo.findAll());
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isEmpty()) {
            throw new IllegalStateException();
        }
        Employee updatedEmployee = employeeRepo.getById(id);
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setAge(employee.getAge());
        updatedEmployee.setPersonalId(employee.getPersonalId());
        updatedEmployee.setPosition(employee.getPosition());
        return employeeRepo.save(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isEmpty()) {
            throw new IllegalStateException();
        }

        employeeRepo.deleteById(id);
    }
}
