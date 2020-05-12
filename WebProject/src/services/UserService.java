package services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Gender;
import beans.Role;
import beans.User;
import dao.UserDao;
import dto.UserDto;

@Path("")
public class UserService {
	
	@Context
	ServletContext ctx;
	
	@PostConstruct
	public void initialization() {
		System.out.println("INICIJALIZACIJA");
		
		if(ctx.getAttribute("userDao") == null) {
			String contextPath = ctx.getRealPath("");
			System.out.println(contextPath);
			ctx.setAttribute("userDao", new UserDao(contextPath));
		}
	}
	
	@GET
	@Path("/test")
	public String test() {
		return "Rest is working";
	}
	
	@GET
	@Path("/getAllUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getAllUsers() {
		
		UserDao dao = (UserDao) ctx.getAttribute("userDao");

		return dao.getAll();
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request, UserDto userDto) {
		UserDao userDao = (UserDao) ctx.getAttribute("userDao");
		
		User loggedUser = userDao.find(userDto.getUserName(), userDto.getPassword());
		if (loggedUser == null) {
			return Response.status(400).entity("Invalid username and/or password").build();
		}else {
			request.getSession().setAttribute("user", loggedUser);
		}
		return Response.status(200).build();
	}
	
}
