package erp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import erp.model.Customer;
import erp.repository.CustomerRepository;

@CrossOrigin
@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("customer")
	public Collection<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping("customer/{customerID}")
	public Customer getCustomer(@PathVariable("customerID") Integer customerID) {
		
		return customerRepository.getById(customerID);
	}
	
	/*@GetMapping("customerName/{customerFirstName}")
	public Collection<Customer> getCustomerByFirstName(@PathVariable("customerFirstName") String customerFirstName) {
		return customerRepository.findByCustomerFirstNameContainingIgnoreCase(customerFirstName);
	}
	
	@GetMapping("customerLastName/{customerLastName}")
	public Collection<Customer> getCustomerByLastName(@PathVariable("customerLastName") String customerLastName) {
		return customerRepository.findByCustomerLastNameContainingIgnoreCase(customerLastName);
	}*/
	
	@PostMapping("customer")
	public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) {
		if (!customerRepository.existsById(customer.getCustomerId())) {
			customerRepository.save(customer);
			return new ResponseEntity<Customer>(HttpStatus.OK); 
		}
		return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		if (!customerRepository.existsById(customer.getCustomerId()))
			return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
		customerRepository.save(customer);
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	
	@DeleteMapping("customer/{customerID}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer customerID)  {
		if (!customerRepository.existsById(customerID))
			return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		customerRepository.deleteById(customerID);
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
}
