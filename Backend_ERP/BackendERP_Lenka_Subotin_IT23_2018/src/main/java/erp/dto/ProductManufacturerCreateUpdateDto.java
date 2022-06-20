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
@ApiModel(description = "Details about the Product Manufacturer")
public class ProductManufacturerCreateUpdateDto {

	@ApiModelProperty(notes = "The name of the Product Manufacturer")
	private String manufacturerName;
	
	@ApiModelProperty(notes = "The phone number of the Product Manufacturer")
	private String manufacturerPhoneNumber;
	
	@ApiModelProperty(notes = "The address of the Product Manufacturer")
	private String manufacturerAddress;
	
	@ApiModelProperty(notes = "The city of the Product Manufacturer")
	private String manufacturerCity;
	
	@ApiModelProperty(notes = "The country of the Product Manufacturer")
	private String manufacturerCountry;
	
	@ApiModelProperty(notes = "The email of the Product Manufacturer")
	private String manufacturerEmail;
}
