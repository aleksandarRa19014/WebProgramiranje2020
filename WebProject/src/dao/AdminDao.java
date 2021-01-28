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
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.json.simple.parser.JSONParser;
import org.objectweb.asm.tree.TryCatchBlockNode;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import beans.Amenity;
import beans.Apartment;




public class AdminDao {

	private Map<String, Amenity> amenities = new HashMap<>();
	private Map<String, Apartment> apartments = new HashMap<>();
	private String contextPath;
	
	
	public AdminDao(String contextPath) {
		 this.contextPath = contextPath;
		 
		 //DUMMY DATA
		 
		 Amenity am1 = new Amenity();
		 am1.setName("TV");
		 
		 Amenity am2 = new Amenity();
		 am2.setName("WiFi");
		 
		 Amenity am3 = new Amenity();
		 am3.setName("Tuš Kabina");
		 
		 getAmenities().put(am1.getId().toString(), am1);
		 getAmenities().put(am2.getId().toString(), am2);
		 getAmenities().put(am3.getId().toString(), am3);
		 
		 this.saveData();
	}
	
	public Map<String, Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(Map<String, Amenity> amenities) {
		this.amenities = amenities;
	}

	public Map<String, Apartment> getApartments() {
		return apartments;
	}

	public void setApartments(Map<String, Apartment> apartments) {
		this.apartments = apartments;
	}	
	
	public Amenity fundById(String id) {
		if(this.amenities.containsKey(id)) {
			return this.amenities.get(id);
		}
		return null;
	}
	
	public  ArrayList<Amenity> getAmenitiesWithIds(Collection<String> idsAmenties) {
		ArrayList<Amenity> amentWithID = new ArrayList<Amenity>();
		
		for (String id :idsAmenties) {
			
			if(this.amenities.containsKey(id.trim())) {
				amentWithID.add(this.amenities.get(id.trim()));
			}
		}
		
		return amentWithID;
	}
	
	@SuppressWarnings("deprecation")
	public void saveData() {

		System.out.println("saving data");

		FileWriter fileWriter = null;
		File file = null;
		try {
			file = new File(this.contextPath + "\\amenities.json");
			file.createNewFile();
			fileWriter = new FileWriter(file);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String string = objectMapper.writeValueAsString(this.amenities);
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
	
	
	 @SuppressWarnings({ "unchecked", "deprecation" })
	public void loadData() {

	        System.out.println("loading data");

	        String loadPath = this.contextPath + "\\amenities.json";
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
		            MapType type = factory.constructMapType(HashMap.class, String.class, Amenity.class);
	
		            objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
		          
		            this.amenities = (Map<String, Amenity>) objectMapper.readValue(file, type);
		            
		            for(Amenity amn : this.amenities.values()) {
		            	System.out.println("-----------------------------------------------------"+amn.getName());
		            }
	            }else {
	            	
	            	return;
	            }
	        } catch (Exception e) {
	        	System.out.println("-----------------------------------------------------GRESKA");
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

	
}
