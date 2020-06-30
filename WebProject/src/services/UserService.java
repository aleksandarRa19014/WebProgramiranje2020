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

@Path("/userService")
public class UserService {
	
	@GET
	@Path("/test")
	public String test() {
		return "REST is working.";
	}
	
	@Context
	ServletContext ctx;
	
	@PostConstruct
	public void initialization() {
		
		if(ctx.getAttribute("userDao") == null) {
			String contextPath = ctx.getRealPath("");
			System.out.println(contextPath);
			ctx.setAttribute("userDao", new UserDao(contextPath));
		}
	}
	
	@POST
	@Path("/singUp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response singUp(@Context HttpServletRequest request, User user) {
		UserDao userDao = (UserDao) ctx.getAttribute("userDao");
		String path = ctx.getRealPath("");
		user.setRole(Role.guest);
		user.setDeleted(false);
		
		User svedUser = userDao.save(user, path);
		if(svedUser != null) {
			request.getSession().setAttribute("user", svedUser);
			return Response.status(200).entity(svedUser).build();
		}else {
			return Response.status(400).entity("Username exist!").build();
		}
			
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request, UserDto userDto) {
		UserDao userDao = (UserDao) ctx.getAttribute("userDao");
		
		System.out.println("LOGINNNN------------------------------------");
		System.out.println(userDto.getUserName());
		System.out.println(userDto.getPassword());
		
		User loggedUser = userDao.find(userDto.getUserName(), userDto.getPassword());
		if (loggedUser == null) {
			return Response.status(400).entity("Invalid username and/or password").build();
		}else {
			System.out.println(loggedUser.toString());
			request.getSession().setAttribute("user", loggedUser);
		}
		return Response.status(200).entity(loggedUser).build();
	}
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(@Context HttpServletRequest request) {
		 System.out.println("-------------logout");
		 User u = (User)request.getSession().getAttribute("user");
		 if(u != null) {
			 System.out.println(u.getLastName());
			 request.getSession().invalidate();
		 }
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User update(@Context HttpServletRequest request, User userUpdate) {
		User oldUser = null;
		oldUser =  (User)request.getSession().getAttribute("user");
		UserDao userDao = (UserDao) ctx.getAttribute("userDao");
		String path =  ctx.getRealPath("");
		
		if(oldUser != null) {
			userDao.update(oldUser, userUpdate, path);
			
			request.getSession().setAttribute("user", userUpdate);
			return userUpdate;
		}else {
			return null;
		}
	}
	
	@GET
	@Path("/getAllUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getAllUsers() {
		UserDao dao = (UserDao) ctx.getAttribute("userDao");
		return dao.getAll();
	}	
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User login(@Context HttpServletRequest request) {
		return (User) request.getSession().getAttribute("user");
	}
}
                            