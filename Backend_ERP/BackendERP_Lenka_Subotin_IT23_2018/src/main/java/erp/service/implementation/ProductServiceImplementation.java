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
	        if (productCategory == null) {
	            throw new BadRequestException("The inserted ID of Product Category does not exist!");
	        }
		 ProductStatus productStatus = statusRepository.findProductStatusById(productCreateDto.getProductStatusID());
		    if (productStatus == null) {
	            throw new BadRequestException("The inserted ID of Product Status does not exist!");
	        }
		 ProductManufacturer manufacturer = manufacturerRepository.findProductManufacturerById(productCreateDto.getProductManufacturerID());
		    if (manufacturer == null) {
	            throw new BadRequestException("The inserted ID of Product Manufacturer does not exist!");
	        }
		 Admin admin = adminRepository.findAdminById(productCreateDto.getAdminID());
		    if (admin == null) {
	            throw new BadRequestException("The inserted ID of Admin does not exist!");
	        }

	     if(productCreateDto.getProductPriceWithDiscount() == null)
	    	 productCreateDto.setProductPriceWithDiscount(productCreateDto.getProductPrice());
	     if(productCreateDto.getProductName() == null)
	    	 throw new BadRequestException("Product name is required field!");
	     if(productCreateDto.getProductQuantity() == null)
	         throw new BadRequestException("Product quantity is required field!");
	     if(productCreateDto.getProductPrice() == null)
	    	 throw new BadRequestException("Product price is required field!");
	     if(productCreateDto.getProductDiscount() == null)
	         throw new BadRequestException("Product discount is required field!");
	     if(productCreateDto.getProductPrice() <= 0)
	    	 throw new BadRequestException("Product price must be greater than 0!");
	     if(productCreateDto.getProductPriceWithDiscount() <= 0)
	    	 throw new BadRequestException("Product price with discount must be greater than 0!");
	     if(productCreateDto.getProductPrice() < productCreateDto.getProductPriceWithDiscount())
	    	 throw new BadRequestException("Product price must be greater than price with discount!");
	     
	     product.setProductCategory(productCategory);
	     product.setProductManufacturer(manufacturer);
	     product.setProductStatus(productStatus);
	     product.setAdmin(admin);
	     
	     productRepository.save(product);
	     
	}

	@Override
	public void modifyProduct(ProductCreateUpdateDto productCreateDto, Integer productId) {
		
		Product product = productRepository.findProductById(productId);
        if(product == null)
            throw new BadRequestException("Product with forwarded ID does not exist!");
        ProductCategory productCategory = categoryRepository.findProductCategoryById(productCreateDto.getProductCategoryID());
        if (productCategory == null) {
            throw new BadRequestException("The inserted ID of Product Category does not exist!");
        }
	    ProductStatus productStatus = statusRepository.findProductStatusById(productCreateDto.getProductStatusID());
	    if (productStatus == null) {
            throw new BadRequestException("The inserted ID of Product Status does not exist!");
        }
	    ProductManufacturer manufacturer = manufacturerRepository.findProductManufacturerById(productCreateDto.getProductManufacturerID());
	    if (manufacturer == null) {
            throw new BadRequestException("The inserted ID of Product Manufacturer does not exist!");
        }
	    Admin admin = adminRepository.findAdminById(productCreateDto.getAdminID());
	    if (admin == null) {
            throw new BadRequestException("The inserted ID of Admin does not exist!");
        }
	    
	     if(productCreateDto.getProductPriceWithDiscount() == null)
	    	 productCreateDto.setProductPriceWithDiscount(productCreateDto.getProductPrice());
	     if(productCreateDto.getProductName() == null)
	    	 throw new BadRequestException("Product name is required field!");
	     if(productCreateDto.getProductQuantity() == null)
	         throw new BadRequestException("Product quantity is required field!");
	     if(productCreateDto.getProductPrice() == null)
	    	 throw new BadRequestException("Product price is required field!");
	     if(productCreateDto.getProductDiscount() == null)
	         throw new BadRequestException("Product discount is required field!");
	     if(productCreateDto.getProductPrice() <= 0)
	    	 throw new BadRequestException("Product price must be greater than 0!");
	     if(productCreateDto.getProductPriceWithDiscount() <= 0)
	    	 throw new BadRequestException("Product price with discount must be greater than 0!");
	     if(productCreateDto.getProductPrice() < productCreateDto.getProductPriceWithDiscount())
	    	 throw new BadRequestException("Product price must be greater than price with discount!");
	     
	     product.setProductName(productCreateDto.getProductName());
	     product.setProductDescription(productCreateDto.getProductDescription());
	     product.setProductSize(productCreateDto.getProductSize());
	     product.setProductQuantity(productCreateDto.getProductQuantity());
	     product.setProductPrice(productCreateDto.getProductPrice());
	     product.setProductImage(productCreateDto.getProductImage());
	     product.setProductDiscount(productCreateDto.getProductDiscount());
	     product.setProductPriceWithDiscount(productCreateDto.getProductPriceWithDiscount());
	     product.setProductCategory(productCategory);
		 product.setProductManufacturer(manufacturer);
		 product.setProductStatus(productStatus);
		 product.setAdmin(admin);
		     
		 productRepository.save(product);
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
