package erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import erp.dto.UserCreateUpdateDto;
import erp.dto.UserDto;
import erp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for User table in the database TennisWebShop"})
public class UserController {

	@Autowired
	private UserService userService;
	
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	private static final String SUCCESS = "Success!";

	@GetMapping("users")
    @ApiOperation(value = "Returns the list of all Users", response = UserDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}
	
	@GetMapping("customers")
    @ApiOperation(value = "Returns the list of all Customers", response = UserDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> getCustomers() {
		return ResponseEntity.ok(userService.getCustomers());
	}
	
	@GetMapping("admins")
	@PreAuthorize("hasAuthority('Admin')")
    @ApiOperation(value = "Returns the list of all Admins", response = UserDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> getAdmins() {
		return ResponseEntity.ok(userService.getAdmins());
	}

	@GetMapping("user/{userId}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Returns User by forwarded ID", notes = "Id of the User is required.", response = UserDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId) throws Exception {
	
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@GetMapping("user/userName/{userName}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Returns User by forwarded user name", notes = "User name of the User is required.",  response = UserDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUserByUserName(@PathVariable("userUserName") String userUserName) throws Exception {
	
		return ResponseEntity.ok(userService.getUserByUserName(userUserName));
	}
	
	@GetMapping("user/role/{roleId}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Returns User by forwarded Role ID", notes = "Role Id of the User is required.", response = UserDto.class,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> getUserByRoleId(@PathVariable("roleId") Integer roleId) throws Exception {
	
		return ResponseEntity.ok(userService.getUserByRoleId(roleId));
	}
	
	@PutMapping("user/{userId}")
	@PreAuthorize("hasAuthority('Admin') or hasRole('Customer')")
	@ApiOperation(value = "Modifies User with forwarded ID", notes = "Request body and User Id are required!")
	public ResponseEntity<String> updateUser(@Valid @RequestBody UserCreateUpdateDto userUpdateDto, @PathVariable("userId") Integer userId) {
		
		userService.modifyUser(userUpdateDto, userId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	
	@DeleteMapping("user/{userId}")
	@PreAuthorize("hasAuthority('Admin')")
	@ApiOperation(value = "Deletes User with forwarded ID", notes = "Id of the User is required.")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId) throws Exception {
		
		userService.deleteUser(userId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
}
