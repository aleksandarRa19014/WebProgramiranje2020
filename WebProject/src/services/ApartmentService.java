package services;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
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

import org.apache.tomcat.util.codec.binary.Base64;

import beans.Address;
import beans.Amenity;
import beans.Apartment;
import beans.Location;
import beans.Role;
import beans.StatusApartment;
import beans.TypeApartment;
import beans.User;
import dao.AdminDao;
import dao.ApartmentDao;
import dto.ApartmentDto;


@Path("/apartmentService")
public class ApartmentService {

	
	@GET
	@Path("/test")
	public String test() {
		return "REST is working.";
	}
	
	@Context
	ServletContext ctx;
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora
	// (@PostConstruct anotacija)
	public void init() {
		System.out.println("Apt service init");
		// Ovaj objekat se instancira vise puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("aptDao") == null) {
			String contextPath = ctx.getRealPath("/");
			ctx.setAttribute("aptDao", new ApartmentDao(contextPath));
		}else if(ctx.getAttribute("adminDao") == null) {
			String contextPath = ctx.getRealPath("/");
			ctx.setAttribute("adminDao", new AdminDao(contextPath));
		}
	}
	

	@POST
	@Path("/addApartment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addApartment(@Context HttpServletRequest request, ApartmentDto apartmentDto) throws ParseException, IOException, URISyntaxException {
			
			User curentUser = (User) request.getSession().getAttribute("user");
			if(curentUser != null) {
				if (curentUser.getRole() == Role.guest ||  curentUser.getRole() == Role.admin) {
					return Response.status(403).entity("Forbidden").build();
				}
			}
		
		    Collection<String> paths = new ArrayList<String>(); ;
		
		    ApartmentDao apartmentDao = ((ApartmentDao) ctx.getAttribute("aptDao"));
		
		    AdminDao adminDao = (AdminDao) ctx.getAttribute("adminDao");
		
			String contextPath = ctx.getRealPath("/");

			System.out.println(contextPath);
			
			Apartment newApartment = new Apartment();
			
			
			newApartment.setLocation(apartmentDto.getLocation());
			
			
			LocalDate startDate = LocalDate.parse(apartmentDto.getStartDate());
			LocalDate endDate = LocalDate.parse(apartmentDto.getEndDate());

			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
				newApartment.getDatesForRent().add(date);
			}
			
			
			
			newApartment.setHost(apartmentDto.getHost());
			
			newApartment.setNameApartment(apartmentDto.getNameApartment());
			
			if(apartmentDto.getTypeApart().equals("apartment"))
			{
				newApartment.setTypeApart(TypeApartment.apartment); 
			}else {
				
				newApartment.setTypeApart(TypeApartment.room);
			}
			newApartment.setNumRoom(Integer.parseInt(apartmentDto.getNumRoom()) );
			newApartment.setNumOfGuests(Integer.parseInt(apartmentDto.getNumOfGuests()) );
						
			newApartment.setHost(apartmentDto.getHost());
			
			newApartment.setPrice(Double.parseDouble(apartmentDto.getPrice()) );

			
			newApartment.setStatus(StatusApartment.inastive);
			
		
			

	
			 
			 int i =0;
			for(String s: apartmentDto.getImages()) {
				
				
				i++;
				System.out.println(apartmentDto.getImages().size());
				
				Base64 decoder = new Base64();
				byte[] imgBytes = decoder.decode(s);
				FileOutputStream osf;
				
				
				try {
						
						String path = contextPath + newApartment.getId() + "-"+ i +".jpg";
						osf = new FileOutputStream(new File(path));
						
						paths.add(path);
						
						osf.write(imgBytes);
						osf.flush();
						osf.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
			
			
			
			adminDao.loadData();
			
			newApartment.setAmenites(adminDao.getAmenitiesWithIds(apartmentDto.getAmenites()));
			
			
			newApartment.setPathToImgs(apartmentDto.getImages());
 			
			
			apartmentDao.getApartments().put(newApartment.getId().toString().trim(), newApartment);
			apartmentDao.saveData();
			apartmentDao.loadData();
			
			
			return Response.status(200).entity(apartmentDto).build();		
	}
	
	
	@GET
	@Path("/allApts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allApts(@Context HttpServletRequest request) {
		try {
			User loggedIn = (User) request.getSession().getAttribute("user");
			
			ApartmentDao apartmentDao = ((ApartmentDao) ctx.getAttribute("aptDao"));
			
			apartmentDao.loadData();
			
			if (loggedIn == null) {
				System.out.println("non logged user requesting apts..");
				return Response.status(200).entity(apartmentDao.getActiveApartments())
						.build();
			} else {
				if (loggedIn.getRole().equals(Role.guest))
					return Response.status(200)
							.entity(apartmentDao.getActiveApartments()).build();
				else if (loggedIn.getRole().equals(Role.host)) {
					return Response.status(200).entity(
							apartmentDao.getApartmentsByHost(loggedIn.getUserName()))
							.build();
				} else if (loggedIn.getRole().equals(Role.admin)) {
					List<Apartment> allApts = new ArrayList<Apartment>(
							apartmentDao.getApartments().values());
					return Response.status(200).entity(allApts).build();
				}
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
			
			return Response.status(400).entity("Error occured").build();
		}
		return Response.status(400).entity("Idk").build();

	}
	
	
	
}
