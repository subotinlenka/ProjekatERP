package erp.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private String adminFirstName;

	@ApiModelProperty(notes = "The Admin's last name")
	private String adminLastName;
	
	@ApiModelProperty(notes = "The Admin's date of birth")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date adminDateOfBirth;

	@ApiModelProperty(notes = "The Admin's phone number")
	@Column(name = "customerphonenumber")
	private String customerPhoneNumber;
	
	@ApiModelProperty(notes = "The Admin's email")
	private String adminEmail;

	@ApiModelProperty(notes = "The Admin's address")
	private String adminAddress;

	@ApiModelProperty(notes = "The Admin's city")
	private String adminCity;

	@ApiModelProperty(notes = "The Admin's country")
	private String adminCountry;
	
	@ApiModelProperty(notes = "The Admin's user name")
	private String adminUserName;
	
	@ApiModelProperty(notes = "The Admin's password")
	private String adminPassword;
	
}
