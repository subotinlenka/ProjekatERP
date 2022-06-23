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
@ApiModel(description = "Details about the Product Category")
public class ProductCategoryCreateUpdateDto {

	@ApiModelProperty(notes = "The name of the Product Category")
	@NotNull(message = "Product category name is required field!")
	private String productCategoryName;
	
}
