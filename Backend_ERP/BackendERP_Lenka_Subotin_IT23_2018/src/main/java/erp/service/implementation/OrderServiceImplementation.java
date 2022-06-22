package erp.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.repository.OrderRepository;
import erp.service.OrderService;

@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderRepository orderRepository;

}
