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
	public List<Wine> findAll() {
		System.out.println("findAll");
//		return dao.findAll();
		return null;
	}

	
	//put product for auction
	@GET
	@Path("/placeinauction/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean list(@PathParam("id") int id) {
		System.out.println("place in auction "+id);
		ProductManagement prodManager = ProductManagement.getInstance();
		return prodManager.placeInAuction(id);
	}
	
	
	//bid for a product
	@PUT @Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Wine update(Wine wine) {
		System.out.println("Updating wine: " + wine.getName());
//		dao.update(wine);
//		return wine;
		return null;
	}
	
	
	
}
