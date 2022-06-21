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
import erp.exception.NotFoundException;
import erp.model.Admin;
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
		super();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProductCategory(Integer productCategoryId) throws Exception {
		// TODO Auto-generated method stub
		
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
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
		ProductCategoryCreateUpdateDto categoryCreateUpdateDto = this.modelMapper.map(category, ProductCategoryCreateUpdateDto.class);
		return categoryCreateUpdateDto;	
	}

	//Mapping ProductCategoryCreateUpdateDto to ProductCategory Entity
	public ProductCategory convertCreateUpdateDtoToEntity(ProductCategoryCreateUpdateDto categoryCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setAmbiguityIgnored(true);
		ProductCategory category = this.modelMapper.map(categoryCreateUpdateDto, ProductCategory.class);
		return category;
	}

}
