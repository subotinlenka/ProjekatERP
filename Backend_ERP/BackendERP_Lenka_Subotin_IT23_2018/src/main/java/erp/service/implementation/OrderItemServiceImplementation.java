package erp.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.OrderItemDto;
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
	
	//Mapping List of OrderItem Entity to List of OrderItemDto
	public List<OrderItemDto> convertEntityToDTOList(List<OrderItem> orderItem) {
        orderItem = orderItemRepository.findAll();
        Type listType = new TypeToken<List<OrderItemDto>>(){}.getType();
        List<OrderItemDto> orderItemDtoList = modelMapper.map(orderItem, listType);
        return orderItemDtoList;
    }
}
