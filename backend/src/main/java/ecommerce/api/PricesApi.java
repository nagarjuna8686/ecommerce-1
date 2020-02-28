package ecommerce.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ecommerce.dao.PricesDao;
import ecommerce.dto.PricesDto;

@Path(value = "/prices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PricesApi {

	@EJB
	PricesDao pricesdao;

	@GET
	public List<PricesDto> getAllPrices() {
		List<PricesDto> listaPrezzi = pricesdao.selectAll();
		return listaPrezzi;

	}

	@GET
	@Path(value = "/pricesByID")
	public List<PricesDto> getPriceByID(PricesDto prdto) {
		List<PricesDto> listaPrezzi = pricesdao.selectByID(prdto);
		return listaPrezzi;
	}

	@DELETE
	@Path(value = "/deletePrice")
	public void deletePricetByID(PricesDto prdto) {
		pricesdao.delete(prdto);
	}

	@PUT
	@Path(value = "/updatePrice")
	public void updatePrice(PricesDto prdto) {
		pricesdao.updatePrice(prdto);
	}

	@PUT
	@Path(value = "/setDiscountDates")
	public void setDiscountDate(PricesDto prdto) {
		pricesdao.setDiscountDates(prdto);
	}

//	METODO PRECEDENTE PER UPDATE DEL DISCOUNT, ORA PARTE DEL METODO updatePrice (DEPRECATO(?))
//	@PUT
//	@Path(value = "/updateDiscount")
//	public void updateDiscount(PricesDto prdto) {
//		pricesdao.updateDiscount(prdto);
//	}
//
//	METODO PRECEDENTE PER CONTROLLO DEL DISCOUNT, ORA PARTE DEL METODO updatePrice (DEPRECATO(?))
//	@PUT
//	@Path(value = "/isDiscounted/{priceID}")
//	public void isDiscounted(@PathParam("priceID") String cond) {
//		pricesdao.isDiscounted(cond);
//	}

}
