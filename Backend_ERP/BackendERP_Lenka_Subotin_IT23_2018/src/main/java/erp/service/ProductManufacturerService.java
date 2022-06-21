package erp.service;

import java.util.List;

import erp.dto.ProductManufacturerCreateUpdateDto;
import erp.dto.ProductManufacturerDto;

public interface ProductManufacturerService {

	 List<ProductManufacturerDto> getProductManufacturers();
	 ProductManufacturerDto getManufacturerById(Integer manufacturerId) throws Exception; 
	 ProductManufacturerDto getManufacturerByName(String manufacturerName);
	 void insertProductManufacturer(ProductManufacturerCreateUpdateDto manufacturerCreateDto);
	 void modifyProductManufacturer(ProductManufacturerCreateUpdateDto manufacturerUpdateDto, Integer manufacturerId);
	 void deleteProductManufacturer(Integer manufacturerId) throws Exception; 
}
