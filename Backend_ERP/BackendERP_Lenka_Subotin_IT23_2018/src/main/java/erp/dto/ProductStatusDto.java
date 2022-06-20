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
@ApiModel(description = "Details about the Product Status")
public class ProductStatusDto {

	@ApiModelProperty(notes = "The unique identifier of the Product Status")
	private Integer productStatusID;

	@ApiModelProperty(notes = "The name of the Product Status")
	private String productStatusName;

}
