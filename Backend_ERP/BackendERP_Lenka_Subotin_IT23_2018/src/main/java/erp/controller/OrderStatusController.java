package erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import erp.dto.OrderStatusCreateUpdateDto;
import erp.dto.OrderStatusDto;
import erp.service.OrderStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Order Status table in the database TennisWebShop"})
public class OrderStatusController {

	@Autowired
	private OrderStatusService statusService;
	
	private static final String SUCCESS = "Success!";
	
	public OrderStatusController(OrderStatusService statusService) {
		this.statusService = statusService;
	}

	@GetMapping("orderStatuses")
    @ApiOperation(value = "Returns the list of all Order Statuses", response = OrderStatusDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderStatusDto>> getOrderStatuses() {
		return ResponseEntity.ok(statusService.getOrderStatuses());
	}

	@GetMapping("orderStatus/{orderStatusId}")
	@ApiOperation(value = "Returns Order Status by forwarded ID", notes = "Id of the Order Status is required.", response = OrderStatusDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderStatusDto> getOrderStatusById(@PathVariable("orderStatusId") Integer orderStatusId) throws Exception {
	
		return ResponseEntity.ok(statusService.getOrderStatusById(orderStatusId));
	}
	
	@GetMapping("orderStatus/statusName/{orderStatusName}")
	@ApiOperation(value = "Returns Order Status by forwarded status name", notes = "Name of the Order Status is required.",  response = OrderStatusDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderStatusDto> getOrderStatusByName(@PathVariable("orderStatusName") String orderStatusName) throws Exception {
	
		return ResponseEntity.ok(statusService.getOrderStatusByName(orderStatusName));
	}
	
	@PostMapping("orderStatus")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Inserts Order Status in the database", notes = "Request body is required!")
	public ResponseEntity<String> insertOrderStatus(@Valid @RequestBody OrderStatusCreateUpdateDto statusCreateDto) {

		statusService.insertOrderStatus(statusCreateDto);
		return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("orderStatus/{orderStatusId}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Modifies Order Status with forwarded ID", notes = "Request body and Order Status Id are required!")
	public ResponseEntity<String> updateOrderStatus(@Valid @RequestBody OrderStatusCreateUpdateDto statusUpdateDto, @PathVariable("orderStatusId") Integer orderStatusId) {
		
		statusService.modifyOrderStatus(statusUpdateDto, orderStatusId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping("orderStatus/{orderStatusId}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Deletes Order Status with forwarded ID", notes = "Id of the Order Status is required.")
	public ResponseEntity<String> deleteOrderStatus(@PathVariable("orderStatusId") Integer orderStatusId) throws Exception {
		
		statusService.deleteOrderStatus(orderStatusId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}

}
