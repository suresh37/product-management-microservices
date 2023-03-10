package com.productmanagement.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.Tracer.SpanInScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.productmanagement.orderservice.event.OrderPlacedEvent;
import com.productmanagement.orderservice.model.InventoryDto;
import com.productmanagement.orderservice.model.Order;
import com.productmanagement.orderservice.model.OrderLineItem;
import com.productmanagement.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
//@Slf4j
public class OrderService {

	private final OrderRepository orderRepo;
	private final WebClient.Builder webClientBuilder;
	private Tracer tracer;
	private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

	public String placeOrder(Order order) {
//		try {
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItem> list = order.getOrderLineItemList().stream().map(this::mapToOrderLineItems).toList();
		order.setOrderLineItemList(list);

		List<String> skuCodeList = list.stream().map(OrderLineItem::getSkuCode).toList();

//		Span inventoryServiceLookup = tracer.nextSpan().name("inventoryServiceLookup");
//		
//		try(SpanInScope spainInSpoce = tracer.withSpan(inventoryServiceLookup)){
		// Check isInStock
		InventoryDto[] inv = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory/isinstock",
						builder -> builder.queryParam("skuCode", skuCodeList).build())
				.retrieve().bodyToMono(InventoryDto[].class).block();

		System.out.println("allInStock");
		boolean allProductsInStock = Arrays.stream(inv).peek(d -> System.out.println(d.toString()))
				.allMatch(InventoryDto::getIsInStock);
		if (allProductsInStock) {
			orderRepo.save(order);
			kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
			return "Order Placed Successfully";
		} else {
			throw new IllegalArgumentException("Order Line Items not in Stock");
		}
//		}
//		finally {
//			inventoryServiceLookup.end();
//		}

//		} catch (Exception e) {
//			System.out.println("Exception while placing order: " + e.getMessage());
//		}
	}

	private OrderLineItem mapToOrderLineItems(OrderLineItem item) {
		OrderLineItem build = OrderLineItem.builder().skuCode(item.getSkuCode()).price(item.getPrice())
				.quantity(item.getQuantity()).build();
		return build;
	}

}
