package com.senthamil.ecommerce.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.senthamil.ecommerce.model.User;
import com.senthamil.ecommerce.util.ConnectionUtil;

public class UserDAO {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(User user) {
		String sql = "insert into users(name,email_id,password, mobile_no) values(?,?,?,?)";
		Object[] args = { user.getName(), user.getEmailId(), user.getPassword(), user.getMobileNo() };
		int rows = jdbcTemplate.update(sql, args);
		System.out.println("number of row inserted " + rows);
	}

	public void saveOrder(int userId, String productName, Integer quantity) {
		String sql = "insert into orders(user_id, product_name, quantity) values ( ? , ?, ?)";
		Object[] args = { userId, productName, quantity };
		int rows = jdbcTemplate.update(sql, args);
		System.out.println("number of row inserted " + rows);
	}

	public User validate(String emailID, String password) {
		User user = null;
		try {
			String sql = "select id,name,password from users where email_id=? and password=?";
			Object[] args = { emailID, password };
			user = jdbcTemplate.queryForObject(sql, args, (rse, rowNo) -> {
				User u = new User();
				u.setId(rse.getInt("id"));
				u.setName(rse.getString("name"));
				u.setPassword(rse.getString("password"));
				return u;
			});

		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		}
		return user;
	}
}
