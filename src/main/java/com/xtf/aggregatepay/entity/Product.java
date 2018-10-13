package com.xtf.aggregatepay.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-10-13
*/
@Table(name="PRODUCT_T")
@Data
@Builder
public class Product extends com.xtf.aggregatepay.core.BaseEntity  {
	
	private Integer id ;
	private BigDecimal maxPrice ;
	private BigDecimal minPrice ;
	private String channelCode ;
	private String industryCode ;
	private String productName ;
	private BigDecimal price;
	@Tolerate
	public Product() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public String getChannelCode(){
		return  channelCode;
	}
	public void setChannelCode(String channelCode ){
		this.channelCode = channelCode;
	}
	
	public String getIndustryCode(){
		return  industryCode;
	}
	public void setIndustryCode(String industryCode ){
		this.industryCode = industryCode;
	}
	
	public String getProductName(){
		return  productName;
	}
	public void setProductName(String productName ){
		this.productName = productName;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
}
