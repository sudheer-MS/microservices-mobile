package com.orderapp.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderapp.model.Cart;
import com.orderapp.model.Mobile;
import com.orderapp.model.Order;

@Service
public class OrderService implements IOrderService{
	
	private RestTemplate restTemplate;

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public static final String BASEURL =  "http://sudheerms:8082/cart-service";
	
	List<Mobile> mobileItems = new ArrayList<>();
	
	Order order = new Order();
	
	@Override
	public Order viewOrderDetails() {
		String url = BASEURL + "/cart/view-cart";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("desc", "adding cart details...");
		
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		
		ResponseEntity<Cart> response = restTemplate.exchange(url, HttpMethod.GET, entity, Cart.class);
		Cart oneCart = response.getBody();
		order.setOrderId(1);
		order.setCart(oneCart);
		order.setCity("hyd");
		order.setPaymentMode("debit card");
		order.setOrderTotal(oneCart.getMobiles().stream().mapToDouble(nMobile -> nMobile.getPrice()).sum());
		return order;
	}

}
