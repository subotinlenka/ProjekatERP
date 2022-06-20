package erp.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import erp.model.Customer;
import erp.model.Product;
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
public class ProductReviewDto {
	
	@ApiModelProperty(notes = "The unique identifier of the Product Review")
	private Integer productReviewID;
	
	@ApiModelProperty(notes = "The Product Review text")
	private String productReviewText;
	
	@ApiModelProperty(notes = "The Product Review date")
	@JsonFormat(pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date productReviewDate;
	
	@ApiModelProperty("The product to which the Review relates")
	private Product product;
	
	@ApiModelProperty("The customer who wrote the Product Review")
	private Customer customer;

}
