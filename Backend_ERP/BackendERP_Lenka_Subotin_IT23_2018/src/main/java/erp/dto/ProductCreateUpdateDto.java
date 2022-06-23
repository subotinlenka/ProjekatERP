package erp.dto;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

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
	@NotNull(message = "Product name is required field!")
	private String productName;
	
	@ApiModelProperty(notes = "The Product description")
	private String productDescription;
	
	@ApiModelProperty(notes = "The Product size")
	private String productSize;
	
	@ApiModelProperty(notes = "The Product quantity")
	@NotNull(message = "Product quantity is required field!")
	private Integer productQuantity;
	
	@ApiModelProperty(notes = "The Product price")
	@NotNull(message = "Product price is required field!")
	private Float productPrice;
	
	@ApiModelProperty(notes = "The Product image")
	@NotNull(message = "Product image is required field!")
	private String productImage;
	
	@ApiModelProperty(notes = "The Product does have/ does not have a discount")
	@NotNull(message = "Product discount is required field!")
	private Boolean productDiscount;
	
	@ApiModelProperty(notes = "The Product price with discount")
	@Nullable
	private Float productPriceWithDiscount;
	
	@ApiModelProperty(notes = "The Product status ID related to the Product")
	@NotNull(message = "Product Status ID can not be null!")
	private Integer productStatusID;
	
	@ApiModelProperty(notes = "The Product category ID related to the Product")
	@NotNull(message = "Product Category ID can not be null!")
	private Integer productCategoryID;
	
	@ApiModelProperty(notes = "The Product manufacturer ID related to the Product")
	@NotNull(message = "Product Manufacturer ID can not be null!")
	private Integer productManufacturerID;
	
	@ApiModelProperty(notes = "The admin who added a product ID related to the Product")
	@NotNull(message = "Admin ID can not be null!")
	private Integer adminID;
	
}
