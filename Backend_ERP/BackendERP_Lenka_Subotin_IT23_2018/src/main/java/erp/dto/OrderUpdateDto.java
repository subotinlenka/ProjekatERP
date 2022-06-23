package erp.dto;

import javax.validation.constraints.NotNull;

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
public class OrderUpdateDto {
	
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
	
	@ApiModelProperty(notes = "The payment type of the Order")
	@NotNull(message = "Order payment type is required field!")
	private String orderPaymentType;

}
