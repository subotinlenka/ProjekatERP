package erp.service;

import java.util.List;

import erp.dto.OrderStatusCreateUpdateDto;
import erp.dto.OrderStatusDto;

public interface OrderStatusService {

	 List<OrderStatusDto> getOrderStatuses();
	 OrderStatusDto getOrderStatusById(Integer orderStatusId) throws Exception; 
	 OrderStatusDto getOrderStatusByName(String orderStatusName);
	 void insertOrderStatus(OrderStatusCreateUpdateDto orderStatusCreateDto);
	 void modifyOrderStatus(OrderStatusCreateUpdateDto orderStatusCreateDto, Integer orderStatusId);
	 void deleteOrderStatus(Integer orderStatusId) throws Exception; 
}
