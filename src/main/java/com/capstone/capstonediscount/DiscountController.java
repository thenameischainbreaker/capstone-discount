package com.capstone.capstonediscount;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capstone.capstonediscount.Admin;
import com.capstone.capstonediscount.UserRole;

@RestController
@RequestMapping("/discount")
@CrossOrigin(origins = {"https://domainofchain.s3.us-east-2.amazonaws.com","http://localhost:4200/"})
public class DiscountController {
	@Autowired
	DiscountDAO repo;
	@Value("${gateway-host}")
	private String gatewayHost;
	
	@PostMapping("/post")
	//check if loggedin and admin
	public String postDiscount(@RequestHeader("googleBearerToken") String headerValue, @RequestBody Discount discount)
	{
		try {
			RestTemplate restTemplate = new RestTemplate(); 
			  String url = "http://" + gatewayHost + "/user/getUserRole";
		//	  System.out.println("gatewayHost: " +gatewayHost);
			  UserRole response = restTemplate.postForObject(url, headerValue, UserRole.class); 
			if(response.getRole() == Admin.FALSE)
				throw new SecurityException();
			
			
			
			
			if(repo.postDiscount(discount))
				return "Discount Posted";
			else {
				return "Discount Not Posted";
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error in posting discount. Check permissions.";
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error in posting discount.";
		}
	}
	
	@GetMapping("/getById/{id}")
	//check if logged in and admin
	public Discount getDiscount(@PathVariable int id) 
	{
		try {
			/*
			 * RestTemplate restTemplate = new RestTemplate(); String url = "http://" +
			 * gatewayHost + "/user/getUserRole";
			 * 
			 * UserRole response = restTemplate.postForObject(url, headerValue,
			 * UserRole.class); if(response.getRole() == Admin.FALSE) throw new
			 * SecurityException();
			 */
			
			return repo.getDiscount(id);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteDiscount(@PathVariable int discount_id) 
	{
		/*
		 * //user can only delete if discount_user_id same as user_id //if admin okay to
		 * delete any try { RestTemplate restTemplate = new RestTemplate(); String url =
		 * "http://" + gatewayHost + "/user/getUserRole"; //
		 * System.out.println("gatewayHost: " +gatewayHost); UserRole response =
		 * restTemplate.postForObject(url, headerValue, UserRole.class); int user_id =
		 * response.getUserId(); int discount_user_id =
		 * repo.getDiscount(discount_id).getUserId(); //if user_id not equal to id on
		 * discount, and not admin throw securityexzception if(user_id!=discount_user_id
		 * && response.getRole() == Admin.FALSE) throw new SecurityException();
		 */
		try {
			if(repo.deleteDiscount(discount_id))
				return "Discount Deleted";
			else
				return "Discount Not Deleted";
		}
		    catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error in deleting discount. Check permissions or if discount matches your user_id.";
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error in deleting discount";
		}
	}
	@GetMapping("/findAllByUserId")
	public List<Discount> findAllByUserId(@RequestParam (name = "userId") int userId) {
		return repo.findAllByUserId(userId);
	}
	
	
}
