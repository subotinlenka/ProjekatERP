package erp.service;

import java.util.List;

import erp.dto.ProductCreateUpdateDto;
import erp.dto.ProductDto;

public interface ProductService {

	 List<ProductDto> getProducts();
	 ProductDto getProductById(Integer productId) throws Exception; 
	 List<ProductDto> getProductsByCategory(Integer productCategoryId);
	 List<ProductDto> getProductsByStatus(Integer productStatusId);
	 List<ProductDto> getProductsByManufacturer(Integer productManufacturerId);
	 void insertProduct(ProductCreateUpdateDto productCreateDto);
	 void modifyProduct(ProductCreateUpdateDto productCreateDto, Integer productId);
	 void deleteProduct(Integer productId) throws Exception; 
}
