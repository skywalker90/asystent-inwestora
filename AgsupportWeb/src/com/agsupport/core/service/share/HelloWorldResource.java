package com.agsupport.core.service.share;

import java.util.Date;
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
import com.agsupport.core.jpa.model.StockMarket;
import com.agsupport.core.service.communication.ListOfDate;
import com.agsupport.core.service.communication.ListOfDerivative;
import com.agsupport.core.service.communication.ListOfDerivativeValue;
import com.agsupport.core.service.communication.ListOfStockIndex;
import com.agsupport.core.service.communication.ListOfStockMarket;

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
	public ListOfDerivative getDerivativeList() {
		logger.info("HelloWorldResource.getDerivativeList - invoked");
		return new ListOfDerivative(derivativeFacade.getDerivativeList());
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getDerivativeById")
	public Derivative getDerivativeById(
			@QueryParam("derivativeId") long derivativeId) {
		logger.info("HelloWorldResource.getDerivativeById - invoked");
		return derivativeFacade.getDerivativeById(derivativeId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getDerivativeByName")
	public Derivative getDerivativeByName(@QueryParam("name") String name) {
		logger.info("HelloWorldResource.getDerivativeByName - invoked");
		return derivativeFacade.getDerivativeByName(name);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("deleteDerivativeValue")
	public Boolean deleteDerivativeValue(
			@QueryParam("derivativeValueId") long derivativeValueId) {
		logger.info("HelloWorldResource.deleteDerivativeValue - invoked");
		return derivativeFacade.deleteDerivativeValue(derivativeValueId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("updateDerivative")
	public Boolean updateDerivative(@QueryParam("dateOfAdd") long dateOfAdd,
			@QueryParam("description") String description,
			@QueryParam("name") String name, @QueryParam("id") long id) {
		logger.info("HelloWorldResource.updateDerivative - invoked");
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
	public ListOfDerivativeValue getDerivativeValuesByDateOfAdd(
			@QueryParam("dateOfAdd") long dateOfAdd,
			@QueryParam("derivativeId") long derivativeId) {
		logger.info("HelloWorldResource.getDerivativeValuesByDateOfAdd - invoked");
		return new ListOfDerivativeValue(
				derivativeValueFacade.getDerivativeValuesByDateOfAdd(new Date(
						dateOfAdd), derivativeId));
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getDerivativeValuesForRangeAndExpireDate")
	public ListOfDerivativeValue getDerivativeValuesForRangeAndExpireDate(
			@QueryParam("from") long from, @QueryParam("to") long to,
			@QueryParam("expierdDate") long expierdDate,
			@QueryParam("derivativeId") long derivativeId) {
		logger.info("HelloWorldResource.getDerivativeValuesForRangeAndExpireDate - invoked");
		return new ListOfDerivativeValue(
				derivativeValueFacade.getDerivativeValuesForRangeAndExpireDate(
						new Date(from), new Date(to), new Date(expierdDate),
						derivativeId));
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getExpiredDateList")
	public ListOfDate getExpiredDateList(
			@QueryParam("derivativeId") long derivativeId) {
		logger.info("HelloWorldResource.getExpiredDateList - invoked");
		return new ListOfDate(
				derivativeValueFacade.getExpiredDateList(derivativeId));
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getDateOfAddList")
	public ListOfDate getDateOfAddList(
			@QueryParam("derivativeId") long derivativeId) {
		logger.info("HelloWorldResource.getDateOfAddList - invoked");
		return new ListOfDate(
				derivativeValueFacade.getDateOfAddList(derivativeId));
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getAllStockIndex")
	public ListOfStockIndex getAllStockIndex(
			@QueryParam("stockMarketId") long stockMarketId) {
		logger.info("HelloWorldResource.getAllStockIndex - invoked");
		return new ListOfStockIndex(
				stockIndexFacade.getAllStockIndex(stockMarketId));
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getAllStockIndex")
	public ListOfStockIndex getAllStockIndex(
			@QueryParam("stockMarketId") long stockMarketId,
			@QueryParam("from") long from, @QueryParam("to") long to) {
		logger.info("HelloWorldResource.getAllStockIndex - invoked");
		return new ListOfStockIndex(stockIndexFacade.getStockIndexForRange(
				stockMarketId, new Date(from), new Date(to)));
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("updateStockMarket")
	public Boolean updateStockMarket(
			@QueryParam("stockMarketId") long stockMarketId,
			@QueryParam("dateOfAdd") long dateOfAdd,
			@QueryParam("description") String description,
			@QueryParam("abbreviatedName") String abbreviatedName,
			@QueryParam("name") String name) {
		logger.info("HelloWorldResource.updateStockMarket - invoked");
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
	public ListOfStockMarket getStockMarketList() {
		logger.info("HelloWorldResource.getStockMarketList - invoked");
		return new ListOfStockMarket(stockMarketFacade.getStockMarketList());
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getStockMarketById")
	public StockMarket getStockMarketById(
			@QueryParam("stockMarketId") long stockMarketId) {
		logger.info("HelloWorldResource.getStockMarketById - invoked");
		return stockMarketFacade.getStockMarketById(stockMarketId);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("getStockMarketByAbbreviatedName")
	public StockMarket getStockMarketByAbbreviatedName(
			@QueryParam("abberviatedName") String abberviatedName) {
		logger.info("HelloWorldResource.getStockMarketByAbbreviatedName - invoked");
		return stockMarketFacade
				.getStockMarketByAbbreviatedName(abberviatedName);
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("deleteStockIndex")
	public Boolean deleteStockIndex(
			@QueryParam("stockIndexId") long stockIndexId) {
		logger.info("HelloWorldResource.deleteStockIndex - invoked");
		return stockMarketFacade.deleteStockIndex(stockIndexId);
	}

}
