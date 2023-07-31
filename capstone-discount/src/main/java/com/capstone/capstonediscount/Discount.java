package com.capstone.capstonediscount;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int d_id;
	private int p_id;
	private int u_id;
	private double discount;
	
	public Discount() {}
	
	public Discount(int d_id, int p_id, int u_id, double discount)
	{
		this.d_id = d_id;
		this.p_id = p_id;
		this.u_id = u_id;
		this.discount = discount;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
