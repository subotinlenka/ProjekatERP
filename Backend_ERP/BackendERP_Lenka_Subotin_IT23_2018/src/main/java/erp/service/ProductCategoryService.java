package erp.service;

import java.util.List;

import erp.dto.ProductCategoryCreateUpdateDto;
import erp.dto.ProductCategoryDto;

public interface ProductCategoryService {

	 List<ProductCategoryDto> getProductCategories();
	 ProductCategoryDto getProductCategoryById(Integer productCategoryId) throws Exception; 
	 ProductCategoryDto getProductCategoryByName(String productCategoryName);
	 void insertProductCategory(ProductCategoryCreateUpdateDto categoryCreateDto);
	 void modifyProductCategory(ProductCategoryCreateUpdateDto categoryCreateDto, Integer productCategoryId);
	 void deleteProductCategory(Integer productCategoryId) throws Exception; 
}
