package erp.controller;

import java.util.Collection;

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

import erp.model.ProductStatus;
import erp.repository.ProductStatusRepository;

@CrossOrigin
@RestController
public class ProductStatusController {

	@Autowired
	private ProductStatusRepository productStatusRepository;
	
	@GetMapping("productStatus")
	public Collection<ProductStatus> getProductStatuses() {
		return productStatusRepository.findAll();
	}

	@GetMapping("productStatus/{productStatusID}")
	public ProductStatus getProductStatus(@PathVariable("productStatusID") Integer productStatusID) {
		
		return productStatusRepository.getById(productStatusID);
	}
	
	@PostMapping("productStatus")
	public ResponseEntity<ProductStatus> insertProductStatus(@RequestBody ProductStatus productStatus) {
		if (!productStatusRepository.existsById(productStatus.getProductStatusId())) {
			productStatusRepository.save(productStatus);
			return new ResponseEntity<ProductStatus>(HttpStatus.OK); 
		}
		return new ResponseEntity<ProductStatus>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("productStatus")
	public ResponseEntity<ProductStatus> updateProductStatus(@RequestBody ProductStatus productStatus) {
		if (!productStatusRepository.existsById(productStatus.getProductStatusId()))
			return new ResponseEntity<ProductStatus>(HttpStatus.CONFLICT);
		productStatusRepository.save(productStatus);
		return new ResponseEntity<ProductStatus>(HttpStatus.OK);
	}
	
	@DeleteMapping("productStatus/{productStatusID}")
	public ResponseEntity<ProductStatus> deleteProductStatus(@PathVariable Integer productStatusID)  {
		if (!productStatusRepository.existsById(productStatusID))
			return new ResponseEntity<ProductStatus>(HttpStatus.NO_CONTENT);
		productStatusRepository.deleteById(productStatusID);
		return new ResponseEntity<ProductStatus>(HttpStatus.OK);
	}
}
