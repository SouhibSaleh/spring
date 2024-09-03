package com.example.MyProject.Repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.MyProject.Components.UserRowMapper;
import com.example.MyProject.Components.UserTemp;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;

	public UserRepository(JdbcTemplate jdbc) {
		super();
		this.jdbc = jdbc;
	}
	public List<UserTemp> getAll()
	{
		String sql = "select * from account";
		return jdbc.query(sql,new UserRowMapper());
	}
    public UserTemp getUserById(int id)
    {
    	String sql = "select *from account where id = ?";
    	return jdbc.query(sql, new UserRowMapper(),id).get(0);
    }
    public void addUser(UserTemp temp)
    {
        String sql = 
        "insert into account (id,name,amount,password) values (?,?,?,?)";
        jdbc.update(sql,temp.getId(),temp.getName(),temp.getAmount(),temp.getPassword());
    }
    public void deleteUser(int id)
    {
      String sql="DELETE FROM account WHERE id = ?";
      jdbc.update(sql,id);
    	
    }
    public UserTemp getUserByName(String name)
    {
    	String sql = "select *from account where name = ?";
           if(jdbc.query(sql,
                   new UserRowMapper(),
                   name).isEmpty())return new UserTemp(0,"",BigDecimal.ZERO,null);
           
             return jdbc.query(sql,
               new UserRowMapper(),
              name).get(0);
    
    }
    public int getSize()
    {
    	String sql = "select * from account";
		return jdbc.query(sql,new UserRowMapper()).size();
    }
	public void changeAmount(long id,BigDecimal amount)
	{   
		String sql="update account set amount = ? where id = ?";
		jdbc.update(sql,amount,id);
	}
}












