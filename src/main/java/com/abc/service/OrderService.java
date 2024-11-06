package com.abc.service;

import java.util.Set;

import com.abc.dto.OrderDTO;

public interface OrderService {

	OrderDTO saveOrder(long customerId, OrderDTO orderDTO);
	
	OrderDTO findOrderById(long orderId);
	
	Set<OrderDTO> findAllOrdersByCustomer(long customerId);
}
