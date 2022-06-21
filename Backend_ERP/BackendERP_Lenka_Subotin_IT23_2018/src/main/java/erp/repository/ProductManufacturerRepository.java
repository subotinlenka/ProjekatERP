package erp.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.ProductManufacturer;

@Repository
@Transactional
public interface ProductManufacturerRepository extends JpaRepository<ProductManufacturer, Integer>{
	
	
	@Query(value = "SELECT * FROM productmanufacturer p WHERE p.productmanufacturerid = :manufacturerId ", nativeQuery = true)
	ProductManufacturer findProductManufacturerById(Integer manufacturerId);
	
	@Query(value = "SELECT * FROM productmanufacturer p WHERE upper(p.manufacturername) = :manufacturerName OR "
			+ "lower(p.manufacturername) = :manufacturerName OR p.manufacturername = :manufacturerName ", nativeQuery = true)
	ProductManufacturer findProductManufacturerByName(String manufacturerName);
	
	@Modifying
	@Query(value = "DELETE FROM productmanufacturer p WHERE p.productmanufacturerid = :manufacturerId ", nativeQuery = true)
	void deleteProductManufacturerById(Integer manufacturerId);
	
}
