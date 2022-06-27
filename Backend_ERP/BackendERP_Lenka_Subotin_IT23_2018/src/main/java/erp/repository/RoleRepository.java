package erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {

	@Query(value = "SELECT * FROM roles r WHERE r.roleid = :roleId ", nativeQuery = true)
	Role findRoleById(Integer roleId);
	
	@Query(value = "SELECT * FROM roles r WHERE r.rolename = :roleName ", nativeQuery = true)
	Role findRoleByName(String roleName);
	
}
