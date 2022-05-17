package erp.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customers database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUSTOMERS_CUSTOMERID_GENERATOR", sequenceName="CUSTOMERS_SEQ",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMERS_CUSTOMERID_GENERATOR")
	@Column(name = "customerid")
	private Integer customerID;

	@Column(name = "customeraddress")
	private String customerAddress;

	@Column(name = "customercity")
	private String customerCity;

	@Column(name = "customercountry")
	private String customerCountry;

	@Temporal(TemporalType.DATE)
	@Column(name = "customerdateofbirth")
	private Date customerDateOfBirth;

	@Column(name = "customeremail")
	private String customerEmail;

	@Column(name = "customerfirstname")
	private String customerFirstName;

	@Column(name = "customerlastname")
	private String customerLastName;

	@Column(name = "customerpassword")
	private String customerPassword;

	@Column(name = "customerphonenumber")
	private String customerPhoneNumber;

	@Column(name = "customerusername")
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