package com.example.bankmanagement.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankmanagement.exception.UserNotFoundException;
import com.example.bankmanagement.model.LinksResponse;
import com.example.bankmanagement.model.LoanRequest;
import com.example.bankmanagement.repository.LoanRequestRepository;


@RestController
public class LoanController {

	@Autowired
	private LoanRequestRepository loanreqrepo;
	
	private final Logger Log= LoggerFactory.getLogger(LoanController.class);


	@PostMapping("/loan-request-add")
	public ResponseEntity<LoanRequest> addLoanRequests(@RequestBody @Valid LoanRequest loanreq) throws UserNotFoundException {
		loanreq.setApprovalstatus("Pending");
		LoanRequest loanRequest = loanreqrepo.save(loanreq);
		loanRequest.add(linkTo(methodOn(CustomerController.class).employee()).withRel("Go Back"));
		Log.info("Adding Loan Request");
		return new ResponseEntity<LoanRequest>(loanRequest, HttpStatus.CREATED);

	}
	@GetMapping("/loan-requests")
	public ResponseEntity<List<LoanRequest>> retreiveLoanRequests() throws UserNotFoundException {
		Log.info("Retreiving all Loan Requests");
		List<LoanRequest> all = loanreqrepo.findAll();
		for(LoanRequest loanRequest:all) {
			if(loanRequest.getApprovalstatus().equals("Pending"))
			{
				Log.info("Adding Hateoas to pending Loan Request");
				loanRequest.add(linkTo(methodOn(LoanController.class).approveLoanRequests(loanRequest.getLid())).withRel("Approve Request"));
			}
		}
		return new ResponseEntity<List<LoanRequest>>(all, HttpStatus.OK);
	}

	@GetMapping("/loan-approval-page")
	public ResponseEntity<List<LoanRequest>> retreivePendingLoanRequests() throws UserNotFoundException {
		Log.info("Retreiving all Pending Loan Requests");
		List<LoanRequest> all = loanreqrepo.findByApprovalstatus("Pending");
		for(LoanRequest loanRequest:all) {
			Log.info("Adding Hateos to pending Loan Requests");
			loanRequest.add(linkTo(methodOn(LoanController.class).approveLoanRequests(loanRequest.getLid())).withRel("Approve Request"));
		}
		return new ResponseEntity<List<LoanRequest>>(all, HttpStatus.OK);

	}

	@PutMapping("/loan-approval-page/approve/{id}")
	public ResponseEntity<LoanRequest> approveLoanRequests(@PathVariable int id) throws UserNotFoundException {
		Log.info("Finding user with ID"+id);
		Optional<LoanRequest> req = loanreqrepo.findById(id);
		if (req.isEmpty())
		{
			Log.error("User Not Found");
			throw new UserNotFoundException("Customer not found with id: " + id);
		}
		else {
			Log.info("Approving the request for the ID"+id);
			req.get().setApprovalstatus("Approved");
			loanreqrepo.save(req.get());
			LoanRequest add = req.get().add(linkTo(methodOn(CustomerController.class).employee()).withRel("Go Back"));
			return new ResponseEntity<LoanRequest>(add, HttpStatus.OK);
		}
	}

}
