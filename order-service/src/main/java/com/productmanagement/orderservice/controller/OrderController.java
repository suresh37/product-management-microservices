package com.productmanagement.orderservice.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagement.orderservice.model.Order;
import com.productmanagement.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping(value = "")
	@ResponseStatus(value = HttpStatus.OK)
	public String getOrders() {
		return "Get Orders Response";
	}

	@PostMapping(value = "")
	@ResponseStatus(value = HttpStatus.CREATED)
//	@CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//	@TimeLimiter(name = "inventory") // CompletableFuture<String>
//	@Retry(name ="inventory")
	public String placeOrder(@RequestBody Order order) {
		// return CompletableFuture.supplyAsync(() -> orderService.placeOrder(order));
		return orderService.placeOrder(order);
	}

	public String fallbackMethod(Order order, RuntimeException runtimeException) {
		// return CompletableFuture.supplyAsync(() -> "Something went wrong. Please
		// order after some time!");
		return "Something went wrong. Please order after some time!";
	}

}
