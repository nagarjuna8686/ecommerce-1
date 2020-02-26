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

import ecommerce.dto.WishlistDto;

@Stateless
public class WishlistDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public List<WishlistDto> selectAll() {

		List<WishlistDto> wishReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from wishlist";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			ResultSet result = select_stmt.executeQuery();

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

	public List<WishlistDto> selectByUserID(int wishlistID) {

		List<WishlistDto> wishReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from wishlist where wishlistID = ?";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setInt(1, wishlistID);

			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int wishID = result.getInt(1);
				int productID = result.getInt(3);

				wishReg.add(new WishlistDto(wishlistID, wishID, productID));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wishReg;

	}

	public void insert(int userID, int prodID) {

		try {
			Connection conn = ds.getConnection();
			String sql = " insert into wishlist ( userID, productID)" + " values (?, ?)";

			PreparedStatement insert_statement = conn.prepareStatement(sql);
			insert_statement.setInt(1, userID);
			insert_statement.setInt(2, prodID);
			insert_statement.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	public void deleteWishlistByID(int wishlistID) {
		try {

			Connection conn = ds.getConnection();
			String sql = "delete from wishlist where wishlistID = ?";

			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setInt(1, wishlistID);
			delete_stmt.execute();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
