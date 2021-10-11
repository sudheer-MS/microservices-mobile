package com.orderapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderapp.model.Order;
import com.orderapp.service.IOrderService;

@RestController
@RequestMapping("/order-service")
public class OrderController {
	
	IOrderService orderService;

	@Autowired
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/orders/view-order")
	public Order viewOrderDetails(@RequestHeader("desc") String header) {
		System.out.println("headers: " + header);
		return orderService.viewOrderDetails();
	}
	
	@GetMapping("/orders/pay")
	public String proceedToPay() {
		return "payment successful";
	}
	
}
