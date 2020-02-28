package ecommerce.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ecommerce.dto.PricesDto;
import ecommerce.exceptions.EcommerceException;

@Stateless
public class PricesDao {

	@Resource(lookup = "java:/jdbc/ecommerce")
	private DataSource ds;

	public List<PricesDto> selectAll() throws EcommerceException {

		List<PricesDto> pricesReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from prices";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			ResultSet result = select_stmt.executeQuery(sql);

			while (result.next()) {
				int priceID = result.getInt(1);
				int productID = result.getInt(2);
				double price = result.getDouble(3);
				double discount = result.getDouble(4);
				boolean isDiscounted = result.getBoolean(5);
				double discountedPrice = result.getDouble(6);
				String beginDate = result.getString(7);
				String endDate = result.getString(8);

				pricesReg.add(new PricesDto(priceID, productID, price, discount, isDiscounted, discountedPrice,
						beginDate, endDate));
			}

			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return pricesReg;

	}

	public List<PricesDto> selectByID(PricesDto prdto) throws EcommerceException {

		List<PricesDto> pricesReg = new ArrayList<>();

		try {
			Connection conn = ds.getConnection();
			String sql = "select  * from prices where priceID = ?";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setInt(1, prdto.getPriceID());

			ResultSet result = select_stmt.executeQuery(sql);

			while (result.next()) {
				int priceID = result.getInt(1);
				int productID = result.getInt(2);
				double price = result.getDouble(3);
				double discount = result.getDouble(4);
				boolean isDiscounted = result.getBoolean(5);
				double discountedPrice = result.getDouble(6);
				String beginDate = result.getString(7);
				String endDate = result.getString(8);

				pricesReg.add(new PricesDto(priceID, productID, price, discount, isDiscounted, discountedPrice,
						beginDate, endDate));
			}

			result.close();
			select_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}

		return pricesReg;

	}

	public int delete(PricesDto prdto) throws EcommerceException {

		int flag = 0;
		try {
			Connection conn = ds.getConnection();
			String sql = "delete from prices where priceID = ?";

			PreparedStatement delete_stmt = conn.prepareStatement(sql);
			delete_stmt.setInt(1, prdto.getPriceID());
			flag = delete_stmt.executeUpdate();

			delete_stmt.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}
		
		return flag;

	}

	
	
	public int updatePrice(PricesDto prdto) throws EcommerceException {
		int flag = 0;
		try {

			Connection conn = ds.getConnection();

			String sql = "update prices set currentDate = ?";

			Date today = new Date(Calendar.getInstance().getTime().getTime());

			// EVENTUALE MODO ALTERNATIVO PER OTTENERE LA DATA ATTUALE
			// System.currentTimeMillis();

			PreparedStatement update_statement = conn.prepareStatement(sql);
			update_statement.setDate(1, today);

			update_statement.executeUpdate();

			update_statement.close();

			sql = "select beginDate, endDate from prices where priceID = ?";

			PreparedStatement update_statement1 = conn.prepareStatement(sql);
			update_statement1.setInt(1, prdto.getPriceID());

			ResultSet result = update_statement1.executeQuery();

			Date bDate = null;
			Date eDate = null;
			if (result.next()) {
				bDate = result.getDate(1);
				eDate = result.getDate(2);
			}

			result.close();
			update_statement1.close();

			sql = "update prices set isDiscounted = 1  where priceID = ? and currentDate between ? and ?";

			PreparedStatement update_statement2 = conn.prepareStatement(sql);
			update_statement2.setInt(1, prdto.getPriceID());
			update_statement2.setDate(2, bDate);
			update_statement2.setDate(3, eDate);

			flag = update_statement2.executeUpdate();

			update_statement2.close();

			sql = "update prices set isDiscounted = 0  where priceID = ? and currentDate not between ? and ?";

			PreparedStatement update_statement3 = conn.prepareStatement(sql);
			update_statement3.setInt(1, prdto.getPriceID());
			update_statement3.setDate(2, bDate);
			update_statement3.setDate(3, eDate);

			flag = update_statement3.executeUpdate();

			update_statement3.close();

			sql = "select isDiscounted from prices where priceID = ?";

			PreparedStatement select_stmt = conn.prepareStatement(sql);
			select_stmt.setInt(1, prdto.getPriceID());

			ResultSet result1 = select_stmt.executeQuery();

			int dis = 0;
			if (result1.next()) {
				dis = result1.getInt(1);
			}

			result1.close();
			select_stmt.close();

			if (dis == 1) {

				sql = "update prices, products set prices.discountedPrice = ?, prices.discount =?, prices.price = ? where prices.productID = products.productID and prices.priceID = ?";

				PreparedStatement update_statement4 = conn.prepareStatement(sql);
				update_statement4.setDouble(1, prdto.getPrice() - prdto.getDiscount());
				update_statement4.setDouble(2, prdto.getDiscount());
				update_statement4.setDouble(3, prdto.getPrice());
				update_statement4.setInt(4, prdto.getPriceID());

				flag = update_statement4.executeUpdate();

				update_statement4.close();

			} else {

				sql = "update prices, products set prices.discountedPrice = null, prices.discount = null, prices.price = ? where prices.productID = products.productID and prices.priceID = ?";

				PreparedStatement update_statement5 = conn.prepareStatement(sql);
				update_statement5.setDouble(1, prdto.getPrice());
				// update_statement2.setDouble(2, prdto.getPrice());
				update_statement5.setInt(2, prdto.getPriceID());

				flag = update_statement5.executeUpdate();

				update_statement5.close();
				conn.close();
			}
		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}
		
		return flag;

	}

