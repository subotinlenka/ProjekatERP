package erp.dto;

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
	private Integer orderItemQuantity;
	
	@ApiModelProperty(notes = "The Order Item total price")
	private float orderItemTotalPrice;
	
	@ApiModelProperty("The product ID related to the Order Item")
	private Integer productID;
	
	@ApiModelProperty("The order ID to which the Order Item belongs")
	private Integer orderID;

}
