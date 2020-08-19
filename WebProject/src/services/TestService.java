package services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Role;
import beans.User;
import dao.UserDao;

@Path("/testService")
public class TestService {
	
	
	@GET
	@Path("/test")
	public String test() {
		return "REST is working.";
	}
	
	@POST
	@Path("/saveImg")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveImg(@Context HttpServletRequest request, String[] path) {
		
		
         
         return Response.status(200).entity(path).build();
    
		
	}

}
