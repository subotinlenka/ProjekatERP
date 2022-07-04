package erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import erp.dto.OrderCreateDto;
import erp.dto.OrderDto;
import erp.dto.OrderUpdateDto;
import erp.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Order table in the database TennisWebShop"})
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	private static final String SUCCESS = "Success!";

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("orders")
    @ApiOperation(value = "Returns the list of all Orders", response = OrderDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> getOrders() {
		return ResponseEntity.ok(orderService.getOrders());
	}

	@GetMapping("order/{orderId}")
	@ApiOperation(value = "Returns Order by forwarded ID", notes = "Id of the Order is required.", response = OrderDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Integer orderId) throws Exception {
	
		return ResponseEntity.ok(orderService.getOrderById(orderId));
	}
	
	@Secured("Admin")
	@GetMapping("orders/city/{orderCity}")
	@ApiOperation(value = "Return Order(s) by forwarded Order city", notes = "Name of the Order city is required.",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> getOrdersByCity(@PathVariable("orderCity") String orderCity) throws Exception {
	
		return ResponseEntity.ok(orderService.getOrdersByCity(orderCity));
	}
	
	@Secured("Admin")
	@GetMapping("orders/paid/{orderPaid}")
	@ApiOperation(value = "Return Order(s) based on whether orders are paid or not ", notes = "Value of Order paid is required.",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> getOrdersByPaidOrNot(@PathVariable("orderPaid") Boolean orderPaid) throws Exception {
	
		return ResponseEntity.ok(orderService.getOrdersByPaidOrNot(orderPaid));
	}
	
	@Secured("Admin")
	@GetMapping("orders/payment/{paymentType}")
	@ApiOperation(value = "Return Order(s) based on payment type", notes = "Payment type of Order is required.",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> getOrdersByPaymentType(@PathVariable("paymentType") String paymentType) throws Exception {
	
		return ResponseEntity.ok(orderService.getOrdersByPaymentType(paymentType));
	}
	
	@Secured("Admin")
	@PostMapping("order")
	@PreAuthorize("hasRole('Customer')")
	@ApiOperation(value = "Inserts Order in the database", notes = "Request body is required!")
	public ResponseEntity<String> insertOrder(@Valid @RequestBody OrderCreateDto orderCreateDto) {

		orderService.insertOrder(orderCreateDto);
		return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	}
	
	@Secured("Admin")
	@PutMapping("order/{orderId}")
	@PreAuthorize("hasRole('Customer')")
	@ApiOperation(value = "Modifies existing Order with forwarded ID", notes = "Request body and Order Id are required!")
	public ResponseEntity<String> updateOrder(@Valid @RequestBody OrderUpdateDto orderUpdateDto, @PathVariable("orderId") Integer orderId) {
		
		orderService.modifyOrder(orderUpdateDto, orderId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@Secured("Admin")
	@DeleteMapping("order/{orderId}")
	@PreAuthorize("hasRole('Customer')")
	@ApiOperation(value = "Deletes Order with forwarded ID", notes = "Id of the Order is required.")
	public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Integer orderId) throws Exception {
		orderService.deleteOrder(orderId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}

}
