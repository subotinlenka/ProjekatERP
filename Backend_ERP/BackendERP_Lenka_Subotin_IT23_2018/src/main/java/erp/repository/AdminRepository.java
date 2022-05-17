package erp.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import erp.jpa.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Collection<Admin> findByAdminFirstNameContainingIgnoreCase(@Param("adminFirstName") String adminFirstName);
	
	Collection<Admin> findByAdminLastNameContainingIgnoreCase(@Param("adminLastName") String adminLastName);
}
