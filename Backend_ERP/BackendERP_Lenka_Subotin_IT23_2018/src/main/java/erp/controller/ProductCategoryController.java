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

import erp.dto.ProductCategoryCreateUpdateDto;
import erp.dto.ProductCategoryDto;
import erp.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Product Category table in the database TennisWebShop"})
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService categoryService;
	
	private static final String SUCCESS = "Success!";
	
	public ProductCategoryController(ProductCategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("productCategories")
    @ApiOperation(value = "Returns the list of all Product Categories", response = ProductCategoryDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductCategoryDto>> getProductCategories() {
		return ResponseEntity.ok(categoryService.getProductCategories());
	}

	@GetMapping("productCategory/{productCategoryId}")
	@ApiOperation(value = "Returns Product Category by forwarded ID", notes = "Id of the Product Category is required.", response = ProductCategoryDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductCategoryDto> getProductCategoryById(@PathVariable("productCategoryId") Integer productCategoryId) throws Exception {
	
		return ResponseEntity.ok(categoryService.getProductCategoryById(productCategoryId));
	}
	
	@GetMapping("productCategory/categoryName/{categoryName}")
	@ApiOperation(value = "Returns Product Category by forwarded category name", notes = "Name of the Product Category is required.",  response = ProductCategoryDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductCategoryDto> getProductCategoryByName(@PathVariable("categoryName") String categoryName) throws Exception {
	
		return ResponseEntity.ok(categoryService.getProductCategoryByName(categoryName));
	}
	
	@PostMapping("productCategory")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Inserts Product Category in the database", notes = "Request body is required!")
	public ResponseEntity<String> insertProductCategory(@Valid @RequestBody ProductCategoryCreateUpdateDto categoryCreateDto) {

		categoryService.insertProductCategory(categoryCreateDto);
		return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("productCategory/{productCategoryId}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Modifies Product Category with forwarded ID", notes = "Request body and Product Category Id are required!")
	public ResponseEntity<String> updateProductCategory(@Valid @RequestBody ProductCategoryCreateUpdateDto categoryUpdateDto, @PathVariable("productCategoryId") Integer productCategoryId) {
		
		categoryService.modifyProductCategory(categoryUpdateDto, productCategoryId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping("productCategory/{productCategoryId}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Deletes Product Category with forwarded ID", notes = "Id of the Product Category is required.")
	public ResponseEntity<String> deleteProductCategory(@PathVariable("productCategoryId") Integer productCategoryId) throws Exception {
		
		categoryService.deleteProductCategory(productCategoryId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	

}
