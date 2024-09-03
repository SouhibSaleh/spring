package com.example.dolist.Components;

import java.sql.Date;

public class Task {
	private int id;
	private String description;
	private Date date;
	private boolean status = false;
	public Task()
	{
		
	}
	public Task(int id, String description, Date date, boolean status) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", date=" + date + ", status=" + status + "]";
	}


	

}
