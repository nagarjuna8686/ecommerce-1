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
import ecommerce.dto.CartProdDto;
import ecommerce.dto.UsersDto;
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
	
	
	
	@POST
	@Path(value = "/regCart/{productID}/")
	public Response registerCart(@PathParam("productID")int productID, UsersDto udto) throws EcommerceException {
		if(cartdao.insert(productID, udto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	
	}
	
	@POST
	@Path(value = "/changeQuantityProduct/{productID}/{quantity}")
	public Response changeQuantityProduct(@PathParam("productID")int productID, @PathParam("quantity") int quantity, UsersDto udto) throws EcommerceException {
		if(cartdao.changeQuantity(productID, quantity, udto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	
	}
	
	@DELETE
	@Path(value = "/deleteCart")
	public Response deleteCartByID(UsersDto udto) throws EcommerceException {
		if(cartdao.delete(udto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	}
	
	@GET
	@Path(value = "/cartSearch/{offset}/{pageSize}")
	public Response cartSearch(@PathParam("offset")String offset, @PathParam("pageSize")String pageSize,
			UsersDto udto) throws EcommerceException{
		List<CartProdDto> listaCarrello = cartdao.cartSearch(offset,pageSize,udto);
		if(listaCarrello==null || listaCarrello.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaCarrello).build();

	}
	
	@GET
	@Path(value = "/orderCart/{sortField}/{sortDir}/{offset}/{pageSize}")
	public Response orderCart(@PathParam("filterValue") String filterValue,
			@PathParam("sortField") String sortField, @PathParam("sortDir") String sortDir,
			@PathParam("offset")String offset, @PathParam("pageSize")String pageSize, UsersDto udto) throws EcommerceException{
		List<CartProdDto> listaCart = cartdao.selectOrd(sortField, sortDir, offset, pageSize,udto);
		if(listaCart==null || listaCart.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaCart).build();

	}
	
	@DELETE
	@Path(value = "/deleteProdInCart/{productID}")
	public Response deleteProdByID(UsersDto udto, @PathParam("productID") int productID) throws EcommerceException {
		if(cartdao.deleteProd(udto, productID)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	}
	

}
