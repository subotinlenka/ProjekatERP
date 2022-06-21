package erp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.CustomerCreateUpdateDto;
import erp.dto.CustomerDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.Customer;
import erp.repository.CustomerRepository;
import erp.service.CustomerService;


@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public CustomerServiceImplementation(ModelMapper modelMapper, CustomerRepository customerRepository) {
		super();
		this.modelMapper = modelMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDto> getCustomers() {
		return customerRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public CustomerDto getCustomerById(Integer customerId) throws Exception {
		Customer customer = customerRepository.findCustomerById(customerId);
        if(customer != null)
        {
            return convertEntityToDto(customer);
        }
        throw new NotFoundException("Customer with forwarded ID does not exist!");
	}

	@Override
	public CustomerDto getCustomerByUserName(String customerUserName) {
		Customer customer = customerRepository.findCustomerByUserName(customerUserName);
        if(customer != null)
        {
            return convertEntityToDto(customer);
        }
        throw new NotFoundException("Customer with forwarded user name does not exist!");
	}

	@Override
	public void insertCustomer(CustomerCreateUpdateDto customerCreateDto) {
		
		 if(customerCreateDto.getCustomerFirstName() == null)
	        throw new BadRequestException("Customer first name is required field!");
	     else if(customerCreateDto.getCustomerLastName() == null)
	        throw new BadRequestException("Customer last name is required field!");
	     else if(customerCreateDto.getCustomerDateOfBirth() == null)
	        throw new BadRequestException("Customer date of birth is required field!");
	     else if(customerCreateDto.getCustomerAddress() == null)
	        throw new BadRequestException("Customer address is required field!");
	     else if(customerCreateDto.getCustomerCity() == null)
	        throw new BadRequestException("Customer city is required field!");
	     else if(customerCreateDto.getCustomerCountry() == null)
	        throw new BadRequestException("Customer country is required field!");
	     else if(customerCreateDto.getCustomerEmail() == null)
	        throw new BadRequestException("Customer email is required field!");
	     else if(customerCreateDto.getCustomerUserName() == null)
	        throw new BadRequestException("Customer user name is required field!");
	     else if(customerCreateDto.getCustomerPassword() == null)
	        throw new BadRequestException("Customer password is required field!");
		
		customerRepository.save(convertCreateUpdateDtoToEntity(customerCreateDto));
	}

	@Override
	public void modifyCustomer(CustomerCreateUpdateDto customerUpdateDto, Integer customerId) {
		
		Customer customer = customerRepository.findCustomerById(customerId);
        if(customer == null)
            throw new NotFoundException("Customer with forwarded ID does not exist!");
        customer.setCustomerFirstName(customerUpdateDto.getCustomerFirstName());
        customer.setCustomerLastName(customerUpdateDto.getCustomerLastName());
        customer.setCustomerDateOfBirth(customerUpdateDto.getCustomerDateOfBirth());
        customer.setCustomerPhoneNumber(customerUpdateDto.getCustomerPhoneNumber());
        customer.setCustomerAddress(customerUpdateDto.getCustomerAddress());
        customer.setCustomerCity(customerUpdateDto.getCustomerCity());
        customer.setCustomerCountry(customerUpdateDto.getCustomerCountry());
        customer.setCustomerEmail(customerUpdateDto.getCustomerEmail());
        customer.setCustomerUserName(customerUpdateDto.getCustomerUserName());
        customer.setCustomerPassword(customerUpdateDto.getCustomerPassword());
        
        if(customer.getCustomerFirstName() == null)
        	throw new BadRequestException("Customer first name is required field!");
        else if(customer.getCustomerLastName() == null)
        	throw new BadRequestException("Customer last name is required field!");
        else if(customer.getCustomerDateOfBirth() == null)
        	throw new BadRequestException("Customer date of birth is required field!");
        else if(customer.getCustomerAddress() == null)
        	throw new BadRequestException("Customer address is required field!");
        else if(customer.getCustomerCity() == null)
        	throw new BadRequestException("Customer city is required field!");
        else if(customer.getCustomerCountry() == null)
        	throw new BadRequestException("Customer country is required field!");
        else if(customer.getCustomerEmail() == null)
        	throw new BadRequestException("Customer email is required field!");
        else if(customer.getCustomerUserName() == null)
        	throw new BadRequestException("Customer user name is required field!");
        else if(customer.getCustomerPassword() == null)
        	throw new BadRequestException("Customer password is required field!");
        
        customerRepository.save(customer);		
	}

	@Override
	public void deleteCustomer(Integer customerId) throws Exception {
		
		Customer customer = customerRepository.findCustomerById(customerId);
        if (customer != null) {
        	try {
        		customerRepository.deleteCustomerById(customerId);
        	}catch(Exception e) {
        		throw new ConflictException("Customer with forwarded ID is used in other table!");
        	}
        }else
            throw new NotFoundException("Customer with forwarded ID does not exist!");
	}
	
	//Mapping Customer Entity to CustomerDto
	public CustomerDto convertEntityToDto(Customer customer) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
		return customerDto;	
	}
		
	//Mapping CustomerDto to Customer Entity
	public Customer convertDtoToEntity(CustomerDto customerDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Customer customer = this.modelMapper.map(customerDto, Customer.class);
		return customer;
	}
		
	//Mapping Customer Entity to CustomerCreateUpdateDto
	public CustomerCreateUpdateDto convertEntityToCreateUpdateDto(Customer customer) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
		CustomerCreateUpdateDto customerCreateUpdateDto = this.modelMapper.map(customer, CustomerCreateUpdateDto.class);
		return customerCreateUpdateDto;	
	}

	//Mapping CustomerCreateUpdateDto to Customer Entity
	public Customer convertCreateUpdateDtoToEntity(CustomerCreateUpdateDto customerCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
		Customer customer = this.modelMapper.map(customerCreateUpdateDto, Customer.class);
		return customer;
	}

}
