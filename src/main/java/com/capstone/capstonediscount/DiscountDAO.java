package com.capstone.capstonediscount;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DiscountDAO {
	public boolean postDiscount(Discount discount) throws SQLException;
	public Discount getDiscount(int id) throws SQLException;
	public boolean deleteDiscount(int id) throws SQLException;
	public List<Discount> findAllByUserId(int userId);
	
}
