package services;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.Console;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
import dto.SearchDto;


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
			
			if (curentUser.getRole() == Role.guest ||  curentUser.getRole() == Role.admin || curentUser == null) {
				return Response.status(403).entity("Forbidden").build();
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
				newApartment.getDatesAvailable().add(date);
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

			
			newApartment.setStatus(StatusApartment.inactive);
			
		
			

	
			 
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
	
	
	
	@POST
	@Path("/changeApartment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changeApartment(@Context HttpServletRequest request, ApartmentDto apartmentDto) throws ParseException, IOException, URISyntaxException {
			
			User curentUser = (User) request.getSession().getAttribute("user");
			
			if (curentUser.getRole() == Role.guest || curentUser == null) {
				return Response.status(403).entity("Forbidden").build();
			}
		
		
		    Collection<String> paths = new ArrayList<String>(); ;
		
		    ApartmentDao apartmentDao = ((ApartmentDao) ctx.getAttribute("aptDao"));
		
		    AdminDao adminDao = (AdminDao) ctx.getAttribute("adminDao");
		
			String contextPath = ctx.getRealPath("/");

			System.out.println(contextPath);
			
			Apartment newApartment = new Apartment();
			
			newApartment.setId(UUID.fromString(apartmentDto.getId()));
			
			
			newApartment.setLocation(apartmentDto.getLocation());
			
			
			LocalDate startDate = LocalDate.parse(apartmentDto.getStartDate());
			LocalDate endDate = LocalDate.parse(apartmentDto.getEndDate());

			for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
				newApartment.getDatesForRent().add(date);
				newApartment.getDatesAvailable().add(date);
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

			
			newApartment.setStatus(StatusApartment.inactive);
			
		
			

	
			 
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

			
			if(apartmentDto.getStatus().equals("inactive")) {
				
				newApartment.setStatus(StatusApartment.inactive);
				
			}else if(apartmentDto.getStatus().equals("active")) {
				
				newApartment.setStatus(StatusApartment.active);
				
			}
				
			
			newApartment.setAmenites(adminDao.getAmenitiesWithIds(apartmentDto.getAmenites()));
			
			
			newApartment.setPathToImgs(apartmentDto.getImages());
 			
			
			System.out.println("-------------------ID" + newApartment.getId().toString());
			
			apartmentDao.getApartments().remove(newApartment.getId().toString().trim());
			
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
				return Response.status(200).entity(apartmentDao.getActiveApartments()).build();
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
	
	@GET
	@Path("/getApart")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApart(@QueryParam("id") String id, @Context HttpServletRequest request) {
		
		User curentUser = (User) request.getSession().getAttribute("user");
		
		if (curentUser.getRole() == Role.guest  || curentUser == null) {
			return Response.status(403).entity("Forbidden").build();
		}
		
		ApartmentDao apartmentDao = ((ApartmentDao) ctx.getAttribute("aptDao"));

		
		return Response.status(200).entity(apartmentDao.findById(id)).build();
				
	}
	
	@DELETE
	public Response deleteApartmant(@QueryParam("id") String id, @Context HttpServletRequest request) {
		
		System.out.println(id);
		
		try {
			ApartmentDao aptDao = ((ApartmentDao) ctx.getAttribute("aptDao"));
			Apartment a = aptDao.findById(id.toString());
			Map<String, Apartment> apts = aptDao.getApartments();

			if (a != null) {
				
					a.setDeleted(true);
					apts.put(id.toString(), a);
					aptDao.setApartments(apts);
					aptDao.saveData();
					ctx.setAttribute("aptDao", aptDao);
					return Response.status(200).entity("Uspesno ste obrisali apartman").build();
				
			}

			else {
				System.err.println("error occured on delete");
				return Response.status(400).entity("Apartment not found").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Internal server error").build();
		}

	}
	
	
	@POST
	@Path("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(SearchDto searchDto) {
		System.out.println(searchDto.getMaxPrice());

		ApartmentDao aptDao = ((ApartmentDao) ctx.getAttribute("aptDao"));
		Map<String, Apartment> apts = aptDao.getApartments();

		// =====================================search
		// date============================================

		List<Apartment> searchDate = new ArrayList<>();

		if (searchDto.getStartDate() != null && searchDto.getStartDate() != "" && searchDto.getEndDate() != null
				&& searchDto.getEndDate() != "")
			for (Apartment a : apts.values()) {
				if (checkAvailable(searchDto.getStartDate(), searchDto.getEndDate(), a)) {
					searchDate.add(a);
					System.out.println("dates available" + a.getNameApartment());
				}
			}
		else
			searchDate = new ArrayList<Apartment>(apts.values());

		// ======================================search
		// price============================================

		List<Apartment> searchPrice = new ArrayList<>();

		if (searchDto.getMinPrice() != 0 && searchDto.getMaxPrice() != 0) {
			for (Apartment a : searchDate) {
				if (a.getPrice() != 0) {
					if (a.getPrice() > searchDto.getMinPrice() && a.getPrice() <= searchDto.getMaxPrice()) {
						searchPrice.add(a);
					}
				}
			}
		} else if (searchDto.getMinPrice() != 0) {
			for (Apartment a : searchDate) {
				if (a.getPrice() != 0) {
					if (a.getPrice() >= searchDto.getMinPrice()) {
						searchPrice.add(a);
					}
				}
			}
		} else if (searchDto.getMaxPrice() != 0) {
			for (Apartment a : searchDate) {
				if (a.getPrice() != 0) {
					if (a.getPrice() <= searchDto.getMaxPrice()) {
						searchPrice.add(a);
					}
				}
			}
		} else {
			searchPrice = searchDate;
		}

		// ======================================search
		// rooms============================================

		List<Apartment> searchRoom = new ArrayList<>();

		if (searchDto.getMinRoomNo() != 0 && searchDto.getMaxRoomNo() != 0) {
			for (Apartment a : searchPrice) {
				if (a.getNumRoom() != 0) {
					if (a.getNumRoom() > searchDto.getMinRoomNo() && a.getNumRoom() <= searchDto.getMaxRoomNo()) {
						searchRoom.add(a);
					}
				}
			}
		} else if (searchDto.getMinRoomNo() != 0) {
			for (Apartment a : searchPrice) {
				if (a.getNumRoom() != 0) {
					if (a.getNumRoom() >= searchDto.getMinRoomNo()) {
						searchRoom.add(a);
					}
				}
			}
		} else if (searchDto.getMaxRoomNo() != 0) {
			for (Apartment a : searchPrice) {
				if (a.getNumRoom() != 0) {
					if (a.getNumRoom() <= searchDto.getMaxRoomNo()) {
						searchRoom.add(a);
					}
				}
			}
		} else {
			searchRoom = searchPrice;
		}

		// ======================================search
		// persons============================================

		List<Apartment> searchPersons = new ArrayList<>();

		if (searchDto.getPersonNo() != 0) {
			for (Apartment a : searchRoom) {
				if (a.getNumOfGuests() != 0) {
					if (a.getNumOfGuests() == searchDto.getPersonNo())
						searchPersons.add(a);
				}
			}
		} else {
			searchPersons = searchRoom;
		}

		// ======================================search
		// location==============================================

		List<Apartment> searchLocation = new ArrayList<>();

		if (searchDto.getPlace() != "") {
			for (Apartment a : searchPersons) {
				if (a.getLocation().getAddress().getPlace().toLowerCase().equals(searchDto.getPlace().toLowerCase())) {
				
							searchLocation.add(a);
				}
			}
		} else
			searchLocation = searchPersons;

		return Response.status(200).entity(searchLocation).build();
	}

	public boolean checkAvailable(String startDate, String endDate, Apartment a1) {

		if (a1.getDatesAvailable().size() == 0)
			return false;

		long daysBetween = ChronoUnit.DAYS.between(LocalDate.parse(startDate), LocalDate.parse(endDate));
		// System.out.println(daysBetween);

		for (int i = 0; i < daysBetween; i++) {
			// System.out.println(LocalDate.parse(startDate).plusDays(i));
			if (!a1.getDatesAvailable().contains(LocalDate.parse(startDate).plusDays(i))) {
				return false;
			}
		}

		return true;
	}
	
	
}
