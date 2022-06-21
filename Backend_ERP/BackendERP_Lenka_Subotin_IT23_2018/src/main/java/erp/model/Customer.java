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
 * The persistent class for the customers database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="customers", schema ="public")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="CUSTOMERS_CUSTOMERID_GENERATOR", sequenceName="CUSTOMERS_SEQ",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMERS_CUSTOMERID_GENERATOR")
	@Column(name = "customerid")
	@NotNull
	private Integer customerID;

	@Column(name = "customeraddress")
	@NotNull(message = "Customer address is required field!")
	private String customerAddress;

	@Column(name = "customercity")
	@NotNull(message = "Customer city is required field!")
	private String customerCity;

	@Column(name = "customercountry")
	@NotNull(message = "Customer country is required field!")
	private String customerCountry;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "customerdateofbirth")
	@NotNull(message = "Customer date of birth is required field!")
	private Date customerDateOfBirth;

	@Column(name = "customeremail", unique = true)
	@NotNull(message = "Customer email is required field!")
	@Email(message = "Invalid format of the email address!")
	private String customerEmail;

	@Column(name = "customerfirstname")
	@NotNull(message = "Customer first name is required field!")
	private String customerFirstName;

	@Column(name = "customerlastname")
	@NotNull(message = "Customer last name is required field!")
	private String customerLastName;

	@Column(name = "customerpassword")
	@Size(min = 8, message = "Password should have at least 8 characters!")
	@NotNull(message = "Customer password is required field!")
	private String customerPassword;

	@Column(name = "customerphonenumber")
	private String customerPhoneNumber;

	@Column(name = "customerusername", unique = true)
	@NotNull(message = "Customer user name is required field!")
	private String customerUserName;

	@JsonIgnore
	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="customer", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Order> orders;

	@JsonIgnore
	//bi-directional many-to-one association to ProductReview
	@OneToMany(mappedBy="customer", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<ProductReview> productReviews;

	public Customer() {
	}

	public Integer getCustomerId() {
		return this.customerID;
	}

	public void setCustomerid(Integer customerID) {
		this.customerID = customerID;
	}

	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return this.customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerCountry() {
		return this.customerCountry;
	}

	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	public Date getCustomerDateOfBirth() {
		return this.customerDateOfBirth;
	}

	public void setCustomerDateOfBirth(Date customerDateOfBirth) {
		this.customerDateOfBirth = customerDateOfBirth;
	}

	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerFirstName() {
		return this.customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return this.customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerPassword() {
		return this.customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerPhoneNumber() {
		return this.customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerUserName() {
		return this.customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

	public List<ProductReview> getProductReviews() {
		return this.productReviews;
	}

	public void setProductReviews(List<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}

	public ProductReview addProductReview(ProductReview productReview) {
		getProductReviews().add(productReview);
		productReview.setCustomer(this);

		return productReview;
	}

	public ProductReview removeProductReview(ProductReview productReview) {
		getProductReviews().remove(productReview);
		productReview.setCustomer(null);

		return productReview;
	}

}