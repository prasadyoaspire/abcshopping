package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.dto.OrderDTO;
import com.abc.service.OrderService;

@CrossOrigin(value = "http://localhost:4200/")
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/{customerId}")
	public ResponseEntity<OrderDTO> placeOrder(@PathVariable long customerId,@RequestBody OrderDTO orderDTO) {
		
		orderDTO = orderService.saveOrder(customerId, orderDTO);
		
		return new ResponseEntity<>(orderDTO,HttpStatus.CREATED);		
	}
}
