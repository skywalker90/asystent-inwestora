package example;

import java.util.Date;

import com.agsupport.core.jpa.model.StockMarket;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ShowExample {

	public static void main(String[] args) {

		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(JacksonJsonProvider.class);
		Client c = Client.create(cc);

		Date d1 = new Date();
		
		/**
		 * przykład użycia klienta
		 * biblioteki wszystkie wymagane do użycia tego klienta są w katalogu LIB, tego projektu
		 */
		
		WebResource wr1 = c.resource("http://localhost:8080/NazwaProjektu");
		StockMarket resp = wr1.path("NawzwaFunckji")
				.queryParam("nazwaParametru1", "WartoscParametru1")
				.queryParam("data1", Long.toString(d1.getTime())).accept("application/json")
				.get(StockMarket.class);

	}

}
