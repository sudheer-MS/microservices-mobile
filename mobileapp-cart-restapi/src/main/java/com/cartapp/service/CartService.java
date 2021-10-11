package com.cartapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cartapp.model.Cart;
import com.cartapp.model.Mobile;

@Service
public class CartService implements ICartService {
	
	private RestTemplate restTemplate;

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public static final String BASEURL =  "http://localhost:8081/mobile-service";
	
	List<Mobile> mobileItems = new ArrayList<>();
	
	Cart cart = new Cart();

	@Override
	public List<Mobile> getByBrand(String brand) {
		String url = BASEURL + "/mobiles/brand/" + brand;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		HttpHeaders headers = response.getHeaders();
		List<String> header = headers.get("desc");
		return response.getBody();
	}

	@Override
	public Mobile getMobileById(int id) {
		String url = BASEURL + "/mobiles/" + id;
		Mobile response = restTemplate.getForObject(url, Mobile.class);
		return response;
	}

	@Override
	public void addToCart(int mobileId) {
		String url = BASEURL + "/mobiles/" + mobileId;
		Mobile mobile = restTemplate.getForObject(url, Mobile.class);
		mobileItems.add(mobile);
		cart.setCartId(1);
		cart.setMobiles(mobileItems);
		cart.setQuantity(mobileItems.size());
		cart.setPrice(mobileItems.stream().mapToDouble(nMobile -> nMobile.getPrice()).sum());
	}

	@Override
	public Cart showCart() {
		return cart;
	}

}
