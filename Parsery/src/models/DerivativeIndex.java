package models;

import interfaces.Index;

import java.util.Date;


public class DerivativeIndex implements Index{
	private Date dateOfAdd;
	private Date expiredDate;
	private String Value;
	private String name;
	
	public Date getDateOfAdd() {
		return dateOfAdd;
	}
	public void setDateOfAdd(Date dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		String str =
			"Name: " + getName() + "\n" +
			"date of add: " + dateOfAdd.toString() + "\n" +
			"expired date: " + expiredDate.toString() + "\n";// +
//			"Time: " + _time.toString() + "\n";
		
		return str;
	}
	
	
}
