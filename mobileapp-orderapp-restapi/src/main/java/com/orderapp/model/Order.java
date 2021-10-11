package com.orderapp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
	private Integer orderId;
	private String city;
	private String paymentMode;
	private Cart cart;
	private double orderTotal;
}
