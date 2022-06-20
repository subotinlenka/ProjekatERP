package erp.dto;

import erp.model.Order;
import erp.model.Product;
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
public class OrderItemDto {

	@ApiModelProperty(notes = "The unique identifier of the Order Item")
	private Integer orderItemID;
	
	@ApiModelProperty(notes = "The Order Item quantity")
	private Integer orderItemQuantity;
	
	@ApiModelProperty(notes = "The Order Item total price")
	private float orderItemTotalPrice;
	
	@ApiModelProperty("The product related to the Order Item")
	private Product product;
	
	@ApiModelProperty("The order to which the Order Item belongs")
	private Order order;
	
}
