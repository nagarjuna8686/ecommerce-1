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

import ecommerce.dao.WishProdDto;
import ecommerce.dao.WishlistDao;
import ecommerce.dto.UsersDto;
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
    @Path("/regWish/{productID}")
    public Response regWish(@PathParam("productID") int prodID, UsersDto udto) throws EcommerceException {
      if(wishlistdao.insert(prodID,udto)>0) {
        return Response.status(Status.NO_CONTENT).build();
      }
      return Response.status(Status.CONFLICT).build();
    
    }
    
    
    @DELETE
    @Path("/deleteWishByID")
    public Response deleteWishlistByID(UsersDto udto) throws EcommerceException {
      if(wishlistdao.deleteWishlistByID(udto)>0) {
        return Response.status(Status.NO_CONTENT).build();
      }
      return Response.status(Status.CONFLICT).build();
    
    }
    
    @DELETE
    @Path("/deleteProd/{wishlistID}")
    public Response deleteProd(@PathParam("wishlistID") int wishlistID) throws EcommerceException {
      if(wishlistdao.deleteProd(wishlistID)>0) {
        return Response.status(Status.NO_CONTENT).build();
      }
      return Response.status(Status.CONFLICT).build();
    }
    
    
    @GET
	@Path(value = "/searchWishByID/{offset}/{pageSize}")
	public Response wishSearch(@PathParam("offset")String offset, @PathParam("pageSize")String pageSize, UsersDto udto) throws EcommerceException{
		List<WishProdDto> listaWish = wishlistdao.wishSearch(offset,pageSize,udto);
		if(listaWish==null || listaWish.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaWish).build();

	}
	
	@GET
	@Path(value = "/wishOrd/{sortField}/{sortDir}/{offset}/{pageSize}")
	public Response selectOrd(@PathParam("sortField") String sortField, @PathParam("sortDir") String sortDir,
			@PathParam("offset")String offset, @PathParam("pageSize")String pageSize,
			UsersDto udto) throws EcommerceException{
		List<WishProdDto> listaWish = wishlistdao.selectOrd(sortField, sortDir,offset,pageSize, udto);
		if(listaWish==null || listaWish.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaWish).build();

	}
    
  }
  