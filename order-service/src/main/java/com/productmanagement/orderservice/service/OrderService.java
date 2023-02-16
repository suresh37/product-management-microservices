package com.productmanagement.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.productmanagement.orderservice.model.InventoryDto;
import com.productmanagement.orderservice.model.Order;
import com.productmanagement.orderservice.model.OrderLineItem;
import com.productmanagement.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderRepository orderRepo;
	@Autowired
	WebClient webClient;

	public void placeOrder(Order order) {
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItem> list = order.getOrderLineItemList().stream().map(this::mapToOrderLineItems).toList();
		order.setOrderLineItemList(list);

		List<String> skuCodeList = list.stream().map(OrderLineItem::getSkuCode).toList();

		// Check isInStock
		InventoryDto[] inv = webClient.get().uri(builder -> builder.path("http://localhost:8082/api/inventory")
				.queryParam("skuCode", skuCodeList).build()).retrieve().bodyToMono(InventoryDto[].class).block();

		boolean allInStock = Arrays.stream(inv).peek(d -> System.out.println(d.toString()))
				.allMatch(InventoryDto::getIsInStock);
		if (allInStock)
			orderRepo.save(order);
		else {
			throw new RuntimeException("Order Line Items not in Stock");
		}
	}

	private OrderLineItem mapToOrderLineItems(OrderLineItem item) {
		OrderLineItem build = OrderLineItem.builder().skuCode(item.getSkuCode()).price(item.getPrice())
				.quantity(item.getQuantity()).build();
		return build;
	}

}
