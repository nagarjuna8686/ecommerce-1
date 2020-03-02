package ecommerce.api;
import java.util.ArrayList;
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
import ecommerce.dao.UsersDao;
import ecommerce.dto.PasswordsDto;
import ecommerce.dto.UsersDto;
import ecommerce.exceptions.EcommerceException;

@Path(value = "/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsersApi {

	@EJB
	UsersDao userdao;

	@GET
	public Response getAllUsers() throws EcommerceException {
		List<UsersDto> listaUtenti = new ArrayList<>();
		listaUtenti = userdao.selectAll();
		if(listaUtenti==null || listaUtenti.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaUtenti).build();
	}
	
	@GET
	@Path("/usersByName/{name}")
	public Response getUserByName(@PathParam("name") String n) throws EcommerceException {
		List<UsersDto> listaUtenti = new ArrayList<>();
		listaUtenti = userdao.selectByName(n);
		if(listaUtenti==null || listaUtenti.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaUtenti).build();
	}
	
	@GET
	@Path("/userByID/{userID}")
	public Response getUserByID(@PathParam("userID") int id) throws EcommerceException {
		List<UsersDto> listaUtenti = new ArrayList<>();
		listaUtenti = userdao.selectByID(id);
		if(listaUtenti==null || listaUtenti.size()==0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(listaUtenti).build();
	}
	
	@POST
	@Path("/regUser")
	public Response regUser( UsersDto udto) throws EcommerceException {
		if(userdao.insert(udto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.CONFLICT).build();
	}
	
	@PUT
	@Path("/updateUser")
	public Response updateUserByID(UsersDto udto) throws EcommerceException {
		if(userdao.updateUserByID(udto)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("/changePassword/{userID}")
	public Response changePasswordByID(@PathParam("userID")int id, PasswordsDto passwords) throws EcommerceException {
		if(userdao.changeUserPassword(id,passwords)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	
	@DELETE
	@Path("/deleteUserByID/{userID}")
	public Response deleteUserByID(@PathParam("userID") int id) throws EcommerceException {
		if(userdao.deleteUser(id)>0) {
			return Response.status(Status.NO_CONTENT).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
	@POST
	@Path("/resetPassword")
	public Response resetPasswordByEmail(UsersDto udto) throws EcommerceException {
		userdao.resetPassword(udto);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	
}