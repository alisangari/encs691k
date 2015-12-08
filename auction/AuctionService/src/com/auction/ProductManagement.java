package com.auction;

import java.util.ArrayList;

public class ProductManagement {
	private static ProductManagement instance = null;
	private ArrayList<Product> products;

	private ProductManagement() {
		products = new ArrayList<Product>();
	}

	public static ProductManagement getInstance() {
		if (instance == null) {
			instance = new ProductManagement();
		}
		return instance;
	}

	public boolean create(String name, float price, String ownerUsername) {
		return products.add(new Product(name, price, ownerUsername));
	}

	public void updatePrice(int id, float price) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				Product prod = products.get(i);
				prod.setBasePrice(price);
				products.set(i, prod);
				break;
			}
		}
	}

	public void changeOwner(int id, String newOwnerUsername) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				Product prod = products.get(i);
				prod.setOwnerUsername(newOwnerUsername);
				products.set(i, prod);
				break;
			}
		}
	}

	public boolean placeInAuction(int id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				Product prod = products.get(i);
				prod.setInAuction(true);
				products.set(i, prod);
				return true;
			}
		}
		return false;
	}

	public void removeFromAuction(int id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				Product prod = products.get(i);
				prod.setInAuction(false);
				products.set(i, prod);
				break;
			}
		}
	}

	public boolean userHasActiveProducts(String ownerUsername) {
		for (Product prod : products) {
			if (prod.isInAuction()
					&& prod.getOwnerUsername().equalsIgnoreCase(ownerUsername)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return products.toString();
	}

	public ArrayList<Product> getItems(String ownerUsername) {
		ArrayList<Product> prods = new ArrayList<Product>();
		for (Product prod : products) {
			if (prod.getOwnerUsername().equalsIgnoreCase(ownerUsername)) {
				prods.add(prod);
			}
		}
		return prods;
	}

	public void productTimeInAuctionIsUp(int productId) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == productId) {
				if (!products.get(i).getHighestBiderUsername()
						.equalsIgnoreCase("")) {
					// conclude auction and update owner.
					Product prod = products.get(i);
					prod.setOwnerUsername(prod.getHighestBiderUsername());
					prod.resetHighestBid();
					prod.setInAuction(false);
				} else {
					new AuctionTimer(products.get(i).getId());
				}
			}
		}
	}

	public boolean placeBid(int productId, String biderUsername) {
		for (int i = 0; i < products.size(); i++) {
			Product prod = products.get(i);
			if (prod.getId() == productId) {
				if (prod.isInAuction()) {
					if (prod.getHighestBid() > 0) {
						prod.setHighestBid(prod.getBasePrice()
								+ Constants.BID_INCREMENT, biderUsername);
					} else {
						prod.setHighestBid(prod.getBasePrice(), biderUsername);
					}
					products.set(i, prod);
					return true;
				} else {// is not in auction
					return false;
				}
			}
		}
		return false;
	}

	public ArrayList<Product> getAuctionItems() {
		ArrayList<Product> res = new ArrayList<Product>();
		for (Product prod : products) {
			if (prod.isInAuction()) {
				res.add(prod);
			}
		}
		return res;
	}

	public float getHighestBid(int id) {
		for (Product prod : products) {
			if (prod.isInAuction()) {
				return prod.getHighestBid();
			}
		}
		return 0;
	}
}
