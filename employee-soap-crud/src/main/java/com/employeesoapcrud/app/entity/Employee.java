package com.employeesoapcrud.app.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L; 

	@Id
	private long employeeId;
	private String name;
	private String department;
	private String phone;
	private String address;
}
