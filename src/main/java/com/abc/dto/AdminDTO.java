package com.abc.dto;

import java.util.Set;

import com.abc.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
	private Long id;	
	private String username;	
	private String email;	
	private String password;	
	private Set<Role> roles;
	private String name;
	private String contactNumber;
}
