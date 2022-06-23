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
import erp.model.Customer;
import erp.model.Order;
import erp.model.OrderStatus;
import erp.repository.CustomerRepository;
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
	CustomerRepository customerRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;

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
	     OrderStatus orderStatus = statusRepository.findOrderStatusById(orderCreateDto.getOrderStatusID());
	     if (orderStatus == null) {
             throw new BadRequestException("The inserted ID of Order Status does not exist!");
         }
	     Customer customer = customerRepository.findCustomerById(orderCreateDto.getCustomerID());
	     if (customer == null) {
             throw new BadRequestException("The inserted ID of Customer does not exist!");
         } 
	     if(orderCreateDto.getOrderDate() == null)
	    	 throw new BadRequestException("Order date is required field!");
	     if(orderCreateDto.getOrderAmount() == null)
	         throw new BadRequestException("Order amount is required field!");
	     if(orderCreateDto.getOrderDeliveryFee() == null)
	    	 throw new BadRequestException("Order delivery fee is required field!");
	     if(orderCreateDto.getOrderTotalAmount() == null)
	         throw new BadRequestException("Order total amount is required field!");
	     if(orderCreateDto.getOrderAddress() == null)
	         throw new BadRequestException("Order address is required field!");
	     if(orderCreateDto.getOrderCity() == null)
	         throw new BadRequestException("Order city is required field!");
	     if(orderCreateDto.getOrderPaid() == null)
	         throw new BadRequestException("Order paid is required field!");
	     if(orderCreateDto.getOrderPaymentType() == null)
	         throw new BadRequestException("Order payment type is required field!");
	     if(orderCreateDto.getOrderAmount() <= 0)
	    	 throw new BadRequestException("Order amount must be greater than 0!");
	     if(orderCreateDto.getOrderDeliveryFee() < 0)
	    	 throw new BadRequestException("Order delivery fee must be greater than 0!");
	     if(orderCreateDto.getOrderTotalAmount() <= 0)
	    	 throw new BadRequestException("Order total amount must be greater than 0!");
	     if(orderCreateDto.getOrderPaymentDate().before(orderCreateDto.getOrderDate()))
	    	 throw new BadRequestException("Order payment date must be same as order date or greater than order date!");
	     if(orderCreateDto.getOrderAmount() > orderCreateDto.getOrderTotalAmount())
	    	 throw new BadRequestException("Order total amount must be greater than order amount!");

	     LocalDate date = LocalDate.now();
	     Date currentDate = Date.valueOf(date);
	     order.setOrderDate(currentDate);
	     order.setOrderStatus(orderStatus);
	     order.setCustomer(customer);
	     
	     Float totalAmount = orderCreateDto.getOrderAmount() + orderCreateDto.getOrderDeliveryFee();
	     order.setOrderTotalAmount(totalAmount);
	     OrderStatus status = statusRepository.findOrderStatusByName("In progress");
	     order.setOrderStatus(status);
	     if(orderCreateDto.getOrderPaid() == true) {
	    	 order.setOrderPaymentType("CARD");
	    	 order.setOrderPaymentDate(currentDate);
	     }
	     if(orderCreateDto.getOrderPaid() == false)
	    	 order.setOrderPaymentType("CASH");
	     
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

	     order.setOrderAmount(orderUpdateDto.getOrderAmount());
	     order.setOrderDeliveryFee(orderUpdateDto.getOrderDeliveryFee());
	     order.setOrderTotalAmount(orderUpdateDto.getOrderTotalAmount());
	     order.setOrderAddress(orderUpdateDto.getOrderAddress());
	     order.setOrderCity(orderUpdateDto.getOrderCity());
	     order.setOrderPaymentType(orderUpdateDto.getOrderPaymentType());
	     
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
