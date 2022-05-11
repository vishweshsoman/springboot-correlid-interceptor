package com.svish.correlidinterceptor.controller;

import com.svish.correlidinterceptor.entity.Employee;
import com.svish.correlidinterceptor.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @PostMapping("/employee")
    public ResponseEntity<Employee> newEmployee(
            @RequestBody Employee employeeRequest
    ) {
        Employee response = service.newEmployee(employeeRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(
            @PathVariable(name = "id") Integer id
    ) {
        Employee response = service.getEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
