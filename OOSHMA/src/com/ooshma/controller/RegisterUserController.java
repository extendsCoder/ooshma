package com.ooshma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ooshma.domain.Dealer;
import com.ooshma.service.RegisterUserServcie;

@RestController
@RequestMapping("/register")
public class RegisterUserController {
	
	@Autowired
	private RegisterUserServcie registerUserService;
	
	@RequestMapping(method=RequestMethod.POST, value="/dealer")
	public String registerDealer(@RequestBody Dealer dealer){
		String jsonResponse = null;
		String response = registerUserService.registerDealer(dealer);
		return jsonResponse;
	}

}
