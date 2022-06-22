package erp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.ProductCategoryCreateUpdateDto;
import erp.dto.ProductCategoryDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.ProductCategory;
import erp.repository.ProductCategoryRepository;
import erp.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImplementation implements ProductCategoryService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductCategoryRepository categoryRepository;
	
	public ProductCategoryServiceImplementation(ModelMapper modelMapper, ProductCategoryRepository categoryRepository) {
		this.modelMapper = modelMapper;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<ProductCategoryDto> getProductCategories() {
		return categoryRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public ProductCategoryDto getProductCategoryById(Integer productCategoryId) throws Exception {
		ProductCategory category = categoryRepository.findProductCategoryById(productCategoryId);
        if(category != null)
        {
            return convertEntityToDto(category);
        }
        throw new NotFoundException("Product Category with forwarded ID does not exist!");
	}

	@Override
	public ProductCategoryDto getProductCategoryByName(String productCategoryName) {
		ProductCategory category = categoryRepository.findProductCategoryByName(productCategoryName);
        if(category != null)
        {
            return convertEntityToDto(category);
        }
        throw new NotFoundException("Product Category with forwarded category name does not exist!");
	}

	@Override
	public void insertProductCategory(ProductCategoryCreateUpdateDto categoryCreateDto) {
		
		if(categoryCreateDto.getProductCategoryName() == null)
        	throw new BadRequestException("Product Category name is required field!");
		
		categoryRepository.save(convertCreateUpdateDtoToEntity(categoryCreateDto));		
	}

	@Override
	public void modifyProductCategory(ProductCategoryCreateUpdateDto categoryCreateDto, Integer productCategoryId) {
		
		ProductCategory category = categoryRepository.findProductCategoryById(productCategoryId);
        if(category == null)
            throw new NotFoundException("Product Category with forwarded ID does not exist!");
        category.setProductCategoryName(categoryCreateDto.getProductCategoryName());
        
        if(categoryCreateDto.getProductCategoryName()== null)
        	throw new BadRequestException("Product Category name is required field!");
		
        categoryRepository.save(category);
		
	}

	@Override
	public void deleteProductCategory(Integer productCategoryId) throws Exception {
		
		ProductCategory category = categoryRepository.findProductCategoryById(productCategoryId);
        if (category != null) {
        	try {
        		categoryRepository.deleteProductCategoryById(productCategoryId);
        	}catch(Exception e) {
        		throw new ConflictException("Product Category with forwarded ID is used in other table!");
        	}
        }
        else
            throw new NotFoundException("Product Category with forwarded ID does not exist!");
		
	}
	
	//Mapping ProductCategory Entity to ProductCategoryDto
	public ProductCategoryDto convertEntityToDto(ProductCategory category) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProductCategoryDto categoryDto = this.modelMapper.map(category, ProductCategoryDto.class);
		return categoryDto;	
	}
	
	//Mapping ProductCategoryDto to ProductCategory Entity
	public ProductCategory convertDtoToEntity(ProductCategoryDto categoryDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProductCategory category = this.modelMapper.map(categoryDto, ProductCategory.class);
		return category;
	}
	
	//Mapping ProductCategory Entity to ProductCategoryCreateUpdateDto
	public ProductCategoryCreateUpdateDto convertEntityToCreateUpdateDto(ProductCategory category) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		ProductCategoryCreateUpdateDto categoryCreateUpdateDto = this.modelMapper.map(category, ProductCategoryCreateUpdateDto.class);
		return categoryCreateUpdateDto;	
	}

	//Mapping ProductCategoryCreateUpdateDto to ProductCategory Entity
	public ProductCategory convertCreateUpdateDtoToEntity(ProductCategoryCreateUpdateDto categoryCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		ProductCategory category = this.modelMapper.map(categoryCreateUpdateDto, ProductCategory.class);
		return category;
	}

}
