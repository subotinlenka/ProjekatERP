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
@ApiModel(description = "Details about the Admin")
public class AdminCreateUpdateDto {
	
	@ApiModelProperty(notes = "The Admin's first name")
	@NotNull(message = "Admin first name is required field!")
	private String adminFirstName;

	@ApiModelProperty(notes = "The Admin's last name")
	@NotNull(message = "Admin last name is required field!")
	private String adminLastName;
	
	@ApiModelProperty(notes = "The Admin's date of birth")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Admin date of birth is required field!")
	private Date adminDateOfBirth;

	@ApiModelProperty(notes = "The Admin's phone number")
	private String adminPhoneNumber;
	
	@ApiModelProperty(notes = "The Admin's email")
	@Email(message = "Invalid format of the email address!")
	@NotNull(message = "Admin email is required field!")
	private String adminEmail;

	@ApiModelProperty(notes = "The Admin's address")
	@NotNull(message = "Admin address is required field!")
	private String adminAddress;

	@ApiModelProperty(notes = "The Admin's city")
	@NotNull(message = "Admin city is required field!")
	private String adminCity;

	@ApiModelProperty(notes = "The Admin's country")
	@NotNull(message = "Admin country is required field!")
	private String adminCountry;
	
	@ApiModelProperty(notes = "The Admin's user name")
	@NotNull(message = "Admin user name is required field!")
	private String adminUserName;
	
	@ApiModelProperty(notes = "The Admin's password")
	@Size(min = 8, message = "Password should have at least 8 characters!")
	@NotNull(message = "Admin password is required field!")
	private String adminPassword;
	
}
