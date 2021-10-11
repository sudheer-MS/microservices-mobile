package com.resilencecart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.resilencecart.model.Cart;
import com.resilencecart.model.Mobile;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CartService implements ICartService {

	private RestTemplate restTemplate;

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public static final String BASEURL = "http://localhost:8081/mobile-service";

	List<Mobile> mobileItems = new ArrayList<>();

	Cart cart = new Cart();

	@Override
	@CircuitBreaker(name = "CART_SERVICE", fallbackMethod = "fallbackGetByBrand")
	public List<Mobile> getByBrand(String brand) {
		String url = BASEURL + "/mobiles/brand/" + brand;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		HttpHeaders headers = response.getHeaders();
		List<String> header = headers.get("desc");
		return response.getBody();
	}

	// This the fallback method of getBrand. This will be called automatically by
	// Resilence
	public List<Mobile> fallbackGetByBrand(String brand, RuntimeException e) {
		return new ArrayList<>();
	}

	@Override
	@CircuitBreaker(name = "CART_SERVICE", fallbackMethod = "fallbackGetMobileById")
	public Mobile getMobileById(int id) {
		String url = BASEURL + "/mobiles/" + id;
		Mobile response = restTemplate.getForObject(url, Mobile.class);
		return response;
	}

	// This the fallback method of get by id.
	public Mobile fallbackGetMobileById(int id, RuntimeException e) {
		return new Mobile(0, "no mobile available", "no mobile available", 0);
	}

	@Override
	@CircuitBreaker(name = "CART_SERVICE", fallbackMethod = "fallbackaddToCart")
	public String addToCart(int mobileId) {
		String url = BASEURL + "/mobiles/" + mobileId;
		Mobile mobile = restTemplate.getForObject(url, Mobile.class);
		mobileItems.add(mobile);
		cart.setCartId(1);
		cart.setMobiles(mobileItems);
		cart.setQuantity(mobileItems.size());
		cart.setPrice(mobileItems.stream().mapToDouble(nMobile -> nMobile.getPrice()).sum());
		return "mobile added to cart";
	}

	// This the fallback method of add to cart.
	public String fallbackaddToCart(int id, RuntimeException e) {
		cart = null;
		return "not added to cart";
	}

	@Override
	public Cart showCart() {
		return cart;
	}

}
