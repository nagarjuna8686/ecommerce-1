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

import ecommerce.dto.ProductsDto;

@Stateless
public class ProductsDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public List<ProductsDto> selectAll() {

		List<ProductsDto> productsReg = new ArrayList<>();
		try {

			Connection conn = ds.getConnection();
			String sql = "select  * from products";
			PreparedStatement select_stmt = conn.prepareStatement(sql);
			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int productID = result.getInt(1);
				String name = result.getString(2);
				double price = result.getDouble(3);
				String template = result.getString(4);
				String brand = result.getString(5);
				String description = result.getString(6);
				String url = result.getString(7);

				productsReg.add(new ProductsDto(productID, name, price, template, brand, description, url));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productsReg;

	}

	public List<ProductsDto> selectByName(String cond) {

		List<ProductsDto> productsReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from products where name = ?";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setString(1, cond);

			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int productID = result.getInt(1);
				String name = result.getString(2);
				double price = result.getDouble(3);
				String template = result.getString(4);
				String brand = result.getString(5);
				String description = result.getString(6);
				String url = result.getString(7);

				productsReg.add(new ProductsDto(productID, name, price, template, brand, description, url));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productsReg;

	}
	
	public List<ProductsDto> selectById(String cond) {

		List<ProductsDto> productsReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from products where productID = ?";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setString(1, cond);

			ResultSet result = select_stmt.executeQuery();

			while (result.next()) {
				int productID = result.getInt(1);
				String name = result.getString(2);
				double price = result.getDouble(3);
				String template = result.getString(4);
				String brand = result.getString(5);
				String description = result.getString(6);
				String url = result.getString(7);

				productsReg.add(new ProductsDto(productID, name, price, template, brand, description, url));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productsReg;

	}

	public void insert(ProductsDto pdto) {

		try {
			
			Connection conn = ds.getConnection();
			String sql = " insert into products (name, price, template, brand, description, url)"
					+ " values (?, ?, ?, ?, ?, ?)";

			PreparedStatement insert_statement = conn.prepareStatement(sql);
			insert_statement.setString(1, pdto.getName());
			insert_statement.setDouble(2, pdto.getPrice());
			insert_statement.setString(3, pdto.getTemplate());
			insert_statement.setString(4, pdto.getBrand());
			insert_statement.setString(5, pdto.getDescription());
			insert_statement.setString(6, pdto.getUrl());

			insert_statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String cond) {

		try {
			Connection conn = ds.getConnection();
			String sql = "delete from products where productID = ?";

			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setString(1, cond);
			delete_stmt.execute();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void update(ProductsDto pdto) {
try {
			
			Connection conn = ds.getConnection();
			String sql = "update products set name = ? , price = ?, template = ?, brand = ?, description = ?, url = ? where productID = ?";

			PreparedStatement update_statement = conn.prepareStatement(sql);
			update_statement.setString(1, pdto.getName());
			update_statement.setDouble(2, pdto.getPrice());
			update_statement.setString(3, pdto.getTemplate());
			update_statement.setString(4, pdto.getBrand());
			update_statement.setString(5, pdto.getDescription());
			update_statement.setString(6, pdto.getUrl());
			update_statement.setInt(7, pdto.getProductID());
			

			update_statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
