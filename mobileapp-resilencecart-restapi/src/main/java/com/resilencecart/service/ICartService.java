package com.resilencecart.service;

import java.util.List;

import com.resilencecart.model.Cart;
import com.resilencecart.model.Mobile;



public interface ICartService {
	public List<Mobile> getByBrand(String brand);

	public Mobile getMobileById(int id);
	
	public String addToCart(int mobileId);
	
	public Cart showCart();
}
