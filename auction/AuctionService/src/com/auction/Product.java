package com.auction;

import utility.IdGenerator;

public class Product {
	private int id;
	private String name;
	private float basePrice;
	private boolean inAuction;
	private String ownerUsername;
	private float highestBid;
	private int highestBiderId;

	private Product() {
	}

	public Product(String name, float price, String ownerUsername) {
		this.id = IdGenerator.getInstance().getNextIncrementalId();
		this.name = name;
		this.basePrice = price;
		this.ownerUsername = ownerUsername;
		this.inAuction = false;
		this.highestBid = 0f;
		this.highestBiderId = -1;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public boolean isInAuction() {
		return inAuction;
	}

	public void setInAuction(boolean inAuction) {
		this.inAuction = inAuction;
	}

	public String getOwnerUsername() {
		return this.ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public boolean setHighestBid(float bid, int biderId) {
		if (bid > this.highestBid) {
			this.highestBid = bid;
			this.highestBiderId = biderId;
			return true;
		}
		return false;
	}

	public String toString() {
		return "id: " + id + ", name: " + name + ", base price: " + basePrice
				+ ", currently in auction: " + inAuction;
	}
}