package erp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{

	 @Query(value = "SELECT * FROM users u WHERE u.userid = :userId ", nativeQuery = true)
	 User findUserById(Integer userId);
	 
	 @Query(value = "SELECT * FROM users u WHERE u.userusername = :userUserName", nativeQuery = true)
	 User findUserByUserName(String userUserName);
	 
	 @Query(value = "SELECT * FROM users u WHERE u.useremail = :userEmail", nativeQuery = true)
	 User findUserByEmail(String userEmail);
	 
	 @Query(value = "SELECT * FROM users u WHERE u.roleid = :roleId", nativeQuery = true)
	 List<User> findUsersByRoleId(Integer roleId);
	 
	 @Query(value = "SELECT * FROM users u WHERE u.roleid = 2 ", nativeQuery = true)
	 List<User> findCustomers();
		
     @Query(value = "SELECT * FROM users u WHERE u.roleid = 1 ", nativeQuery = true)
     List<User> findAdmins();
	 
	 @Modifying
	 @Query(value = "DELETE FROM users u WHERE u.userid = :userId", nativeQuery = true)
	 void deleteUserById(Integer userId);
	 
}
