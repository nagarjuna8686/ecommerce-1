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
	ProductsDao pdao;
	

	@GET
	public List<ProductsDto> getAllProducts() {
		List<ProductsDto> listaProdotti = pdao.selectAll();
		return listaProdotti;

	}
	
	@GET
	@Path(value = "/productsByName/{name}") 
	public List<ProductsDto> getProductByName(@PathParam("name")String cond) {
		List<ProductsDto> listaProdotti = pdao.selectByName(cond);
		return listaProdotti;
	}
	
	@GET
	@Path(value = "/productsById/{id}") 
	public List<ProductsDto> getProductById(@PathParam("id")String cond) {
		List<ProductsDto> listaProdotti = pdao.selectById(cond);
		return listaProdotti;
	}
	
	@DELETE
	@Path(value = "/deleteProduct/{productID}")
	public void deleteProductById(@PathParam("productID")String cond) {
		pdao.delete(cond);
	}
	
	@POST 
	@Path(value = "/regProduct") 
	public void registerProduct(ProductsDto pdto) {
		pdao.insert(pdto);
	}
	
	@PUT
	@Path(value = "/updateProduct") 
	public void updateProductById(ProductsDto pdto) {
		pdao.update(pdto);
	}

}