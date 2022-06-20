package erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import erp.dto.AdminDto;
import erp.service.implementation.AdminServiceImplementation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = {"CRUD operations and search by criteria for Admin table in the database TennisWebShop"})
public class AdminController {

	@Autowired
	private AdminServiceImplementation adminService;

    public AdminController(AdminServiceImplementation adminService){
    	
        this.adminService = adminService;
    }

	@GetMapping("admin")
    @ApiOperation(value = "Returns the list of all Admins", response = AdminDto.class)
	public ResponseEntity<List<AdminDto>> getAdmins() {
		return ResponseEntity.ok(adminService.getAdmins());
	}

	@GetMapping("admin/{adminId}")
	@ApiOperation(value = "Returns Admin by forwarded Id", response = AdminDto.class)
	public ResponseEntity<AdminDto> getAdminById(@PathVariable("adminId") Integer adminId) throws Exception {
	
		return ResponseEntity.ok(adminService.getAdminById(adminId));
	}
	
	@GetMapping("admin/userName/{adminUserName}")
	@ApiOperation(value = "Returns Admin by forwarded user name", response = AdminDto.class)
	public ResponseEntity<AdminDto> getAdminByUserName(@PathVariable("adminUserName") String adminUserName) throws Exception {
	
		return ResponseEntity.ok(adminService.getAdminByAdminUserName(adminUserName));
	}
	
	/*
	@PostMapping("admin")
	public ResponseEntity<Admin> insertAdmin(@Valid @RequestBody Admin admin) {
		if (!adminRepository.existsById(admin.getAdminId())) {
			adminRepository.save(admin);
			return new ResponseEntity<Admin>(HttpStatus.OK); 
		}
		return new ResponseEntity<Admin>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("admin")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
		if (!adminRepository.existsById(admin.getAdminId()))
			return new ResponseEntity<Admin>(HttpStatus.CONFLICT);
		adminRepository.save(admin);
		return new ResponseEntity<Admin>(HttpStatus.OK);
	}
	
	@DeleteMapping("admin/{adminID}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable Integer adminID)  {
		if (!adminRepository.existsById(adminID))
			return new ResponseEntity<Admin>(HttpStatus.NO_CONTENT);
		adminRepository.deleteById(adminID);
		return new ResponseEntity<Admin>(HttpStatus.OK);
	}*/

}
