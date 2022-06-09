package com.example.bankmanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankmanagement.dto.DepositRequest;
import com.example.bankmanagement.model.Deposit;
import com.example.bankmanagement.repository.DepositRepository;

@Service
@Transactional
public class DepositService {

	@Autowired
	private DepositRepository depositRepository;
	
	public List<Deposit> listAll() {
		return depositRepository.findAll();
	}

	public Deposit saveDeposit(DepositRequest depositRequest) {
		Deposit deposit= new Deposit(0,depositRequest.getAccountNo(), depositRequest.getAmount());
		return depositRepository.save(deposit);
	}
}
