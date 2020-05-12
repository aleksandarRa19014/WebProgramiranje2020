package dao;

import java.util.Collection;
import java.util.Optional;


public interface CrudDao<T,ID> {
	
		Optional<T> findbyId(ID id);
	
	 	Collection<T> getAll();
     
	    void save(T t);
	     
	    void update(T t, String[] params);
	     
	    void delete(T t);

}
