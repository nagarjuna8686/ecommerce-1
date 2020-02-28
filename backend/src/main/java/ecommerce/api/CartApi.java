package ecommerce.api;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ecommerce.dao.CartDao;
import ecommerce.dto.CartDto;
import ecommerce.exceptions.EcommerceException;

@Path(value = "/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartApi {
	
	@EJB
	CartDao cartdao;

	@GET
	public Response getAllCarts() throws EcommerceException {
		List<CartDto> listaCarrello = new ArrayList<>();
		listaCarrello = cartdao.selectAll();
		if(listaCarrello==null || listaCarrello.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaCarrello).build();

	
	}
	
	@GET
	@Path(value = "/cartByID/{cartID}") 
	public Response getProductByName(@PathParam("cartID")String cond) throws EcommerceException {
		List<CartDto> listaCarrello = cartdao.selectById(cond);
		if(listaCarrello==null || listaCarrello.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaCarrello).build();

	
	}
	
	@POST
	@Path(value = "/regCart/{userID}/{productID}")
	public Response registerCart(@PathParam("userID")int userID, @PathParam("productID")int prodID) throws EcommerceException {
		if(cartdao.insert(userID, prodID)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	
	}
	
	@DELETE
	@Path(value = "/deleteCart/{cartID}")
	public Response deleteCartByID(@PathParam("cartID")String cond) throws EcommerceException {
		if(cartdao.delete(cond)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	}
	
	
	

}
