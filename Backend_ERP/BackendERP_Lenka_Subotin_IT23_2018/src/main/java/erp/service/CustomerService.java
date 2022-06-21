package erp.service;

import java.util.List;

import erp.dto.CustomerCreateUpdateDto;
import erp.dto.CustomerDto;

public interface CustomerService {

	 List<CustomerDto> getCustomers();
	 CustomerDto getCustomerById(Integer customerId) throws Exception; 
	 CustomerDto getCustomerByUserName(String customerUserName);
	 void insertCustomer (CustomerCreateUpdateDto customerCreateDto);
	 void modifyCustomer (CustomerCreateUpdateDto customerUpdateDto, Integer customerId);
	 void deleteCustomer (Integer customerId) throws Exception; 
}
