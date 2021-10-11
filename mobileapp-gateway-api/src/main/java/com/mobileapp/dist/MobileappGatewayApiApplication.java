package com.mobileapp.dist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class MobileappGatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileappGatewayApiApplication.class, args);
	}
	
//	@Bean
//	public RouteLocator customRoutes(RouteLocatorBuilder builder) {
//		return builder.routes().build();
//		
//		return builder.routes()
//				// create individual routes with path, URI and filters
//				// 1st route
//				.route("mobileService", r->
//					r.path("/mobile-service/**")
//					 .uri("lb://MOBILE-SERVICE"))
//				// 2nd route
//				.route("cartService", r->
//					r.path("/cart-service/**")
//					 .filters(f->f.addRequestHeader("desc", "Adding items to cart")
//						 	  .addResponseHeader("result", "Showing Cart details"))
//					 .uri("lb://CART-SERVICE"))
//				// 3rd route
//				.route("orderService", r->
//					r.path("/order-service/**")
//					 .filters(f->f.addRequestHeader("desc", "Show Order Details")
//						 	      .addResponseHeader("result", "Sending Order Details"))
//					 .uri("lb://ORDER-SERVICE"))
//				.build();
//	}

}
