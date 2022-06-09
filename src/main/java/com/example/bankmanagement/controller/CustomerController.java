package com.example.bankmanagement.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankmanagement.dto.CustomerRequest;
import com.example.bankmanagement.dto.DepositRequest;
import com.example.bankmanagement.exception.UserNotFoundException;
import com.example.bankmanagement.model.Customer;
import com.example.bankmanagement.model.LinksResponse;
import com.example.bankmanagement.model.LoanRequest;
import com.example.bankmanagement.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
		
	@GetMapping("/")
	public HttpEntity<LinksResponse> employee() {

			CustomerRequest customer = new CustomerRequest(null, null, null);
			DepositRequest deposit = new DepositRequest();
			LoanRequest req = new LoanRequest();

			LinksResponse linksResponse = new LinksResponse("Welcome Back");
			linksResponse.add(linkTo(methodOn(CustomerController.class).list()).withRel("view all customers"),
					linkTo(methodOn(CustomerController.class).saveCustomer(customer)).withRel("Add customers"),
					linkTo(methodOn(DepositController.class).saveDeposit(deposit)).withRel("Add deposit request"),
					linkTo(methodOn(DepositController.class).list()).withRel("view all deposit request"),
					linkTo(methodOn(LoanController.class).addLoanRequests(req)).withRel("Add Loan Request"),
					linkTo(methodOn(LoanController.class).retreivePendingLoanRequests()).withRel("Pending Loan Requests"),
					linkTo(methodOn(LoanController.class).retreiveLoanRequests()).withRel("All Loan Requests")
					);
			
			logger.debug("Home Request Called");
			return new ResponseEntity<>(linksResponse, HttpStatus.OK);
		}
	
	
	@GetMapping("/getCustomers")
	public ResponseEntity<CollectionModel<EntityModel<Customer>>> list() {
		
		List<EntityModel<Customer>> cutomerList1 = (List<EntityModel<Customer>>) StreamSupport.stream(customerService.listAll().spliterator(), false)
				.map(customer -> {
					try {
						return EntityModel.of(customer,
								linkTo(methodOn(CustomerController.class).changeStatus(customer.getId())).withRel("Change Status"));
					} catch (UserNotFoundException e) {
					
						logger.error(e.toString());
						e.printStackTrace();
					}
					return null;
				}).collect(Collectors.toList());
		
		logger.debug("Get Customer Request Called");
		return ResponseEntity.ok( 
				CollectionModel.of(cutomerList1, 
						linkTo(methodOn(CustomerController.class).employee()).withRel("Go Back")));
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid CustomerRequest customerRequest)
	{
		Customer customer = customerService.saveCustomer(customerRequest);
		logger.debug("Add Customer Request Called");
		return new ResponseEntity<Customer>(
				customer.add(linkTo(methodOn(CustomerController.class).employee()).withRel((String) "Go Back")),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) throws UserNotFoundException{
		Customer customer = customerService.getUser(id);
		if(customer!=null) {
			logger.debug("Get customer by id request called");
			return ResponseEntity.ok(customer);
		}else {
			logger.error("Customer not found with id: "+id);
			throw new UserNotFoundException("Customer not found with id: "+id);
		}
	}
	
	@PutMapping("/updateCustomer/{id}")
	  public  ResponseEntity<?> changeStatus(@PathVariable Long id) throws UserNotFoundException {
		
		try {
	        Customer existProduct = customerService.getUser(id);
	        CustomerRequest customerRequest = new CustomerRequest();
	        customerRequest.setName(existProduct.getName());
	        customerRequest.setKyc(existProduct.getKyc());
	        customerRequest.setStatus(!existProduct.getStatus());
	        customerService.saveCustomer(customerRequest);
	        existProduct.add(linkTo(methodOn(CustomerController.class).employee()).withRel("Go Back"));
	        
			logger.debug("Update customer by id request called");
	        return new ResponseEntity<>(existProduct,HttpStatus.OK);
	        //return existProduct.add(linkTo(methodOn(CustomerController.class).employee()).withRel("Go Back"));
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }     
	  }

	
	
	 
}
