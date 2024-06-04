package com.example.task4.client;

import com.example.task4.config.FeignClientConfig;
import com.example.task4.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employeeService", url = "http://external-service.com", configuration = FeignClientConfig.class)
public interface EmployeeServiceClient {

    @GetMapping("/employees/{id}")
    Employee getEmployeeById(@PathVariable("id") Long id);
}
