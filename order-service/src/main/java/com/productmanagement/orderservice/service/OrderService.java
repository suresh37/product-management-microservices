package com.productmanagement.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.productmanagement.orderservice.model.Order;
import com.productmanagement.orderservice.model.OrderLineItem;
import com.productmanagement.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepo;
	

	public void placeOrder(Order order) {
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItem> list = order.getOrderLineItemList().stream().map(this::mapToOrderLineItems).toList();
		order.setOrderLineItemList(list);
		orderRepo.save(order);
	}
	
	private OrderLineItem mapToOrderLineItems(OrderLineItem item){
		OrderLineItem build = OrderLineItem.builder().skuCode(item.getSkuCode()).price(item.getPrice()).quantity(item.getQuantity()).build();
		return build;
	}

}
