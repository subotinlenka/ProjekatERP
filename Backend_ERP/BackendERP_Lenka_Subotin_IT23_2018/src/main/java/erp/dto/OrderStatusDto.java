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
@ApiModel(description = "Details about the Order Status")
public class OrderStatusDto {

	@ApiModelProperty(notes = "The unique identifier of the Order Status")
	private Integer orderStatusID;

	@ApiModelProperty(notes = "The name of the Order Status")
	private String orderStatusName;

}