package com.abc.service;

import java.time.LocalDate;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.dto.CustomerDTO;
import com.abc.dto.OrderDTO;
import com.abc.dto.OrderItemDTO;
import com.abc.dto.ProductDTO;
import com.abc.entity.Customer;
import com.abc.entity.Order;
import com.abc.entity.OrderItem;
import com.abc.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OrderDTO saveOrder(long customerId, OrderDTO orderDTO) {

		CustomerDTO customerDTO = customerService.findCustomerById(customerId);
		Customer customer = modelMapper.map(customerDTO, Customer.class);

		Order order = modelMapper.map(orderDTO, Order.class);
		order.setCustomer(customer);

		Set<OrderItemDTO> orderItems = orderDTO.getOrderItems();

		double itemTotal = 0;
		double orderAmount = 0;

		for (OrderItemDTO orderItemDTO : orderItems) {
			long productId = orderItemDTO.getProductId();
			ProductDTO productDTO = productService.findProductById(productId);
			itemTotal = productDTO.getProductPrice() * orderItemDTO.getQuantity();

			// calculate item total
			orderItemDTO.setItemTotal(itemTotal);

			OrderItem orderItem = modelMapper.map(orderItemDTO, OrderItem.class);
			orderItem.setOrder(order);

			orderAmount = orderAmount + itemTotal;
		}

		orderDTO.setOrderAmount(orderAmount);
		orderDTO.setOrderDate(LocalDate.now());
		orderDTO.setOrderStatus("Success");

		orderRepository.save(order);

		return modelMapper.map(order, OrderDTO.class);
	}

	@Override
	public OrderDTO findOrderById(long orderId) {

		return null;
	}

	@Override
	public Set<OrderDTO> findAllOrdersByCustomer(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
