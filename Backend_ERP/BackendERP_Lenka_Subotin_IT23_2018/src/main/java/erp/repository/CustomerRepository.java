package erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	 @Query(value = "SELECT * FROM customers c WHERE c.customerid = :customerId ", nativeQuery = true)
	 Customer findCustomerById(Integer customerId);
	 
	 @Query(value = "SELECT * FROM customers c WHERE c.customerusername = :customerUserName", nativeQuery = true)
	 Customer findCustomerByUserName(String customerUserName);
	 
}
