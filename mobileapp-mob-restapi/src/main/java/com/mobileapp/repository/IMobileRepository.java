package com.mobileapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobileapp.model.Mobile;

@Repository
public interface IMobileRepository extends JpaRepository<Mobile, Integer> {
	
	List<Mobile> findByModel(String brand);

	List<Mobile> findByBrand(String brand);

	List<Mobile> findByPriceLessThan(double price);

	List<Mobile> findByModelAndBrand(String model, String brand);
	
}
