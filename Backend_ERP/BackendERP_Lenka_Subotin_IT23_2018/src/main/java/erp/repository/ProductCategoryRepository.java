package erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

	@Query(value = "SELECT * FROM productCategory p WHERE p.productcategoryid = :productCategoryId ", nativeQuery = true)
	ProductCategory findProductCategoryById(Integer productCategoryId);
	
}
