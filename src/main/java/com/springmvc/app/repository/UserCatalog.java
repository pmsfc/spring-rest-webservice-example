package com.springmvc.app.repository;

import com.springmvc.app.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Simple user catalog class that uses hibernate
 * to do simple queries to the database and binds
 * it to objects.
 * @author Pedro Caldeira
 */
@Repository
public class UserCatalog {
	
	@PersistenceContext
	private EntityManager manager;

	/**
	 * Returns all users
	 * @return
	 */
	public List<User> getAllUsers() {
		List<User>  users = new ArrayList<User>();
		Query q = manager.createQuery("select p from User p", User.class);
		users = q.getResultList();
		return users;
	}

	/**
	 * Finds user by id
	 * @param id
	 * @return
	 */
	public User findById(long id) {
		User user = new User();
		user = manager.find(User.class, id);
		return user;
	}

	/**
	 * Verify if users exists
	 * @param user
	 * @return
	 */
	public boolean usersExists(User user) {
		User userExist = new User();
		userExist = findById(user.getGoogleID());
		if(null != userExist) {
			return true;
		}
		return false;
	}

	/**
	 * Persists a user into the db
	 * @param user
	 */
	public void saveUser(User user) {
		manager.merge(user);
	}

	/**
	 * Updates a user
	 * @param user
	 */
	public void updateUser(User user) {
		manager.merge(user);
	}

	/**
	 * Deletes a user by its id
	 * @param id
	 */
	public void deleteUserById(int id) {
		User user = new User();
		user.setGoogleID(id);
		user = manager.find(User.class, user);
		manager.remove(user);
	}

	/**
	 * Deletes all users
	 */
	public void deleteAllUsers() {
		List<User> users = new ArrayList<User>();
		users = getAllUsers();
		for (User c: users) {
			manager.remove(c.getGoogleID());
		}
	}

}

