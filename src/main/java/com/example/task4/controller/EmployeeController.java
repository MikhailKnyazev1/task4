package com.example.task4.controller;

import com.example.task4.model.Employee;
import com.example.task4.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        logger.info("Adding new employee: {}", employee);
        try {
            Employee savedEmployee = employeeService.addEmployee(employee);
            return ResponseEntity.ok(savedEmployee);
        } catch (Exception e) {
            logger.error("Error adding employee: {}", employee, e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        logger.info("Retrieving employee with ID: {}", id);
        try {
            Employee employee = employeeService.getEmployeeById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Employee with ID " + id + " not found"));
            return ResponseEntity.ok(employee);
        } catch (EntityNotFoundException e) {
            logger.error("Employee with ID {} not found", id, e);
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Retrieving all employees");
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employeeDetails) {
        logger.info("Updating employee with ID: {}", id);
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
            return ResponseEntity.ok(updatedEmployee);
        } catch (Exception e) {
            logger.error("Error updating employee with ID: {}", id, e);
            throw e;
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Employee>> addEmployeesBulk(@Valid @RequestBody List<Employee> employees) {
        logger.info("Adding employees in bulk: {}", employees);
        try {
            List<Employee> savedEmployees = employeeService.addEmployeesBulk(employees);
            return ResponseEntity.ok(savedEmployees);
        } catch (Exception e) {
            logger.error("Error adding employees in bulk", e);
            throw e;
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            logger.error("Validation error - Field: {}, Message: {}", fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
