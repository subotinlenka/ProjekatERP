package erp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.ProductStatusCreateUpdateDto;
import erp.dto.ProductStatusDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.ProductStatus;
import erp.repository.ProductStatusRepository;
import erp.service.ProductStatusService;

@Service
public class ProductStatusServiceImplementation implements ProductStatusService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductStatusRepository productStatusRepository;
	
	
	public ProductStatusServiceImplementation(ModelMapper modelMapper, ProductStatusRepository productStatusRepository) {
		this.modelMapper = modelMapper;
		this.productStatusRepository = productStatusRepository;
	}

	@Override
	public List<ProductStatusDto> getProductStatuses() {
		return productStatusRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public ProductStatusDto getProductStatusById(Integer productStatusId) throws Exception {
		
		ProductStatus status = productStatusRepository.findProductStatusById(productStatusId);
        if(status != null)
        {
            return convertEntityToDto(status);
        }
        throw new NotFoundException("Product Status with forwarded ID does not exist!");
	}

	@Override
	public ProductStatusDto getProductStatusByName(String productStatusName) {
		
		ProductStatus status = productStatusRepository.findProductStatusByName(productStatusName);
        if(status != null)
        {
            return convertEntityToDto(status);
        }
        throw new NotFoundException("Product Status with forwarded status name does not exist!");
	}

	@Override
	public void insertProductStatus(ProductStatusCreateUpdateDto productStatusCreateDto) {
		if(productStatusCreateDto.getProductStatusName() == null)
        	throw new BadRequestException("Product Status name is required field!");
		productStatusRepository.save(convertCreateUpdateDtoToEntity(productStatusCreateDto));		
		
	}

	@Override
	public void modifyProductStatus(ProductStatusCreateUpdateDto productStatusCreateDto, Integer productStatusId) {
		
		ProductStatus status = productStatusRepository.findProductStatusById(productStatusId);
        if(status == null)
            throw new NotFoundException("Product Status with forwarded ID does not exist!");
        status.setProductStatusName(productStatusCreateDto.getProductStatusName());
        
        if(productStatusCreateDto.getProductStatusName()== null)
        	throw new BadRequestException("Product Status name is required field!");
		
        productStatusRepository.save(status);
	}

	@Override
	public void deleteProductStatus(Integer productStatusId) throws Exception {
		
		ProductStatus status = productStatusRepository.findProductStatusById(productStatusId);
        if (status != null) {
        	try {
        		productStatusRepository.deleteProductStatusById(productStatusId);
        	}catch(Exception e) {
        		throw new ConflictException("Product Status with forwarded ID is used in other table!");
        	}
        }
        else
            throw new NotFoundException("Product Status with forwarded ID does not exist!");
		
	}
	
	//Mapping ProductStatus Entity to ProductStatusDto
	public ProductStatusDto convertEntityToDto(ProductStatus status) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProductStatusDto statusDto = this.modelMapper.map(status, ProductStatusDto.class);
		return statusDto;	
	}
		
	//Mapping ProductStatusDto to ProductStatus Entity
	public ProductStatus convertDtoToEntity(ProductStatusDto statusDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProductStatus status = this.modelMapper.map(statusDto, ProductStatus.class);
		return status;
	}
		
	//Mapping ProductStatus Entity to ProductStatusCreateUpdateDto
	public ProductStatusCreateUpdateDto convertEntityToCreateUpdateDto(ProductStatus status) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		ProductStatusCreateUpdateDto statusCreateUpdateDto = this.modelMapper.map(status, ProductStatusCreateUpdateDto.class);
		return statusCreateUpdateDto;	
	}

	//Mapping ProductStatusCreateUpdateDto to ProductStatus Entity
	public ProductStatus convertCreateUpdateDtoToEntity(ProductStatusCreateUpdateDto statusCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		ProductStatus status = this.modelMapper.map(statusCreateUpdateDto, ProductStatus.class);
		return status;
	}

}
