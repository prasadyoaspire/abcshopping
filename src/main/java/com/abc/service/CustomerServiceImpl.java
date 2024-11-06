package com.abc.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.dto.CustomerDTO;
import com.abc.entity.Customer;
import com.abc.exception.ResourceNotFoundException;
import com.abc.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
		
		Customer customer = modelMapper.map(customerDTO, Customer.class);
		
		customerRepository.save(customer);
		
		customerDTO = modelMapper.map(customer, CustomerDTO.class);		
		return customerDTO;
	}

	@Override
	public CustomerDTO findCustomerById(long customerId) {

		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer not found with id: " + customerId);
		}
		Customer customer = optionalCustomer.get();
		
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
		return customerDTO;
	}

	@Override
	public Set<CustomerDTO> findAllCustomers() {
		
		logger.info("starts fetchAllCustomers Service - CustomerServiceImpl");
		
		List<Customer> customers = customerRepository.findAll();
		
		logger.info("ends fetchAllCustomers Service - CustomerServiceImpl");
		return customers.stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).collect(Collectors.toSet());
	}

}
