package erp.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the productCategory database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@NamedQuery(name="ProductCategory.findAll", query="SELECT p FROM ProductCategory p")
public class ProductCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTCATEGORY_PRODUCTCATEGORYID_GENERATOR", sequenceName="PRODUCTCATEGORY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTCATEGORY_PRODUCTCATEGORYID_GENERATOR")
	@Column(name = "productcategoryid")
	private Integer productCategoryID;

	@Column(name = "productcategoryname")
	private String productCategoryName;

	@JsonIgnore
	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productCategory", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Product> products;

	public ProductCategory() {
	}

	public Integer getProductCategoryId() {
		return this.productCategoryID;
	}

	public void setProductCategoryId(Integer productCategoryID) {
		this.productCategoryID = productCategoryID;
	}

	public String getProductCategoryName() {
		return this.productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductCategory(null);

		return product;
	}

}