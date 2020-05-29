package dao;

import java.util.Collection;
import java.util.Optional;

import beans.User;


public interface CrudDao<T,ID> {
	
		T findbyId(ID id);
	
	 	Collection<T> getAll();
     
	 	T save(T t, String path);
	     
	    void update(T oldObject,T newObject, String path);
	     
	    void delete(T t);

}
