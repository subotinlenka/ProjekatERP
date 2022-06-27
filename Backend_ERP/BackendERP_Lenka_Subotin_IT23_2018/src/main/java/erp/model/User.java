package erp.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customers database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="users", schema ="public")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_USERID_GENERATOR", sequenceName="USERS_SEQ",  allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_USERID_GENERATOR")
	@Column(name = "userid")
	@NotNull
	private Integer userID;

	@Column(name = "useraddress")
	@NotNull(message = "User address is required field!")
	private String userAddress;

	@Column(name = "usercity")
	@NotNull(message = "User city is required field!")
	private String userCity;

	@Column(name = "usercountry")
	@NotNull(message = "User country is required field!")
	private String userCountry;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "userdateofbirth")
	@NotNull(message = "User date of birth is required field!")
	private Date userDateOfBirth;

	@Column(name = "useremail", unique = true)
	@NotNull(message = "User email is required field!")
	@Email(message = "Invalid format of the email address!")
	private String userEmail;

	@Column(name = "userfirstname")
	@NotNull(message = "User first name is required field!")
	private String userFirstName;

	@Column(name = "userlastname")
	@NotNull(message = "User last name is required field!")
	private String userLastName;

	@Column(name = "userpassword")
	@Size(min = 8, message = "Password should have at least 8 characters!")
	@NotNull(message = "User password is required field!")
	private String userPassword;

	@Column(name = "userphonenumber")
	private String userPhoneNumber;

	@Column(name = "userusername", unique = true)
	@NotNull(message = "User's user name is required field!")
	private String userUserName;

	@JsonIgnore
	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<Order> orders;
	
	//bi-directional many-to-one association to ProductStatus
		@ManyToOne
		@JoinColumn(name="roleid")
		private Role role;

	public User() {
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public Date getUserDateOfBirth() {
		return userDateOfBirth;
	}

	public void setUserDateOfBirth(Date userDateOfBirth) {
		this.userDateOfBirth = userDateOfBirth;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserUserName() {
		return userUserName;
	}

	public void setUserUserName(String userUserName) {
		this.userUserName = userUserName;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

	@Override
	public Collection<Role> getAuthorities() {
		List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
	}

	@Override
	public String getPassword() {
		return userPassword;
	}

	@Override
	public String getUsername() {
		return userUserName;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;	
	}


}