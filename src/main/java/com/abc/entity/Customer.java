package com.abc.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer_tbl")
public class Customer extends User {

	@Column(name="first_name")	
	private String firstName;	
	
	@Column(name="last_name")	
	private String lastName;
	
	private LocalDate dob;
	private String mobile;
	
	private String city;
	
	@OneToMany(mappedBy = "customer")
	private Set<Order> orders;	
	
}
