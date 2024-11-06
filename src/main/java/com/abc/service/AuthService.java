package com.abc.service;

import com.abc.dto.AdminDTO;
import com.abc.dto.CustomerDTO;
import com.abc.dto.UserDTO;

public interface AuthService {

	public UserDTO login(String usernameEmail,String password);
	
	public CustomerDTO registerAsCustomer(CustomerDTO customerDTO);
	
	public AdminDTO registerAsAdmin(AdminDTO adminDTO);
	
}
