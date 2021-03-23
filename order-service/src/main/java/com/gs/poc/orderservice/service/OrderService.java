package com.gs.poc.orderservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gs.poc.orderservice.domain.Order;


@Service
public class OrderService {
	
	@Value("${order.history-days}")
	private Integer historyDays;
	
	@Value("${order.page-size}")
	private Integer pageSize;

	// use JPA repo.

	public List<Order> getOrders(LocalDate startDate, LocalDate endDate) {
		return getOrdersFromRepo(startDate, endDate);
	}

	public Order getOrder(Long orderNumber) {
		LocalDate currentDate = new LocalDate();
		List<Order> orders = getOrdersFromRepo(currentDate.minusYears(historyDays).minusDays(1), currentDate);
		Optional<Order> orderOptional = orders.stream().filter(order -> orderNumber.equals(order.getOrderNumber()))
				.findFirst();
		return orderOptional.orElse(new Order()); // ideally throw 404.
	}
	
	// Method to be replaced with repo call
	private List<Order> getOrdersFromRepo(LocalDate startDate, LocalDate endDate) {

		List<Order> orders = new ArrayList<>();
		LocalDate currentDate = new LocalDate();
		orders.add(createOrder(112L, "Pampers", 550.0, currentDate.minusDays(historyDays)));
		orders.add(createOrder(113L, "Baby Shampoo", 350.0, currentDate.minusWeeks(historyDays)));
		orders.add(createOrder(114L, "Baby Soap", 150.0, currentDate.minusMonths(historyDays)));
		orders.add(createOrder(115L, "Stroller", 10250.0, currentDate.minusYears(historyDays)));
		orders.add(createOrder(116L, "Car Seat", 6250.0, currentDate));
		List<Order> filteredOrders = orders.stream()
				.filter(order -> startDate.isBefore(order.getOrderDate()) && endDate.isAfter(order.getOrderDate()))
				.collect(Collectors.toList());

		return Collections.unmodifiableList(filteredOrders.subList(0, pageSize));
	}

	private Order createOrder(Long orderNumber, String productName, Double price, LocalDate orderDate) {
		Order order = new Order();
		order.setOrderNumber(orderNumber);
		order.setProduct(productName);
		order.setPrice(price);
		order.setOrderDate(orderDate);
		return order;
	}

}
