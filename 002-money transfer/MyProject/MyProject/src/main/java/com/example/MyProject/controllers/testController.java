package com.example.MyProject.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.MyProject.Components.UserTemp;
import com.example.MyProject.Repository.UserRepository;

@Controller("/")
public class testController {

	private final UserRepository RepTemp;
	
	public testController(UserRepository repTemp) {
		super();
		RepTemp = repTemp;
	}
     
	@ResponseBody
	@GetMapping("/getall")
	public List<UserTemp> getAll()
	{ 
	   return RepTemp.getAll();
	}
}
