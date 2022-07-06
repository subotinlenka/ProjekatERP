package erp.dto;

import java.util.List;

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
public class OrderCreateDto {

	@ApiModelProperty(notes = "The address of the Order")
	@NotNull(message = "Order address is required field!")
	private String orderAddress;

	@ApiModelProperty(notes = "The city of the Order")
	@NotNull(message = "Order city is required field!")
	private String orderCity;
	
	@ApiModelProperty(notes = "The payment type of the Order")
	@NotNull(message = "Order payment type is required field!")
	private String orderPaymentType;

	List<OrderItemCreateUpdateDto> orderItems; 
}

