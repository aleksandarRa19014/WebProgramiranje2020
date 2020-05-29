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
	
	@POST
	@Path("/singUp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User singUp(@Context HttpServletRequest request, User user) {
		UserDao userDao = (UserDao) ctx.getAttribute("userDao");
		
		if(user != null) {
			String path = ctx.getRealPath("");
			User svedUser = userDao.save(user, path);
			if(svedUser != null) {
				request.getSession().setAttribute("user", svedUser);
				return svedUser;
			}else {
				return null;
			}
		}else {
			return null;
		}		
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
	
	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean logout(@Context HttpServletRequest request) {
	  User user = null;
	  user =  (User)request.getSession().getAttribute("user");
	  if (user != null) {
		  System.out.println(user.toString());
		  request.getSession().invalidate();
	  }else {
		return false;
	  }
	  return true;
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
}
                            