package com.gs.poc.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gs.poc.orderservice.domain.ShippingAddress;
import com.gs.poc.orderservice.service.OrderDeliveryService;

@RestController
public class OrderDeliveryController {
	
	@Autowired
	private OrderDeliveryService orderDeliveryService;
	
	@GetMapping(path = "/orders/{order-number}/address")
	public ShippingAddress getOrder(@PathVariable("order-number") Long orderNumber){
		return orderDeliveryService.getShippingDetails(orderNumber);
	}
	
}
