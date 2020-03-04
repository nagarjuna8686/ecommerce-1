package ecommerce.dto;

public class CartProdDto {

	private int quantity;
	private int productID;
	private String name;
	private String template;
	private String brand;
	private String description;
	private String url;

	public CartProdDto(int quantity, int productID, String name, String template, String brand, String description, String url) {
		this.quantity = quantity;
		this.productID = productID;
		this.name = name;
		this.template = template;
		this.brand = brand;
		this.description = description;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String productName) {
		this.name = productName;
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
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	
}
