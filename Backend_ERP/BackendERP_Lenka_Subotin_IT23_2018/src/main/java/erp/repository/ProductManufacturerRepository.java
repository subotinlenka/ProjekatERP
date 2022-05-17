package erp.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import erp.jpa.ProductManufacturer;


public interface ProductManufacturerRepository extends JpaRepository<ProductManufacturer, Integer>{

	Collection<ProductManufacturer> findByManufacturerNameContainingIgnoreCase(@Param("manufacturerName") String manufacturerName);

}
