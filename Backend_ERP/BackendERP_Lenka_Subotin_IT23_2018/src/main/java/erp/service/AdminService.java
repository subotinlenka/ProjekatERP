package erp.service;

import java.util.List;

import erp.dto.AdminCreateUpdateDto;
import erp.dto.AdminDto;

public interface AdminService {

	 List<AdminDto> getAdmins();
	 AdminDto getAdminById(Integer adminId) throws Exception; 
	 AdminDto getAdminByAdminUserName(String adminUserName);
	 void insertAdmin(AdminCreateUpdateDto adminCreateDto);
	 void modifyAdmin(AdminCreateUpdateDto adminUpdateDto, Integer adminId);
	 void deleteAdmin(Integer adminId);
	 
}
