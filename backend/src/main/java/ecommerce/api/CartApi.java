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

import ecommerce.dao.CartDao;
import ecommerce.dto.CartDto;

@Path(value = "/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartApi {
	
	@EJB
	CartDao cartdao;

	@GET
	public List<CartDto> getAllCarts() {
		List<CartDto> listaCarrello = new ArrayList<>();
		listaCarrello = cartdao.selectAll();
		return listaCarrello;
	}
	
	@GET
	@Path(value = "/cartByID/{cartID}") 
	public List<CartDto> getProductByName(@PathParam("cartID")String cond) {
		List<CartDto> listaCarrello = cartdao.selectById(cond);
		return listaCarrello;
	}
	
	@POST
	@Path(value = "/regCart/{userID}/{productID}")
	public void registerCart(@PathParam("userID")int userID, @PathParam("productID")int prodID) {
		cartdao.insert(userID, prodID);
		return;
	}
	
	@DELETE
	@Path(value = "/deleteCart/{cartID}")
	public void deleteCartByID(@PathParam("cartID")String cond) {
		cartdao.delete(cond);
	}
	
	
	

}
