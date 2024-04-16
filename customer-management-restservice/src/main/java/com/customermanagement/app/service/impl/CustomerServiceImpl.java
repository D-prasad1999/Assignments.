package com.customermanagement.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.customermanagement.app.entity.Customer;
import com.customermanagement.app.repository.CustomerRepository;
import com.customermanagement.app.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public String saveCustomer(Customer customer) {
		customerRepository.save(customer);
		return "Customer data saved successfully";
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(int id) {
		Customer customer=customerRepository.findByCustId(id).orElseThrow(()->new RuntimeException("Customer not found with id: " + id));
		return customer;
	}

	@Override
	public Customer getCustomerByContactNo(String contactNo) {
		Customer customer=customerRepository.findByContactNo(contactNo).orElseThrow(()->new RuntimeException("Customer not found with Contact No: " + contactNo));
		return customer;
	}

	@Override
	public List<Customer> getCustomerByAge(int age) {
		List<Customer> customers=customerRepository.findByAge(age);
		return customers;
	}

	

}
