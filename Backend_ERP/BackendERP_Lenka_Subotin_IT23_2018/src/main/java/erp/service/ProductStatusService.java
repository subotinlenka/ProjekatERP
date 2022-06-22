package erp.service;

import java.util.List;

import erp.dto.ProductStatusCreateUpdateDto;
import erp.dto.ProductStatusDto;

public interface ProductStatusService {
	
	 List<ProductStatusDto> getProductStatuses();
	 ProductStatusDto getProductStatusById(Integer productStatusId) throws Exception; 
	 ProductStatusDto getProductStatusByName(String productStatusName);
	 void insertProductStatus(ProductStatusCreateUpdateDto productStatusCreateDto);
	 void modifyProductStatus(ProductStatusCreateUpdateDto productStatusCreateDto, Integer productStatusId);
	 void deleteProductStatus(Integer productStatusId) throws Exception; 

}
