package models;

import interfaces.Index;

import java.util.Date;


public class MarketIndex implements Index{
	private Date _time;
	private String _name;
	private String _value; /* @TODO: change to Double? */
	
	public Date getTime() {
		return _time;
	}
	
	public void setTime(Date time) {
		this._time = time;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		this._name = name;
	}
	
	public String getValue() {
		return _value;
	}
	
	public void setValue(String value) {
		this._value = value;
	}
	
	public String toString() {
		String str =
			"Name: " + _name + "\n" +
			"Value: " + _value + "\n";// +
//			"Time: " + _time.toString() + "\n";
		
		return str;
	}
}
