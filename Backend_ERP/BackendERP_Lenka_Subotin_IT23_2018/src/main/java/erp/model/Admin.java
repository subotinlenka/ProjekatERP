package erp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the admins database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="admins")
@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ADMINS_ADMINID_GENERATOR", sequenceName="ADMINS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ADMINS_ADMINID_GENERATOR")
	@Column(name = "adminid")
	@NotNull
	private Integer adminID;

	@Column(name = "adminaddress")
	@NotNull(message = "Admin address is required field!")
	private String adminAddress;

	@Column(name = "admincity")
	@NotNull(message = "Admin city is required field!")
	private String adminCity;

	@Column(name = "admincountry")
	@NotNull(message = "Admin country is required field!")
	private String adminCountry;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "admindateofbirth")
	@NotNull(message = "Admin date of birth is required field!")
	private Date adminDateOfBirth;

	@Column(name = "adminemail")
	@NotNull(message = "Admin email is required field!")
	@Email(message = "Invalid format of the email address!")
	private String adminEmail;

	@Column(name = "adminfirstname")
	@NotNull(message = "Admin first name is required field!")
	private String adminFirstName;

	@Column(name = "adminlastname")
	@NotNull(message = "Admin last name is required field!")
	private String adminLastName;

	@Column(name = "adminpassword")
	@Size(min = 8, message = "Password should have at least 8 characters!")
	@NotNull(message = "Admin password is required field!")
	private String adminPassword;

	@Column(name = "adminphonenumber")
	private String adminPhoneNumber;

	@Column(name = "adminusername")
	@NotNull(message = "Admin user name is required field!")
	private String adminUserName;

	@JsonIgnore
	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="admin", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Product> products;

	public Admin() {
	}

	public Integer getAdminId() {
		return this.adminID;
	}

	public void setAdminId(Integer adminID) {
		this.adminID = adminID;
	}

	public String getAdminAddress() {
		return this.adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	public String getAdminCity() {
		return this.adminCity;
	}

	public void setAdminCity(String adminCity) {
		this.adminCity = adminCity;
	}

	public String getAdminCountry() {
		return this.adminCountry;
	}

	public void setAdminCountry(String adminCountry) {
		this.adminCountry = adminCountry;
	}

	public Date getAdminDateOfBirth() {
		return this.adminDateOfBirth;
	}

	public void setAdminDateOfBirth(Date adminDateOfBirth) {
		this.adminDateOfBirth = adminDateOfBirth;
	}

	public String getAdminEmail() {
		return this.adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminFirstName() {
		return this.adminFirstName;
	}

	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}

	public String getAdminLastName() {
		return this.adminLastName;
	}

	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminPhoneNumber() {
		return this.adminPhoneNumber;
	}

	public void setAdminPhoneNumber(String adminPhoneNumber) {
		this.adminPhoneNumber = adminPhoneNumber;
	}

	public String getAdminUserName() {
		return this.adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setAdmin(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setAdmin(null);

		return product;
	}

}