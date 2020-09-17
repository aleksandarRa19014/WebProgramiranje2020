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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Amenity;
import beans.Apartment;




public class AdminDao {

	private Map<UUID, Amenity> amenities = new HashMap<>();
	private Map<UUID, Apartment> apertments = new HashMap<>();
	
	public AdminDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ArrayList<Amenity> getAmenitiesWithIds(Collection<String> idsAmenties) {
		
		
		ArrayList<Amenity> list=new ArrayList<Amenity>();
		
		System.out.println("----------------  getAmenitiesWithIds" + idsAmenties);
		
		
		UUID uuid = null;
		for(String id : idsAmenties) {
			
			
			System.out.println("111111111111============" + id);
			
			uuid = UUID.fromString(id.trim());
			System.out.println(  UUID.fromString(id.trim()) );
			
			if(amenities.containsKey(  uuid )) {
				
				list.add( amenities.get( uuid ));
				
				System.out.println("----------- " + amenities.get( uuid ).getName());
			}
		}
		
		System.out.println("----------------  getAmenitiesWithIds");
		
		return list;	
	}
	
	
	public Collection<Amenity> getAllAmenities(){
		Collection<Amenity> allAmenities = null;
		
		allAmenities = amenities.values();
		
		return  allAmenities;
	}
	
	
	public boolean saveAmenity(Amenity amenity, String path) {
		
		  ObjectMapper mapper = new ObjectMapper();
	        //Converting the Object to JSONString
	        String jsonString = "";
	       

	        
			try {
				jsonString = mapper.writeValueAsString(amenity);
				
				System.out.println(jsonString);
				
				BufferedWriter bw = null;
				try {
					File apartmantFile =  new File(path+"/amenity.json");
					FileWriter fileWriter = new FileWriter(apartmantFile,true);  
					
					bw = new BufferedWriter(fileWriter);
					
					bw.write(jsonString);
					bw.newLine();
				}catch (IOException e) {
		    		System.out.println("An error occurred.");
		    		e.printStackTrace();
		    		return false;
			    }finally{		    	
				   try{
				      if(bw!=null) {
				    	  bw.flush();
				    	  bw.close();
				      }
				   }catch(Exception ex){
				       System.out.println("Error in closing the BufferedWriter"+ex);
				    }
				}
				
				
				
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		      
		
		
		return true;
	}
	
	
	public void readAmenity(String path) {

        BufferedReader br = null;
       

        try {

            String sCurrentLine;
            File file = new File(path+"/amenity.json");
            if(file.exists()) {
	            FileReader fReader = new FileReader(file);
           
            
	            br = new BufferedReader(fReader);
	         
	
	            while ((sCurrentLine = br.readLine()) != null) {
	            	ObjectMapper mapper = new ObjectMapper(); 
	            	Amenity cricketer = mapper.readValue(sCurrentLine, Amenity.class); 
	            	amenities.put(cricketer.getId(),cricketer);
	            }
            }else {
            	return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        
        for (Entry<UUID, Amenity>  amanity : amenities.entrySet()) {
        	System.out.println("Key = " + amanity.getKey() +  
                    ", Value = " + amanity.getValue().getName()); 
		}
        
    }
	
	public boolean deleteAmenity( String id ,String path) {
		
		UUID uuid = UUID.fromString(id.trim());
		System.out.println(  UUID.fromString(id.trim()) );
		
		if(amenities.containsKey(  uuid )) {
			
			amenities.get(uuid).setDeleted(false);
		
		////
		
		
		
		  ObjectMapper mapper = new ObjectMapper();
	        //Converting the Object to JSONString
	        String jsonString = "";
	       

	        
			System.out.println(jsonString);
			
			BufferedWriter bw = null;
			try {
				File apartmantFile =  new File(path+"/amenity.json");
				FileWriter fileWriter = new FileWriter(apartmantFile,true);  
				
				bw = new BufferedWriter(fileWriter);
				
				
				
				for (Amenity amenity : amenities.values()) {
					
					jsonString = mapper.writeValueAsString(amenity);
					bw.write(jsonString);
					bw.newLine();
					
				}

			}catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
				return false;
			}finally{		    	
			   try{
			      if(bw!=null) {
			    	  bw.flush();
			    	  bw.close();
			      }
			   }catch(Exception ex){
			       System.out.println("Error in closing the BufferedWriter"+ex);
			    }
			}
		
		///		
			
		}else {
			return false;
		}
		
		return true;
	}

}
