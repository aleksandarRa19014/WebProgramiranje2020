package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import beans.Apartment;
import beans.Reservation;

public class ReservationDao {

	private String contextPath;
	
	private Map<String, Reservation> reservations = new HashMap<>();

	public ReservationDao(String contextPath) {
		super();
		this.contextPath = contextPath;
	}

	public Map<String, Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Map<String, Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public ArrayList<Reservation> getGuestReservations(String userName){
		ArrayList<Reservation> resGuest = new ArrayList<Reservation>();
		
		for(Reservation res : reservations.values()){
			if(res.getGuest().getUserName().equals(userName.trim())) {
				resGuest.add(res);
			}
		}
		
		return resGuest;
		
	}
	
	
	public ArrayList<Reservation> getHostReservations(String userName){
		ArrayList<Reservation> resHost = new ArrayList<Reservation>();
		
		for(Reservation res : reservations.values()){
			if(res.getReservedApart().getHost().getUserName().equals(userName)) {
				resHost.add(res);
			}
		}
		
		return resHost;
		
	}
	
	 @SuppressWarnings("unchecked")
	    public void loadData() {

	        System.out.println("loading data");

	        String loadPath = this.contextPath + "\\reservations.json";
	        BufferedReader in = null;
	        File file = null;
	        try {
	            file = new File(loadPath);
	            if(file.exists()) {
		            in = new BufferedReader(new FileReader(file));
	
		            ObjectMapper objectMapper = new ObjectMapper();
		            objectMapper.setVisibility(
		                    VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
		            TypeFactory factory = TypeFactory.defaultInstance();
		            MapType type = factory.constructMapType(HashMap.class, String.class, Reservation.class);
	
		            objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
		            objectMapper.registerModule(new JavaTimeModule());
		            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		            System.out.println((Map<String, Reservation>) objectMapper.readValue(file, type));
		            this.reservations = (Map<String, Reservation>) objectMapper.readValue(file, type);
		            
		           
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	
	
	public void saveData() {

		System.out.println("saving data");

		FileWriter fileWriter = null;
		File file = null;
		try {
			file = new File(this.contextPath + "\\reservations.json");
			file.createNewFile();
			fileWriter = new FileWriter(file);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			String string = objectMapper.writeValueAsString(this.reservations);
			fileWriter.write(string);
		} catch (IOException eeee) {
			eeee.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		}
	}
	
}
