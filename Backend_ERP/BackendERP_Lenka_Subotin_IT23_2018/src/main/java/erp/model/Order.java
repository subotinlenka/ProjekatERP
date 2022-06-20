package erp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDERS_ORDERID_GENERATOR", sequenceName="ORDERS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERS_ORDERID_GENERATOR")
	@Column(name = "orderid")
	@NotNull
	private Integer orderID;

	@Column(name = "orderaddress")
	@NotNull(message = "Order address is required field!")
	private String orderAddress;

	@Column(name = "orderamount")
	@NotNull(message = "Order amount is required field!")
	private float orderAmount;

	@Column(name = "ordercity")
	@NotNull(message = "Order city is required field!")
	private String orderCity;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "orderdate")
	@NotNull(message = "Order date is required field!")
	private Date orderDate;

	@Column(name = "orderdeliveryfee")
	@NotNull(message = "Order delivery fee is required field!")
	private float orderDeliveryFee;

	@Column(name = "orderpaid")
	@NotNull(message = "Order paid is required field!")
	private Boolean orderPaid;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "orderpaymentdate")
	private Date orderPaymentDate;

	@Column(name = "orderpaymenttype")
	@NotNull(message = "Order payment type is required field!")
	private String orderPaymentType;

	@Column(name = "ordertotalamount")
	@NotNull(message = "Order total amount is required field!")
	private float orderTotalAmount;

	@JsonIgnore
	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy="order", cascade = {CascadeType.DETACH, CascadeType.REMOVE})
	private List<OrderItem> orderItems;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;

	//bi-directional many-to-one association to OrderStatus
	@ManyToOne
	@JoinColumn(name="orderstatusid")
	private OrderStatus orderStatus;

	public Order() {
	}

	public Integer getOrderId() {
		return this.orderID;
	}

	public void setOrderid(Integer orderID) {
		this.orderID = orderID;
	}

	public String getOrderAddress() {
		return this.orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public float getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderCity() {
		return this.orderCity;
	}

	public void setOrderCity(String orderCity) {
		this.orderCity = orderCity;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public float getOrderDeliveryFee() {
		return this.orderDeliveryFee;
	}

	public void setOrderDeliveryFee(float orderDeliveryFee) {
		this.orderDeliveryFee = orderDeliveryFee;
	}

	public Boolean getOrderPaid() {
		return this.orderPaid;
	}

	public void setOrderPaid(Boolean orderPaid) {
		this.orderPaid = orderPaid;
	}

	public Date getOrderPaymentDate() {
		return this.orderPaymentDate;
	}

	public void setOrderPaymentDate(Date orderPaymentDate) {
		this.orderPaymentDate = orderPaymentDate;
	}

	public String getOrderPaymentType() {
		return this.orderPaymentType;
	}

	public void setOrderPaymentType(String orderPaymentType) {
		this.orderPaymentType = orderPaymentType;
	}

	public float getOrderTotalAmount() {
		return this.orderTotalAmount;
	}

	public void setOrderTotalAmount(float orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setOrder(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setOrder(null);

		return orderItem;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}