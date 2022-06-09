package com.example.bankmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankmanagement.model.LoanRequest;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Integer> {

	List<LoanRequest> findByApprovalstatus(String a);

}
