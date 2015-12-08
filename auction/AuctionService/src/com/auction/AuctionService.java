package com.auction;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.coenraets.cellar.Wine;

@Path("/auctionservice")
public class AuctionService {

	
	//show all items currently in auction
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ArrayList<Product> listAllAuctionItems() {
		System.out.println("list of all auction items");
		ArrayList<Product> prods = new ArrayList<Product>();
		ProductManagement prodManager = ProductManagement.getInstance();
		prods = prodManager.getAuctionItems();
		System.out.println(prodManager);
		return prods;
	}

	
	//place product in auction
	@GET
	@Path("/placeinauction/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean list(@PathParam("id") int id) {
		System.out.println("place in auction "+id);
		ProductManagement prodManager = ProductManagement.getInstance();
		new AuctionTimer(id);
		return prodManager.placeInAuction(id);
	}
	
	
	//bid for a product
	@GET
	@Path("/placebid/{id}/{biderUsername}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean placeBid(@PathParam("id") int id, @PathParam("biderUsername") String biderUsername) {
		System.out.println("place bid for "+id);
		ProductManagement prodManager = ProductManagement.getInstance();
		return prodManager.placeBid(id, biderUsername);
	}
	
	
	
}
