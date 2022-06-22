package erp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.ProductManufacturerCreateUpdateDto;
import erp.dto.ProductManufacturerDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.ProductManufacturer;
import erp.repository.ProductManufacturerRepository;
import erp.service.ProductManufacturerService;

@Service
public class ProductManufacturerServiceImplementation implements ProductManufacturerService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductManufacturerRepository manufacturerRepository;
	
	public ProductManufacturerServiceImplementation(ProductManufacturerRepository manufacturerRepository, ModelMapper modelMapper) {
		this.manufacturerRepository = manufacturerRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<ProductManufacturerDto> getProductManufacturers() {
		return manufacturerRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public ProductManufacturerDto getManufacturerById(Integer manufacturerId) throws Exception {
		
		ProductManufacturer manufacturer = manufacturerRepository.findProductManufacturerById(manufacturerId);
        if(manufacturer != null)
        {
            return convertEntityToDto(manufacturer);
        }
        throw new NotFoundException("Product manufacturer with forwarded ID does not exist!");
	}

	@Override
	public ProductManufacturerDto getManufacturerByName(String manufacturerName) {
		
		ProductManufacturer manufacturer = manufacturerRepository.findProductManufacturerByName(manufacturerName);
        if(manufacturer != null)
        {
            return convertEntityToDto(manufacturer);
        }
        throw new NotFoundException("Product manufacturer with forwarded name does not exist!");
	}

	@Override
	public void insertProductManufacturer(ProductManufacturerCreateUpdateDto manufacturerCreateDto) {
		
		if(manufacturerCreateDto.getManufacturerName() == null)
        	throw new BadRequestException("Product manufacturer name is required field!");
        else if(manufacturerCreateDto.getManufacturerPhoneNumber() == null)
        	throw new BadRequestException("Product manufacturer phone number is required field!");
        else if(manufacturerCreateDto.getManufacturerAddress() == null)
        	throw new BadRequestException("Product manufacturer address is required field!");
        else if(manufacturerCreateDto.getManufacturerCity() == null)
        	throw new BadRequestException("Product manufacturer city is required field!");
        else if(manufacturerCreateDto.getManufacturerCountry() == null)
        	throw new BadRequestException("Product manufacturer country is required field!");
        else if(manufacturerCreateDto.getManufacturerEmail() == null)
        	throw new BadRequestException("Product manufacturer email is required field!");
		
		 manufacturerRepository.save(convertCreateUpdateDtoToEntity(manufacturerCreateDto));
		
	}

	@Override
	public void modifyProductManufacturer(ProductManufacturerCreateUpdateDto manufacturerUpdateDto,
			Integer manufacturerId) {
		
		ProductManufacturer manufacturer = manufacturerRepository.findProductManufacturerById(manufacturerId);
        if(manufacturer == null)
            throw new NotFoundException("Product manufacturer with forwarded ID does not exist!");
        manufacturer.setManufacturerName(manufacturerUpdateDto.getManufacturerName());
        manufacturer.setManufacturerPhoneNumber(manufacturerUpdateDto.getManufacturerPhoneNumber());
        manufacturer.setManufacturerAddress(manufacturerUpdateDto.getManufacturerAddress());
        manufacturer.setManufacturerCity(manufacturerUpdateDto.getManufacturerCity());
        manufacturer.setManufacturerCountry(manufacturerUpdateDto.getManufacturerCountry());
        manufacturer.setManufacturerEmail(manufacturerUpdateDto.getManufacturerEmail());
        
        if(manufacturerUpdateDto.getManufacturerName() == null)
        	throw new BadRequestException("Product manufacturer name is required field!");
        else if(manufacturerUpdateDto.getManufacturerPhoneNumber() == null)
        	throw new BadRequestException("Product manufacturer phone number is required field!");
        else if(manufacturerUpdateDto.getManufacturerAddress() == null)
        	throw new BadRequestException("Product manufacturer address is required field!");
        else if(manufacturerUpdateDto.getManufacturerCity() == null)
        	throw new BadRequestException("Product manufacturer city is required field!");
        else if(manufacturerUpdateDto.getManufacturerCountry() == null)
        	throw new BadRequestException("Product manufacturer country is required field!");
        else if(manufacturerUpdateDto.getManufacturerEmail() == null)
        	throw new BadRequestException("Product manufacturer email is required field!");
		
		 manufacturerRepository.save(manufacturer);	
	}

	@Override
	public void deleteProductManufacturer(Integer manufacturerId) throws Exception {
		
		ProductManufacturer manufacturer = manufacturerRepository.findProductManufacturerById(manufacturerId);
	    if (manufacturer != null) {
	        try {
	        	manufacturerRepository.deleteProductManufacturerById(manufacturerId);
	        }catch(Exception e) {
	        	throw new ConflictException("Product Manufacturer with forwarded ID is used in other table!");
	        }
	    }else
	    	throw new NotFoundException("Product Manufacturer with forwarded ID does not exist!");
		
	}
	
	//Mapping ProductManufacturer Entity to ProductManufacturerDto
	public ProductManufacturerDto convertEntityToDto(ProductManufacturer manufacturer) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProductManufacturerDto manufacturerDto = this.modelMapper.map(manufacturer, ProductManufacturerDto.class);
		return manufacturerDto;	
	}
		
	//Mapping ProductManufacturerDto to ProductManufacturer Entity
	public ProductManufacturer convertDtoToEntity(ProductManufacturerDto manufacturerDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		ProductManufacturer manufacturer = this.modelMapper.map(manufacturerDto, ProductManufacturer.class);
		return manufacturer;
	}
		
	//Mapping ProductManufacturer Entity to ProductManufacturerCreateUpdateDto
	public ProductManufacturerCreateUpdateDto convertEntityToCreateUpdateDto(ProductManufacturer manufacturer) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		ProductManufacturerCreateUpdateDto manufacturerCreateUpdateDto = this.modelMapper.map(manufacturer, ProductManufacturerCreateUpdateDto.class);
		return manufacturerCreateUpdateDto;	
	}
	
	//Mapping ProductManufacturerCreateUpdateDto to ProductManufacturer Entity
	public ProductManufacturer convertCreateUpdateDtoToEntity(ProductManufacturerCreateUpdateDto manufacturerCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		ProductManufacturer manufacturer = this.modelMapper.map(manufacturerCreateUpdateDto, ProductManufacturer.class);
		return manufacturer;
	}

}
