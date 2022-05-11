package com.svish.correlidinterceptor.repository;

import com.svish.correlidinterceptor.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
