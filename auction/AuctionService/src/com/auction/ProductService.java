package com.auction;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/productservice")
public class ProductService {

	//add product
	@POST
	@Path("/addnew")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean create(Product prod) {
		System.out.println(prod.toString());
		ProductManagement prodManager = ProductManagement.getInstance();
		boolean flag = prodManager.create(prod.getName(), prod.getBasePrice(), prod.getOwnerUsername());
		System.out.println("product created: " + flag);
		System.out.println(prodManager);
		return flag;
	}
	
	

	//list items
	@GET
	@Path("/list/{username}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ArrayList<Product> list(@PathParam("username") String username) {
		System.out.println("list for "+username);
		ArrayList<Product> prods = new ArrayList<>();
		ProductManagement prodManager = ProductManagement.getInstance();
		prods = prodManager.getItems(username);
		System.out.println(prodManager);
		return prods;
	}
}
