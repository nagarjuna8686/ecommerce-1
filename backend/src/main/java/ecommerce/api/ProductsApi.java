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

import ecommerce.dao.ProductsDao;
import ecommerce.dto.ProductsDto;

@Path(value = "/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductsApi {

	@EJB
	ProductsDao productdao;
	

	@GET
	public List<ProductsDto> getAllProducts() {
		List<ProductsDto> listaProdotti = productdao.selectAll();
		return listaProdotti;

	}
	
	@GET
	@Path(value = "/productsByName/{name}") 
	public List<ProductsDto> getProductByName(@PathParam("name")String cond) {
		List<ProductsDto> listaProdotti = productdao.selectByName(cond);
		return listaProdotti;
	}
	
	@GET
	@Path(value = "/productsByID/{productID}") 
	public List<ProductsDto> getProductByID(@PathParam("productID")String cond) {
		List<ProductsDto> listaProdotti = productdao.selectById(cond);
		return listaProdotti;
	}
	
	@DELETE
	@Path(value = "/deleteProduct/{productID}")
	public void deleteProductByID(@PathParam("productID")String cond) {
		productdao.delete(cond);
	}
	
	@POST 
	@Path(value = "/regProduct") 
	public void registerProduct(ProductsDto pdto) {
		productdao.insert(pdto);
	}
	
	@PUT
	@Path(value = "/updateProduct") 
	public void updateProductByID(ProductsDto pdto) {
		productdao.update(pdto);
	}

}