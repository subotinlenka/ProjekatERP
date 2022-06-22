package erp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.ProductStatus;

@Repository
@Transactional
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Integer>{

	@Query(value = "SELECT * FROM productStatus p WHERE p.productstatusid = :productStatusId ", nativeQuery = true)
	ProductStatus findProductStatusById(Integer productStatusId);
	
	@Query(value = "SELECT * FROM productStatus p WHERE upper(p.productstatusname) = :productStatusName OR "
			+ "lower(p.productstatusname) = :productStatusName OR p.productstatusname = :productStatusName ", nativeQuery = true)
	ProductStatus findProductStatusByName(String productStatusName);
	
	@Modifying
	@Query(value = "DELETE FROM productStatus p WHERE p.productstatusid = :productStatusId ", nativeQuery = true)
	void deleteProductStatusById(Integer productStatusId);
}
