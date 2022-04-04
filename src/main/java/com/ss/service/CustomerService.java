package com.ss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.model.Customer;
import com.ss.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	public void saveCustomer(Customer customer) {
		this.customerRepository.save(customer);
		
	}

	public boolean loginVerify(String email, String password) {
		Customer customer = customerRepository.findByEmail(email);
        if (customer!= null && customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
            return true;
        }
        return false;
	}

	public Customer getCustomer(String email) {
		return customerRepository.findByEmail(email);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public void deleteCustomer(String email) {
		customerRepository.deleteById(email);
	}

	public List<Customer> searchCustomer(String keyword) {
		return customerRepository.userSearch(keyword);
	}
	
	public List<String> customerEmails(){
		return customerRepository.customerEmails();
	}

}
