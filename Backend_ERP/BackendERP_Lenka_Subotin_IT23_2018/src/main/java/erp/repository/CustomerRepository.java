package erp.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import erp.jpa.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Collection<Customer> findByCustomerFirstNameContainingIgnoreCase(@Param("customerFirstName") String customerFirstName);
	
	Collection<Customer> findByCustomerLastNameContainingIgnoreCase(@Param("customerLastName") String customerLastName);
}
