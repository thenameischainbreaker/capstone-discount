package com.capstone.capstonediscount;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountJPARepository extends JpaRepository<Discount, Integer>{
	List<Discount> findAllByUserId(int userId);
}
