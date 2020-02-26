package ecommerce.dto;

public class WishlistDto {

	private int wishlistID;
	private int userID;
	private int productID;

	public WishlistDto() {}
	
	public WishlistDto (int wishlistID, int userID, int productID) {
		
		this.wishlistID = wishlistID;
		this.userID = userID;
		this.productID = productID;
	}

	public int getWishlistID() {
		return wishlistID;
	}

	public void setWishlistID(int wishlistID) {
		this.wishlistID = wishlistID;
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
