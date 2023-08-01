package com.capstone.capstonediscount;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discount")
public class DiscountController {
	@Autowired
	DiscountDAO repo;
	
	@PostMapping("/post")
	public String postDiscount(@RequestBody Discount discount) throws SQLException
	{
		if(repo.postDiscount(discount))
			return "Discount Posted";
		else {
			return "Discount Not Posted";
		}
	}
	
	@GetMapping("/get/{id}")
	public Discount getDiscount(@PathVariable int id) throws SQLException
	{
		return repo.getDiscount(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteDiscount(@PathVariable int id) throws SQLException
	{
		if(repo.deleteDiscount(id))
			return "Discount Deleted";
		else
			return "Discount Not Deleted";
	}
}
