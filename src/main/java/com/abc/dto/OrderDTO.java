package com.abc.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private long id;
	private double orderAmount;
	private LocalDate orderDate;
	private String orderStatus;
	private Set<OrderItemDTO> orderItems;
}
