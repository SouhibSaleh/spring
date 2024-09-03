package com.example.dolist.Components;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TasksRowMapper implements RowMapper<Task>{

	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task temp =  new Task();
		temp.setId(rs.getInt("id"));
		temp.setDescription(rs.getString("description"));
		temp.setDate(rs.getDate("date"));
		temp.setStatus(rs.getBoolean("status"));
		return temp;
	}

}
