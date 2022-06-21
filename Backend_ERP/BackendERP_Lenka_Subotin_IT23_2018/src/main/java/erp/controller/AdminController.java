package erp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import erp.dto.AdminCreateUpdateDto;
import erp.dto.AdminDto;
import erp.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Admin table in the database TennisWebShop"})
public class AdminController {

	@Autowired
	private AdminService adminService;

    public AdminController(AdminService adminService){
    	
        this.adminService = adminService;
    }
    
    private static final String SUCCESS = "Success!";

	@GetMapping("admin")
    @ApiOperation(value = "Returns the list of all Admins", response = AdminDto.class)
	public ResponseEntity<List<AdminDto>> getAdmins() {
		return ResponseEntity.ok(adminService.getAdmins());
	}

	@GetMapping("admin/{adminId}")
	@ApiOperation(value = "Returns Admin by forwarded ID", notes = "Id of the Admin is required.", response = AdminDto.class)
	public ResponseEntity<AdminDto> getAdminById(@PathVariable("adminId") Integer adminId) throws Exception {
	
		return ResponseEntity.ok(adminService.getAdminById(adminId));
	}
	
	@GetMapping("admin/userName/{adminUserName}")
	@ApiOperation(value = "Returns Admin by forwarded user name", notes = "User name of the Admin is required.",  response = AdminDto.class)
	public ResponseEntity<AdminDto> getAdminByUserName(@PathVariable("adminUserName") String adminUserName) throws Exception {
	
		return ResponseEntity.ok(adminService.getAdminByAdminUserName(adminUserName));
	}
	
	@PostMapping("admin")
	@ApiOperation(value = "Inserts Admin in the database", notes = "Request body is required!")
	public ResponseEntity<String> insertAdmin(@Valid @RequestBody AdminCreateUpdateDto adminCreateDto) {

			adminService.insertAdmin(adminCreateDto);
			return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	}
	
	@PutMapping("admin/{adminId}")
	@ApiOperation(value = "Modifies Admin with forwarded ID", notes = "Request body and Admin Id are required!")
	public ResponseEntity<String> updateAdmin(@Valid @RequestBody AdminCreateUpdateDto adminUpdateDto, @PathVariable("adminId") Integer adminId) {
		
		adminService.modifyAdmin(adminUpdateDto, adminId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}
	
	
	@DeleteMapping("admin/{adminId}")
	@ApiOperation(value = "Deletes Admin with forwarded ID", notes = "Id of the Admin is required.")
	public ResponseEntity<String> deleteAdmin(@PathVariable("adminId") Integer adminId) throws Exception {
		
		adminService.deleteAdmin(adminId);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
	}

}
