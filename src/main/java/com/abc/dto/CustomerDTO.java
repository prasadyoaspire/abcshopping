package com.abc.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {	
	private Long id;	
	private String username;	
	private String email;	
	private String password;	
	private Set<RoleDTO> roles;
	private String firstName;	
	private String lastName;	
	private LocalDate dob;
	private String mobile;	
	private String city;	
}
