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

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import ecommerce.dao.WishlistDao;
import ecommerce.dto.WishlistDto;

@Path(value = "/wishlist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(info = @Info(title = "Web-API Service", version = "1.0", description = "Web-API Service APIs"))
public class WishlistApi {

	@EJB
	WishlistDao wishlistdao;

	@GET
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Get most recently added articles", content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = WishlistDto[].class))),
			@APIResponse(responseCode = "500", description = "Internal service error") })
	@Operation(summary = "Get most recently added articles", description = "Get most recently added articles")
	public List<WishlistDto> getAllWishlist() {
		List<WishlistDto> listaWishlist = new ArrayList<>();
		listaWishlist = wishlistdao.selectAll();
		return listaWishlist;
	}

	@GET
	@Path("/getWishByID/{wishlistID}")
	public List<WishlistDto> getWishlistBYUserID(@PathParam("wishlistID") int id) {
		List<WishlistDto> listaWishlist = new ArrayList<>();
		listaWishlist = wishlistdao.selectByWishID(id);
		return listaWishlist;
	}

	@POST
	@Path("/regWish/{userID}/{productID}")
	public void regUser(@PathParam("userID") int userID, @PathParam("productID") int prodID) {
		wishlistdao.insert(userID, prodID);
		return;
	}

	@DELETE
	@Path("/deleteWishByID/{wishlistID}")
	public void deleteWishlistByID(@PathParam("wishlistID") int id) {
		wishlistdao.deleteWishlistByID(id);
	}
}
