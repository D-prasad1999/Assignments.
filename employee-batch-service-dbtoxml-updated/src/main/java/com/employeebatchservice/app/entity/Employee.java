package com.employeebatchservice.app.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
		
		@Id
		private int id;
		private String name;
		private int salary;
		private int age;
		
}


