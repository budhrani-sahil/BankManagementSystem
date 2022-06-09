package com.example.bankmanagement.controller;


import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.CollectionModel;
import com.example.bankmanagement.dto.DepositRequest;
import com.example.bankmanagement.model.Deposit;
import com.example.bankmanagement.service.DepositService;

@RestController
public class DepositController {
	
	@Autowired
	private DepositService depositService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/getDeposit")
	public ResponseEntity<CollectionModel<Deposit>> list() {
		
		logger.debug("Get deposit request called");
		return ResponseEntity.ok(CollectionModel.of(depositService.listAll(), 
				linkTo(methodOn(CustomerController.class).employee()).withRel("Go Back")));
		
		//return ResponseEntity.ok(depositService.listAll());
	}
	
	@PostMapping("/addDepositeRequest")
	public ResponseEntity<Deposit> saveDeposit(@RequestBody @Valid DepositRequest depositRequest)
	{
		Deposit deposit= depositService.saveDeposit(depositRequest);
		
		logger.debug("Add deposit request called");
		return new ResponseEntity<Deposit>(
				deposit.add(linkTo(methodOn(CustomerController.class).employee()).withRel((String) "Go Back")),HttpStatus.CREATED);
		
		//return new ResponseEntity<Deposit>(deposit,HttpStatus.CREATED);
	}
}
