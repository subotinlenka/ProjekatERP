package erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import erp.dto.ProductStatusCreateUpdateDto;
import erp.dto.ProductStatusDto;
import erp.service.ProductStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Product Status table in the database TennisWebShop"})
public class ProductStatusController {

	@Autowired
	private ProductStatusService statusService;
	
	private static final String SUCCESS = "Success!";
	
	public ProductStatusController(ProductStatusService statusService) {
		this.statusService = statusService;
	}

	@GetMapping("productStatus")
    @ApiOperation(value = "Returns the list of all Product Statuses", response = ProductStatusDto.class)
	public ResponseEntity<List<ProductStatusDto>> getProductStatuses() {
		return ResponseEntity.ok(statusService.getProductStatuses());
	}

	@GetMapping("productStatus/{productStatusId}")
	@ApiOperation(value = "Returns Product Status by forwarded ID", notes = "Id of the Product Status is required.", response = ProductStatusDto.class)
	public ResponseEntity<ProductStatusDto> getProductStatusById(@PathVariable("productStatusId") Integer productStatusId) throws Exception {
	
		return ResponseEntity.ok(statusService.getProductStatusById(productStatusId));
	}
	
	@GetMapping("productStatus/statusName/{statusName}")
	@ApiOperation(value = "Returns Product Status by forwarded status name", notes = "Name of the Product Status is required.",  response = ProductStatusDto.class)
	public ResponseEntity<ProductStatusDto> getProductStatusByName(@PathVariable("statusName") String statusName) throws Exception {
	
		return ResponseEntity.ok(statusService.getProductStatusByName(statusName));
	}
	
	@PostMapping("productStatus")
	@ApiOperation(value = "Inserts Product Status in the database", notes = "Request body is required!")
	public ResponseEntity<String> insertProductStatus(@Valid @RequestBody ProductStatusCreateUpdateDto statusCreateDto) {

		statusService.insertProductStatus(statusCreateDto);
		return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("productStatus/{productStatusId}")
	@ApiOperation(value = "Modifies Product Status with forwarded ID", notes = "Request body and Product Status Id are required!")
	public ResponseEntity<String> updateProductStatus(@Valid @RequestBody ProductStatusCreateUpdateDto statusUpdateDto, @PathVariable("productStatusId") Integer productStatusId) {
		
		statusService.modifyProductStatus(statusUpdateDto, productStatusId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping("productStatus/{productStatusId}")
	@ApiOperation(value = "Deletes Product Status with forwarded ID", notes = "Id of the Product Status is required.")
	public ResponseEntity<String> deleteProductStatus(@PathVariable("productStatusId") Integer productStatusId) throws Exception {
		
		statusService.deleteProductStatus(productStatusId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
}
