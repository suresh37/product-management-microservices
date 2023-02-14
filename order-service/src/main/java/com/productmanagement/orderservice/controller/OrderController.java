package com.productmanagement.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagement.orderservice.model.Order;
import com.productmanagement.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;

	@GetMapping(value = "")
	@ResponseStatus( value = HttpStatus.OK)
	public String getOrders() {
		return "";
	}
	
	@PostMapping(value = "")
	@ResponseStatus( value = HttpStatus.CREATED)
	public String placeOrder(@RequestBody Order order) {
		orderService.placeOrder(order);
		return "Order Placed Successfully";
	}

	
}
