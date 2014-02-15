package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.user.User;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class UserDao extends GenericDAOImpl<User, Long>{

	@Inject
	public UserDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=User.class;
	}
	
	
	public User findOneByUsername(String name) {
    	User user = (User) getEntityManager()
        		.createNativeQuery("SELECT * FROM User u ", User.class).getSingleResult(); 
        return user;
    }


}
