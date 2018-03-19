package com.wanma.model;

public class Favorites {

	private String favoriteId;//收藏ID
	private String favoriteType;//收藏   收藏类型 1-商城收藏  2-电桩 3-电站
	private String productId;//产品ID
	private String favoriteImage;//产品图片
	private String favoriteName;//产品名称
	private String favoriteProductPrice;//产品成本金额
	private String favoriteMarketPrice;//产品市场价格
	private String favoriteStockQuantity;//产品库存
	
	private String favoriteProductTag;//产品属性
	public String getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}
	public String getFavoriteImage() {
		return favoriteImage;
	}
	public void setFavoriteImage(String favoriteImage) {
		this.favoriteImage = favoriteImage;
	}
	public String getFavoriteName() {
		return favoriteName;
	}
	public void setFavoriteName(String favoriteName) {
		this.favoriteName = favoriteName;
	}
	public String getFavoriteProductPrice() {
		return favoriteProductPrice;
	}
	public void setFavoriteProductPrice(String favoriteProductPrice) {
		this.favoriteProductPrice = favoriteProductPrice;
	}
	public String getFavoriteMarketPrice() {
		return favoriteMarketPrice;
	}
	public void setFavoriteMarketPrice(String favoriteMarketPrice) {
		this.favoriteMarketPrice = favoriteMarketPrice;
	}
	public String getFavoriteStockQuantity() {
		return favoriteStockQuantity;
	}
	public void setFavoriteStockQuantity(String favoriteStockQuantity) {
		this.favoriteStockQuantity = favoriteStockQuantity;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getFavoriteProductTag() {
		return favoriteProductTag;
	}
	public void setFavoriteProductTag(String favoriteProductTag) {
		this.favoriteProductTag = favoriteProductTag;
	}
	public String getFavoriteType() {
		return favoriteType;
	}
	public void setFavoriteType(String favoriteType) {
		this.favoriteType = favoriteType;
	}
	
	
}
