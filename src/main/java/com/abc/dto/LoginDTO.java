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
public class LoginDTO {

	private String emailOrUsername;	
	private String password;
}
