package erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.ProductStatus;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Integer>{

	@Query(value = "SELECT * FROM productStatus p WHERE p.productstatusid = :productStatusId ", nativeQuery = true)
	ProductStatus findProductStatusById(Integer productStatusId);
}
