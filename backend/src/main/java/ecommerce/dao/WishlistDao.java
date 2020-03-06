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
import ecommerce.dto.WishlistDto;
import ecommerce.exceptions.EcommerceException;

@Stateless
public class WishlistDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public List<WishlistDto> selectAll() throws EcommerceException {

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

			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException("Error: failed select all wushlists");
		}

		return wishReg;

	}

	public List<WishlistDto> selectByWishID(int wishlistID) throws EcommerceException {

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

			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException("Error: failed select wishlist by id");
		}

		return wishReg;

	}

	public int insert(int prodID, UsersDto udto) throws EcommerceException {

		int flag = 0;
		try {
			Connection conn = ds.getConnection();
			String sql = "select * from wishlist where userID = ? and productID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, udto.getUserID());
			stmt.setInt(2, prodID);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next()) {
				sql = "insert into wishlist (userID, productID)" + " values (?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, udto.getUserID());
				stmt.setInt(2, prodID);
				stmt.executeUpdate();
			}
			rs.close();
			stmt.close();
			conn.close();
			flag = 1;
		} catch (SQLException e) {
			throw new EcommerceException("Error: failed insert product into wishlist");
		}

		return flag;
	}

	public int deleteWishlistByID(UsersDto udto) throws EcommerceException {

		int flag = 0;
		try {
			Connection conn = ds.getConnection();
			String sql = "delete from wishlist where userID = ?";
			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setInt(1, udto.getUserID());
			flag = delete_stmt.executeUpdate();
			delete_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException("Error: failed delete wishlist by user");
		}

		return flag;

	}

	public List<WishProdDto> wishSearch(String offset, String pageSize, UsersDto udto) throws EcommerceException {

		List<WishProdDto> wishReg = new ArrayList<>();

		Connection conn;
		PreparedStatement select_stmt;
		try {
			ResultSet result;

			conn = ds.getConnection();
			String sql = "select wishlist.wishlistID, products.* from wishlist inner join products on wishlist.productID = products.productID "
					+ "where wishlist.userID like '%" + udto.getUserID() + "%' " + " limit " + pageSize + " offset "
					+ offset;
			select_stmt = conn.prepareStatement(sql);

			result = select_stmt.executeQuery();

			while (result.next()) {
				int cartID = result.getInt(1);
				int productID = result.getInt(2);
				String name = result.getString(3);
				String template = result.getString(4);
				String brand = result.getString(5);
				String description = result.getString(6);
				String url = result.getString(7);
				wishReg.add(new WishProdDto(cartID, productID, name, template, brand, description, url));
			}

			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException("Error: failed ordered selection on wishlist");
		}

		return wishReg;

	}

	public List<WishProdDto> selectOrd(String sortField, String sortDir, String offset, String pageSize, UsersDto udto)
			throws EcommerceException {

		List<WishProdDto> wishProdReg = new ArrayList<>();
		String sql = null;
		ResultSet result;
		Connection conn;
		PreparedStatement select_stmt;
		try {
			conn = ds.getConnection();
			sql = "select wishlist.wishlistID, products.* from wishlist inner join products on wishlist.productID = products.productID where userID = "
					+ udto.getUserID() + " order by " + sortField + " " + sortDir + " limit "
					+ pageSize + " offset " + offset;
			select_stmt = conn.prepareStatement(sql);
			result = select_stmt.executeQuery();

			while (result.next()) {
				int wishID = result.getInt(1);
				int productID = result.getInt(2);
				String name = result.getString(3);
				String template = result.getString(4);
				String brand = result.getString(5);
				String description = result.getString(6);
				String url = result.getString(7);

				wishProdReg.add(new WishProdDto(wishID, productID, name, template, brand, description, url));
			}

			result.close();
			select_stmt.close();
			conn.close();

		} catch (

		SQLException e) {
			throw new EcommerceException("Error: failed ordered selection on wishlist by user");
		}

		return wishProdReg;

	}

	public int deleteProd(int wishlistID) throws EcommerceException {
		int flag = 0;
		try {
			Connection conn = ds.getConnection();
			String sql = "delete from wishlist where wishlistID = ?";
			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setInt(1, wishlistID);
			flag = delete_stmt.executeUpdate();
			delete_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException("Error: failed delete product from wishlist");
		}

		return flag;
	}

}
