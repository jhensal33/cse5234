package com.ups.shipping;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/initiation")
public class ShippingInitiation {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping() {
		return Response.ok("Hello, UPS is up and running to serve you ...").build();
	}

}
