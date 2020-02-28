package ecommerce.dto;

public class PricesDto {

	private int priceID;
	private double price;
	private double discount;
	private int productID;
	private boolean isDiscounted;
	private double discountedPrice;
	private String beginDate;
	private String endDate;

	public PricesDto() {}
	
	public PricesDto(int priceID, int productID, double price, double discount, boolean isDiscounted, double discountedPrice, String beginDate, String endDate) {
		this.priceID = priceID;
		this.productID = productID;
		this.price = price;
		this.discount = discount;
		this.isDiscounted = isDiscounted;
		this.discountedPrice = discountedPrice;
		this.beginDate = beginDate;
		this.endDate = endDate;
		

	}
	
	


	public boolean isDiscounted() {
		return isDiscounted;
	}

	public void setDiscounted(boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getDiscountedprice() {
		return discountedPrice;
	}

	public void setDiscountedprice(double discountedprice) {
		this.discountedPrice = discountedprice;
	}

	public boolean isIsdiscounted() {
		return isDiscounted;
	}

	public void setIsdiscounted(boolean isdiscounted) {
		this.isDiscounted = isdiscounted;
	}

	public int getPriceID() {
		return priceID;
	}

	public void setPriceID(int priceID) {
		this.priceID = priceID;
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
