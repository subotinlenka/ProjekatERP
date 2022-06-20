package erp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.ToString;

import java.util.List;


/**
 * The persistent class for the orderStatus database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@ToString
@Entity
@Table(name="orderStatus")
@NamedQuery(name="OrderStatus.findAll", query="SELECT o FROM OrderStatus o")
public class OrderStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDERSTATUS_ORDERSTATUSID_GENERATOR", sequenceName="ORDERSTATUS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERSTATUS_ORDERSTATUSID_GENERATOR")
	@Column(name = "orderstatusid")
	@NotNull
	private Integer orderStatusID;

	@Column(name = "orderstatusname")
	@NotNull(message = "Order status name is required field!")
	private String orderStatusName;

	@JsonIgnore
	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="orderStatus")
	private List<Order> orders;

	public OrderStatus() {
	}

	public Integer getOrderStatusId() {
		return this.orderStatusID;
	}

	public void setOrderStatusId(Integer orderStatusID) {
		this.orderStatusID = orderStatusID;
	}

	public String getOrderStatusName() {
		return this.orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setOrderStatus(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderStatus(null);

		return order;
	}

}