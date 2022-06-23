package erp.service;

import java.util.List;

import erp.dto.OrderItemCreateUpdateDto;
import erp.dto.OrderItemDto;

public interface OrderItemService {

	 List<OrderItemDto> getOrderItems();
	 OrderItemDto getOrderItemById(Integer orderItemId) throws Exception; 
	 List<OrderItemDto> getOrderItemsByOrder(Integer orderId);
	 void insertOrderItem(OrderItemCreateUpdateDto orderItemCreateDto);
	 void modifyOrderItem(OrderItemCreateUpdateDto orderItemUpdateDto, Integer orderItemId);
	 void deleteOrderItem(Integer orderItemId) throws Exception; 
}
