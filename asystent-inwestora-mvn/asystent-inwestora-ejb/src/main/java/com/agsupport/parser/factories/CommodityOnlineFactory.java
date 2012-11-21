package com.agsupport.parser.factories;

import com.agsupport.parser.derivative.CommodityOnlineParser;

public class CommodityOnlineFactory {
	private static CommodityOnlineParser[] parsers = new CommodityOnlineParser[] {
		/* Bullion */
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/bullion/gold.php", "Gold"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/bullion/silver.php", "Silver"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/bullion/diamond.php", "Diamond"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/bullion/palladium.php", "Palladium"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/bullion/platinum.php", "Platinum"),
		
		/* Metals */
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/metals/aluminum.php", "Aluminium"), /* To je amelinium! */
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/metals/copper.php", "Copper"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/metals/lead.php", "Lead"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/metals/nickel.php", "Nickel"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/metals/spongeiron.php", "Sponge Iron"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/metals/steel.php", "Steel"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/metals/tin.php", "Tin"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/metals/zinc.php", "Zinc"),
		
		/* Energy */
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/energy/brentcrudeoil.php", "Brent Crude Oil"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/energy/crudeoil.php", "Crude Oil"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/energy/furnaceoil.php", "Furnace Oil"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/energy/mesourcrudeoil.php", "M E Sour Crude Oil"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/energy/naturalgas.php", "Natural Gas"),
		
		/* Cereals */
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/cereal/maize.php", "Maize"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/cereal/rice.php", "Rice"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/cereal/sarbatirice.php", "Sharbati Rice"),

		/* Fibers */
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/fibers/cotton.php", "Cotton"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/fibers/cottonyarn.php", "Cotton Yarn"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/fibers/kapas.php", "Kapas"),

		/* Spices */
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/spices/cardamom.php", "Cardamom"),

		/* Others */
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/others/menthaoil.php", "Mentha Oil"),
		new CommodityOnlineParser("http://www.commodityonline.com/commodities/others/potato-agra.php", "Potato")
		
	};
	
	public static CommodityOnlineParser[] getParsers() {
		return parsers; 
	}
}
