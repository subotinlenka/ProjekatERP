package erp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.ProductCategory;

@Repository
@Transactional
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

	@Query(value = "SELECT * FROM productcategory p WHERE p.productcategoryid = :productCategoryId ", nativeQuery = true)
	ProductCategory findProductCategoryById(Integer productCategoryId);
	
	@Query(value = "SELECT * FROM productcategory p WHERE upper(p.productcategoryname) = :productCategoryName OR "
			+ "lower(p.productcategoryname) = :productCategoryName OR p.productcategoryname = :productCategoryName ", nativeQuery = true)
	ProductCategory findProductCategoryByName(String productCategoryName);
	
	@Modifying
	@Query(value = "DELETE FROM productcategory p WHERE p.productcategoryid = :productCategoryId ", nativeQuery = true)
	void deleteProductCategoryById(Integer productCategoryId);
	
}
