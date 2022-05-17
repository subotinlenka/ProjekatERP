package erp.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the orderItem database table.
 * 
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@NamedQuery(name="OrderItem.findAll", query="SELECT o FROM OrderItem o")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDERITEM_ORDERITEMID_GENERATOR", sequenceName="ORDERITEM_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERITEM_ORDERITEMID_GENERATOR")
	@Column(name = "orderitemid")
	private Integer orderItemID;

	@Column(name = "orderitemquantity")
	private Integer orderItemQuantity;

	@Column(name = "orderitemtotalprice")
	private float orderItemTotalPrice;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="orderid")
	private Order order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;

	public OrderItem() {
	}

	public Integer getOrderItemId() {
		return this.orderItemID;
	}

	public void setOrderItemId(Integer orderItemID) {
		this.orderItemID = orderItemID;
	}

	public Integer getOrderItemQuantity() {
		return this.orderItemQuantity;
	}

	public void setOrderItemQuantity(Integer orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}

	public float getOrderItemTotalPrice() {
		return this.orderItemTotalPrice;
	}

	public void setOrderItemTotalPrice(float orderItemTotalPrice) {
		this.orderItemTotalPrice = orderItemTotalPrice;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}