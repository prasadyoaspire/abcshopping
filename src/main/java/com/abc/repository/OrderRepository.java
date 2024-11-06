package com.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
