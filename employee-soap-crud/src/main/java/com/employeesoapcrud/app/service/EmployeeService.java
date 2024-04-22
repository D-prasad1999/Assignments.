package com.employeesoapcrud.app.service;

import java.util.List;

import com.employeesoapcrud.app.entity.Employee;

public interface EmployeeService {

	void AddEmployee(Employee employee);
	 
	Employee getEmployeeById(long employeeId);
	 
    void updateEmployee(Employee employee);
    
    void deleteEmployee(long employeeId);
    
    public List<Employee> getAllEmployees();
}
