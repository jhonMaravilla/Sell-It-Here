package models;

import java.io.Serializable;
import java.sql.Timestamp;

public class TransactionBean implements Serializable{
	private int sellerID;
	private int buyerID;
	private int productID;
	private UserBean seller;
	private UserBean buyer;
	private ProductBean product;
	private Timestamp created_at;
	
	public TransactionBean() {};
	public TransactionBean(ProductBean product, UserBean seller, UserBean buyer) {
		this.product = product;
		this.productID = product.getProductId();
		this.seller = seller;
		this.sellerID = seller.getId();
		this.buyer = buyer;
		this.buyerID = buyer.getId();
	}
	
	public TransactionBean(int productID, int sellerID, int buyerID) {
		this.productID = productID;
		this.sellerID = sellerID;
		this.buyerID = buyerID;	
	}
	public int getSellerID() {
		return sellerID;
	}
	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}
	public int getBuyerID() {
		return buyerID;
	}
	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public UserBean getSeller() {
		return seller;
	}
	public void setSeller(UserBean seller) {
		this.seller = seller;
		this.sellerID = seller.getId();
	}
	public UserBean getBuyer() {
		return buyer;
	}
	public void setBuyer(UserBean buyer) {
		this.buyer = buyer;
		this.buyerID = buyer.getId();
	}
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean product) {
		this.product = product;
		this.productID = product.getProductId();
	}
	
	
}
