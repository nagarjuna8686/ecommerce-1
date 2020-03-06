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
import ecommerce.dto.CartDto;
import ecommerce.dto.CartProdDto;
import ecommerce.dto.UsersDto;
import ecommerce.exceptions.EcommerceException;

@Stateless
public class CartDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public List<CartDto> selectAll() throws EcommerceException {

		List<CartDto> cartReg = new ArrayList<>();

		try {

			Connection conn = ds.getConnection();
			String sql = "select  * from cart";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int cartID = result.getInt(1);
				int userID = result.getInt(2);
				int productID = result.getInt(3);

				cartReg.add(new CartDto(cartID, userID, productID));
			}

			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}
		return cartReg;

	}

	@SuppressWarnings("resource")
	public int insert(int productID, UsersDto udto) throws EcommerceException {

		int flag = 0;
		
			try {
				Connection conn = ds.getConnection();
				String sql = "select * from cart where userID = ? and productID = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, udto.getUserID());
				stmt.setInt(2, productID);
				ResultSet resQ = stmt.executeQuery();
				if (resQ.next()) {
					sql = "update cart set quantity = ? where userID = ? and productID = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, 1 + resQ.getInt(4));
					stmt.setInt(2, udto.getUserID());
					stmt.setInt(3, productID);
					flag = stmt.executeUpdate();
				} else {
					sql = " insert into cart (userID, productID, quantity)" + " values (?, ?, 1)";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, udto.getUserID());
					stmt.setInt(2, productID);
					flag = stmt.executeUpdate();
				}
				resQ.close();
				stmt.close();
				conn.close();

			} catch (SQLException e) {
				throw new EcommerceException(e.getMessage());
			}
		
		return flag;
	}

	public int delete(UsersDto udto) throws EcommerceException {

		int flag = 0;
		try {

			Connection conn = ds.getConnection();
			String sql = "delete from cart where userID = ?";

			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setInt(1, udto.getUserID());
			flag = delete_stmt.executeUpdate();
			delete_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}
		return flag;

	}

	@SuppressWarnings("resource")
	public int changeQuantity(int productID, int quantity, UsersDto udto) throws EcommerceException {
		// quantity è un flag per incrementare o decrementare un prodotto nel carrello abbinato al bottone + e -.
		//i valori consentiti sono 1 e -1
		int flag = 0;
		try {
			Connection conn = ds.getConnection();
			String sql = "select * from cart where userID = ? and productID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, udto.getUserID());
			stmt.setInt(2, productID);
			ResultSet resQ = stmt.executeQuery();
			if (resQ.next()) {
				int totQuantity = resQ.getInt(4) ;
				if (totQuantity > 0) {
					sql = "update cart set quantity = ? where userID = ? and productID = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, totQuantity);
					stmt.setInt(2, udto.getUserID());
					stmt.setInt(3, productID);
					flag = stmt.executeUpdate();
				} else {
					sql = "delete from cart where userID = ? and productID = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, udto.getUserID());
					stmt.setInt(2, productID);
					flag = stmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}
		return flag;
	}

	public List<CartProdDto> cartSearch(String offset, String pageSize, UsersDto udto) throws EcommerceException {

		List<CartProdDto> cartReg = new ArrayList<>();
		String sql = null;
		Connection conn;
		PreparedStatement select_stmt;
		try {
			ResultSet result;
			if (udto.getUserID() == 0) {
				sql = "select cart.quantity, products.* from cart inner join products on cart.productID = products.productID "
						+ " limit " + pageSize + " offset " + offset;
				conn = ds.getConnection();
				select_stmt = conn.prepareStatement(sql);
				result = select_stmt.executeQuery();
				while (result.next()) {
					int quantity = result.getInt(1);
					int productID = result.getInt(2);
					String name = result.getString(3);
					String template = result.getString(4);
					String brand = result.getString(5);
					String description = result.getString(6);
					String url = result.getString(7);
					cartReg.add(new CartProdDto(quantity, productID, name, template, brand, description, url));
				}

			} else {
				conn = ds.getConnection();

				sql = "select cart.quantity, products.* from cart inner join products on cart.productID = products.productID where cart.userID like "
						+ "'%" + udto.getUserID() + "%'" + " limit " + pageSize + " offset " + offset;
				select_stmt = conn.prepareStatement(sql);
				result = select_stmt.executeQuery();

				while (result.next()) {
					int quantity = result.getInt(1);
					int productID = result.getInt(2);
					String name = result.getString(3);
					String template = result.getString(4);
					String brand = result.getString(5);
					String description = result.getString(6);
					String url = result.getString(7);
					cartReg.add(new CartProdDto(quantity, productID, name, template, brand, description, url));
				}
			}
			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return cartReg;

	}

	public List<CartProdDto> selectOrd(String sortField, String sortDir, String offset, String pageSize, UsersDto udto)
			throws EcommerceException {

		List<CartProdDto> cartProdReg = new ArrayList<>();
		String sql = null;
		ResultSet result;
		Connection conn;
		PreparedStatement select_stmt;
		try {

			if (udto.getUserID() == 0) {
				sql = "select cart.quantity, products.* from cart inner join products on cart.productID = products.productID "
						+ "order by " + sortField + " " + sortDir + " limit " + pageSize + " offset " + offset;
				conn = ds.getConnection();
				select_stmt = conn.prepareStatement(sql);
				result = select_stmt.executeQuery();

				while (result.next()) {
					int quantity = result.getInt(1);
					int productID = result.getInt(2);
					String name = result.getString(3);
					String template = result.getString(4);
					String brand = result.getString(5);
					String description = result.getString(6);
					String url = result.getString(7);

					cartProdReg.add(new CartProdDto(quantity, productID, name, template, brand, description, url));
				}

			} else {
				conn = ds.getConnection();
				sql = "select cart.quantity, products.* from cart inner join products on cart.productID = products.productID where "
						+ "userID like '%" + udto.getUserID() + "%' order by " + sortField + " " + sortDir + " limit "
						+ pageSize + " offset " + offset;
				select_stmt = conn.prepareStatement(sql);
				result = select_stmt.executeQuery();

				while (result.next()) {
					int quantity = result.getInt(1);
					int productID = result.getInt(2);
					String name = result.getString(3);
					String template = result.getString(4);
					String brand = result.getString(5);
					String description = result.getString(6);
					String url = result.getString(7);

					cartProdReg.add(new CartProdDto(quantity, productID, name, template, brand, description, url));
				}

			}
			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return cartProdReg;
	}

	public int deleteProd(UsersDto udto, int productID) throws EcommerceException {

		int flag = 0;
		try {

			Connection conn = ds.getConnection();
			String sql = "delete from cart where userID = ? and productID = ?";

			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setInt(1, udto.getUserID());
			delete_stmt.setInt(2, productID);
			flag = delete_stmt.executeUpdate();
			delete_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}
		return flag;

	}

}
