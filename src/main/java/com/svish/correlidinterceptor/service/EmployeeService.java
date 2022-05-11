package com.svish.correlidinterceptor.service;

import com.svish.correlidinterceptor.entity.Employee;
import com.svish.correlidinterceptor.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public Employee newEmployee(Employee employeeRequest) {
        Employee response = repository.save(employeeRequest);
        logger.info(response.toString());
        return response;
    }

    public Employee getEmployee(Integer id) {
        Employee response = repository.getById(id);
        logger.info(response.toString());
        return response;
    }

}
