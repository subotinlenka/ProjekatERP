package erp.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import erp.model.User;
import erp.model.OrderStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@ApiModel(description = "Details about the Order")
public class OrderDto {

	@ApiModelProperty(notes = "The unique identifier of the Order")
	private Integer orderID;
	
	@ApiModelProperty(notes = "The date of the Order")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@ApiModelProperty(notes = "The amount of the Order")
	private Float orderAmount;
	
	@ApiModelProperty(notes = "The delivery fee of the Order")
	private Float orderDeliveryFee;
	
	@ApiModelProperty(notes = "The total amount of the Order")
	private Float orderTotalAmount;

	@ApiModelProperty(notes = "The address of the Order")
	private String orderAddress;

	@ApiModelProperty(notes = "The city of the Order")
	private String orderCity;

	@ApiModelProperty(notes = "The Order is/is not paid")
	private Boolean orderPaid;
	
	@ApiModelProperty(notes = "The payment type of the Order")
	private String orderPaymentType;

	@ApiModelProperty(notes = "The payment date of the Order")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date orderPaymentDate;

	@ApiModelProperty(notes = "The order status of the Order")
	private OrderStatus orderStatus;
	
	@ApiModelProperty(notes = "The customer whose Order is")
	private User user;
	
	@ApiModelProperty(notes = "The order items")
	private List<OrderItemWithoutOrderDto> orderItems;

}
