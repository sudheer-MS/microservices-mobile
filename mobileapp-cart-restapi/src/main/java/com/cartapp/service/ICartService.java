package com.cartapp.service;

import java.util.List;

import com.cartapp.model.Cart;
import com.cartapp.model.Mobile;

public interface ICartService {
	public List<Mobile> getByBrand(String brand);

	public Mobile getMobileById(int id);
	
	public void addToCart(int mobileId);
	
	public Cart showCart();
}
