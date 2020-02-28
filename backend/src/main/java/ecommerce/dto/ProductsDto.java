package ecommerce.dto;

public class ProductsDto {

	private int productID;
	private String name;
	private double price;
	private String template;
	private String brand;
	private String description;
	private String url;
	private boolean isDiscounted;
	private double discount;
	private double discountedPrice;
	
	
	public ProductsDto() {}

	public ProductsDto(int productID, String name, double price, String template, String brand, String description,
			String url, boolean isDiscounted, double discount, double discountedPrice) {

		this.productID = productID;
		this.name = name;
		this.price = price;
		this.template = template;
		this.brand = brand;
		this.description = description;
		this.url = url;
		this.isDiscounted = isDiscounted;
		this.discount = discount;
		this.discountedPrice = discountedPrice;

	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public boolean isDiscounted() {
		return isDiscounted;
	}

	public void setDiscounted(boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
