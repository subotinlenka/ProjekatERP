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
@ApiModel(description = "Details about the Order Item")
public class OrderItemCreateUpdateDto {
	
	@ApiModelProperty(notes = "The Order Item quantity")
	@NotNull(message = "Order item quantity is required field!")
	private Integer orderItemQuantity;
	
	@ApiModelProperty(notes = "The Order Item total price")
	@NotNull(message = "Order item total price is required field!")
	private Float orderItemTotalPrice;
	
	@ApiModelProperty("The product ID related to the Order Item")
	@NotNull(message = "Product ID can not be null!")
	private Integer productID;
	
	@ApiModelProperty("The order ID to which the Order Item belongs")
	@NotNull(message = "Order ID can not be null!")
	private Integer orderID;

}
