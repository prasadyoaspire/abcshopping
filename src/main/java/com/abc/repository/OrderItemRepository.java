package com.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
