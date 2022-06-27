package erp.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class OrderCreateDto {
	
	@ApiModelProperty(notes = "The date of the Order")
	@JsonFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "Order date is required field!")
	private Date orderDate;
	
	@ApiModelProperty(notes = "The amount of the Order")
	@NotNull(message = "Order amount is required field!")
	private Float orderAmount;
	
	@ApiModelProperty(notes = "The delivery fee of the Order")
	@NotNull(message = "Order delivery fee is required field!")
	private Float orderDeliveryFee;
	
	@ApiModelProperty(notes = "The total amount of the Order")
	@NotNull(message = "Order total amount is required field!")
	private Float orderTotalAmount;

	@ApiModelProperty(notes = "The address of the Order")
	@NotNull(message = "Order address is required field!")
	private String orderAddress;

	@ApiModelProperty(notes = "The city of the Order")
	@NotNull(message = "Order city is required field!")
	private String orderCity;

	@ApiModelProperty(notes = "The Order is/is not paid")
	@NotNull(message = "Order paid is required field!")
	private Boolean orderPaid;
	
	@ApiModelProperty(notes = "The payment type of the Order")
	@NotNull(message = "Order payment type is required field!")
	private String orderPaymentType;

	@ApiModelProperty(notes = "The payment date of the Order")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date orderPaymentDate;

	@ApiModelProperty(notes = "The order status ID related to the Order")
	@NotNull(message = "Order Status ID can not be null!")
	private Integer orderStatusID;
	
	@ApiModelProperty(notes = "The user ID related to the Order")
	@NotNull(message = "User ID can not be null!")
	private Integer userID;
	
}

