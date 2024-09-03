package com.example.MyProject.Components;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class UserRowMapper implements RowMapper<UserTemp>{

	@Override
	public UserTemp mapRow(ResultSet rs, int rowNum) throws SQLException {
	   UserTemp temp = new UserTemp();
	   temp.setId(rs.getInt("id"));
	   temp.setName(rs.getString("name"));
	   temp.setAmount(rs.getBigDecimal("amount"));
	   temp.setPassword(rs.getString("password"));
	   return temp;
	}

}
