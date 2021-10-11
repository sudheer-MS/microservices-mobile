package com.feigncart.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feigncart.model.Cart;
import com.feigncart.model.Mobile;
import com.feigncart.service.IFeignCartProxyService;

// feign included

@RestController
@RequestMapping("/feign-cart-service")
public class FeignCartController {
	
	IFeignCartProxyService cartService;
	private List<Mobile> mobileItems = new ArrayList<>();
	private Cart cart = new Cart();
	
	@Autowired
	public void setMobileService(IFeignCartProxyService mobileService) {
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
		Mobile mobile = cartService.getMobileById(mobileId);
		mobileItems.add(mobile);
		cart.setCartId(1);
		cart.setMobiles(mobileItems);
		cart.setQuantity(mobileItems.size());
		cart.setPrice(mobileItems.stream().mapToDouble(nMobile -> nMobile.getPrice()).sum());
		return ResponseEntity.ok().body("mobile added to cart");
	}
	
	@GetMapping("/cart/view-cart")
	Cart viewCart() {
		return cart;
	}
	
}
