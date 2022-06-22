package erp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer>{

	 @Query(value = "SELECT * FROM products p WHERE p.productid = :productId ", nativeQuery = true)
	 Product findProductById(Integer productId);
	 
	 @Query(value = "SELECT * FROM products p WHERE p.productcategoryid = :productCategoryId ", nativeQuery = true)
	 List<Product> findProductsByProductCategory(Integer productCategoryId);
	 
	 @Query(value = "SELECT * FROM products p WHERE p.productstatusid = :productStatusId ", nativeQuery = true)
	 List<Product> findProductsByProductStatus(Integer productStatusId);
	 
	 @Query(value = "SELECT * FROM products p WHERE p.productmanufacturerid = :productManufacturerId ", nativeQuery = true)
	 List<Product> findProductsByProductManufacturer(Integer productManufacturerId);
	 
	 @Modifying
	 @Query(value = "DELETE FROM products p WHERE p.productid = :productId ", nativeQuery = true)
	 void deleteProductById(Integer productId);
}
