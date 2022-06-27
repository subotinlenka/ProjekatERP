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

import erp.dto.ProductManufacturerCreateUpdateDto;
import erp.dto.ProductManufacturerDto;
import erp.service.ProductManufacturerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Product Manufacturer table in the database TennisWebShop"})
public class ProductManufacturerController {

	@Autowired
	private ProductManufacturerService manufacturerService;
	
	public ProductManufacturerController(ProductManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}
	
	private static final String SUCCESS = "Success!";

	@GetMapping("productManufacturers")
	@ApiOperation(value = "Returns the list of all Product Manufacturers", response = ProductManufacturerDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductManufacturerDto>> getProductManufacturers() {
		return ResponseEntity.ok(manufacturerService.getProductManufacturers());
	}

	@GetMapping("productManufacturer/{manufacturerId}")
	@ApiOperation(value = "Returns Product Manufacturer by forwarded ID", notes = "Id of the Product Manufacturer is required.", response = ProductManufacturerDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductManufacturerDto> getManufacturerById(@PathVariable("manufacturerId") Integer manufacturerId) throws Exception {
	
		return ResponseEntity.ok(manufacturerService.getManufacturerById(manufacturerId));
	}
	
	@GetMapping("productManufacturer/manufacturerName/{manufacturerName}")
	@ApiOperation(value = "Returns Product Manufacturer by forwarded name", notes = "Name of the Product Manufacturer is required.",  response = ProductManufacturerDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductManufacturerDto> getManufacturerByName(@PathVariable("manufacturerName") String manufacturerName) throws Exception {
	
		return ResponseEntity.ok(manufacturerService.getManufacturerByName(manufacturerName));
	}
	
	@PostMapping("productManufacturer")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Inserts Product Manufacturer in the database", notes = "Request body is required!")
	public ResponseEntity<String> insertProductManufacturer(@Valid @RequestBody ProductManufacturerCreateUpdateDto manufacturerCreateDto) {

		manufacturerService.insertProductManufacturer(manufacturerCreateDto);
		return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("productManufacturer/{manufacturerId}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Modifies Product Manufacturer with forwarded ID", notes = "Request body and Product Manufacturer Id are required!")
	public ResponseEntity<String> updateProductManufacturer(@Valid @RequestBody ProductManufacturerCreateUpdateDto manufacturerUpdateDto, @PathVariable("manufacturerId") Integer manufacturerId) {
		
		manufacturerService.modifyProductManufacturer(manufacturerUpdateDto, manufacturerId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping("productManufacturer/{manufacturerId}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Deletes Product Manufacturer with forwarded ID", notes = "Id of the Product Manufacturer is required.")
	public ResponseEntity<String> deleteProductManufacturer(@PathVariable("manufacturerId") Integer manufacturerId) throws Exception {
		
		manufacturerService.deleteProductManufacturer(manufacturerId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	

}
