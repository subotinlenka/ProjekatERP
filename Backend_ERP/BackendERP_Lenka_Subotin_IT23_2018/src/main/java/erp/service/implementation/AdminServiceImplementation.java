package erp.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erp.dto.AdminCreateUpdateDto;
import erp.dto.AdminDto;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyAdmin(AdminCreateUpdateDto adminUpdateDto, Integer adminId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAdmin(Integer adminId) {
		// TODO Auto-generated method stub
		
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
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		AdminCreateUpdateDto adminCreateUpdateDto = this.modelMapper.map(admin, AdminCreateUpdateDto.class);
		return adminCreateUpdateDto;	
	}

	//Mapping AdminCreateUpdateDto to Admin Entity
	public Admin convertCreateUpdateDtoToEntity(AdminCreateUpdateDto adminCreateUpdateDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		Admin admin = this.modelMapper.map(adminCreateUpdateDto, Admin.class);
		return admin;
	}
	
}
