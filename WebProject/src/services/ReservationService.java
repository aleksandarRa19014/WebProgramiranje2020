package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Apartment;
import beans.Reservation;
import beans.Role;
import beans.StatusReser;
import beans.User;

import dao.ApartmentDao;
import dao.ReservationDao;
import dto.ReservationDto;





@Path("/reservation")
public class ReservationService {
	
	@Context
	ServletContext ctx;

	public ReservationService() {

	}

	@PostConstruct
	public void init() {
		if (ctx.getAttribute("reservationDao") == null) {
			String contextPath = ctx.getRealPath("");
			System.out.println("Reservation service initialized");
			ctx.setAttribute("reservationDao", new ReservationDao(contextPath));
		}
	}
	
	@GET
	@Path("/getReservations")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservation(@Context HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		ReservationDao resDao = (ReservationDao) ctx.getAttribute("reservationDao");
		
		
		resDao.loadData();
		
		if (user.getRole() == Role.guest) {
	
			return Response.status(200).entity(resDao.getGuestReservations(user.getUserName())).build();
		} else if (user.getRole() == Role.host) {

			
			return Response.status(200).entity(resDao.getHostReservations(user.getUserName())).build();
		}else if(user.getRole() == Role.admin) {
			
			List<Reservation> reservations =  new ArrayList<Reservation>(resDao.getReservations().values());
			
			return Response.status(200).entity(reservations).build();
		}else {
			return Response.status(403).entity("Pregled rezervacija je omogucen smo korisnicima. ").build();
		}
		
		 
	}
	
	@POST
	@Path("/createRes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("text/html")
	public Response createReservation(ReservationDto reservationDto, @Context HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user.getRole() != Role.guest) {
			return Response.status(403).entity("Samo gosti mogu da rezervisu. ").build();
		} else {
			try {
				
				ApartmentDao aptDao = ((ApartmentDao) ctx.getAttribute("aptDao"));
				Apartment a1 = aptDao.findById(reservationDto.getIdApartment());
				
				if(a1 != null) {
					

					for (int i = 0; i < reservationDto.getNumOfNights(); i++) {
						if (!a1.getDatesAvailable().contains(LocalDate.parse(reservationDto.getBookingDate()).plusDays(i))) {
							System.out.println(LocalDate.parse(reservationDto.getBookingDate()).plusDays(i));
							System.out.println(!a1.getDatesAvailable().contains(LocalDate.parse(reservationDto.getBookingDate()).plusDays(i)));
							return Response.status(400).entity("Rezervacija nije moguca za navedene datume.").build();
						}
					}
					
					ReservationDao resDao = ((ReservationDao) ctx.getAttribute("reservationDao"));
					
					Reservation reservation = new Reservation();
					
					reservation.setReservedApart(a1);
					reservation.setStatusReser(StatusReser.created);
					reservation.setNumOfNights(reservationDto.getNumOfNights());
					
					LocalDate date = LocalDate.parse(reservationDto.getBookingDate());
					reservation.setBookingDate(date);
					reservation.setGuest(user);
					reservation.setText(reservationDto.getText());
					
					List<LocalDate> newDatesAvailable = a1.getDatesAvailable();
					for (int i = 0; i < reservationDto.getNumOfNights(); i++) {
						System.out.println("request dates: ");
						System.out.println(LocalDate.parse(reservationDto.getBookingDate()).plusDays(i));
						if (a1.getDatesAvailable().contains(LocalDate.parse(reservationDto.getBookingDate()).plusDays(i))) {
							newDatesAvailable.remove(LocalDate.parse(reservationDto.getBookingDate()).plusDays(i));
						}
					}		
					
					reservation.setPrice(a1.getPrice() * reservationDto.getNumOfNights());
					
					a1.setDatesAvailable(newDatesAvailable);
					a1.getReservations().add(reservation);
					
					resDao.getReservations().put(reservation.getIdRes().toString().trim(), reservation);
					
					resDao.saveData();
					aptDao.saveData();
					
					resDao.loadData();
					aptDao.loadData();
					
					return Response.status(200).entity("Uspesna rezarvacija.").build();
					
				} else {
					return Response.status(400).entity("Bad request").build();
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(400).entity("Bad request").build();
			}
			
		} 
		
	}
	
	
}
