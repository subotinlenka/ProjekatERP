package erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import erp.dto.OrderItemCreateUpdateDto;
import erp.dto.OrderItemDto;
import erp.service.OrderItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Order Item table in the database TennisWebShop"})
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;
	
	private static final String SUCCESS = "Success!";

	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}
	
	@GetMapping("orderItems")
    @ApiOperation(value = "Returns the list of all Order Items", response = OrderItemDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderItemDto>> getOrderItems() {
		return ResponseEntity.ok(orderItemService.getOrderItems());
	}
	
	@GetMapping("orderItem/{orderItemId}")
	@ApiOperation(value = "Returns Order Item by forwarded ID", notes = "Id of the Order Item is required.", response = OrderItemDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable("orderItemId") Integer orderItemId) throws Exception {
	
		return ResponseEntity.ok(orderItemService.getOrderItemById(orderItemId));
	}
	
	@GetMapping("orderItems/order/{orderId}")
	@ApiOperation(value = "Return Order Item(s) based on forwarded Order ID ", notes = "Value of Order ID is required.",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderItemDto>> getOrderItemsByOrderId(@PathVariable("orderId") Integer orderId) throws Exception {
	
		return ResponseEntity.ok(orderItemService.getOrderItemsByOrder(orderId));
	}
	
	@PostMapping("orderItem")
	@ApiOperation(value = "Inserts Order Item in the database", notes = "Request body is required!")
	public ResponseEntity<String> insertOrderItem(@Valid @RequestBody OrderItemCreateUpdateDto orderItemCreateDto) {

		orderItemService.insertOrderItem(orderItemCreateDto);
		return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("orderItem/{orderItemId}")
	@ApiOperation(value = "Modifies existing Order Item with forwarded ID", notes = "Request body and Order Item Id are required!")
	public ResponseEntity<String> updateOrderItem(@Valid @RequestBody OrderItemCreateUpdateDto orderItemUpdateDto, @PathVariable("orderItemId") Integer orderItemId) {
		
		orderItemService.modifyOrderItem(orderItemUpdateDto, orderItemId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping("orderItem/{orderItemId}")
	@ApiOperation(value = "Deletes Order Item with forwarded ID", notes = "Id of the Order Item is required.")
	public ResponseEntity<String> deleteOrderItem(@PathVariable("orderItemId") Integer orderItemId) throws Exception {
		orderItemService.deleteOrderItem(orderItemId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}

}
