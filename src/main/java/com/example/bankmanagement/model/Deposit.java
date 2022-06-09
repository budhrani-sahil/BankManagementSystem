package com.example.bankmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.hateoas.RepresentationModel;


@Entity
public class Deposit extends RepresentationModel<Deposit>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long depositId;
	private long accountNo;
	private long amount;
	public long getDepositId() {
		return depositId;
	}
	public void setDepositId(long depositId) {
		this.depositId = depositId;
	}
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
	public Deposit(long depositId, long accountNo, long amount) {
		super();
		this.depositId = depositId;
		this.accountNo = accountNo;
		this.amount = amount;
	}
	public Deposit() {
		super();
	}

	
}
