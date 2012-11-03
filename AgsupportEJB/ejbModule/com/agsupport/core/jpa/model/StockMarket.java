package com.agsupport.core.jpa.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stock_market")
public class StockMarket {

	@Id
	@GeneratedValue
	@Column(name = "stock_market_id")
	private Long id;

	
	private String name;

	@Column(name = "abbreviated_name", nullable = false)
	private String abbreviatedName;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_of_add", nullable = false)
	private Date dateOfAdd;

	@OneToMany(mappedBy = "stockMarket", cascade = CascadeType.ALL)
	private List<StockIndex> stockIndexes = new LinkedList<StockIndex>();

	@OneToMany(mappedBy = "stockMarket", cascade = CascadeType.ALL)
	private List<Company> companies = new LinkedList<Company>();

	public Long getId() {
		return id;
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

	public String getAbbreviatedName() {
		return abbreviatedName;
	}

	public void setAbbreviatedName(String abbreviatedName) {
		this.abbreviatedName = abbreviatedName;
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

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<StockIndex> getStockIndexes() {
		return stockIndexes;
	}

	public void setStockIndexes(List<StockIndex> stockIndexes) {
		this.stockIndexes = stockIndexes;
	}

}
