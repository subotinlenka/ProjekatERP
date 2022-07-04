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

import erp.dto.ProductCreateUpdateDto;
import erp.dto.ProductDto;
import erp.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Product table in the database TennisWebShop"})
public class ProductController {

	@Autowired
	private ProductService productService;
	
	private static final String SUCCESS = "Success!";
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("products")
    @ApiOperation(value = "Returns the list of all Products", response = ProductDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getProducts() {
		return ResponseEntity.ok(productService.getProducts());
	}

	@GetMapping("product/{productId}")
	@ApiOperation(value = "Returns Product by forwarded ID", notes = "Id of the Product is required.", response = ProductDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Integer productId) throws Exception {
	
		return ResponseEntity.ok(productService.getProductById(productId));
	}
	
	@GetMapping("products/category/{productCategoryId}")
	@ApiOperation(value = "Return Product(s) by forwarded Product Category", notes = "Id of the Product Category is required.",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable("productCategoryId") Integer productCategoryId) throws Exception {
	
		return ResponseEntity.ok(productService.getProductsByCategory(productCategoryId));
	}
	
	@GetMapping("products/status/{productStatusId}")
	@ApiOperation(value = "Return Product(s) by forwarded Product Status", notes = "Id of the Product Status is required.",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getProductsByStatus(@PathVariable("productStatusId") Integer productStatusId) throws Exception {
	
		return ResponseEntity.ok(productService.getProductsByStatus(productStatusId));
	}
	
	@GetMapping("products/manufacturer/{manufacturerId}")
	@ApiOperation(value = "Return Product(s) by forwarded Product Manufacturer", notes = "Id of the Product Manufacturer is required.",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getProductsByManufacturer(@PathVariable("manufacturerId") Integer manufacturerId) throws Exception {
	
		return ResponseEntity.ok(productService.getProductsByManufacturer(manufacturerId));
	}
	
	@Secured("Admin")
	@PostMapping("product")
	@ApiOperation(value = "Inserts Product in the database", notes = "Request body is required!")
	public ResponseEntity<String> insertProduct(@Valid @RequestBody ProductCreateUpdateDto productCreateDto) {

		productService.insertProduct(productCreateDto);
		return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	}
	
	@Secured("Admin")
	@PutMapping("product/{productId}")
	@ApiOperation(value = "Modifies Product with forwarded ID", notes = "Request body and Product Id are required!")
	public ResponseEntity<String> updateProduct(@Valid @RequestBody ProductCreateUpdateDto productCreateDto, @PathVariable("productId") Integer productId) {
		
		productService.modifyProduct(productCreateDto, productId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@Secured("Admin")
	@DeleteMapping("product/{productId}")
	@ApiOperation(value = "Deletes Product with forwarded ID", notes = "Id of the Product is required.")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId) throws Exception {
		productService.deleteProduct(productId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
}
