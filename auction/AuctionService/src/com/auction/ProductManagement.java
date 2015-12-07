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

	public void placeInAuction(int id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				Product prod = products.get(i);
				prod.setInAuction(true);
				products.set(i, prod);
				break;
			}
		}
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
		for(Product prod: products){
			if(prod.getOwnerUsername().equalsIgnoreCase(ownerUsername)){
				return false;
			}
		}
		return true;
	}
	
	public String toString(){
		return products.toString();
	}

	public ArrayList<Product> getItems(String ownerUsername) {
		ArrayList<Product> prods = new ArrayList<Product>();
		for(Product prod: products){
			if(prod.getOwnerUsername().equalsIgnoreCase(ownerUsername)){
				prods.add(prod);
			}
		}
		return prods;
	}
}
