package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import beans.Apartment;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class ApartmentDao implements CrudDao<Apartment, String> {
	
	
	private Map<String, Apartment> apartments = new HashMap<>();
	
	
	
	@Override
	public Apartment findbyId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Apartment> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment save(Apartment t, String path) {
	 	// TODO Auto-generated method stub
		//if(apartments.containsKey(t.getIdApartment())) {
				//return null;
		//}else {
		
			    ObjectMapper mapper = new ObjectMapper();
		        //Converting the Object to JSONString
		        String jsonString = "";
		        
		        
		    
		       
				try {
					jsonString = mapper.writeValueAsString(t);
					
					System.out.println(jsonString);
					
					BufferedWriter bw = null;
					try {
						File apartmantFile =  new File(path+"/apartmant.txt");
						FileWriter fileWriter = new FileWriter(apartmantFile,true);  
						
						bw = new BufferedWriter(fileWriter);
						bw.newLine();
						bw.write(jsonString);
					}catch (IOException e) {
			    		System.out.println("An error occurred.");
			    		e.printStackTrace();
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
				}
			      
		//}
		return null;
	}

	@Override
	public void update(Apartment oldObject, Apartment newObject, String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Apartment t) {
		// TODO Auto-generated method stub
		
	}

	public ApartmentDao() {
		super();
		// TODO Auto-generated constructor stub
	}



	

}
