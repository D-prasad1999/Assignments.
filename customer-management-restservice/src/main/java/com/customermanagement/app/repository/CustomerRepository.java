package com.customermanagement.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customermanagement.app.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Optional<Customer> findByCustId(int id);
	Optional<Customer> findByContactNo(String contactNo);
	List<Customer> findByAge(int age);
}
