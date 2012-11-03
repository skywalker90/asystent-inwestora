package com.agsupport.core.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Klasa modelu reprezentująca indeks danej giełdy.
 * @author Michał Gruszczyński
 *
 */

@Entity
@Table(name = "stock_index")
public class StockIndex {

	@Id
	@GeneratedValue
	@Column(name = "stock_index_id")
	private Long id;

	@Column(columnDefinition = "Decimal(15,2)")
	private Double price;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_add", nullable = false)
	private Date dateOfAdd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_page_update", nullable = false)
	private Date dateOfPageUpdate;

	@Column(name = "time_of_last_update")
	private String timeOfLastUpdate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "stock_market_id")
	private StockMarket stockMarket;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDateOfAdd() {
		return dateOfAdd;
	}

	public void setDateOfAdd(Date dateOfAdd) {
		this.dateOfAdd = dateOfAdd;
	}

	public Date getDateOfPageUpdate() {
		return dateOfPageUpdate;
	}

	public String getTimeOfLastUpdate() {
		return timeOfLastUpdate;
	}

	public void setTimeOfLastUpdate(String timeOfLastUpdate) {
		this.timeOfLastUpdate = timeOfLastUpdate;
	}

	public void setDateOfPageUpdate(Date dateOfPageUpdate) {
		this.dateOfPageUpdate = dateOfPageUpdate;
	}

	public StockMarket getStockMarket() {
		return stockMarket;
	}

	public void setStockMarket(StockMarket stockMarket) {
		this.stockMarket = stockMarket;
	}

}
