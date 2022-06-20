package erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import erp.model.ProductReview;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {

	 @Query(value = "SELECT * FROM productReview p WHERE p.productreviewid = :productReviewId ", nativeQuery = true)
	 ProductReview findProductReviewById(Integer productReviewId);
	 
	 @Query(value = "SELECT * FROM productReview p WHERE p.productid = :productId ", nativeQuery = true)
	 List<ProductReview> findAllProductReviewsOfProduct(Integer productId);
	 
	 @Query(value = "SELECT * FROM productReview p WHERE p.customerid = :customerId ", nativeQuery = true)
	 List<ProductReview> findAllProductReviewsOfCustomer(Integer customerId);
}
