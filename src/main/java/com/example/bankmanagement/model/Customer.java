package com.example.bankmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

@Entity
public class Customer extends RepresentationModel<Customer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long customerId;
	private String name;
	private String kyc;
	private Boolean status;
	public long getId() {
		return customerId;
	}
	public void setId(long id) {
		this.customerId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKyc() {
		return kyc;
	}
	public void setKyc(String kyc) {
		this.kyc = kyc;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Customer(long id, String name, String kyc, Boolean status) {
		super();
		this.customerId = id;
		this.name = name;
		this.kyc = kyc;
		this.status = status;
	}
	public Customer() {
		super();
	}
	
	
}
