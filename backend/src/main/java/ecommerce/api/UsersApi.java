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

import ecommerce.dao.UsersDao;
import ecommerce.dto.UsersDto;



@Path(value = "/users")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class UsersApi {

	@EJB
	UsersDao userdao;

	@GET
	public List<UsersDto> getAllUsers() {
		List<UsersDto> listaUtenti = new ArrayList<>();
		listaUtenti = userdao.selectAll();
		return listaUtenti;
	}
	
	@GET
	@Path("/usersByName/{name}")
	public List<UsersDto> getUserByName(@PathParam("name") String n) {
		List<UsersDto> listaUtenti = new ArrayList<>();
		listaUtenti = userdao.selectByName(n);
		return listaUtenti;
	}
	
	@GET
	@Path("/userByID/{userID}")
	public List<UsersDto> getUserByName(@PathParam("userID") int id) {
		return userdao.selectByID(id);
	}
	
	@POST
	@Path("/regUser")
	public void regUser( UsersDto udto) {
		userdao.insert(udto);
		return;
	}
	
	@PUT
	@Path("/updateUser")
	public void updateUserByID(UsersDto udto) {
		userdao.updateUserByID(udto);
	}
	
	@PUT
	@Path("/changePassword/{userID}")
	public void changePasswordByID(@PathParam("userID")int id, String psw) {
		userdao.changeUserPassword(id,psw);
	}
	
	@DELETE
	@Path("/deleteUserByID/{userID}")
	public void deleteUserByID(@PathParam("userID") int id) {
		userdao.deleteUser(id);
	}
	
	
}