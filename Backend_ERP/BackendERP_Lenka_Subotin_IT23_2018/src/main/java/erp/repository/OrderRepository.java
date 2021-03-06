package erp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.Order;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer>{

	 @Query(value = "SELECT * FROM orders o WHERE o.orderid = :orderId ", nativeQuery = true)
	 Order findOrderById(Integer orderId);

	 @Query(value =  "SELECT * FROM orders o WHERE upper(o.ordercity) = :orderCity OR "
				+ "lower(o.ordercity) = :orderCity OR o.ordercity = :orderCity ", nativeQuery = true)
	 List<Order> findOrdersByCity(String orderCity);
	 
	 @Query(value = "SELECT * FROM orders o WHERE o.orderpaid = :orderPaid", nativeQuery = true)
	 List<Order> findOrdersByPaidOrNot(Boolean orderPaid);

	 @Query(value = "SELECT * FROM orders o WHERE upper(o.orderpaymenttype) = :orderPaymentType OR "
	 		+ "	lower(o.orderpaymenttype) = :orderPaymentType OR o.orderpaymenttype = :orderPaymentType", nativeQuery = true)
	 List<Order> findOrdersByPaymentType(String orderPaymentType);
	 
	 @Modifying
	 @Query(value = "DELETE FROM orders o WHERE o.orderid = :orderId ", nativeQuery = true)
	 void deleteOrderById(Integer orderId);
}
