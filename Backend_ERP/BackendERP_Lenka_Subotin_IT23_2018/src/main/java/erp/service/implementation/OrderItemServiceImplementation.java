package erp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.OrderItemCreateUpdateDto;
import erp.dto.OrderItemDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.Order;
import erp.model.OrderItem;
import erp.model.Product;
import erp.repository.OrderItemRepository;
import erp.repository.OrderRepository;
import erp.repository.ProductRepository;
import erp.service.OrderItemService;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public OrderItemServiceImplementation(ModelMapper modelMapper, OrderItemRepository orderItemRepository,
			OrderRepository orderRepository, ProductRepository productRepository) {
		this.modelMapper = modelMapper;
		this.orderItemRepository = orderItemRepository;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	@Override
	public List<OrderItemDto> getOrderItems() {
		return orderItemRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public OrderItemDto getOrderItemById(Integer orderItemId) throws Exception {
		OrderItem orderItem = orderItemRepository.findOrderItemById(orderItemId);
        if(orderItem != null)
        {
            return convertEntityToDto(orderItem);
        }
        throw new NotFoundException("Order Item with forwarded ID does not exist!");
	}

	@Override
	public List<OrderItemDto> getOrderItemsByOrder(Integer orderId) {
		List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrder(orderId);
		Type listType = new TypeToken<List<OrderItemDto>>(){}.getType();
		List<OrderItemDto> orderItemDtoList = modelMapper.map(orderItems,listType);
		if(!orderItems.isEmpty())
        {
            return orderItemDtoList;
        }
        throw new NotFoundException("Order Item(s) with forwarded order Id do not exist!");
	}

	@Override
	public void insertOrderItem(OrderItemCreateUpdateDto orderItemCreateDto) {
		
		OrderItem orderItem = convertCreateUpdateDtoToEntity(orderItemCreateDto);
		Product product = productRepository.findProductById(orderItemCreateDto.getProductID());
	    //Order order = orderRepository.findOrderById(orderItemCreateDto.getOrderID());
		 if (product == null) {
	        throw new BadRequestException("The inserted ID of Product does not exist!");
	     }
	    // if (order == null) {
        //    throw new BadRequestException("The inserted ID of Order does not exist!");
	    // } 
	     if(orderItemCreateDto.getOrderItemQuantity() == null)
	    	 throw new BadRequestException("Order Item quantity is required field!");
	     if(orderItemCreateDto.getOrderItemTotalPrice() == null)
	         throw new BadRequestException("Order Item total price is required field!");
	     if(orderItemCreateDto.getOrderItemTotalPrice() <= 0)
	    	 throw new BadRequestException("Order Item total price must be greater than 0!");
	     if(orderItemCreateDto.getOrderItemQuantity() < 0)
	    	 throw new BadRequestException("Order Item quantity must be greater than 0!");
	    
	     orderItem.setProduct(product);
	     //orderItem.setOrder(order);
	     
	     Float orderItemTotalPrice = product.getProductPrice() * orderItemCreateDto.getOrderItemQuantity();
	     orderItem.setOrderItemTotalPrice(orderItemTotalPrice);
	     
	     orderItemRepository.save(orderItem);
		
		
	}

	@Override
	public void modifyOrderItem(OrderItemCreateUpdateDto orderItemUpdateDto, Integer orderItemId) {
		
		OrderItem orderItem = orderItemRepository.findOrderItemById(orderItemId);
        if(orderItem == null)
        {
        	throw new NotFoundException("Order Item with forwarded ID does not exist!");
        }
		
        if(orderItemUpdateDto.getOrderItemQuantity() == null)
        	throw new BadRequestException("Order Item quantity is required field!");
	    if(orderItemUpdateDto.getOrderItemTotalPrice() == null)
	        throw new BadRequestException("Order Item total price is required field!");
	    if(orderItemUpdateDto.getOrderItemTotalPrice() <= 0)
	    	throw new BadRequestException("Order Item total price must be greater than 0!");
	    if(orderItemUpdateDto.getOrderItemQuantity() < 0)
	    	throw new BadRequestException("Order Item quantity must be greater than 0!");
	    
	    Product product = productRepository.findProductById(orderItemUpdateDto.getProductID());
	     orderItem.setOrderItemQuantity(orderItemUpdateDto.getOrderItemQuantity());
	     Float orderItemTotalPrice = product.getProductPrice() * orderItemUpdateDto.getOrderItemQuantity();
	     orderItem.setOrderItemTotalPrice(orderItemTotalPrice);
	     
	     orderItemRepository.save(orderItem);
	}

	@Override
	public void deleteOrderItem(Integer orderItemId) throws Exception {
		
		OrderItem orderItem = orderItemRepository.findOrderItemById(orderItemId);
	    if (orderItem != null) {
	        try {
	        	orderItemRepository.deleteOrderItemById(orderItemId);
	        }catch(Exception e) {
	        	throw new ConflictException("Order Item with forwarded ID is used in other table!");
	        }
	    }
	    else
	    	throw new NotFoundException("Order Item with forwarded ID does not exist!");
		
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
	
	//Mapping OrderItem Entity to OrderItemCreateUpdateDto
	public OrderItemCreateUpdateDto convertEntityToCreateUpdateDto(OrderItem orderItem) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		OrderItemCreateUpdateDto orderItemCreateUpdateDto = this.modelMapper.map(orderItem, OrderItemCreateUpdateDto.class);
		return orderItemCreateUpdateDto;	
	}
				
	//Mapping OrderItemCreateUpdateDto to Order Entity
	public OrderItem convertCreateUpdateDtoToEntity(OrderItemCreateUpdateDto orderItemCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		OrderItem orderItem = this.modelMapper.map(orderItemCreateUpdateDto, OrderItem.class);
		return orderItem;
	}
				
}
