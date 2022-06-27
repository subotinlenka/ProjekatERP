package erp.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import erp.model.Role;
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
public class UserDto {

	@ApiModelProperty(notes = "The unique identifier of the User")
	private Integer userID;
	
	@ApiModelProperty(notes = "The User's first name")
	private String userFirstName;

	@ApiModelProperty(notes = "The User's last name")
	private String userLastName;
	
	@ApiModelProperty(notes = "The User's date of birth")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date userDateOfBirth;

	@ApiModelProperty(notes = "The User's phone number")
	private String userPhoneNumber;
	
	@ApiModelProperty(notes = "The User's email")
	private String userEmail;

	@ApiModelProperty(notes = "The User's address")
	private String userAddress;

	@ApiModelProperty(notes = "The User's city")
	private String userCity;

	@ApiModelProperty(notes = "The User's country")
	private String userCountry;
	
	@ApiModelProperty(notes = "The User's user name")
	private String userUserName;
	
	@ApiModelProperty(notes = "The User's role")
	private Role role;

}
