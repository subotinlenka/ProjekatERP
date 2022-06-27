package erp.service;

import java.util.List;

import erp.dto.UserCreateUpdateDto;
import erp.dto.UserDto;

public interface UserService {

	 List<UserDto> getUsers();
	 UserDto getUserById(Integer userId) throws Exception; 
	 UserDto getUserByUserName(String userUserName);
	 List<UserDto> getUserByRoleId(Integer roleId);
	 List<UserDto> getCustomers();
	 List<UserDto> getAdmins();
	 void insertUser(UserCreateUpdateDto userCreateDto, String roleName);
	 void modifyUser(UserCreateUpdateDto userUpdateDto, Integer userId);
	 void deleteUser(Integer userId) throws Exception; 
}
