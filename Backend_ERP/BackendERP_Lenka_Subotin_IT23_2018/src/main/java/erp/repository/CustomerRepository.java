package erp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.Customer;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	 @Query(value = "SELECT * FROM customers c WHERE c.customerid = :customerId ", nativeQuery = true)
	 Customer findCustomerById(Integer customerId);
	 
	 @Query(value = "SELECT * FROM customers c WHERE c.customerusername = :customerUserName", nativeQuery = true)
	 Customer findCustomerByUserName(String customerUserName);
	 
	 @Modifying
	 @Query(value = "DELETE FROM customers c WHERE c.customerid = :customerId", nativeQuery = true)
	 void deleteCustomerById(Integer customerId);
	 
}
