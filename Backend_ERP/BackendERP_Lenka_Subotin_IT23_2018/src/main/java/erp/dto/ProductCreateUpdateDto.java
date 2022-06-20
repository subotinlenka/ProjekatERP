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
@ApiModel(description = "Details about the Product")
public class ProductCreateUpdateDto {

	@ApiModelProperty(notes = "The Product name")
	private String productName;
	
	@ApiModelProperty(notes = "The Product description")
	private String productDescription;
	
	@ApiModelProperty(notes = "The Product size")
	private String productSize;
	
	@ApiModelProperty(notes = "The Product quantity")
	private Integer productQuantity;
	
	@ApiModelProperty(notes = "The Product price")
	private float productPrice;
	
	@ApiModelProperty(notes = "The Product image")
	private String productImage;
	
	@ApiModelProperty(notes = "The Product does have/ does not have a discount")
	private Boolean productDiscount;
	
	@ApiModelProperty(notes = "The discount amount of the Product")
	private float discountAmount;
	
	@ApiModelProperty(notes = "The Product status ID related to the Product")
	private Integer productStatusID;
	
	@ApiModelProperty(notes = "The Product category ID related to the Product")
	private Integer productCategoryID;
	
	@ApiModelProperty(notes = "The Product manufacturer ID related to the Product")
	private Integer productManufacturerID;
	
	@ApiModelProperty(notes = "The admin who added a product ID related to the Product")
	private Integer adminID;
	
}
