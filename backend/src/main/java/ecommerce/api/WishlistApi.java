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
import ecommerce.dao.WishlistDao;
import ecommerce.dto.WishlistDto;



	@Path(value = "/wishlist")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public class WishlistApi {

		@EJB
		WishlistDao wishlistdao;

		@GET
		public List<WishlistDto> getAllWishlist() {
			List<WishlistDto> listaWishlist = new ArrayList<>();
			listaWishlist = wishlistdao.selectAll();
			return listaWishlist;
		}
		
		@GET
		@Path("/getWishByID/{wishlistID}")
		public List<WishlistDto> getWishlistBYUserID(@PathParam("wishlistID") int id) {
			List<WishlistDto> listaWishlist = new ArrayList<>();
			listaWishlist = wishlistdao.selectByUserID(id);
			return listaWishlist;
		}
		
		@POST
		@Path("/regWish/{userID}/{productID}")
		public void regUser( @PathParam("userID") int userID,  @PathParam("productID") int prodID) {
			wishlistdao.insert(userID,prodID);
			return;
		}
		
		@DELETE
		@Path("/deleteWishByID/{wishlistID}")
		public void deleteWishlistByID(@PathParam("wishlistID") int id) {
			wishlistdao.deleteWishlistByID(id);
		}
	}
