package ecommerce.dto;

public class PricesDto {

	private int pricesID;
	private double price;
	private double discount;
	private int productID;

	public PricesDto(int pricesID, double price, double discount, int productID) {
		this.pricesID = pricesID;
		this.price = price;
		this.discount = discount;
		this.productID = productID;

	}

	public int getPricesID() {
		return pricesID;
	}

	public void setPricesID(int pricesID) {
		this.pricesID = pricesID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

}
