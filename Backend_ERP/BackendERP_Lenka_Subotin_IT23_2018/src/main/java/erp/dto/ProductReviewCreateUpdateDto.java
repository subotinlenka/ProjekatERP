package erp.dto;

import java.util.Date;

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
@ApiModel(description = "Details about the Product Review")
public class ProductReviewCreateUpdateDto {

	@ApiModelProperty(notes = "The Product Review text")
	private String productReviewText;
	
	@ApiModelProperty(notes = "The Product Review date")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date productReviewDate;
	
	@ApiModelProperty("The ID of the product to which the Review relates")
	private Integer productID;
	
	@ApiModelProperty("The ID of the customer who wrote the Product Review")
	private Integer customerID;

}
