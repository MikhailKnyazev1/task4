package com.example.task4.service;

import com.example.task4.model.Employee;
import com.example.task4.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Сотрудник с ID " + id + " не найден"));
        updateEmployeeData(existingEmployee, employeeDetails);
        return employeeRepository.save(existingEmployee);
    }

    private void updateEmployeeData(Employee existingEmployee, Employee employeeDetails) {
        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setAge(employeeDetails.getAge());
        existingEmployee.setProfession(employeeDetails.getProfession());
        existingEmployee.setCompany(employeeDetails.getCompany());
        existingEmployee.setEffectiveDate(employeeDetails.getEffectiveDate());
    }

    public List<Employee> addEmployeesBulk(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }
}
