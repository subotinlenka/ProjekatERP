package erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.ProductManufacturer;


@Repository
public interface ProductManufacturerRepository extends JpaRepository<ProductManufacturer, Integer>{

	@Query(value = "SELECT * FROM productManufacturer m WHERE m.productmanufacturerid = :manufacturerId ", nativeQuery = true)
	ProductManufacturer findProductManufacturerById(Integer manufacturerId);
	
	@Query(value = "SELECT * FROM productManufacturer m WHERE m.manufacturername = :manufacturerName", nativeQuery = true)
	ProductManufacturer findProductManufacturerByName(Integer manufacturerName);
	
}
