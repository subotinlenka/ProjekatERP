package erp.dto;

import erp.model.ProductCategory;
import erp.model.ProductManufacturer;
import erp.model.ProductStatus;
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
public class ProductDto {

	@ApiModelProperty(notes = "The unique identifier of the Product")
	private Integer productID;

	@ApiModelProperty(notes = "The Product name")
	private String productName;
	
	@ApiModelProperty(notes = "The Product description")
	private String productDescription;
	
	@ApiModelProperty(notes = "The Product size")
	private String productSize;
	
	@ApiModelProperty(notes = "The Product quantity")
	private Integer productQuantity;
	
	@ApiModelProperty(notes = "The Product price")
	private Float productPrice;
	
	@ApiModelProperty(notes = "The Product does have/ does not have a discount")
	private Boolean productDiscount;
	
	@ApiModelProperty(notes = "The Product price with discount")
	private Float productPriceWithDiscount;
	
	@ApiModelProperty(notes = "The Product status")
	private ProductStatus productStatus;
	
	@ApiModelProperty(notes = "The Product category")
	private ProductCategory productCategory;
	
	@ApiModelProperty(notes = "The Product manufacturer")
	private ProductManufacturer productManufacturer;
	
	private String priceStripe;
	
}
