package ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ecommerce.dto.PricesDto;

@Stateless
public class PricesDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public ArrayList<PricesDto> selectAll() {

		ArrayList<PricesDto> pricesReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from prices";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			ResultSet result = select_stmt.executeQuery(sql);

			while (result.next()) {
				int pricesID = result.getInt(1);
				double price = result.getDouble(2);
				double discount = result.getDouble(3);
				int productID = result.getInt(4);

				pricesReg.add(new PricesDto(pricesID, price, discount, productID));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pricesReg;

	}

	public ArrayList<PricesDto> select(String col, String cond) {

		ArrayList<PricesDto> pricesReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from prices where ? = ?";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setString(1, col);
			select_stmt.setString(2, cond);

			ResultSet result = select_stmt.executeQuery(sql);

			while (result.next()) {
				int pricesID = result.getInt(1);
				double price = result.getDouble(2);
				double discount = result.getDouble(3);
				int productID = result.getInt(4);

				pricesReg.add(new PricesDto(pricesID, price, discount, productID));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pricesReg;

	}

	public void insert(int pricesID, double price, double discount, int productID) throws Exception {

		try {

			Connection conn = ds.getConnection();
			String sql = " insert into prices (pricesID, price, discount, productID)" + " values (?, ?, ?, ?)";

			PreparedStatement insert_statement = conn.prepareStatement(sql);
			insert_statement.setInt(1, pricesID);
			insert_statement.setDouble(2, price);
			insert_statement.setDouble(3, discount);
			insert_statement.setInt(4, productID);

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
			String sql = "delete from prices where ? = ?";

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
