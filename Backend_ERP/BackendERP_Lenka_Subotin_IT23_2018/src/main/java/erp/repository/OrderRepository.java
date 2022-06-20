package erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	 @Query(value = "SELECT * FROM orders o WHERE o.orderid = :orderId ", nativeQuery = true)
	 Order findOrderById(Integer orderId);

	 @Query(value = "SELECT * FROM orders o WHERE o.ordercity = :orderCity ", nativeQuery = true)
	 List<Order> findOrdersByCity(String orderCity);
	 
	 @Query(value = "SELECT * FROM orders o WHERE o.ordepaid = :orderPaid", nativeQuery = true)
	 List<Order> findOrdersByPaidOrNot(Boolean orderPaid);

	 @Query(value = "SELECT * FROM orders o WHERE o.orderpaymenttype = :orderPaymentType", nativeQuery = true)
	 List<Order> findOrdersByPaymentType(String orderPaymentType);
}
