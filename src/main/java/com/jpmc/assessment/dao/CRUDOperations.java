package com.jpmc.assessment.dao;

import java.util.List;

/**
 * @author Reena
 *
 * @param <T>
 * 
 * This interface has generic crud operation and 
 * can be used to by any bean in future
 */
public interface CRUDOperations<T> {
	
	/**
	 * @param t
	 */
	void add(T t);
	
	/**
	 * @return
	 */
	List<T> fetchData();

}
