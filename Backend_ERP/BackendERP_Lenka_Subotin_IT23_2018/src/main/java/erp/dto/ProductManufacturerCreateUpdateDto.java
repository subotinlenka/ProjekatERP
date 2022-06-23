package erp.dto;

import javax.validation.constraints.Email;
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
@ApiModel(description = "Details about the Product Manufacturer")
public class ProductManufacturerCreateUpdateDto {

	@ApiModelProperty(notes = "The name of the Product Manufacturer")
	@NotNull(message = "Product Manufacturer name is required field!")
	private String manufacturerName;
	
	@ApiModelProperty(notes = "The phone number of the Product Manufacturer")
	@NotNull(message = "Product Manufacturer phone number is required field!")
	private String manufacturerPhoneNumber;
	
	@ApiModelProperty(notes = "The address of the Product Manufacturer")
	@NotNull(message = "Product Manufacturer address is required field!")
	private String manufacturerAddress;
	
	@ApiModelProperty(notes = "The city of the Product Manufacturer")
	@NotNull(message = "Product Manufacturer city is required field!")
	private String manufacturerCity;
	
	@ApiModelProperty(notes = "The country of the Product Manufacturer")
	@NotNull(message = "Product Manufacturer country is required field!")
	private String manufacturerCountry;
	
	@ApiModelProperty(notes = "The email of the Product Manufacturer")
	@Email(message = "Invalid format of the email address!")
	@NotNull(message = "Product Manufacturer email is required field!")
	private String manufacturerEmail;
}
