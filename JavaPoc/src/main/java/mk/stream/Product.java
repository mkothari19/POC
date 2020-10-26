package mk.stream;

public class Product {
	
	private Float price;
	private String productName;
	private int productID;
	
	
	public Product(Float price, String productName, int productID) {
		super();
		this.price = price;
		this.productName = productName;
		this.productID = productID;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	

}
