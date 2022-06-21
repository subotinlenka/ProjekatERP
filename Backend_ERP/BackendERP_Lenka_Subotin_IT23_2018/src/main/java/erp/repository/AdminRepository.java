package erp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.Admin;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	 @Query(value = "SELECT * FROM admins a WHERE a.adminid = :adminId ", nativeQuery = true)
	 Admin findAdminById(Integer adminId);
	 
	 @Query(value = "SELECT * FROM admins a WHERE a.adminusername = :adminUserName", nativeQuery = true)
	 Admin findAdminByUserName(String adminUserName);
	 
	 @Modifying
	 @Query(value = "DELETE FROM admins a WHERE a.adminid = :adminId", nativeQuery = true)
	 void deleteAdminById(Integer adminId);
} 
