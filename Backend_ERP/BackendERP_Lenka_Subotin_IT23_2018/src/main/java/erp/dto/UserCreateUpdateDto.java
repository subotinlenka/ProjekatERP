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
@ApiModel(description = "Details about the User")
public class UserCreateUpdateDto {
	
	@ApiModelProperty(notes = "The User's first name")
	@NotNull(message = "User first name is required field!")
	private String userFirstName;

	@ApiModelProperty(notes = "The User's last name")
	@NotNull(message = "User last name is required field!")
	private String userLastName;
	
	@ApiModelProperty(notes = "The User's date of birth")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "User date of birth is required field!")
	private Date userDateOfBirth;

	@ApiModelProperty(notes = "The User's phone number")
	private String userPhoneNumber;
	
	@ApiModelProperty(notes = "The User's email")
	@Email(message = "Invalid format of the email address!")
	@NotNull(message = "User email is required field!")
	private String userEmail;

	@ApiModelProperty(notes = "The User's address")
	@NotNull(message = "User address is required field!")
	private String userAddress;

	@ApiModelProperty(notes = "The User's city")
	@NotNull(message = "User city is required field!")
	private String userCity;

	@ApiModelProperty(notes = "The User's country")
	@NotNull(message = "User country is required field!")
	private String userCountry;
	
	@ApiModelProperty(notes = "The User's user name")
	@NotNull(message = "User's user name is required field!")
	private String userUserName;
	
	@ApiModelProperty(notes = "The User's password")
	@Size(min = 8, message = "Password should have at least 8 characters!")
	@NotNull(message = "User password is required field!")
	private String userPassword;
	
	/*@ApiModelProperty(notes = "The Role ID related to the User")
	@NotNull(message = "Role ID can not be null!")
	private Integer roleID;*/
}
