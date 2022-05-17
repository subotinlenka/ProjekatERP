package erp.ctrls;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import erp.jpa.Admin;
import erp.repository.AdminRepository;

@RestController
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	
	@GetMapping("admin")
	public Collection<Admin> getAdmins() {
		return adminRepository.findAll();
	}

	@GetMapping("admin/{adminID}")
	public Admin getAdmin(@PathVariable("adminID") Integer adminID) {
		
		return adminRepository.getById(adminID);
	}
	
	@GetMapping("adminName/{adminFirstName}")
	public Collection<Admin> getAdminByFirstName(@PathVariable("adminFirstName") String adminFirstName) {
		return adminRepository.findByAdminFirstNameContainingIgnoreCase(adminFirstName);
	}
	
	@GetMapping("adminLastName/{adminLastName}")
	public Collection<Admin> getAdminByLastName(@PathVariable("adminLastName") String adminLastName) {
		return adminRepository.findByAdminLastNameContainingIgnoreCase(adminLastName);
	}
	
	@PostMapping("admin")
	public ResponseEntity<Admin> insertAdmin(@RequestBody Admin admin) {
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
	}

}
