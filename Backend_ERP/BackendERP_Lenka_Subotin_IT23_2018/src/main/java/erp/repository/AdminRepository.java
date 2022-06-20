package erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	 @Query(value = "SELECT * FROM admins a WHERE a.adminid = :adminId ", nativeQuery = true)
	 Admin findAdminById(Integer adminId);
	 
	 @Query(value = "SELECT * FROM admins a WHERE a.adminusername = :adminUserName", nativeQuery = true)
	 Admin findAdminByUserName(String adminUserName);
	 
}
