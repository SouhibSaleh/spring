package com.example.MyProject.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.MyProject.Components.UserTemp;
import com.example.MyProject.Components.userLogin;
import com.example.MyProject.Repository.UserRepository;

@Controller
public class loginController {

	private final userLogin loginTemp;
    private final UserRepository UserRep;
	
	public loginController(userLogin loginTemp, UserRepository userRep) {
		super();
		this.loginTemp = loginTemp;
		UserRep = userRep;
	}
	@PostMapping("/login")
	public String loginPost(@RequestParam String name,
			                @RequestParam String pass,
			          Model model)
	{
       UserTemp uTemp = UserRep.getUserByName(name);
            String ss = uTemp.getPassword();  
      if(name!=""&&pass!=""&&uTemp.getId()!=0&&ss!=null&&ss.equals(pass)) {
    	  loginTemp.setPassWord(pass);
	      loginTemp.setUserName(name);
	      loginTemp.getLogSes().setName(name);
	      loginTemp.getLogSes().setId(uTemp.getId());
	      loginTemp.getLogSes().setAmount(uTemp.getAmount());
	      
	      model.addAttribute("name",name);
	      
    	  return "redirect:/inside";
      }
      else if(ss!=null&&!uTemp.getPassword().equals(uTemp)){ 
    	  model.addAttribute("errorone","wrong Password!");
      }
      else  model.addAttribute("errorone","wrong Username or Password!");
    	  return "redirect:/login";
      
	}
    
	
	@GetMapping("/login")
	public String loginGet()
	{
		return "login.html";
	}
	
	
	
	@PostMapping("/signup")
	public String signupPost(@RequestParam String name,
			                @RequestParam String pass,
			          Model model)
	{
       UserRep.addUser(new UserTemp(UserRep.getSize()+1,
    		                         name,	
    		                         BigDecimal.ZERO,pass));

	    return "redirect:/inside";
	}

	
	@GetMapping("/signup")
	public String signupGet()
	{
		return "redirect:/signup";
	}

	
}
















