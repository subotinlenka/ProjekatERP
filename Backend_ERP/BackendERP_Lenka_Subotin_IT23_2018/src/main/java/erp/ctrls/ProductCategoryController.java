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

import erp.jpa.ProductCategory;
import erp.repository.ProductCategoryRepository;


@RestController
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	@GetMapping("productCategory")
	public Collection<ProductCategory> getProductCategories() {
		return productCategoryRepository.findAll();
	}

	@GetMapping("productCategory/{productCategoryID}")
	public ProductCategory getProductCategory(@PathVariable("productCategoryID") Integer productCategoryID) {
		
		return productCategoryRepository.getById(productCategoryID);
	}
	
	@PostMapping("productCategory")
	public ResponseEntity<ProductCategory> insertProductCategory(@RequestBody ProductCategory productCategory) {
		if (!productCategoryRepository.existsById(productCategory.getProductCategoryId())) {
			productCategoryRepository.save(productCategory);
			return new ResponseEntity<ProductCategory>(HttpStatus.OK); 
		}
		return new ResponseEntity<ProductCategory>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("productCategory")
	public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory) {
		if (!productCategoryRepository.existsById(productCategory.getProductCategoryId()))
			return new ResponseEntity<ProductCategory>(HttpStatus.CONFLICT);
		productCategoryRepository.save(productCategory);
		return new ResponseEntity<ProductCategory>(HttpStatus.OK);
	}
	
	@DeleteMapping("productCategory/{productCategoryID}")
	public ResponseEntity<ProductCategory> deleteProductCategory(@PathVariable Integer productCategoryID)  {
		if (!productCategoryRepository.existsById(productCategoryID))
			return new ResponseEntity<ProductCategory>(HttpStatus.NO_CONTENT);
		productCategoryRepository.deleteById(productCategoryID);
		return new ResponseEntity<ProductCategory>(HttpStatus.OK);
	}

}
