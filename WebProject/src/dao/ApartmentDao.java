package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import beans.Amenity;
import beans.Apartment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;




public class ApartmentDao {
	
	
	private Map<String, Apartment> apartments = new HashMap<>();
	private Map<String, Amenity> amanties = new HashMap<>();
    private String contextPath;
		
	
	public ApartmentDao(String contextPath) {
        this.contextPath = contextPath;
	}
	
	
	
	public Map<String, Apartment> getApartments() {
		return apartments;
	}





	public void setApartments(Map<String, Apartment> apartments) {
		this.apartments = apartments;
	}





	public Map<String, Amenity> getAmanties() {
		return amanties;
	}





	public void setAmanties(Map<String, Amenity> amanties) {
		this.amanties = amanties;
	}
	
	 public List<Apartment> getActiveApartments() {
	        List<Apartment> activeApts = new ArrayList<>();

	        for (Apartment a : apartments.values()) {
	            if (a.getStatus().equals("active"))
	                activeApts.add(a);
	        }

	        return activeApts;
	    }

	    public List<Apartment> getApartmentsByHost(String username) {
	        System.out.println("Getting host apts");

	        List<Apartment> aptsByHost = new ArrayList<>();

	        for (Apartment a : apartments.values()) {
	            System.out.println(a.toString());
	            if(a.getHost()!=null)
	                if (a.getHost().getUserName().equals(username))
	                    aptsByHost.add(a);
	        }

	        System.out.println(this.apartments);
	        return aptsByHost;
	    }

	    public Apartment findById(String id) {
	        if (this.apartments.containsKey(id)) {
	            return this.apartments.get(id);
	        }

	        return null;
	    }
	
	
	  @SuppressWarnings("unchecked")
	    public void loadData() {

	        System.out.println("loading data");

	        String loadPath = this.contextPath + "\\apartment.json";
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
		            MapType type = factory.constructMapType(HashMap.class, String.class, Apartment.class);
	
		            objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
		            objectMapper.registerModule(new JavaTimeModule());
		            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		            System.out.println((Map<String, Apartment>) objectMapper.readValue(file, type));
		            this.apartments = (Map<String, Apartment>) objectMapper.readValue(file, type);
		            
		            for(Apartment apt : this.apartments.values()) {
		            	System.out.println("-----------------------------------------------------"+apt.getNameApartment());
		            }
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
			file = new File(this.contextPath + "\\apartment.json");
			file.createNewFile();
			fileWriter = new FileWriter(file);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			String string = objectMapper.writeValueAsString(this.apartments);
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