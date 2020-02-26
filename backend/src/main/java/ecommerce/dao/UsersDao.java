package ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ecommerce.dto.UsersDto;

@Stateless
public class UsersDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public ArrayList<UsersDto> selectAll() {

		ArrayList<UsersDto> usersReg = new ArrayList<>();

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

	public ArrayList<UsersDto> select(String col, String cond) {

		ArrayList<UsersDto> usersReg = new ArrayList<>();

		try {

			Connection conn = ds.getConnection();
			String sql = "select  * from users where ? = ?";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setString(1, col);
			select_stmt.setString(2, cond);

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

	public void insert(int userID, String email, String password, String name, String surname, String birthdate,
			String phone, String token) throws Exception {

		try {

			Connection conn = ds.getConnection();
			String sql = " insert into users (userID, email, password, name,  surname, birthdate, phone, token)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement insert_statement = conn.prepareStatement(sql);
			insert_statement.setInt(1, userID);
			insert_statement.setString(2, email);
			insert_statement.setString(3, password);
			insert_statement.setString(4, name);
			insert_statement.setString(5, surname);
			insert_statement.setString(6, birthdate);
			insert_statement.setString(7, phone);
			insert_statement.setString(8, token);

			int rows_affected = insert_statement.executeUpdate();

			if (rows_affected == 0) {
				throw new Exception("Inserimento non valido");
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String col, String cond){

		try {

			Connection conn = ds.getConnection();
			String sql = "delete from users where ? = ?";

			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setString(1, col);
			delete_stmt.setString(2, cond);
			delete_stmt.execute();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
