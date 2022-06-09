package com.example.bankmanagement.dto;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;
import com.example.bankmanagement.model.Deposit;


public class DepositRequest extends RepresentationModel<Deposit>{
	
	@NotNull(message = "Account no shouldn't be empty")
	private long accountNo ;
	@NotNull(message = "Amount shouldn't be empty")
	private long amount;
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public DepositRequest( long accountNo, long amount) {
		super();
		this.accountNo = accountNo;
		this.amount = amount;
	}
	public DepositRequest() {
		super();
	}
	
	
}
