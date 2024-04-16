package com.customermanagement.app.service;

import java.util.List;

import com.customermanagement.app.entity.Customer;

public interface CustomerService {
	
	String saveCustomer(Customer customer);
	
	List<Customer> getAllCustomers();
	
	Customer getCustomerById(int id);
	
	Customer getCustomerByContactNo(String contactNo);
		
	List<Customer> getCustomerByAge(int age);
}
