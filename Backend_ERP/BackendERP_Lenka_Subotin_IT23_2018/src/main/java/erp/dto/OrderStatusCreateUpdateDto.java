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
@ApiModel(description = "Details about the Order Status")
public class OrderStatusCreateUpdateDto {

	@ApiModelProperty(notes = "The name of the Order Status")
	@NotNull(message = "Order status name is required field!")
	private String orderStatusName;
}
