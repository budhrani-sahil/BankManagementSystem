package com.example.bankmanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.example.bankmanagement.model.Customer;


public class CustomerRequest extends RepresentationModel<Customer>{
	
	@NotBlank(message = "Name shouldn't be empty")
	private String name;
	@NotBlank(message = "Kyc shouldn't be empty")
	private String kyc;
	@NotNull(message = "Status shouldn't be null")
	private Boolean status;
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
	public CustomerRequest(String name, String kyc, Boolean status) {
		super();
		this.name = name;
		this.kyc = kyc;
		this.status = status;
	}
	public CustomerRequest() {
		super();
	}
	
	

}
