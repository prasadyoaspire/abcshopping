package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.dto.AdminDTO;
import com.abc.dto.CustomerDTO;
import com.abc.dto.LoginDTO;
import com.abc.dto.UserDTO;
import com.abc.service.AuthService;

@CrossOrigin(value = "http://localhost:4200/")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> signIn(@RequestBody LoginDTO loginDTO) {
		
		UserDTO userDTO = authService.login(loginDTO.getEmailOrUsername(), loginDTO.getPassword());
		
		return new ResponseEntity<>(userDTO,HttpStatus.OK);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<CustomerDTO> customerRegistration(@RequestBody CustomerDTO customerDTO) {
		
		customerDTO = authService.registerAsCustomer(customerDTO);
		
		return new ResponseEntity<>(customerDTO,HttpStatus.CREATED);
	}
	
	@PostMapping("/admin")
	public ResponseEntity<AdminDTO> adminRegistration(@RequestBody AdminDTO adminDTO) {
		
		adminDTO = authService.registerAsAdmin(adminDTO);
		
		return new ResponseEntity<>(adminDTO,HttpStatus.CREATED);
	}
}
