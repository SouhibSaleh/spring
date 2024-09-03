package com.example.dolist.Repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dolist.Components.Task;
import com.example.dolist.Components.TasksRowMapper;

@Repository
public class TasksRepository {
	  private final JdbcTemplate jdbc;

	  public TasksRepository(JdbcTemplate jdbc) {
			super();
			this.jdbc = jdbc;
		}
	  public List<Task>getAll()
	  {
		  String sql = "select *from task";
		  return jdbc.query(sql,new TasksRowMapper());
	  }
	  public void add(Task temp)
	  {
		  String sql = 
		"insert into task (id,description,date,status) values (?,?,?,?)";
		  jdbc.update(sql,temp.getId(),temp.getDescription(),temp.getDate(),temp.isStatus());
	  }
	 public void delete(Task temp)
	 {
		 String sql=
			"delete from task where id = ?";
		 jdbc.update(sql,temp.getId());
	 }
     public void updateCheckbox(Task temp,boolean flag)
     {
    	 System.out.println(flag);
    	 String sql =
    				"update task set status = ? where id = ?";
    	
    		 jdbc.update(sql,flag,
    			     temp.getId());
    	
     }
}
