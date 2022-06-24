package erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import erp.dto.CustomerCreateUpdateDto;
import erp.dto.CustomerDto;
import erp.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Customer table in the database TennisWebShop"})
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private static final String SUCCESS = "Success!";

	@GetMapping("customers")
    @ApiOperation(value = "Returns the list of all Customers", response = CustomerDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDto>> getCustomers() {
		return ResponseEntity.ok(customerService.getCustomers());
	}

	@GetMapping("customer/{customerId}")
	@ApiOperation(value = "Returns Customer by forwarded ID", notes = "Id of the Customer is required.", response = CustomerDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") Integer customerId) throws Exception {
	
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
	
	@GetMapping("customer/userName/{customerUserName}")
	@ApiOperation(value = "Returns Customer by forwarded user name", notes = "User name of the Customer is required.",  response = CustomerDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerDto> getCustomerByUserName(@PathVariable("customerUserName") String customerUserName) throws Exception {
	
		return ResponseEntity.ok(customerService.getCustomerByUserName(customerUserName));
	}
	
	@PostMapping("customer")
	@ApiOperation(value = "Inserts Customer in the database", notes = "Request body is required!")
	public ResponseEntity<String> insertCustomer(@Valid @RequestBody CustomerCreateUpdateDto customerCreateDto) {

			customerService.insertCustomer(customerCreateDto);
			return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("customer/{customerId}")
	@ApiOperation(value = "Modifies Customer with forwarded ID", notes = "Request body and Customer Id are required!")
	public ResponseEntity<String> updateCustomer(@Valid @RequestBody CustomerCreateUpdateDto customerUpdateDto, @PathVariable("customerId") Integer customerId) {
		
		customerService.modifyCustomer(customerUpdateDto, customerId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	
	@DeleteMapping("customer/{customerId}")
	@ApiOperation(value = "Deletes Customer with forwarded ID", notes = "Id of the Customer is required.")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId) throws Exception {
		
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
}
