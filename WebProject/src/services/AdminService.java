package services;

import java.util.Collection;


import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Amenity;
import beans.Apartment;
import beans.Role;
import beans.User;
import dao.AdminDao;
import dao.ApartmentDao;
import dao.UserDao;
import java.util.Iterator;



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
			String contextPath = ctx.getRealPath("/");
			ctx.setAttribute("adminDao", new AdminDao(contextPath));
		}else if(ctx.getAttribute("aptDao") == null) {
			String contextPath = ctx.getRealPath("/");
			ctx.setAttribute("aptDao", new ApartmentDao(contextPath));
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
		}else {
			return Response.status(403).entity("Forbidden").build();
		}
		
		Amenity newAmennity = new Amenity();
		newAmennity.setName(amenity.getName());
		
		AdminDao adminDao = (AdminDao) ctx.getAttribute("adminDao");
		
		adminDao.getAmenities().put(newAmennity.getId().toString().trim(), newAmennity);
		
		adminDao.saveData();
		
		if(adminDao.getAmenities().containsKey(newAmennity.getId().toString().trim())) {
			return Response.status(200).entity(adminDao.getAmenities().get(newAmennity.getId().toString().trim())).build();
		}else {
			return Response.status(400).entity("Bad Request").build();
		}	
	}
	

	@GET
	@Path("/getAmenities")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Amenity> getAmenities() {
		AdminDao adminDao = (AdminDao) ctx.getAttribute("adminDao");
		
		if(adminDao != null) {
			adminDao.loadData();
			return adminDao.getAmenities().values();
		}else {
			return null;
		}
		
	}	
	
	

	@DELETE
	public Response deleteAmenity(@QueryParam("id") String id, @Context HttpServletRequest request) {
		User curentUser = (User) request.getSession().getAttribute("user");
		if(curentUser != null) {
			if (curentUser.getRole() != Role.admin) {
				return Response.status(403).entity("Forbidden").build();
			}
		}else {
			return Response.status(403).entity("Forbidden").build();
		}
		
		System.out.println("id================= " + id);
		
		AdminDao adminDao = (AdminDao) ctx.getAttribute("adminDao");
		
		Amenity amenity = adminDao.fundById(id.trim());
		
		if(amenity != null) {
			
			
			if(adminDao.getAmenities() != null) {
				
				adminDao.getAmenities().remove(amenity); //izbacim iz liste postavim deleted na true i opet ubacim u listu i sacuvam tako
				
				ApartmentDao aptDao = ((ApartmentDao) ctx.getAttribute("aptDao"));
				
				aptDao.loadData();
				
				if(aptDao.getApartments() != null) {
					

					for (Apartment apt : aptDao.getApartments().values()) {
						
						if(apt.getAmenites() != null) {	
							for (Amenity amen : apt.getAmenites()) { // izbacim iz svih apartmana obrisan sadrzaj apartmana
								if (amen.getId().toString().trim().equals(id.trim())) {
									apt.getAmenites().remove(amen);
									amen.setDeleted(true);
									apt.getAmenites().add(amen);
								}
							}
						}
					}
					aptDao.saveData();
				}
				
			}
			
			
			amenity.setDeleted(true);
			
			adminDao.getAmenities().put(amenity.getId().toString().trim(), amenity);
			
			adminDao.saveData();
			
			
			return Response.status(200).entity("Uspesno ste obrisali").build();
			
		}else {
			return Response.status(400).entity("Sadrzaj aprtmana ne postoji").build();
		}	
	}
	
	
	
}
