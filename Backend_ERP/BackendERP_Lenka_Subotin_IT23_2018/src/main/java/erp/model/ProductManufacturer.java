package erp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

import java.util.List;


/**
 * The persistent class for the productManufacturer database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="productmanufacturer", schema ="public")
@NamedQuery(name="ProductManufacturer.findAll", query="SELECT p FROM ProductManufacturer p")
public class ProductManufacturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTMANUFACTURER_PRODUCTMANUFACTURERID_GENERATOR", sequenceName="PRODUCTMANUFACTURER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTMANUFACTURER_PRODUCTMANUFACTURERID_GENERATOR")
	@Column(name = "productmanufacturerid")
	@NotNull
	private Integer productManufacturerID;

	@Column(name = "manufactureraddress")
	@NotNull(message = "Product manufacturer address is required field!")
	private String manufacturerAddress;

	@Column(name = "manufacturercity")
	@NotNull(message = "Product manufacturer city is required field!")
	private String manufacturerCity;

	@Column(name = "manufacturercountry")
	@NotNull(message = "Product manufacturer country is required field!")
	private String manufacturerCountry;

	@Column(name = "manufactureremail", unique = true)
	@NotNull(message = "Product manufacturer email is required field!")
	@Email(message = "Invalid format of the email address!")
	private String manufacturerEmail;

	@Column(name = "manufacturername")
	@NotNull(message = "Product manufacturer name is required field!")
	private String manufacturerName;

	@Column(name = "manufacturerphonenumber")
	@NotNull(message = "Product manufacturer phone number is required field!")
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