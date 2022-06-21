package erp.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@ApiModel(description = "Details about the Customer")
public class CustomerCreateUpdateDto {
	
	@ApiModelProperty(notes = "The Customer's first name")
	private String customerFirstName;

	@ApiModelProperty(notes = "The Customer's last name")
	private String customerLastName;
	
	@ApiModelProperty(notes = "The Customer's date of birth")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date customerDateOfBirth;

	@ApiModelProperty(notes = "The Customer's phone number")
	private String customerPhoneNumber;
	
	@ApiModelProperty(notes = "The Customer's email")
	@Email(message = "Invalid format of the email address!")
	private String customerEmail;

	@ApiModelProperty(notes = "The Customer's address")
	private String customerAddress;

	@ApiModelProperty(notes = "The Customer's city")
	private String customerCity;

	@ApiModelProperty(notes = "The Customer's country")
	private String customerCountry;
	
	@ApiModelProperty(notes = "The Customer's user name")
	private String customerUserName;
	
	@ApiModelProperty(notes = "The Customer's password")
	@Size(min = 8, message = "Password should have at least 8 characters!")
	private String customerPassword;
}
