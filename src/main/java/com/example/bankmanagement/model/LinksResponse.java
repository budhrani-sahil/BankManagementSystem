package com.example.bankmanagement.model;

import org.springframework.hateoas.RepresentationModel;

public class LinksResponse extends RepresentationModel<LinksResponse> {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LinksResponse(String message) {
		super();
		this.message = message;
	}

	public LinksResponse() {
		super();
	}

}
