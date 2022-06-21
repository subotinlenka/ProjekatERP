package erp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

import java.util.List;


/**
 * The persistent class for the products database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="products", schema ="public")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTS_PRODUCTID_GENERATOR", sequenceName="PRODUCTS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTS_PRODUCTID_GENERATOR")
	@Column(name = "productid")
	@NotNull
	private Integer productID;

	@Column(name = "discountamount")
	private Float discountAmount;

	@Column(name = "productdescription")
	private String productDescription;

	@Column(name = "productdiscount")
	@NotNull(message = "Product discount is required field!")
	private Boolean productDiscount;

	@Column(name = "productimage")
	@NotNull(message = "Product image is required field!")
	private String productImage;

	@Column(name = "productname")
	@NotNull(message = "Product name is required field!")
	private String productName;

	@Column(name = "productprice")
	@NotNull(message = "Product price is required field!")
	private float productPrice;

	@Column(name = "productquantity")
	@NotNull(message = "Product quantity is required field!")
	private Integer productQuantity;

	@Column(name = "productsize")
	private String productSize;

	@JsonIgnore
	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="product", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<OrderItem> orderItems;

	@JsonIgnore
	//bi-directional many-to-one association to ProductReview
	@OneToMany(mappedBy="product", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<ProductReview> productReviews;

	//bi-directional many-to-one association to Admin
	@ManyToOne
	@JoinColumn(name="adminid")
	private Admin admin;

	//bi-directional many-to-one association to ProductCategory
	@ManyToOne
	@JoinColumn(name="productcategoryid")
	private ProductCategory productCategory;

	//bi-directional many-to-one association to ProductManufacturer
	@ManyToOne
	@JoinColumn(name="productmanufacturerid")
	private ProductManufacturer productManufacturer;

	//bi-directional many-to-one association to ProductStatus
	@ManyToOne
	@JoinColumn(name="productstatusid")
	private ProductStatus productStatus;

	public Product() {
	}

	public Integer getProductId() {
		return this.productID;
	}

	public void setProductId(Integer productID) {
		this.productID = productID;
	}

	public Float getDiscountAmount() {
		return this.discountAmount;
	}

	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Boolean getProductDiscount() {
		return this.productDiscount;
	}

	public void setProductDiscount(Boolean productDiscount) {
		this.productDiscount = productDiscount;
	}

	public String getProductImage() {
		return this.productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductQuantity() {
		return this.productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductSize() {
		return this.productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setProduct(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setProduct(null);

		return orderItem;
	}

	public List<ProductReview> getProductReviews() {
		return this.productReviews;
	}

	public void setProductReviews(List<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}

	public ProductReview addProductReview(ProductReview productReview) {
		getProductReviews().add(productReview);
		productReview.setProduct(this);

		return productReview;
	}

	public ProductReview removeProductReview(ProductReview productReview) {
		getProductReviews().remove(productReview);
		productReview.setProduct(null);

		return productReview;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public ProductManufacturer getProductManufacturer() {
		return this.productManufacturer;
	}

	public void setProductManufacturer(ProductManufacturer productManufacturer) {
		this.productManufacturer = productManufacturer;
	}

	public ProductStatus getProductStatus() {
		return this.productStatus;
	}

	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}

}