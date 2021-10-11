package com.feigncart.model;

import java.util.List;

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
public class Cart {
	private Integer cartId;
	private List<Mobile> mobiles;
	private int quantity;
	private double price;
}
