package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import beans.Gender;
import beans.Role;
import beans.User;

public class UserDao implements CrudDao<User, String> {
	
	private Map<String, User> users = new HashMap<>();
	

	public UserDao() {

	}
	
	
	public User find(String username, String password, String path) {
		loadUsers(path);
		if (!users.containsKey(username)) {

			return null;
		}
		User user = users.get(username);
		if (!user.getPassword().equals(password)) {

			return null;
		}
		return user;
	}
	
	@Override
	public User save(User user, String path) {
		// TODO Auto-generated method stub
		loadUsers(path);
		if(users.containsKey(user.getUserName()))
		{
			return null;
		}else {
			
			BufferedWriter bw = null;
			try {
				File userFile =  new File(path+"/users.txt");
				FileWriter fileWriter = new FileWriter(userFile,true);  
				
				bw = new BufferedWriter(fileWriter);
				bw.newLine();
				bw.write(user.toString());
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
			
			users.put(user.getUserName(), user);
			
			return user;
		}
	}
	
	@Override
	public void update(User oldObject, User newObject, String path) {
		// TODO Auto-generated method stub
		
		File fileToBeModified = new File(path + "/users.txt");
        String oldContent = ""; 
        BufferedReader reader = null;      
        FileWriter writer = null;
        BufferedWriter bw = null;
         
        try{
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();       
                line = reader.readLine();
            }
            //Replacing oldString with newString in the oldContent
            String oldString = oldObject.toString();
            String newString = newObject.toString();
             
            String newContent = oldContent.replaceAll(oldString, newString);
             
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
            bw = new BufferedWriter(writer);
            bw.write(newContent.trim());//-------------
            
            users.put(newObject.getUserName(), newObject);
        }catch (IOException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
        }finally{ 	
 		   try{
 		      if(reader!=null){	  
 		    	  
 		    	 bw.flush();
 		    	 bw.close(); 
 		    	 reader.close();
 		    	
 		      }
 		   }catch(Exception ex){
 		       System.out.println("Error in closing the BufferedReader"+ex);
 		    }
 		}
	}
	
	@Override
	public User findbyId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> getAll() {
		// TODO Auto-generated method stub
		return users.values();
	}



	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}
	
	public void loadUsers(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/users.txt");
			
			System.out.println("File exists : " + file.exists());
			System.out.println(path);
			if(file.exists()) {
				
				in = new BufferedReader(new FileReader(file));
				String line;
				StringTokenizer st;
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					
					st = new StringTokenizer(line, ";");
					while (st.hasMoreTokens()) {
						String userName = st.nextToken().trim();
						String password = st.nextToken().trim();
						String name = st.nextToken().trim();
						String lastName = st.nextToken().trim();
						String role = st.nextToken().trim();
						String gander = st.nextToken().trim();
						String isDeleted = st.nextToken().trim();
						
						users.put(userName, new User(userName, password, name, lastName, role, gander, isDeleted));
					}
				}
			}else {
				createAdmins(path);
			}
		} catch (IOException e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
		}finally{ 	
		   try{
		      if(in!=null) {
		    	  in.close();
		      }
		   }catch(Exception ex){
		       System.out.println("Error in closing the BufferedReader"+ex);
		    }
		}
	}
	
	public void createAdmins(String path) {
		BufferedWriter bw = null;
		try{
			File userFile =  new File(path + "/users.txt");
			
			System.out.println("File created: " + userFile.getName());
			
			User admin1 = new User("lesa","lesa","Aleksandar","Radovanovic",Role.admin,Gender.male,false);
			User admin2 = new User("aleksej","aleksej","Aleksej","Lukic",Role.admin,Gender.male,false);
			FileWriter fileWriter = new FileWriter(userFile,true);  
			
			bw = new BufferedWriter(fileWriter);				
			bw.write(admin1.toString());
			bw.newLine();
			bw.write(admin2.toString());
			
			
			users.put(admin1.getUserName(),admin1);
			users.put(admin2.getUserName(), admin2);
			
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
	}
}
