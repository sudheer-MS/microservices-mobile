package com.mobileapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;
import com.mobileapp.service.IMobileService;

@RestController
@RequestMapping("/mobile-service")
@Profile(value = "dev") 
public class MobileController {

	IMobileService mobileService;
	
	@Autowired
	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}
	
	@Value("${message}")
	String message;

	@GetMapping("/greet")
	String greet(){
		return message;
	}

	@PostMapping("/mobiles")
	ResponseEntity<Mobile> addMobile(@RequestBody Mobile mobile){
		Mobile addedMobile = mobileService.addMobile(mobile);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "add mobile to database");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(addedMobile);
	}

	@PutMapping("/mobiles")
	ResponseEntity<Void> updateMobile(@RequestBody Mobile mobile){
		mobileService.updateMobile(mobile);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@DeleteMapping("/mobiles/{mobileId}")
	ResponseEntity<String> deleteMobile(@PathVariable("mobileId") int mobileId){
		mobileService.deleteMobile(mobileId);
		return ResponseEntity.status(HttpStatus.OK).body("deleted succesfully");
	}
	
	@GetMapping("/mobiles/{mobileId}")
	ResponseEntity<Mobile> getMobileById(@PathVariable("mobileId") int mobileId) throws MobileNotFoundException{
		Mobile oneMobile = mobileService.getById(mobileId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting mobile by id");
		return ResponseEntity.ok().headers(headers).body(oneMobile);
	}
	
	@GetMapping("/mobiles")
	ResponseEntity<List<Mobile>> getAllMobiles(){
		List<Mobile> allMobiles = mobileService.getAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting all list of mobiles");
		return ResponseEntity.ok().headers(headers).body(allMobiles);
	}
	
	@GetMapping("/mobiles/model/{model}")
	ResponseEntity<List<Mobile>> getMobilesByModel(@PathVariable("model") String model)  throws MobileNotFoundException{
		List<Mobile> mobilesByModel = mobileService.getModel(model);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting list of mobiles by model");
		return ResponseEntity.ok().headers(headers).body(mobilesByModel);
	};
	
	@GetMapping("/mobiles/brand/{brand}")
	ResponseEntity<List<Mobile>> getByBrand(@PathVariable("brand") String brand) throws MobileNotFoundException{
		List<Mobile> mobilesByBrand = mobileService.getByBrand(brand);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting list of mobiles by brand");
		return ResponseEntity.ok().headers(headers).body(mobilesByBrand);
	};
	
	@GetMapping("/mobiles/lessprice/{price}")
	ResponseEntity<List<Mobile>> getByLessPrice(@PathVariable("price") double price) throws MobileNotFoundException{
		List<Mobile> mobilesByLessprice = mobileService.getByLessPrice(price);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting list of mobiles by less price");
		return ResponseEntity.ok().headers(headers).body(mobilesByLessprice);
	};
	
	@GetMapping("/mobiles/model/{model}/brand/{brand}")
	ResponseEntity<List<Mobile>> getByModelAndBrand(@PathVariable("model") String model, @PathVariable("brand") String brand) throws MobileNotFoundException{
		List<Mobile> mobilesByModelAndBrand = mobileService.getByModelAndBrand(model, brand);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "getting list of mobiles by model and brand");
		return ResponseEntity.ok().headers(headers).body(mobilesByModelAndBrand);
	};
	
}
