package com.employeesoapcrud.app.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employeesoapcrud.app.entity.Employee;
import com.employeesoapcrud.app.repository.EmployeeRepository;
import com.employeesoapcrud.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;
		
	@Override
	public Employee getEmployeeById(long employeeId) {
		
		Employee obj = employeeRepository.findByEmployeeId(employeeId);
		return obj;
	}

	@Override
	public void AddEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public void deleteEmployee(long employeeId) {
		employeeRepository.deleteById(employeeId);

	}
	
	@Override
    public List<Employee> getAllEmployees() {
       return employeeRepository.findAll();
    }
}
