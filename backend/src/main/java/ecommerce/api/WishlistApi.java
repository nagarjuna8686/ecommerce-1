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

import ecommerce.dao.WishlistDao;
import ecommerce.dto.WishlistDto;
import ecommerce.exceptions.EcommerceException;



	@Path(value = "/wishlist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public class WishlistApi {

		@EJB
		WishlistDao wishlistdao;

		@GET
		public Response getAllWishlist() throws EcommerceException {
			List<WishlistDto> listaWishlist = new ArrayList<>();
			listaWishlist = wishlistdao.selectAll();
			if(listaWishlist==null || listaWishlist.size()==0) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(listaWishlist).build();

		}
		
		@GET
		@Path("/getWishByID/{wishlistID}")
		public Response getWishlistBYUserID(@PathParam("wishlistID") int id) throws EcommerceException {
			List<WishlistDto> listaWishlist = new ArrayList<>();
			listaWishlist = wishlistdao.selectByWishID(id);
			if(listaWishlist==null || listaWishlist.size()==0) {
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.ok(listaWishlist).build();

		}
		
		@POST
		@Path("/regWish/{userID}/{productID}")
		public Response regUser( @PathParam("userID") int userID,  @PathParam("productID") int prodID) throws EcommerceException {
			if(wishlistdao.insert(userID,prodID)>0) {
				return Response.status(Status.NO_CONTENT).build();
			}
			return Response.status(Status.CONFLICT).build();
		
		}
		
		
		@DELETE
		@Path("/deleteWishByID/{wishlistID}")
		public Response deleteWishlistByID(@PathParam("wishlistID") int id) throws EcommerceException {
			if(wishlistdao.deleteWishlistByID(id)>0) {
				return Response.status(Status.NO_CONTENT).build();
			}
			return Response.status(Status.CONFLICT).build();
		
		}
		
	}
