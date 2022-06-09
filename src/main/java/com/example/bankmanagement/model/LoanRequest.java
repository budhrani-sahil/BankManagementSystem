package com.example.bankmanagement.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.hateoas.RepresentationModel;


@Entity
public class LoanRequest extends RepresentationModel<LoanRequest>  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lid;
	@NotBlank(message="First Name cannot be null")
	private String name;
	@Email(message="Email is Invalid")
	private String email;
	@NotNull
	@Pattern(regexp="^\\d{10}$",message="Contact is Invalid")
	private String contact;
	@NotNull(message="LoanAmount is Invalid")
	private BigDecimal loanamount;
	@Min(1)	
	private int duration;
	private String approvalstatus;
	
	public LoanRequest() {
		super();
	}

	public LoanRequest(int lid, @NotBlank(message = "First Name cannot be null") String name,
			@Email(message = "Email is Invalid") String email,
			@NotNull @Pattern(regexp = "^\\d{10}$", message = "Contact is Invalid") String contact,
			@NotNull(message = "LoanAmount is Invalid") BigDecimal loanamount, @Min(1) int duration,
			String approvalstatus) {
		super();
		this.lid = lid;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.loanamount = loanamount;
		this.duration = duration;
		this.approvalstatus = approvalstatus;
	}



	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public BigDecimal getLoanamount() {
		return loanamount;
	}

	public void setLoanamount(BigDecimal loanamount) {
		this.loanamount = loanamount;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getApprovalstatus() {
		return approvalstatus;
	}

	public void setApprovalstatus(String approvalstatus) {
		this.approvalstatus = approvalstatus;
	}
	
}
