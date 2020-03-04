package ecommerce.dao;

public class WishProdDto {

	private int wishListID;
	private int productID;
	private String name;
	private String template;
	private String brand;
	private String description;
	private String url;

	public WishProdDto(int wishListID, int productID, String name, String template, String brand, String description, String url) {
		this.wishListID = wishListID;
		this.productID = productID;
		this.name = name;
		this.template = template;
		this.brand = brand;
		this.description = description;
		this.url = url;
	}

	public int getWishListID() {
		return wishListID;
	}

	public void setWishListID(int wishListID) {
		this.wishListID = wishListID;
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
