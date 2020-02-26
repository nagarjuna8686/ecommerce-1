package ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ecommerce.dto.CartDto;

@Stateless
public class CartDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public ArrayList<CartDto> selectAll() {

		ArrayList<CartDto> cartReg = new ArrayList<>();

		try {

			Connection conn = ds.getConnection();
			String sql = "select  * from cart";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			ResultSet result = select_stmt.executeQuery(sql);

			while (result.next()) {
				int cartID = result.getInt(1);
				int userID = result.getInt(2);
				int productID = result.getInt(3);

				cartReg.add(new CartDto(cartID, userID, productID));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartReg;

	}

	public ArrayList<CartDto> select(String col, String cond) {

		ArrayList<CartDto> cartReg = new ArrayList<>();

		try {

			Connection conn = ds.getConnection();
			String sql = "select  * from cart where ? = ?";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setString(1, col);
			select_stmt.setString(2, cond);

			ResultSet result = select_stmt.executeQuery(sql);

			while (result.next()) {
				int cartID = result.getInt(1);
				int userID = result.getInt(2);
				int productID = result.getInt(3);

				cartReg.add(new CartDto(cartID, userID, productID));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartReg;

	}

	public void insert(int cartID, int userID, int productID) throws Exception {

		try {

			Connection conn = ds.getConnection();
			String sql = " insert into cart (cartID, userID, productID)" + " values (?, ?, ?)";

			PreparedStatement insert_statement = conn.prepareStatement(sql);
			insert_statement.setInt(1, cartID);
			insert_statement.setInt(2, userID);
			insert_statement.setInt(3, productID);

			int rows_affected = insert_statement.executeUpdate();

			if (rows_affected == 0) {
				throw new Exception("Inserimento non valido");
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String col, String cond) {

		try {

			Connection conn = ds.getConnection();
			String sql = "delete from cart where ? = ?";

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
