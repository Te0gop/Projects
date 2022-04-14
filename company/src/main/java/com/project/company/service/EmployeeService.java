package com.project.company.service;
import com.project.company.entity.Employee;
import com.project.company.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepo;

    public Employee getEmployeeById(Long id) {
        LOGGER.debug("Searching employee with id: " + id);
        Optional<Employee> employeeRepoById = employeeRepo.findById(id);

        if(employeeRepoById.isEmpty()) {
            LOGGER.error("Employee with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        LOGGER.info("Employee with id: " + id + " was found successfully.");
        return employeeRepoById.get();
    }

    public List<Employee> getAllEmployees() {
        LOGGER.warn("Searching all employees...");
        ArrayList<Employee> employees = new ArrayList<>(employeeRepo.findAll());
        LOGGER.warn(employees.size() + " employees was found.");
        return employees;
    }

    public Employee addEmployee(Employee employee) {
        LOGGER.warn("Adding new employee to repository: " + employee.getFirstName());
        employeeRepo.save(employee);
        LOGGER.info(employee.getFirstName() + " was saved successfully.");
        return employee;
    }

    public Employee updateEmployee(Long id, Employee employee) {
        LOGGER.warn("Updating employee: " + employee.getFirstName());
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isEmpty()) {
            LOGGER.error("Employee with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        Employee updatedEmployee = employeeRepo.getById(id);
        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setAge(employee.getAge());
        updatedEmployee.setPersonalId(employee.getPersonalId());
        updatedEmployee.setPosition(employee.getPosition());
        LOGGER.info(updatedEmployee.getFirstName() + " was updated successfully.");
        return employeeRepo.save(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if(optionalEmployee.isEmpty()) {
            LOGGER.error("Employee with id: " + id + " was not found.");
            throw new IllegalStateException();
        }
        LOGGER.info("Employee with id: " + id + " was deleted successfully.");
        employeeRepo.deleteById(id);
    }
}
