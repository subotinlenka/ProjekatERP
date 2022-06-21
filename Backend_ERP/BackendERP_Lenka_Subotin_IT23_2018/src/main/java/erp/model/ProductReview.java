package erp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

import java.util.Date;


/**
 * The persistent class for the productReview database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="productreview", schema ="public")
@NamedQuery(name="ProductReview.findAll", query="SELECT p FROM ProductReview p")
public class ProductReview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTREVIEW_PRODUCTREVIEWID_GENERATOR", sequenceName="PRODUCTREVIEW_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTREVIEW_PRODUCTREVIEWID_GENERATOR")
	@Column(name = "productreviewid")
	@NotNull
	private Integer productReviewID;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "productreviewdate")
	@NotNull(message = "Product review date is required field!")
	private Date productReviewDate;

	@Column(name = "productreviewtext")
	private String productReviewText;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;

	public ProductReview() {
	}

	public Integer getProductReviewId() {
		return this.productReviewID;
	}

	public void setProductReviewId(Integer productReviewID) {
		this.productReviewID = productReviewID;
	}

	public Date getProductReviewDate() {
		return this.productReviewDate;
	}

	public void setProductReviewDate(Date productReviewDate) {
		this.productReviewDate = productReviewDate;
	}

	public String getProductReviewText() {
		return this.productReviewText;
	}

	public void setProductReviewText(String productReviewText) {
		this.productReviewText = productReviewText;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}