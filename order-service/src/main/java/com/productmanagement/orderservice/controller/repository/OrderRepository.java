package com.productmanagement.orderservice.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productmanagement.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
