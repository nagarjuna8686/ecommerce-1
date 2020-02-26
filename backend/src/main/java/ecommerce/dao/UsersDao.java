package ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;


import ecommerce.dto.UsersDto;

@Stateless
public class UsersDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public List<UsersDto> selectAll() {

		List<UsersDto> usersReg = new ArrayList<>();

		try {

			Connection conn = ds.getConnection();
			String sql = "select  * from users";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			ResultSet result = select_stmt.executeQuery(sql);

			while (result.next()) {
				int userID = result.getInt(1);
				String email = result.getString(2);
				String password = result.getString(3);
				String name = result.getString(4);
				String surname = result.getString(5);
				String birthdate = result.getString(6);
				String phone = result.getString(7);
				String token = result.getString(8);
				usersReg.add(new UsersDto(userID, email, password, name, surname, birthdate, phone, token));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usersReg;

	}

	public List<UsersDto> selectByName(String n) {

		List<UsersDto> usersReg = new ArrayList<>();

		try {

			Connection conn = ds.getConnection();
			String sql = "select  * from users where name = ?";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setString(1, n);

			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int userID = result.getInt(1);
				String email = result.getString(2);
				String password = result.getString(3);
				String name = result.getString(4);
				String surname = result.getString(5);
				String birthdate = result.getString(6);
				String phone = result.getString(7);
				String token = result.getString(8);

				usersReg.add(new UsersDto(userID, email, password, name, surname, birthdate, phone, token));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usersReg;
	}

	public List<UsersDto> selectByID(int id) {

		List<UsersDto> usersReg = new ArrayList<>();

		try {

			Connection conn = ds.getConnection();
			String sql = "select  * from users where userID = ?";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setInt(1, id);

			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int userID = result.getInt(1);
				String email = result.getString(2);
				String password = result.getString(3);
				String name = result.getString(4);
				String surname = result.getString(5);
				String birthdate = result.getString(6);
				String phone = result.getString(7);
				String token = result.getString(8);

				usersReg.add(new UsersDto(userID, email, password, name, surname, birthdate, phone, token));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usersReg;
	}

	public void insert(UsersDto udto) {
		try {

			Connection conn = ds.getConnection();
			String sql = "select md5(?) from dual";
			String pswMD5 = null;
			PreparedStatement insert_statement = conn.prepareStatement(sql);
			insert_statement.setString(1, udto.getPassword());
			ResultSet result = insert_statement.executeQuery();
			while(result.next()) {
				pswMD5 = result.getString(1);
			}
			sql = " insert into users (email, password, name,  surname, birthdate, phone, token)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";

			insert_statement = conn.prepareStatement(sql);
			insert_statement.setString(1, udto.getEmail());
			insert_statement.setString(2, pswMD5);
			insert_statement.setString(3, udto.getName());
			insert_statement.setString(4, udto.getSurname());
			insert_statement.setString(5, udto.getBirthdate());
			insert_statement.setString(6, udto.getPhone());
			insert_statement.setString(7, udto.getToken());
			insert_statement.executeUpdate();

			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	public void deleteUser(int ID){

		try {

			Connection conn = ds.getConnection();
			String sql = "delete from users where userID = ?";

			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setInt(1, ID);
			delete_stmt.execute();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void updateUserByID(UsersDto udto) {
		try {

			Connection conn = ds.getConnection();
			String sql = "UPDATE users SET email = ?, password = ?, name = ?, surname = ?, birthdate = ?, phone=?, token=? WHERE userID = ?";
			
			PreparedStatement insert_statement = conn.prepareStatement(sql);
			insert_statement.setString(1, udto.getEmail());
			insert_statement.setString(2, udto.getPassword());
			insert_statement.setString(3, udto.getName());
			insert_statement.setString(4, udto.getSurname());
			insert_statement.setString(5, udto.getBirthdate());
			insert_statement.setString(6, udto.getPhone());
			insert_statement.setString(7, udto.getToken());
			insert_statement.setInt(8, udto.getUserID());
			insert_statement.executeUpdate();
	
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public boolean retrievePasswordByID(int id, String password) {
		List<UsersDto> udto;
		Connection conn;
		try {
			conn = ds.getConnection();
		
		String sql = "select md5('?') from dual";
		String pswMD5 = null;
		PreparedStatement insert_statement = conn.prepareStatement(sql);
		insert_statement.setString(1, password);
		ResultSet result = insert_statement.executeQuery();
		while(result.next()) {
			pswMD5 = result.getString(1);
		}
		udto = this.selectByID(id);
		System.out.println(pswMD5);
		System.out.println(udto.get(0).getPassword());
		if(pswMD5.equals(udto.get(0).getPassword())) {
			return true;
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	
	}
	

	public void changeUserPassword(int id, String password) {
		try {
			Connection conn = ds.getConnection();
			String sql = "select md5(?) from dual";
			String pswMD5 = null;
			PreparedStatement insert_statement = conn.prepareStatement(sql);
			insert_statement.setString(1, password);
			ResultSet result = insert_statement.executeQuery();
			while(result.next()) {
				pswMD5 = result.getString(1);
			}
			sql = "update users set password = ? where userID = ?";
			insert_statement = conn.prepareStatement(sql);
			insert_statement.setString(1, pswMD5);
			insert_statement.setInt(2, id);
			insert_statement.executeUpdate();
	
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}


}
