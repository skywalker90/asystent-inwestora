package com.agsupport.core.service.share;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.agsupport.core.jpa.facade.DerivativeFacade;
import com.agsupport.core.jpa.facade.DerivativeValueFacade;
import com.agsupport.core.jpa.facade.StockIndexFacade;
import com.agsupport.core.jpa.facade.StockMarketFacade;
import com.agsupport.core.jpa.model.Derivative;
import com.agsupport.core.jpa.model.DerivativeValue;
import com.agsupport.core.jpa.model.StockIndex;
import com.agsupport.core.jpa.model.StockMarket;

@Stateless
@Path("/service")
public class HelloWorldResource {

	@EJB
	DerivativeFacade derivativeFacade;

	@EJB
	DerivativeValueFacade derivativeValueFacade;

	@EJB
	StockIndexFacade stockIndexFacade;

	@EJB
	StockMarketFacade stockMarketFacade;

	private Logger logger = Logger.getLogger("HelloWorldResource");

	@GET
	@Produces("application/json")
	@Path("getDerivativeList")
	public List<Derivative> getDerivativeList() {
		return derivativeFacade.getDerivativeList();
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getDerivativeById")
	public Derivative getDerivativeById(
			@QueryParam("derivativeId") long derivativeId) {
		return derivativeFacade.getDerivativeById(derivativeId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getDerivativeByName")
	public Derivative getDerivativeByName(@QueryParam("name") String name) {
		return derivativeFacade.getDerivativeByName(name);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("deleteDerivativeValue")
	public boolean deleteDerivativeValue(
			@QueryParam("derivativeValueId") long derivativeValueId) {
		return derivativeFacade.deleteDerivativeValue(derivativeValueId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("updateDerivative")
	public boolean updateDerivative(@QueryParam("dateOfAdd") long dateOfAdd,
			@QueryParam("description") String description,
			@QueryParam("name") String name, @QueryParam("id") long id) {
		Derivative derivative = new Derivative();
		derivative.setDateOfAdd(new Date(dateOfAdd));
		derivative.setDescription(description);
		derivative.setName(name);
		derivative.setId(id);
		return derivativeFacade.updateDerivative(derivative);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getDerivativeValuesByDateOfAdd")
	public List<DerivativeValue> getDerivativeValuesByDateOfAdd(
			@QueryParam("dateOfAdd") long dateOfAdd,
			@QueryParam("derivativeId") long derivativeId) {
		return derivativeValueFacade.getDerivativeValuesByDateOfAdd(new Date(
				dateOfAdd), derivativeId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getDerivativeValuesForRangeAndExpireDate")
	public List<DerivativeValue> getDerivativeValuesForRangeAndExpireDate(
			@QueryParam("from") long from, @QueryParam("to") long to,
			@QueryParam("expierdDate") long expierdDate,
			@QueryParam("derivativeId") long derivativeId) {
		return derivativeValueFacade.getDerivativeValuesForRangeAndExpireDate(
				new Date(from), new Date(to), new Date(expierdDate),
				derivativeId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getExpiredDateList")
	public List<Date> getExpiredDateList(
			@QueryParam("derivativeId") long derivativeId) {
		return derivativeValueFacade.getExpiredDateList(derivativeId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getDateOfAddList")
	public List<Date> getDateOfAddList(
			@QueryParam("derivativeId") long derivativeId) {
		return derivativeValueFacade.getDateOfAddList(derivativeId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getAllStockIndex")
	public List<StockIndex> getAllStockIndex(
			@QueryParam("stockMarketId") long stockMarketId) {
		return stockIndexFacade.getAllStockIndex(stockMarketId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getAllStockIndex")
	public List<StockIndex> getAllStockIndex(
			@QueryParam("stockMarketId") long stockMarketId,
			@QueryParam("from") long from, @QueryParam("to") long to) {
		return stockIndexFacade.getStockIndexForRange(stockMarketId, new Date(
				from), new Date(to));
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("updateStockMarket")
	public boolean updateStockMarket(
			@QueryParam("stockMarketId") long stockMarketId,
			@QueryParam("dateOfAdd") long dateOfAdd,
			@QueryParam("description") String description,
			@QueryParam("abbreviatedName") String abbreviatedName,
			@QueryParam("name") String name) {
		StockMarket stockMarket = new StockMarket();
		stockMarket.setAbbreviatedName(abbreviatedName);
		stockMarket.setDateOfAdd(new Date(dateOfAdd));
		stockMarket.setDescription(description);
		stockMarket.setId(stockMarketId);
		stockMarket.setName(name);
		return stockMarketFacade.updateStockMarket(stockMarket);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getStockMarketList")
	public List<StockMarket> getStockMarketList() {
		return stockMarketFacade.getStockMarketList();
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getStockMarketById")
	public StockMarket getStockMarketById(
			@QueryParam("stockMarketId") long stockMarketId) {
		return stockMarketFacade.getStockMarketById(stockMarketId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getStockMarketByAbbreviatedName")
	public StockMarket getStockMarketByAbbreviatedName(
			@QueryParam("abberviatedName") String abberviatedName) {
		return stockMarketFacade
				.getStockMarketByAbbreviatedName(abberviatedName);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("deleteStockIndex")
	public boolean deleteStockIndex(
			@QueryParam("stockIndexId") long stockIndexId) {
		return stockMarketFacade.deleteStockIndex(stockIndexId);
	}

}
