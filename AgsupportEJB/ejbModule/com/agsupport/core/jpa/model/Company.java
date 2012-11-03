package com.agsupport.core.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue
	@Column(name = "company_id")
	private Long id;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String name;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_add", nullable = false)
	private Date dateOfAdd;

	@ManyToOne(optional = false)
	@JoinColumn(name = "stock_market_id")
	private StockMarket stockMarket;

	public Long getId() {
		return id;
	}

	public StockMarket getStockMarket() {
		return stockMarket;
	}

	public void setStockMarket(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateOfAdd() {
		return dateOfAdd;
	}

	public void setDateOfAdd(Date dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}

}
