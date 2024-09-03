package com.example.bookfourth;


import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentsController {
   
	private static Logger logger = 
			  Logger.getLogger(PaymentsController.class.getName());
	private int Counter=0;
   
	@PostMapping("/payment")
	private ResponseEntity<Payment> createPayment(
			@RequestHeader String requestID,
			@RequestBody Payment myRequest
			)
	  {
		 System.out.println("|"+Counter++ +"| "
      		   +requestID+" "+myRequest);  				
		myRequest.setID(UUID.randomUUID().toString()+"meow");
		myRequest.setAmount(myRequest.getAmount()*10);
		return ResponseEntity
				.status(HttpStatus.OK)
				.header("requestID", requestID)
				.body(myRequest);
	  }
	
	
}
