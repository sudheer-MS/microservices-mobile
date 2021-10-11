package com.cartapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartapp.model.Cart;
import com.cartapp.model.Mobile;
import com.cartapp.service.ICartService;

@RestController
@RequestMapping("/cart-service")
public class CartController {
	
	ICartService cartService;
	
	@Autowired
	public void setMobileService(ICartService mobileService) {
		this.cartService = mobileService;
	}

	@GetMapping("/cart/mobiles/{mobileId}")
	ResponseEntity<Mobile> showMobile(@PathVariable("mobileId") int mobileId){
		Mobile oneMobile = cartService.getMobileById(mobileId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get mobile in cart by id");
		return ResponseEntity.ok().headers(headers).body(oneMobile);
	}
	
	@GetMapping("/cart/mobiles/brand/{brand}")
	ResponseEntity<List<Mobile>> getMobilesByBrand(@PathVariable("brand") String brand){
		List<Mobile> mobilesByBrand = cartService.getByBrand(brand);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get list of mobiles in cart by brand");
		return ResponseEntity.ok().headers(headers).body(mobilesByBrand);
	}
	
	@GetMapping("/cart/add-cart/mobileId/{mobileId}")
	ResponseEntity<String> addToCart(@PathVariable("mobileId") int mobileId) {
		cartService.addToCart(mobileId);
		return ResponseEntity.ok().body("mobile added to cart");
	}
	
	@GetMapping("/cart/view-cart")
	ResponseEntity<Cart>  viewCart(@RequestHeader("desc") String header) {
		System.out.println("header: " + header);
		Cart cart = cartService.showCart();
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "get cart details");
		return ResponseEntity.ok().headers(headers).body(cart);
	}
	
}
