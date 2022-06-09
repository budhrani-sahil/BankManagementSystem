package com.example.bankmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankmanagement.model.Deposit;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
