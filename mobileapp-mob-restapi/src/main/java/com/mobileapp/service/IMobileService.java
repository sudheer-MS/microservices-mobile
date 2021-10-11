package com.mobileapp.service;

import java.util.List;

import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;

public interface IMobileService {
	Mobile addMobile(Mobile mobile);
	void updateMobile(Mobile mobile);
	void deleteMobile(int mobileId);
	
	Mobile getById(int mobileId) throws MobileNotFoundException;
	List<Mobile> getAll();
	List<Mobile> getModel(String model)  throws MobileNotFoundException;
	List<Mobile> getByBrand(String brand) throws MobileNotFoundException;
	List<Mobile> getByLessPrice(double price) throws MobileNotFoundException;
	List<Mobile> getByModelAndBrand(String model, String brand) throws MobileNotFoundException;
}
