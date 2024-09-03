package com.example.MyProject.controllers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.MyProject.Components.UserTemp;
import com.example.MyProject.Components.loginSession;
import com.example.MyProject.Repository.UserRepository;
import com.example.MyProject.Services.moneyTransfer;
import com.mysql.cj.jdbc.Blob;

@Controller
public class sessionController {
     
	private final loginSession logSes;
	private final moneyTransfer mTran;
	private final UserRepository userRep;

	public sessionController(loginSession logSes, moneyTransfer mTran, UserRepository userRep) {
		super();
		this.logSes = logSes;
		this.mTran = mTran;
		this.userRep = userRep;
	}
	
	@GetMapping("/inside")
	public String getInside(@RequestParam(required = false)
	                                      String log,
			                              Model model)
	{
		if(log!=null||logSes.getName()==null)
		{
			return "redirect:/login";
		}
		model.addAttribute("name",logSes.getName());
		
		model.addAttribute("amount",userRep.getUserByName(logSes.getName()).getAmount());
	    

		return "inside.html";
	}
	@PostMapping("/inside")
	 public String sendMoney(@RequestParam String name,
			                 @RequestParam String amount,
			                 Model model
			 )
	 {
		 List<UserTemp>arr= mTran.moneyTransfer(logSes.getName(), name,
				   BigDecimal.valueOf(Integer.parseInt(amount)));
		
		 if(arr.get(0)==null) { }
        else {
        logSes.setAmount(arr.get(0).getAmount());}
		 
     		 model.addAttribute("name",logSes.getName());
		     model.addAttribute("amount",logSes.getAmount());
		     
		return "redirect:/inside";
	 }
	@PostMapping("/upload")
	private String upload(@RequestParam("filename") MultipartFile obj,
			            Model model
			            )
	{
		try {
			BufferedReader Reader = 
					new BufferedReader(
					new InputStreamReader(
					obj.getInputStream()));
			StringBuilder str = new StringBuilder();
			String temp;
			while((temp=Reader.readLine())!=null)
			{
				str.append(temp);
			}
             			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("name",logSes.getName());
		model.addAttribute("amount",userRep.getUserByName(logSes.getName()).getAmount());
		return "redirect:/inside";
	}

	
	
}
