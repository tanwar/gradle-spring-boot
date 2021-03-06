package com.gs.poc.orderservice.service;

import org.springframework.stereotype.Service;

import com.gs.poc.orderservice.domain.ShippingAddress;

@Service
public class OrderDeliveryService {

	// use JPA repo.

	public ShippingAddress getShippingDetails(Long orderNumber) {
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setInvoiceId(12345L);
		shippingAddress.setOrderNumber(orderNumber);
		shippingAddress.setAddress("Dwarka, Delhi");
		return shippingAddress;
	}
	
	
	
}