	public int setDiscountDates(PricesDto prdto) throws EcommerceException {

		int flag = 0;
		try {
			Connection conn = ds.getConnection();

			String sql = "update prices set beginDate = ?, endDate = ? where priceID = ?";

			PreparedStatement update_statement = conn.prepareStatement(sql);
			update_statement.setString(1, prdto.getBeginDate());
			update_statement.setString(2, prdto.getEndDate());
			update_statement.setInt(3, prdto.getPriceID());
			flag = update_statement.executeUpdate();

			update_statement.close();
			conn.close();

		} catch (SQLException e) {
			throw new EcommerceException(e.getMessage());
		}
		
		return flag;
	}

//	METODO PRECEDENTE PER UPDATE DEL DISCOUNT (VEDI PricesApi)
//	public void updateDiscount(PricesDto prdto) {
//		try {
//
//			Connection conn = ds.getConnection();
//			String sql = "update prices set discount = ?  where priceID = ?";
//
//			PreparedStatement update_statement = conn.prepareStatement(sql);
//			update_statement.setDouble(1, prdto.getDiscount());
//			update_statement.setInt(2, prdto.getProductID());
//
//			update_statement.executeUpdate();
//
//			update_statement.close();
//			conn.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//

//	METODO PRECEDENTE PER CONTROLLO DEL DISCOUNT (VEDI PricesApi)
//	public void isDiscounted(String cond) {
//		try {
//			Connection conn = ds.getConnection();
//
//			String sql = "update prices set currentDate = ? where priceID = ?";
//
//			Date today = new Date(Calendar.getInstance().getTime().getTime());
//
//			// EVENTUALE MODO ALTERNATIVO PER OTTENERE LA DATA ATTUALE
//			// System.currentTimeMillis();
//
//			PreparedStatement update_statement = conn.prepareStatement(sql);
//			update_statement.setDate(1, today);
//			update_statement.setString(2, cond);
//
//			update_statement.executeUpdate();
//
//			update_statement.close();
//
//			String sql1 = "select beginDate, endDate from prices where priceID = ?";
//
//			PreparedStatement update_statement1 = conn.prepareStatement(sql1);
//			update_statement1.setString(1, cond);
//
//			ResultSet result = update_statement1.executeQuery();
//
//			Date bDate = null;
//			Date eDate = null;
//			if (result.next()) {
//				bDate = result.getDate(1);
//				eDate = result.getDate(2);
//			}
//
//			result.close();
//			update_statement1.close();
//
//			String sql2 = "update prices set isDiscounted = 1  where currentDate between ? and ?";
//
//			PreparedStatement update_statement2 = conn.prepareStatement(sql2);
//			update_statement2.setDate(1, bDate);
//			update_statement2.setDate(2, eDate);
//
//			update_statement2.executeUpdate();
//
//			update_statement2.close();
//			conn.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

//	METODO PRECEDENTE PER INSERIMENTO PREZZI (DEPRECATO)
//	
//	public void insert(int productID, double price, double discount) {
//
//		try {
//
//			Connection conn = ds.getConnection();
//			String sql = " insert into prices (productID, price, discount)" + " values (?, ?, ?)";
//
//			PreparedStatement insert_statement = conn.prepareStatement(sql);
//			insert_statement.setInt(1, productID);
//			insert_statement.setDouble(2, discount);
//			insert_statement.setDouble(3, discount);
//
//			insert_statement.close();
//			conn.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

}
