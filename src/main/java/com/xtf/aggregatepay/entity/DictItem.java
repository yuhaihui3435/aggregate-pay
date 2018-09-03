package com.xtf.aggregatepay.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2018-09-03
*/
@Table(name="aggregate_pay_db.DICT_ITEM_T")
public class DictItem extends com.xtf.aggregatepay.core.BaseEntity  {
	
	private Integer id ;
	private Integer dictId ;
	private String dictItemCode ;
	private String dictItemName ;
	private String dictItemVal ;
	
	public DictItem() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getDictId(){
		return  dictId;
	}
	public void setDictId(Integer dictId ){
		this.dictId = dictId;
	}
	
	public String getDictItemCode(){
		return  dictItemCode;
	}
	public void setDictItemCode(String dictItemCode ){
		this.dictItemCode = dictItemCode;
	}
	
	public String getDictItemName(){
		return  dictItemName;
	}
	public void setDictItemName(String dictItemName ){
		this.dictItemName = dictItemName;
	}
	
	public String getDictItemVal(){
		return  dictItemVal;
	}
	public void setDictItemVal(String dictItemVal ){
		this.dictItemVal = dictItemVal;
	}
	

}
