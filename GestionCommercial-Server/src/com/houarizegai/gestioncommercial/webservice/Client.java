package com.houarizegai.gestioncommercial.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/client")
public class Client {

	public Client() {
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayMyName(@QueryParam("name") String myName) {
		return "Hello " + myName + " !";
	}

}
