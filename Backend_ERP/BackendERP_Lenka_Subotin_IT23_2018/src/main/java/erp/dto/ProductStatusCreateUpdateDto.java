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
public class ProductStatusCreateUpdateDto {

	@ApiModelProperty(notes = "The name of the Product Status")
	private String productStatusName;
}
