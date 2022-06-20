package erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	 @Query(value = "SELECT * FROM orderItem o WHERE o.orderitemid = :orderItemId ", nativeQuery = true)
	 OrderItem findOrderItemById(Integer orderItemId);
	 
	 @Query(value = "SELECT * FROM orderItem o WHERE o.orderid = :orderId ", nativeQuery = true)
	 List<OrderItem> findOrderItemsByOrder(Integer orderId);
	
}
