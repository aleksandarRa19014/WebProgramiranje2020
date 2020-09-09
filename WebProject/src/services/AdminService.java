package services;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

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

import beans.Amenity;
import beans.Role;
import beans.User;
import dao.AdminDao;
import dao.UserDao;

@Path("/AdminService")
public class AdminService {
	
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
			
			ctx.setAttribute("userDao", new UserDao());
			
		}else if(ctx.getAttribute("adminDao") == null) {
			
			ctx.setAttribute("adminDao", new AdminDao());
		}
	}

	
	@POST
	@Path("/addAmenity")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAmenity (@Context HttpServletRequest request, Amenity amenity) {
		
		User curentUser = (User) request.getSession().getAttribute("user");
		if(curentUser != null) {
			if (curentUser.getRole() != Role.admin) {
				return Response.status(403).entity("Forbidden").build();
			}
		}
		
		Amenity newAmennity = new Amenity();
		newAmennity.setName(amenity.getName());
		
		AdminDao adminDao = (AdminDao) ctx.getAttribute("adminDao");
		
		String contextPath = ctx.getRealPath("/");
		
			
		if(adminDao.saveAmenity(newAmennity,contextPath)) {
			
			adminDao.readAmenity(contextPath);
			return Response.status(200).entity(amenity).build();
		}else {
			return Response.status(400).entity("Bad Request").build();
		}	
	}
	

	@GET
	@Path("/getAmenities")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Amenity> getAmenities() {
		AdminDao adminDao = (AdminDao) ctx.getAttribute("adminDao");
		String contextPath = ctx.getRealPath("/");
		adminDao.readAmenity(contextPath);
		return adminDao.getAllAmenities();
	}	
}
