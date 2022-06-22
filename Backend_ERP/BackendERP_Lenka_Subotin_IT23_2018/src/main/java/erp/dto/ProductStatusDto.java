package erp.dto;

import java.util.List;

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
@ApiModel(description = "Details about the Product Status")
public class ProductStatusDto {

	@ApiModelProperty(notes = "The unique identifier of the Product Status")
	private Integer productStatusID;

	@ApiModelProperty(notes = "The name of the Product Status")
	private String productStatusName;
	
	@ApiModelProperty(notes = "The products which belong to specific Product Status")
	List<Product> products;

}
