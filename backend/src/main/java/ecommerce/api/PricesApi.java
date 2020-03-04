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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ecommerce.dao.PricesDao;
import ecommerce.dto.PricesDto;
import ecommerce.exceptions.EcommerceException;

@Path(value = "/prices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PricesApi {

	@EJB
	PricesDao pricesdao;

	@GET
	public Response getAllPrices() throws EcommerceException {
		List<PricesDto> listaPrezzi = pricesdao.selectAll();
		if(listaPrezzi==null || listaPrezzi.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaPrezzi).build();

	}

	@GET
	@Path(value = "/pricesByID")
	public Response getPriceByID(PricesDto prdto) throws EcommerceException {
		List<PricesDto> listaPrezzi = pricesdao.selectByID(prdto);
		if(listaPrezzi==null || listaPrezzi.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaPrezzi).build();

	}

	@DELETE
	@Path(value = "/deletePrice")
	public Response deletePricetByID(PricesDto prdto) throws EcommerceException {
		if(pricesdao.delete(prdto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	}

	@PUT
	@Path(value = "/updatePrice")
	public Response updatePrice(PricesDto prdto) throws EcommerceException {
		if(pricesdao.updatePrice(prdto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	}

	@PUT
	@Path(value = "/setDiscountDates")
	public Response setDiscountDate(PricesDto prdto) throws EcommerceException {
		if(pricesdao.setDiscountDates(prdto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
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
