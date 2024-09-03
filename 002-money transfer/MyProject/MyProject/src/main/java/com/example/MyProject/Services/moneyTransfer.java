package com.example.MyProject.Services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.example.MyProject.Components.UserTemp;
import com.example.MyProject.Repository.UserRepository;

@Service
public class moneyTransfer {
       private final UserRepository userRep;

	public moneyTransfer(UserRepository userRep) {
		super();
		this.userRep = userRep;
	
	}
	
	
	@Transactional
	public List<UserTemp> moneyTransfer(String sender,String receiver,BigDecimal amount)
	{
		UserTemp sTemp= userRep.getUserByName(sender);
		if(sTemp.getId()==0)return Arrays.asList(null,null);
	     	UserTemp rTemp= userRep.getUserByName(receiver);
	    if(rTemp.getId()==0)return Arrays.asList(null,null);;
	    userRep.changeAmount(sTemp.getId(), sTemp.getAmount().subtract(amount));
	    userRep.changeAmount(rTemp.getId(), rTemp.getAmount().add(amount));
	    System.out.println(sTemp+" || "+rTemp);

	    return Arrays.asList(sTemp,rTemp);
	}
       
}
