package com.abc.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.dto.CustomerDTO;
import com.abc.service.CustomerService;

@CrossOrigin(value = "http://localhost:4200/")
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	static Logger logger = LoggerFactory.getLogger(CustomerController.class);
	

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/")
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
		
		customerDTO = customerService.saveCustomer(customerDTO);
		
		return new ResponseEntity<>(customerDTO,HttpStatus.CREATED);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> fetchCustomerDetails(@PathVariable long customerId) {
		
		CustomerDTO customerDTO = customerService.findCustomerById(customerId);
		
		return new ResponseEntity<>(customerDTO,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Set<CustomerDTO>> fetchAllCustomers() {
		

		logger.info("starts fetchAllCustomers - CustomerController");
		
		Set<CustomerDTO> customers = customerService.findAllCustomers();
		
		logger.info("ends fetchAllCustomers - CustomerController");
		return new ResponseEntity<>(customers,HttpStatus.OK);
	}
}
