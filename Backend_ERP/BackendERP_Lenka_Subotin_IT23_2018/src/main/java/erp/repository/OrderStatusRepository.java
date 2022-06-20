package erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

	@Query(value = "SELECT * FROM orderStatus o WHERE o.orderstatusid = :orderStatusId ", nativeQuery = true)
    OrderStatus findOrderStatusById(Integer orderStatusId);

}
