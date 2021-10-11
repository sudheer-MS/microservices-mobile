package com.feigncart.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.feigncart.model.Mobile;

// this is a declarative client calling the rest API.
//In our case we are calling the mobile service

// give the name of the service to which you want to connect
@FeignClient(name = "MOBILE-SERVICE")
public interface IFeignCartProxyService {
	
	@GetMapping("/mobile-service/mobiles/brand/{brand}")
	public List<Mobile> getByBrand(@PathVariable("brand") String brand);

	@GetMapping("/mobile-service/mobiles/{mobileId}")
	public Mobile getMobileById(@PathVariable("mobileId") int mobileId);
}
