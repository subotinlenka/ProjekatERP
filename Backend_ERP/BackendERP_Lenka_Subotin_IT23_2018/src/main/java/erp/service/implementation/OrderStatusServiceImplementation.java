package erp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.OrderStatusCreateUpdateDto;
import erp.dto.OrderStatusDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.OrderStatus;
import erp.repository.OrderStatusRepository;
import erp.service.OrderStatusService;

@Service
public class OrderStatusServiceImplementation implements  OrderStatusService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderStatusRepository orderStatusRepository;
	
	public OrderStatusServiceImplementation(ModelMapper modelMapper, OrderStatusRepository orderStatusRepository) {
		this.modelMapper = modelMapper;
		this.orderStatusRepository = orderStatusRepository;
	}

	@Override
	public List<OrderStatusDto> getOrderStatuses() {
		return orderStatusRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public OrderStatusDto getOrderStatusById(Integer orderStatusId) throws Exception {
	
		OrderStatus status = orderStatusRepository.findOrderStatusById(orderStatusId);
        if(status != null)
        {
            return convertEntityToDto(status);
        }
        throw new NotFoundException("Order Status with forwarded ID does not exist!");
	}

	@Override
	public OrderStatusDto getOrderStatusByName(String orderStatusName) {
	
		OrderStatus status = orderStatusRepository.findOrderStatusByName(orderStatusName);
        if(status != null)
        {
            return convertEntityToDto(status);
        }
        throw new NotFoundException("Order Status with forwarded status name does not exist!");
	}

	@Override
	public void insertOrderStatus(OrderStatusCreateUpdateDto orderStatusCreateDto) {
		if(orderStatusCreateDto.getOrderStatusName() == null)
        	throw new BadRequestException("Order Status name is required field!");
		orderStatusRepository.save(convertCreateUpdateDtoToEntity(orderStatusCreateDto));
		
	}

	@Override
	public void modifyOrderStatus(OrderStatusCreateUpdateDto orderStatusCreateDto, Integer orderStatusId) {
		OrderStatus status = orderStatusRepository.findOrderStatusById(orderStatusId);
        if(status == null)
            throw new NotFoundException("Order Status with forwarded ID does not exist!");
        status.setOrderStatusName(orderStatusCreateDto.getOrderStatusName());
        
        if(orderStatusCreateDto.getOrderStatusName()== null)
        	throw new BadRequestException("Order Status name is required field!");
		
        orderStatusRepository.save(status);
		
	}

	@Override
	public void deleteOrderStatus(Integer orderStatusId) throws Exception {
		
		OrderStatus status = orderStatusRepository.findOrderStatusById(orderStatusId);
        if (status != null) {
        	try {
        		orderStatusRepository.deleteOrderStatusById(orderStatusId);
        	}catch(Exception e) {
        		throw new ConflictException("Order Status with forwarded ID is used in other table!");
        	}
        }
        else
            throw new NotFoundException("Order Status with forwarded ID does not exist!");
		
	}
	
	//Mapping OrderStatus Entity to OrderStatusDto
	public OrderStatusDto convertEntityToDto(OrderStatus status) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		OrderStatusDto statusDto = this.modelMapper.map(status, OrderStatusDto.class);
		return statusDto;	
	}
			
	//Mapping OrderStatusDto to OrderStatus Entity
	public OrderStatus convertDtoToEntity(OrderStatusDto statusDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		OrderStatus status = this.modelMapper.map(statusDto, OrderStatus.class);
		return status;
	}
			
	//Mapping OrderStatus Entity to OrderStatusCreateUpdateDto
	public OrderStatusCreateUpdateDto convertEntityToCreateUpdateDto(OrderStatus status) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		OrderStatusCreateUpdateDto statusCreateUpdateDto = this.modelMapper.map(status, OrderStatusCreateUpdateDto.class);
		return statusCreateUpdateDto;	
	}

	//Mapping OrderStatusCreateUpdateDto to OrderStatus Entity
	public OrderStatus convertCreateUpdateDtoToEntity(OrderStatusCreateUpdateDto statusCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		OrderStatus status = this.modelMapper.map(statusCreateUpdateDto, OrderStatus.class);
		return status;
	}

}
