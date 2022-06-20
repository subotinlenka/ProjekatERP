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

import erp.model.ProductManufacturer;
import erp.repository.ProductManufacturerRepository;

@CrossOrigin
@RestController
public class ProductManufacturerController {

	@Autowired
	private ProductManufacturerRepository productManufacturerRepository;
	
	@GetMapping("productManufacturer")
	public Collection<ProductManufacturer> getProductManufacturers() {
		return productManufacturerRepository.findAll();
	}

	@GetMapping("productManufacturer/{productManufacturerID}")
	public ProductManufacturer getProductManufacturer(@PathVariable("productManufacturerID") Integer productManufacturerID) {
		
		return productManufacturerRepository.getById(productManufacturerID);
	}
	
	/*@GetMapping("productManufacturerName/{manufacturerName}")
	public Collection<ProductManufacturer> getManufacturerByName(@PathVariable("manufacturerName") String manufacturerName) {
		return productManufacturerRepository.findByManufacturerNameContainingIgnoreCase(manufacturerName);
	}*/
	
	@PostMapping("productManufacturer")
	public ResponseEntity<ProductManufacturer> insertProductManufacturer(@RequestBody ProductManufacturer productManufacturer) {
		if (!productManufacturerRepository.existsById(productManufacturer.getProductManufacturerId())) {
			productManufacturerRepository.save(productManufacturer);
			return new ResponseEntity<ProductManufacturer>(HttpStatus.OK); 
		}
		return new ResponseEntity<ProductManufacturer>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("productManufacturer")
	public ResponseEntity<ProductManufacturer> updateProductManufacturer(@RequestBody ProductManufacturer productManufacturer) {
		if (!productManufacturerRepository.existsById(productManufacturer.getProductManufacturerId()))
			return new ResponseEntity<ProductManufacturer>(HttpStatus.CONFLICT);
		productManufacturerRepository.save(productManufacturer);
		return new ResponseEntity<ProductManufacturer>(HttpStatus.OK);
	}
	
	@DeleteMapping("productManufacturer/{productManufacturerID}")
	public ResponseEntity<ProductManufacturer> deleteProductManufacturer(@PathVariable Integer productManufacturerID)  {
		if (!productManufacturerRepository.existsById(productManufacturerID))
			return new ResponseEntity<ProductManufacturer>(HttpStatus.NO_CONTENT);
		productManufacturerRepository.deleteById(productManufacturerID);
		return new ResponseEntity<ProductManufacturer>(HttpStatus.OK);
	}
}
