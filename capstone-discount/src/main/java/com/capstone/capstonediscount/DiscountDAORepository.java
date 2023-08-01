package com.capstone.capstonediscount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class DiscountDAORepository implements DiscountDAO {
	@Autowired
	JdbcTemplate jt;
	@Autowired
	DataSource ds;
	@Override
	public boolean postDiscount(Discount discount) throws SQLException {
		// TODO Auto-generated method stub
		String query = "insert into discount values (?,?,?,?)";
		int i = jt.update(query, new Object[] {discount.getD_id(), discount.getP_id(), discount.getU_id(), discount.getDiscount()});
		if(i>0)
			return true;
		else
			return false;
	}

	@Override
	public Discount getDiscount(int id) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = ds.getConnection();
		String query = "select * from discount where d_id = " + id;
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		Discount d = new Discount();
		while(rs.next())
		{
			d.setD_id(rs.getInt(1));
			d.setP_id(rs.getInt(2));
			d.setU_id(rs.getInt(3));
			d.setDiscount(rs.getDouble(4));
		}
		return d;
	}

	@Override
	public boolean deleteDiscount(int id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "delete from discount where d_id = " + id;
		int i = jt.update(query);
		if(i>0)
			return true;
		else
			return false;
	}

}
