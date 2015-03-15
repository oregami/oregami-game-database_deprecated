package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.user.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserDao extends GenericDAOUUIDImpl<User, String>{

	@Inject
	public UserDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=User.class;
	}
	
	
	public User findOneByUsername(String name) {
		try {
			User user = (User) getEntityManager()
					.createNativeQuery("SELECT * FROM User u where lower(u.username) = :value ", User.class).setParameter("value", name.toLowerCase()).getSingleResult(); 
			return user;
		} catch (NoResultException e) {
			return null;
		}
    }


}
