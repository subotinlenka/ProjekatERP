package erp.service.implementation;

import java.lang.reflect.Type;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.OrderCreateDto;
import erp.dto.OrderDto;
import erp.dto.OrderUpdateDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.Order;
import erp.repository.UserRepository;
import erp.repository.OrderItemRepository;
import erp.repository.OrderRepository;
import erp.repository.OrderStatusRepository;
import erp.service.OrderService;

@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	OrderStatusRepository statusRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;

	public OrderServiceImplementation(ModelMapper modelMapper, OrderRepository orderRepository,
			OrderStatusRepository statusRepository, UserRepository userRepository,
			OrderItemRepository orderItemRepository) {
		this.modelMapper = modelMapper;
		this.orderRepository = orderRepository;
		this.statusRepository = statusRepository;
		this.userRepository = userRepository;
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	public List<OrderDto> getOrders() {
		return orderRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public OrderDto getOrderById(Integer orderId) throws Exception {
		Order order = orderRepository.findOrderById(orderId);
        if(order != null)
        {
            return convertEntityToDto(order);
        }
        throw new NotFoundException("Order with forwarded ID does not exist!");
	}

	@Override
	public List<OrderDto> getOrdersByCity(String orderCity) {
		List<Order> orders = orderRepository.findOrdersByCity(orderCity);
		Type listType = new TypeToken<List<OrderDto>>(){}.getType();
		List<OrderDto> orderDtoList = modelMapper.map(orders,listType);
		if(!orders.isEmpty())
        {
            return orderDtoList;
        }
        throw new NotFoundException("Orders with forwarded city do not exist!");
	}

	@Override
	public List<OrderDto> getOrdersByPaidOrNot(Boolean orderPaid) {
		List<Order> orders = orderRepository.findOrdersByPaidOrNot(orderPaid);
		Type listType = new TypeToken<List<OrderDto>>(){}.getType();
		List<OrderDto> orderDtoList = modelMapper.map(orders,listType);
		if(!orders.isEmpty())
        {
            return orderDtoList;
        }
        throw new NotFoundException("Orders with forwarded value of orderPaid do not exist!");
	}

	@Override
	public List<OrderDto> getOrdersByPaymentType(String orderPaymentType) {
		List<Order> orders = orderRepository.findOrdersByPaymentType(orderPaymentType);
		Type listType = new TypeToken<List<OrderDto>>(){}.getType();
		List<OrderDto> orderDtoList = modelMapper.map(orders,listType);
		if(!orders.isEmpty())
        {
            return orderDtoList;
        }
        throw new NotFoundException("Orders with forwarded value of payment type do not exist!");
	}

	@Override
	public void insertOrder(OrderCreateDto orderCreateDto) {
		
		 Order order = convertCreateUpdateDtoToEntity(orderCreateDto);
	     
	     if(orderCreateDto.getOrderAddress() == null)
	         throw new BadRequestException("Order address is required field!");
	     if(orderCreateDto.getOrderCity() == null)
	         throw new BadRequestException("Order city is required field!");
	    
	     if(orderCreateDto.getOrderPaymentType() == null)
	         throw new BadRequestException("Order payment type is required field!");
	    
	     /*
	     if(orderCreateDto.getOrderPaid() == true) {
	    	 order.setOrderPaymentType("CARD");
	    	 order.setOrderPaymentDate(currentDate);
	     }
	     if(orderCreateDto.getOrderPaid() == false)
	    	 order.setOrderPaymentType("CASH");
	     
	     order.setOrderAmount(orderCreateDto.getOrderAmount());
	     
	     Float deliveryFee = orderCreateDto.getOrderDeliveryFee();
	     if(order.getOrderItems().size() < 3) {
	    	deliveryFee = (float) 200;
	    	order.setOrderDeliveryFee(deliveryFee);
	    	Float orderTotalAmount = orderCreateDto.getOrderAmount() + deliveryFee;
	    	order.setOrderTotalAmount(orderTotalAmount);
	     }
	     if(order.getOrderItems().size() == 3)
	     {
	    	deliveryFee = (float) 100;
	    	order.setOrderDeliveryFee(deliveryFee);
	    	Float orderTotalAmount = orderCreateDto.getOrderAmount() + deliveryFee;
	    	order.setOrderTotalAmount(orderTotalAmount);
	     }
	     if(order.getOrderItems().size() > 3) 
	     {
	    	deliveryFee = (float) 0;
	    	order.setOrderDeliveryFee(deliveryFee);
	    	Float orderTotalAmount = orderCreateDto.getOrderAmount() + deliveryFee;
	    	order.setOrderTotalAmount(orderTotalAmount);
	     }*/
	     
	     orderRepository.save(order);
		
	}

	@Override
	public void modifyOrder(OrderUpdateDto orderUpdateDto, Integer orderId) {
		
		Order order = orderRepository.findOrderById(orderId);
        if(order == null)
        {
        	throw new NotFoundException("Order with forwarded ID does not exist!");
        }
		
	     if(orderUpdateDto.getOrderAmount() == null)
	         throw new BadRequestException("Order amount is required field!");
	     if(orderUpdateDto.getOrderDeliveryFee() == null)
	    	 throw new BadRequestException("Order delivery fee is required field!");
	     if(orderUpdateDto.getOrderTotalAmount() == null)
	         throw new BadRequestException("Order total amount is required field!");
	     if(orderUpdateDto.getOrderAddress() == null)
	         throw new BadRequestException("Order address is required field!");
	     if(orderUpdateDto.getOrderCity() == null)
	         throw new BadRequestException("Order city is required field!");
	     if(orderUpdateDto.getOrderPaymentType() == null)
	         throw new BadRequestException("Order payment type is required field!");
	     if(orderUpdateDto.getOrderAmount() <= 0)
	    	 throw new BadRequestException("Order amount must be greater than 0!");
	     if(orderUpdateDto.getOrderDeliveryFee() < 0)
	    	 throw new BadRequestException("Order delivery fee must be greater than 0!");
	     if(orderUpdateDto.getOrderTotalAmount() <= 0)
	    	 throw new BadRequestException("Order total amount must be greater than 0!");
	     if(orderUpdateDto.getOrderAmount() > orderUpdateDto.getOrderTotalAmount())
	    	 throw new BadRequestException("Order total amount must be greater than order amount!");
	    
	     LocalDate dateUpdate = LocalDate.now();
	     Date currentDate = Date.valueOf(dateUpdate);
	     if(orderUpdateDto.getOrderPaymentType() == "CARD") {
	    	 order.setOrderPaid(true);
	    	 order.setOrderPaymentDate(currentDate);
	     }
	     if(orderUpdateDto.getOrderPaymentType() == "CASH")
	    	 order.setOrderPaid(false);

	     order.setOrderAmount(orderUpdateDto.getOrderAmount());
	     order.setOrderAddress(orderUpdateDto.getOrderAddress());
	     order.setOrderCity(orderUpdateDto.getOrderCity());
	     order.setOrderPaymentType(orderUpdateDto.getOrderPaymentType());
	     
	     Float deliveryFee = orderUpdateDto.getOrderDeliveryFee();
	     if(order.getOrderItems().size() < 3) {
	    	deliveryFee = (float) 200;
	    	order.setOrderDeliveryFee(deliveryFee);
	    	Float totalAmount = orderUpdateDto.getOrderAmount() + deliveryFee;
	    	order.setOrderTotalAmount(totalAmount);
	     }
	     if(order.getOrderItems().size() == 3)
	     {
	    	deliveryFee = (float) 100;
	    	order.setOrderDeliveryFee(deliveryFee);
	    	Float totalAmount = orderUpdateDto.getOrderAmount() + deliveryFee;
	    	order.setOrderTotalAmount(totalAmount);
	     }
	     if(order.getOrderItems().size() > 3) 
	     {
	    	deliveryFee = (float) 0;
	    	order.setOrderDeliveryFee(deliveryFee);
	    	Float totalAmount = orderUpdateDto.getOrderAmount() + deliveryFee;
	    	order.setOrderTotalAmount(totalAmount);
	     }
	     orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Integer orderId) throws Exception {
		
		Order order = orderRepository.findOrderById(orderId);
	    if (order != null) {
	        try {
	        	orderRepository.deleteOrderById(orderId);
	        }catch(Exception e) {
	        	throw new ConflictException("Order with forwarded ID is used in other table!");
	        }
	    }
	    else
	    	throw new NotFoundException("Order with forwarded ID does not exist!");
		
	}
	
	//Mapping Order Entity to OrderDto
	public OrderDto convertEntityToDto(Order order) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		OrderDto orderDto = this.modelMapper.map(order, OrderDto.class);
		return orderDto;	
	}
				
	//Mapping OrderDto to Order Entity
	public Order convertDtoToEntity(OrderDto orderDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Order order = this.modelMapper.map(orderDto, Order.class);
		return order;
	}
			
	//Mapping Order Entity to OrderCreateUpdateDto
	public OrderCreateDto convertEntityToCreateUpdateDto(Order order) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		OrderCreateDto orderCreateUpdateDto = this.modelMapper.map(order, OrderCreateDto.class);
		return orderCreateUpdateDto;	
	}
			
	//Mapping OrderCreateUpdateDto to Order Entity
	public Order convertCreateUpdateDtoToEntity(OrderCreateDto orderCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		Order order = this.modelMapper.map(orderCreateUpdateDto, Order.class);
		return order;
	}

}
