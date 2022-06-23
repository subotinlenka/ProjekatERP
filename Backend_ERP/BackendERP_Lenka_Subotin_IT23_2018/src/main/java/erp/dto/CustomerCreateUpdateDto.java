package erp.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
	@NotNull(message = "Customer first name is required field!")
	private String customerFirstName;

	@ApiModelProperty(notes = "The Customer's last name")
	@NotNull(message = "Customer last name is required field!")
	private String customerLastName;
	
	@ApiModelProperty(notes = "The Customer's date of birth")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Customer date of birth is required field!")
	private Date customerDateOfBirth;

	@ApiModelProperty(notes = "The Customer's phone number")
	private String customerPhoneNumber;
	
	@ApiModelProperty(notes = "The Customer's email")
	@Email(message = "Invalid format of the email address!")
	@NotNull(message = "Customer email is required field!")
	private String customerEmail;

	@ApiModelProperty(notes = "The Customer's address")
	@NotNull(message = "Customer address is required field!")
	private String customerAddress;

	@ApiModelProperty(notes = "The Customer's city")
	@NotNull(message = "Customer city is required field!")
	private String customerCity;

	@ApiModelProperty(notes = "The Customer's country")
	@NotNull(message = "Customer country is required field!")
	private String customerCountry;
	
	@ApiModelProperty(notes = "The Customer's user name")
	@NotNull(message = "Customer user name is required field!")
	private String customerUserName;
	
	@ApiModelProperty(notes = "The Customer's password")
	@Size(min = 8, message = "Password should have at least 8 characters!")
	@NotNull(message = "Customer password is required field!")
	private String customerPassword;
}
