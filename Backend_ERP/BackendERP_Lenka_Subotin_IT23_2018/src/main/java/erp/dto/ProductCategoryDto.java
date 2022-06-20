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
@ApiModel(description = "Details about the Product Category")
public class ProductCategoryDto {

	@ApiModelProperty(notes = "The unique identifier of the Product Category")
	private Integer productCategoryID;

	@ApiModelProperty(notes = "The name of the Product Category")
	private String productCategoryName;
	
}
