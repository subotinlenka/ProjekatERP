package erp.service;

import java.util.List;

import erp.dto.OrderCreateDto;
import erp.dto.OrderDto;
import erp.dto.OrderUpdateDto;

public interface OrderService {

	 List<OrderDto> getOrders();
	 OrderDto getOrderById(Integer orderId) throws Exception; 
	 List<OrderDto> getOrdersByCity(String orderCity);
	 List<OrderDto> getOrdersByPaidOrNot(Boolean orderPaid);
	 List<OrderDto> getOrdersByPaymentType(String orderPaymentType);
	 void insertOrder(OrderCreateDto orderCreateDto);
	 void modifyOrder(OrderUpdateDto orderUpdateDto, Integer orderId);
	 void deleteOrder(Integer orderId) throws Exception; 
}
