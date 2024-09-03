package com.example.MyProject.Components;

import java.math.BigDecimal;

public class UserTemp {
      private int id;
      private String name;
      private BigDecimal amount;
      private String password;
      public UserTemp() {}
	public UserTemp(int id, String name, BigDecimal amount, String password) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserTemp [id=" + id + ", name=" + name + ", amount=" + amount + ", password=" + password + "]";
	}

      
}
