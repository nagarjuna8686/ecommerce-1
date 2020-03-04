package ecommerce.dto;

public class CartDto {
	private int cartID;
	private int userID;
	private int productID;
	
	public CartDto () {}
		

	public CartDto (int cartID, int userID, int productID) {
		
		this.cartID = cartID;
		this.userID = userID;
		this.productID = productID;
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	

}
