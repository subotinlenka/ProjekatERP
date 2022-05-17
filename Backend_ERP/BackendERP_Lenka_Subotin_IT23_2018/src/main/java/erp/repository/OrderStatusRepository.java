package erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import erp.jpa.OrderStatus;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

	
}
