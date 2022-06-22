package erp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.OrderItem;

@Repository
@Transactional
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

	 @Query(value = "SELECT * FROM orderitem o WHERE o.orderitemid = :orderItemId ", nativeQuery = true)
	 OrderItem findOrderItemById(Integer orderItemId);
	 
	 @Query(value = "SELECT * FROM orderitem o WHERE o.orderid = :orderId ", nativeQuery = true)
	 List<OrderItem> findOrderItemsByOrder(Integer orderId);
	 
	 @Modifying
	 @Query(value = "DELETE FROM orderitem o WHERE o.orderitemid = :orderItemId", nativeQuery = true)
	 void deleteOrderItemById(Integer orderItemId);
	
}
