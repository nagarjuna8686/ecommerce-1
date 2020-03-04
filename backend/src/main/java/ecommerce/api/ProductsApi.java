package ecommerce.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ecommerce.dao.ProductsDao;
import ecommerce.dto.ProductsDto;
import ecommerce.exceptions.EcommerceException;

@Path(value = "/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductsApi {

	@EJB
	ProductsDao productdao;

	@GET
	public Response getAllProducts() throws EcommerceException{
		List<ProductsDto> listaProdotti = productdao.selectAll();
		if(listaProdotti==null || listaProdotti.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaProdotti).build();

	}

	@GET
	@Path(value = "/productsByName/{name}")
	public Response getProductByName(@PathParam("name") String cond) throws EcommerceException{
		List<ProductsDto> listaProdotti = productdao.selectByName(cond);
		if(listaProdotti==null || listaProdotti.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaProdotti).build();

	}

	@GET
	@Path(value = "/productsByID/{productID}")
	public Response getProductByID(@PathParam("productID") String cond) throws EcommerceException {
		List<ProductsDto> listaProdotti = productdao.selectById(cond);
		if(listaProdotti==null || listaProdotti.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaProdotti).build();
	}

	@DELETE
	@Path(value = "/deleteProduct/{productID}")
	public Response deleteProductByID(@PathParam("productID") String cond) throws EcommerceException {
		if(productdao.delete(cond)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	}

	@POST
	@Path(value = "/regProduct")
	public Response registerProduct(ProductsDto pdto) throws EcommerceException {
		if(productdao.insert(pdto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	}

	
	//PER UPDATE DEL PREZZO USARE IL METEODO DI UPDATE PRESENTE IN Prices.Api
	@PUT
	@Path(value = "/updateProduct")
	public Response updateProductByID(ProductsDto pdto) throws EcommerceException {
		if(productdao.update(pdto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	
	}
	
	@GET
	@Path(value = "/productsSearch/{cond}/{offset}/{pageSize}")
	public Response selectSearch(@PathParam("cond") String cond) throws EcommerceException{
		List<ProductsDto> listaProdotti = productdao.selectSearch(cond);
		if(listaProdotti==null || listaProdotti.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaProdotti).build();

	}
	
	@GET
	@Path(value = "/productsOrd/{filterField}/{filterValue}/{sortField}/{sortDir}/{offset}/{pageSize}")
	public Response selectOrd(@PathParam("filterField") String filterField, @PathParam("filterValue") String filterValue, @PathParam("sortField") String sortField, @PathParam("sortDir") String sortDir) throws EcommerceException{
		List<ProductsDto> listaProdotti = productdao.selectOrd(filterField, filterValue, sortField, sortDir);
		if(listaProdotti==null || listaProdotti.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaProdotti).build();

	}

}