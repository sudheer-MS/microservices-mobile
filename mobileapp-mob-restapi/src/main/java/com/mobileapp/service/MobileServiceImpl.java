package com.mobileapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileapp.exceptions.MobileNotFoundException;
import com.mobileapp.model.Mobile;
import com.mobileapp.repository.IMobileRepository;

@Service
public class MobileServiceImpl implements IMobileService {
	

	IMobileRepository mobileRepository;

	@Autowired
	public void setMobileRepository(IMobileRepository mobileRepository) {
		this.mobileRepository = mobileRepository;
	}

	@Override
	public Mobile addMobile(Mobile mobile) {
		return mobileRepository.save(mobile);
	}

	@Override
	public void updateMobile(Mobile mobile) {
		mobileRepository.save(mobile);
	}

	@Override
	public void deleteMobile(int mobileId) {
		mobileRepository.deleteById(mobileId);
	}

	@Override
	public Mobile getById(int mobileId) throws MobileNotFoundException {
		return mobileRepository.findById(mobileId).orElseThrow(() -> new MobileNotFoundException("no mobile found with the given ID"));
	}

	@Override
	public List<Mobile> getAll() {
		return mobileRepository.findAll();
	}

	@Override
	public List<Mobile> getModel(String model) throws MobileNotFoundException {
		List<Mobile> mobilesListByModel = mobileRepository.findByModel(model);
		if(mobilesListByModel.isEmpty()) {
			throw new MobileNotFoundException("no mobiles found for the given model");
		}
		return mobilesListByModel;
	}

	@Override
	public List<Mobile> getByBrand(String brand) throws MobileNotFoundException {
		List<Mobile> mobilesListByBrand = mobileRepository.findByBrand(brand);
		if(mobilesListByBrand.isEmpty()) {
			throw new MobileNotFoundException("no mobiles found for the given brand");
		}
		return mobilesListByBrand;
	}

	@Override
	public List<Mobile> getByLessPrice(double price) throws MobileNotFoundException {
		List<Mobile> mobilesByLessprice = mobileRepository.findByPriceLessThan(price);
		if(mobilesByLessprice.isEmpty()) {
			throw new MobileNotFoundException("no mobiles found for the given price");
		}
		return mobilesByLessprice;
	}

	@Override
	public List<Mobile> getByModelAndBrand(String model, String brand) throws MobileNotFoundException {
		List<Mobile> mobilesByModelAndBrand =  mobileRepository.findByModelAndBrand(model, brand);
		if(mobilesByModelAndBrand.isEmpty()) {
			throw new MobileNotFoundException("no mobiles found for the given model and brand");
		}
		return mobilesByModelAndBrand;
	}

}
