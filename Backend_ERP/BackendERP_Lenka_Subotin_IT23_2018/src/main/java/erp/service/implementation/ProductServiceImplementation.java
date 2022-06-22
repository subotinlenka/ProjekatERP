package erp.service.implementation;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.ProductCreateUpdateDto;
import erp.dto.ProductDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.Admin;
import erp.model.Product;
import erp.model.ProductCategory;
import erp.model.ProductManufacturer;
import erp.model.ProductStatus;
import erp.repository.AdminRepository;
import erp.repository.ProductCategoryRepository;
import erp.repository.ProductManufacturerRepository;
import erp.repository.ProductRepository;
import erp.repository.ProductStatusRepository;
import erp.service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	ProductStatusRepository statusRepository;
	
	@Autowired
	ProductCategoryRepository categoryRepository;
	
	@Autowired
	ProductManufacturerRepository manufacturerRepository;
	
	@Autowired
	AdminRepository adminRepository;

	@Override
	public List<ProductDto> getProducts() {
		return productRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(Integer productId) throws Exception {
		Product product = productRepository.findProductById(productId);
        if(product != null)
        {
            return convertEntityToDto(product);
        }
        throw new NotFoundException("Product with forwarded ID does not exist!");
	}

	@Override
	public List<ProductDto> getProductsByCategory(Integer productCategoryId) {
		List<Product> products = productRepository.findProductsByProductCategory(productCategoryId);
		Type listType = new TypeToken<List<ProductDto>>(){}.getType();
		List<ProductDto> productDtoList = modelMapper.map(products,listType);
		if(!products.isEmpty())
        {
            return productDtoList;
        }
        throw new NotFoundException("Product Category with forwarded ID does not exist!");
	}

	@Override
	public List<ProductDto> getProductsByStatus(Integer productStatusId) {
		List<Product> products = productRepository.findProductsByProductStatus(productStatusId);
		Type listType = new TypeToken<List<ProductDto>>(){}.getType();
		List<ProductDto> productDtoList = modelMapper.map(products,listType);
		if(!products.isEmpty())
        {
            return productDtoList;
        }
        throw new NotFoundException("Product Status with forwarded ID does not exist!");
	}

	@Override
	public List<ProductDto> getProductsByManufacturer(Integer ProductId) {
		List<Product> products = productRepository.findProductsByProductManufacturer(ProductId);
		Type listType = new TypeToken<List<ProductDto>>(){}.getType();
		List<ProductDto> productDtoList = modelMapper.map(products,listType);
		if(!products.isEmpty())
        {
            return productDtoList;
        }
        throw new NotFoundException("Product Manufacturer with forwarded ID does not exist!");
	}

	@Override
	public void insertProduct(ProductCreateUpdateDto productCreateDto) {
		
		 Product product = convertCreateUpdateDtoToEntity(productCreateDto); 
		
	     ProductCategory productCategory = categoryRepository.findProductCategoryById(productCreateDto.getProductCategoryID());
	     ProductStatus productStatus = statusRepository.findProductStatusById(productCreateDto.getProductStatusID());
	     ProductManufacturer manufacturer = manufacturerRepository.findProductManufacturerById(productCreateDto.getProductManufacturerID());
	     Admin admin = adminRepository.findAdminById(productCreateDto.getAdminID());
	     
	     if(productCreateDto.getProductName() == null)
	    	 throw new BadRequestException("Product name is required field!");
	     else if(productCreateDto.getProductQuantity() == null)
	         throw new BadRequestException("Product quantity is required field!");
	     else if(productCreateDto.getProductPrice() == null)
	    	 throw new BadRequestException("Product price is required field!");
	     else if(productCreateDto.getProductDiscount() == null)
	         throw new BadRequestException("Product discount is required field!");
	     else if(productCategory == null || productStatus == null || manufacturer == null || admin == null)
	    	 throw new BadRequestException("Product Category, Product Status, Product Manufacturer and Admin can not be null values!");
	     else if(productCreateDto.getProductPrice() <= 0)
	    	 throw new BadRequestException("Product price must be greater than 0!");
	     else if(productCreateDto.getDiscountAmount() <= 0)
	    	 throw new BadRequestException("Discount amount of the Product must be greater than 0!");
	  
	     product.setProductCategory(productCategory);
	     product.setProductManufacturer(manufacturer);
	     product.setProductStatus(productStatus);
	     product.setAdmin(admin);
	     
	     productRepository.save(product);
	     
	     throw new BadRequestException("Product Manufacturer, Product Status, Product Manufacturer or Admin with forwarded ID does not exist!");
		
	}

	@Override
	public void modifyProduct(ProductCreateUpdateDto productCreateDto, Integer productId) {
		
		
	}

	@Override
	public void deleteProduct(Integer productId) throws Exception {
		
		Product product = productRepository.findProductById(productId);
	    if (product != null) {
	        try {
	        	productRepository.deleteProductById(productId);
	        }catch(Exception e) {
	        	throw new ConflictException("Product with forwarded ID is used in other table!");
	        }
	    }
	    else
	    	throw new NotFoundException("Product with forwarded ID does not exist!");
	}
	
	//Mapping Product Entity to ProductDto
	public ProductDto convertEntityToDto(Product product) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProductDto productDto = this.modelMapper.map(product, ProductDto.class);
		return productDto;	
	}
			
	//Mapping ProductDto to Product Entity
	public Product convertDtoToEntity(ProductDto productDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Product product = this.modelMapper.map(productDto, Product.class);
		return product;
	}
		
	//Mapping Product Entity to ProductCreateUpdateDto
	public ProductCreateUpdateDto convertEntityToCreateUpdateDto(Product product) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		ProductCreateUpdateDto productCreateUpdateDto = this.modelMapper.map(product, ProductCreateUpdateDto.class);
		return productCreateUpdateDto;	
	}
		
	//Mapping ProductCreateUpdateDto to Product Entity
	public Product convertCreateUpdateDtoToEntity(ProductCreateUpdateDto productCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		Product product = this.modelMapper.map(productCreateUpdateDto, Product.class);
		return product;
	}

}
