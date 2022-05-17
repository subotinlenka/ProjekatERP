package erp.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import erp.jpa.OrderStatus;
import erp.repository.OrderStatusRepository;

@RestController
public class OrderStatusController {

	@Autowired
	private OrderStatusRepository orderStatusRepository;
	
	@GetMapping("orderStatus")
	public Collection<OrderStatus> getOrderStatuses() {
		return orderStatusRepository.findAll();
	}

	@GetMapping("orderStatus/{orderStatusID}")
	public OrderStatus getOrderStatus(@PathVariable("orderStatusID") Integer orderStatusID) {
		
		return orderStatusRepository.getById(orderStatusID);
	}
	
	@PostMapping("orderStatus")
	public ResponseEntity<OrderStatus> insertOrderStatus(@RequestBody OrderStatus orderStatus) {
		if (!orderStatusRepository.existsById(orderStatus.getOrderStatusId())) {
			orderStatusRepository.save(orderStatus);
			return new ResponseEntity<OrderStatus>(HttpStatus.OK); 
		}
		return new ResponseEntity<OrderStatus>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("orderStatus")
	public ResponseEntity<OrderStatus> updateOrderStatus(@RequestBody OrderStatus orderStatus) {
		if (!orderStatusRepository.existsById(orderStatus.getOrderStatusId()))
			return new ResponseEntity<OrderStatus>(HttpStatus.CONFLICT);
		orderStatusRepository.save(orderStatus);
		return new ResponseEntity<OrderStatus>(HttpStatus.OK);
	}
	
	@DeleteMapping("orderStatus/{orderStatusID}")
	public ResponseEntity<OrderStatus> deleteOrderStatus(@PathVariable Integer orderStatusID)  {
		if (!orderStatusRepository.existsById(orderStatusID))
			return new ResponseEntity<OrderStatus>(HttpStatus.NO_CONTENT);
		orderStatusRepository.deleteById(orderStatusID);
		return new ResponseEntity<OrderStatus>(HttpStatus.OK);
	}
	
}
