package erp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.AdminCreateUpdateDto;
import erp.dto.AdminDto;
import erp.exception.BadRequestException;
import erp.exception.ConflictException;
import erp.exception.NotFoundException;
import erp.model.Admin;
import erp.repository.AdminRepository;
import erp.service.AdminService;

@Service
public class AdminServiceImplementation implements AdminService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AdminRepository adminRepository;
	
	public AdminServiceImplementation(AdminRepository adminRepository, ModelMapper modelMapper) {
		this.adminRepository = adminRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<AdminDto> getAdmins() {
        return adminRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	@Override
	public AdminDto getAdminById(Integer adminId) throws Exception {
		Admin admin = adminRepository.findAdminById(adminId);
        if(admin != null)
        {
            return convertEntityToDto(admin);
        }
        throw new NotFoundException("Admin with forwarded ID does not exist!");
	}

	@Override
	public AdminDto getAdminByAdminUserName(String adminUserName) {
		Admin admin = adminRepository.findAdminByUserName(adminUserName);
        if(admin != null)
        {
            return convertEntityToDto(admin);
        }
        throw new NotFoundException("Admin with forwarded user name does not exist!");
	}
	
	@Override
	public void insertAdmin(AdminCreateUpdateDto adminCreateDto) {
		
		if(adminCreateDto.getAdminFirstName() == null)
        	throw new BadRequestException("Admin first name is required field!");
        else if(adminCreateDto.getAdminLastName() == null)
        	throw new BadRequestException("Admin last name is required field!");
        else if(adminCreateDto.getAdminDateOfBirth() == null)
        	throw new BadRequestException("Admin date of birth is required field!");
        else if(adminCreateDto.getAdminAddress() == null)
        	throw new BadRequestException("Admin address is required field!");
        else if(adminCreateDto.getAdminCity() == null)
        	throw new BadRequestException("Admin city is required field!");
        else if(adminCreateDto.getAdminCountry() == null)
        	throw new BadRequestException("Admin country is required field!");
        else if(adminCreateDto.getAdminEmail() == null)
        	throw new BadRequestException("Admin email is required field!");
        else if(adminCreateDto.getAdminUserName() == null)
        	throw new BadRequestException("Admin user name is required field!");
        else if(adminCreateDto.getAdminPassword() == null)
        	throw new BadRequestException("Admin password is required field!");
		
		 adminRepository.save(convertCreateUpdateDtoToEntity(adminCreateDto));
	}

	@Override
	public void modifyAdmin(AdminCreateUpdateDto adminUpdateDto, Integer adminId) {
		Admin admin = adminRepository.findAdminById(adminId);
        if(admin == null)
            throw new NotFoundException("Admin with forwarded ID does not exist!");
        admin.setAdminFirstName(adminUpdateDto.getAdminFirstName());
        admin.setAdminLastName(adminUpdateDto.getAdminLastName());
        admin.setAdminDateOfBirth(adminUpdateDto.getAdminDateOfBirth());
        admin.setAdminPhoneNumber(adminUpdateDto.getAdminPhoneNumber());
        admin.setAdminAddress(adminUpdateDto.getAdminAddress());
        admin.setAdminCity(adminUpdateDto.getAdminCity());
        admin.setAdminCountry(adminUpdateDto.getAdminCountry());
        admin.setAdminEmail(adminUpdateDto.getAdminEmail());
        admin.setAdminUserName(adminUpdateDto.getAdminUserName());
        admin.setAdminPassword(adminUpdateDto.getAdminPassword());
      
        if(admin.getAdminFirstName() == null)
        	throw new BadRequestException("Admin first name is required field!");
        else if(admin.getAdminLastName() == null)
        	throw new BadRequestException("Admin last name is required field!");
        else if(admin.getAdminDateOfBirth() == null)
        	throw new BadRequestException("Admin date of birth is required field!");
        else if(admin.getAdminAddress() == null)
        	throw new BadRequestException("Admin address is required field!");
        else if(admin.getAdminCity() == null)
        	throw new BadRequestException("Admin city is required field!");
        else if(admin.getAdminCountry() == null)
        	throw new BadRequestException("Admin country is required field!");
        else if(admin.getAdminEmail() == null)
        	throw new BadRequestException("Admin email is required field!");
        else if(admin.getAdminUserName() == null)
        	throw new BadRequestException("Admin user name is required field!");
        else if(admin.getAdminPassword() == null)
        	throw new BadRequestException("Admin password is required field!");
        
        adminRepository.save(admin);		
	}

	@Override
	public void deleteAdmin(Integer adminId) throws Exception {
		
        Admin admin = adminRepository.findAdminById(adminId);
        if (admin != null) {
        	try {
        		adminRepository.deleteAdminById(adminId);
        	}catch(Exception e) {
        		throw new ConflictException("Admin with forwarded ID is used in other table!");
        	}
        }else
            throw new NotFoundException("Admin with forwarded ID does not exist!");
    }
	
	//Mapping Admin Entity to AdminDto
	public AdminDto convertEntityToDto(Admin admin) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		AdminDto adminDto = this.modelMapper.map(admin, AdminDto.class);
		return adminDto;	
	}
	
	//Mapping AdminDto to Admin Entity
	public Admin convertDtoToEntity(AdminDto adminDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Admin admin = this.modelMapper.map(adminDto, Admin.class);
		return admin;
	}
	
	//Mapping Admin Entity to AdminCreateUpdateDto
	public AdminCreateUpdateDto convertEntityToCreateUpdateDto(Admin admin) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		AdminCreateUpdateDto adminCreateUpdateDto = this.modelMapper.map(admin, AdminCreateUpdateDto.class);
		return adminCreateUpdateDto;	
	}

	//Mapping AdminCreateUpdateDto to Admin Entity
	public Admin convertCreateUpdateDtoToEntity(AdminCreateUpdateDto adminCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		Admin admin = this.modelMapper.map(adminCreateUpdateDto, Admin.class);
		return admin;
	}
	
}
