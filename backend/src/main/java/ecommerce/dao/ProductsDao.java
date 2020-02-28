package ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ecommerce.dto.ProductsDto;
import ecommerce.exceptions.EcommerceException;

@Stateless
public class ProductsDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public List<ProductsDto> selectAll() throws EcommerceException {

		List<ProductsDto> productsReg = new ArrayList<>();
		try {

			Connection conn = ds.getConnection();

			String sql = "select products.* , prices.price, prices.isDiscounted, prices.discount, prices.discountedPrice from products inner join prices on products.productID = prices.productID ";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int productID = result.getInt(1);
				String name = result.getString(2);
				String template = result.getString(3);
				String brand = result.getString(4);
				String description = result.getString(5);
				String url = result.getString(6);
				double price = result.getDouble(7);
				boolean isDiscounted = result.getBoolean(8);
				double discount = result.getDouble(9);
				double discountedPrice = result.getDouble(10);

				productsReg.add(new ProductsDto(productID, name, price, template, brand, description, url, isDiscounted,
						discount, discountedPrice));
			}

			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return productsReg;

	}

	public List<ProductsDto> selectByName(String cond) throws EcommerceException {

		List<ProductsDto> productsReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select products.* , prices.price, prices.isDiscounted, prices.discount, prices.discountedPrice from products inner join prices on products.productID = prices.productID where products.name = ?";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setString(1, cond);

			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int productID = result.getInt(1);
				String name = result.getString(2);
				String template = result.getString(3);
				String brand = result.getString(4);
				String description = result.getString(5);
				String url = result.getString(6);
				double price = result.getDouble(7);
				boolean isDiscounted = result.getBoolean(8);
				double discount = result.getDouble(9);
				double discountedPrice = result.getDouble(10);

				productsReg.add(new ProductsDto(productID, name, price, template, brand, description, url, isDiscounted,
						discount, discountedPrice));
			}

			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return productsReg;

	}

	public List<ProductsDto> selectById(String cond) throws EcommerceException {

		List<ProductsDto> productsReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select products.* , prices.price, prices.isDiscounted, prices.discount, prices.discountedPrice from products inner join prices on products.productID = prices.productID where products.productID = ?";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setString(1, cond);

			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int productID = result.getInt(1);
				String name = result.getString(2);
				String template = result.getString(3);
				String brand = result.getString(4);
				String description = result.getString(5);
				String url = result.getString(6);
				double price = result.getDouble(7);
				boolean isDiscounted = result.getBoolean(8);
				double discount = result.getDouble(9);
				double discountedPrice = result.getDouble(10);

				productsReg.add(new ProductsDto(productID, name, price, template, brand, description, url, isDiscounted,
						discount, discountedPrice));
			}

			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return productsReg;

	}

	public int insert(ProductsDto pdto) throws EcommerceException {

		int flag = 0;
		try {

			Connection conn = ds.getConnection();
			String sql = " insert into products (name, template, brand, description, url)" + " values (?, ?, ?, ?, ?)";

			PreparedStatement insert_statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			insert_statement.setString(1, pdto.getName());
			insert_statement.setString(2, pdto.getTemplate());
			insert_statement.setString(3, pdto.getBrand());
			insert_statement.setString(4, pdto.getDescription());
			insert_statement.setString(5, pdto.getUrl());

			flag = insert_statement.executeUpdate();

			ResultSet result = insert_statement.getGeneratedKeys();
			int id = -1;
			if (result.next()) {
				id = result.getInt(1);
			}

			result.close();
			insert_statement.close();

			sql = "insert into prices (productID, price)"
					+ "values ((select products.productID from products where products.productID = ?) , ?)";

			PreparedStatement insert_statement1 = conn.prepareStatement(sql);
			insert_statement1.setInt(1, id);
			insert_statement1.setDouble(2, pdto.getPrice());

			insert_statement1.executeUpdate();

			insert_statement1.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return flag;
	}

	public int delete(String cond) throws EcommerceException {

		int flag = 0;
		try {
			Connection conn = ds.getConnection();
			String sql = "delete from products where productID = ?";

			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setString(1, cond);
			flag = delete_stmt.executeUpdate();

			delete_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return flag;

	}

	public int update(ProductsDto pdto) throws EcommerceException {

		int flag = 0;
		try {

			Connection conn = ds.getConnection();
			String sql = "update products set name = ? , template = ?, brand = ?, description = ?, url = ? where productID = ?";

			PreparedStatement update_statement = conn.prepareStatement(sql);
			update_statement.setString(1, pdto.getName());
			update_statement.setString(2, pdto.getTemplate());
			update_statement.setString(3, pdto.getBrand());
			update_statement.setString(4, pdto.getDescription());
			update_statement.setString(5, pdto.getUrl());
			update_statement.setInt(6, pdto.getProductID());

			flag = update_statement.executeUpdate();

			update_statement.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return flag;

	}

}
