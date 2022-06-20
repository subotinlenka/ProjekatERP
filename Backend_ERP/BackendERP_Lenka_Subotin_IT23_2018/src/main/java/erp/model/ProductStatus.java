package erp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

import java.util.List;


/**
 * The persistent class for the productStatus database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="productStatus")
@NamedQuery(name="ProductStatus.findAll", query="SELECT p FROM ProductStatus p")
public class ProductStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTSTATUS_PRODUCTSTATUSID_GENERATOR", sequenceName="PRODUCTSTATUS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTSTATUS_PRODUCTSTATUSID_GENERATOR")
	@Column(name = "productstatusid")
	@NotNull
	private Integer productStatusID;

	@Column(name = "productstatusname", nullable = false)
	@NotNull(message = "Product status name is required field!")
	private String productStatusName;

	@JsonIgnore
	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productStatus", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Product> products;

	public ProductStatus() {
	}

	public Integer getProductStatusId() {
		return this.productStatusID;
	}

	public void setProductStatusId(Integer productStatusID) {
		this.productStatusID = productStatusID;
	}

	public String getProductStatusName() {
		return this.productStatusName;
	}

	public void setProductStatusName(String productStatusName) {
		this.productStatusName = productStatusName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductStatus(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductStatus(null);

		return product;
	}

}