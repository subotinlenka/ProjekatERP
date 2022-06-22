package erp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.OrderStatus;

@Repository
@Transactional
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

	@Query(value = "SELECT * FROM orderstatus o WHERE o.orderstatusid = :orderStatusId ", nativeQuery = true)
    OrderStatus findOrderStatusById(Integer orderStatusId);
	
	@Query(value = "SELECT * FROM orderstatus o WHERE upper(o.orderstatusname) = :orderStatusName OR "
			+ "lower(o.orderstatusname) = :orderStatusName OR o.orderstatusname = :orderStatusName ", nativeQuery = true)
	OrderStatus findOrderStatusByName(String orderStatusName);
	
	@Modifying
	@Query(value = "DELETE FROM orderstatus o WHERE o.orderstatusid = :orderStatusId ", nativeQuery = true)
	void deleteOrderStatusById(Integer orderStatusId);

}
