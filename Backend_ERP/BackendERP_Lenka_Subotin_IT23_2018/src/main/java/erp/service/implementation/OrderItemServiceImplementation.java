package erp.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.OrderCreateDto;
import erp.dto.OrderDto;
import erp.dto.OrderItemCreateUpdateDto;
import erp.dto.OrderItemDto;
import erp.model.Order;
import erp.model.OrderItem;
import erp.repository.OrderItemRepository;
import erp.service.OrderItemService;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository, ModelMapper modelMapper) {
		this.orderItemRepository = orderItemRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<OrderItemDto> getOrderItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItemDto getOrderItemById(Integer orderItemId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItemDto> getOrderItemsByOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertOrderItem(OrderItemCreateUpdateDto orderItemCreateDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyOrderItem(OrderItemCreateUpdateDto orderItemUpdateDto, Integer orderItemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrderItem(Integer orderItemId) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//Mapping OrderItem Entity to OrderItemDto
	public OrderItemDto convertEntityToDto(OrderItem orderItem) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		OrderItemDto orderItemDto = this.modelMapper.map(orderItem, OrderItemDto.class);
		return orderItemDto;	
	}
	
	//Mapping OrderItemDto to OrderItem Entity
	public OrderItem convertDtoToEntity(OrderItemDto orderItemDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		OrderItem orderItem = this.modelMapper.map(orderItemDto, OrderItem.class);
		return orderItem;
	}
				
}
