package erp.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the productManufacturer database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@NamedQuery(name="ProductManufacturer.findAll", query="SELECT p FROM ProductManufacturer p")
public class ProductManufacturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTMANUFACTURER_PRODUCTMANUFACTURERID_GENERATOR", sequenceName="PRODUCTMANUFACTURER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTMANUFACTURER_PRODUCTMANUFACTURERID_GENERATOR")
	@Column(name = "productmanufacturerid")
	private Integer productManufacturerID;

	@Column(name = "manufactureraddress")
	private String manufacturerAddress;

	@Column(name = "manufacturercity")
	private String manufacturerCity;

	@Column(name = "manufacturercountry")
	private String manufacturerCountry;

	@Column(name = "manufactureremail")
	private String manufacturerEmail;

	@Column(name = "manufacturername")
	private String manufacturerName;

	@Column(name = "manufacturerphonenumber")
	private String manufacturerPhoneNumber;

	@JsonIgnore
	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productManufacturer", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Product> products;

	public ProductManufacturer() {
	}

	public Integer getProductManufacturerId() {
		return this.productManufacturerID;
	}

	public void setProductManufacturerId(Integer productManufacturerID) {
		this.productManufacturerID = productManufacturerID;
	}

	public String getManufacturerAddress() {
		return this.manufacturerAddress;
	}

	public void setManufacturerAddress(String manufacturerAddress) {
		this.manufacturerAddress = manufacturerAddress;
	}

	public String getManufacturerCity() {
		return this.manufacturerCity;
	}

	public void setManufacturerCity(String manufacturerCity) {
		this.manufacturerCity = manufacturerCity;
	}

	public String getManufacturerCountry() {
		return this.manufacturerCountry;
	}

	public void setManufacturerCountry(String manufacturerCountry) {
		this.manufacturerCountry = manufacturerCountry;
	}

	public String getManufacturerEmail() {
		return this.manufacturerEmail;
	}

	public void setManufacturerEmail(String manufacturerEmail) {
		this.manufacturerEmail = manufacturerEmail;
	}

	public String getManufacturerName() {
		return this.manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getManufacturerPhoneNumber() {
		return this.manufacturerPhoneNumber;
	}

	public void setManufacturerPhoneNumber(String manufacturerPhoneNumber) {
		this.manufacturerPhoneNumber = manufacturerPhoneNumber;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductManufacturer(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductManufacturer(null);

		return product;
	}

}