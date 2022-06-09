package com.example.bankmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankmanagement.dto.CustomerRequest;
import com.example.bankmanagement.exception.UserNotFoundException;
import com.example.bankmanagement.model.Customer;
import com.example.bankmanagement.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> listAll() {
		return customerRepository.findAll();
	}

	public Customer saveCustomer(CustomerRequest customerRequest) {
		Customer customer = new Customer(0, customerRequest.getName(), customerRequest.getKyc(), customerRequest.getStatus());
		return customerRepository.save(customer);
	}

	public Customer getUser(Long id) throws UserNotFoundException {
		Customer customer = customerRepository.findByCustomerId(id);
		if(customer!=null) {
			return customer;
		}else {
			throw new UserNotFoundException("Customer not found with id: "+id);
		}
		
	}

	public Customer findByName(String name) {
		return customerRepository.findByName(name).get();
	}

	public Customer changeStatus(Long id, Customer customer) {
		Optional<Customer> retrievedCustomer = customerRepository.findById(id);
		if (retrievedCustomer == null)
			try {
				throw new Exception("Customer not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		customer.setStatus(!customer.getStatus());
		customerRepository.save(customer);
		return customerRepository.findById(id).get();
	}
}
