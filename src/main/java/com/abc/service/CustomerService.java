package com.abc.service;

import java.util.Set;

import com.abc.dto.CustomerDTO;


public interface CustomerService {

	CustomerDTO saveCustomer(CustomerDTO customerDTO);
	
	CustomerDTO findCustomerById(long customerId);
	
	Set<CustomerDTO> findAllCustomers();
}
