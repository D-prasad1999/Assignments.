package com.employeesoapcrud.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.employeesoapcrud.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByEmployeeId(long employeeId);
}