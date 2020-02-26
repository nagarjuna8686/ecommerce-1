package ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ecommerce.dto.WishlistDto;

@Stateless
public class WishlistDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public ArrayList<WishlistDto> selectAll() {

		ArrayList<WishlistDto> wishReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from wishlist";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			ResultSet result = select_stmt.executeQuery(sql);

			while (result.next()) {
				int wishlistID = result.getInt(1);
				int userID = result.getInt(2);
				int productID = result.getInt(3);

				wishReg.add(new WishlistDto(wishlistID, userID, productID));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wishReg;

	}

	public ArrayList<WishlistDto> select(String col, String cond) {

		ArrayList<WishlistDto> wishReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from wishlist where ? = ?";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setString(1, col);
			select_stmt.setString(2, cond);

			ResultSet result = select_stmt.executeQuery(sql);

			while (result.next()) {
				int wishlistID = result.getInt(1);
				int userID = result.getInt(2);
				int productID = result.getInt(3);

				wishReg.add(new WishlistDto(wishlistID, userID, productID));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wishReg;

	}

	public void insert(int wishlistID, int userID, int productID) throws Exception {

		try {
			Connection conn = ds.getConnection();
			String sql = " insert into wishlist ( wishlistID, userID, productID)" + " values (?, ?, ?)";

			PreparedStatement insert_statement = conn.prepareStatement(sql);
			insert_statement.setInt(1, wishlistID);
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
			String sql = "delete from wishlist where ? = ?";

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
