package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.UserDao;
import org.oregami.entities.KeyObjects.UserStatusKey;
import org.oregami.entities.user.User;
import org.oregami.entities.user.UserStatus;
import org.oregami.util.MailHelper;
import org.oregami.util.validation.UserValidator;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements IUserService {

	@Inject
    private UserDao userDao;
	
	private MailHelper mailhelper = MailHelper.instance();
	
    public void uponSuccessfulRegistration(User user){
 
    	String recipient = user.getEmail();
        mailhelper.sendMail(recipient, "[Development-Test-Mail only] Your Oregami account", "This is only a development mail.\n\nWelcome to Oregami.org!\n\nYour username: " + user.getUsername());
        
        String admin = "gene@kultpower.de";
        mailhelper.sendMail(admin, "New user account", "username: " + user.getUsername() + "\n" + "email: " + user.getEmail());
        
        
    }    
    
    @Override
    public ServiceResult<User> register(User userData) {
        UserValidator validator = buildUserValidator(userData);

        List<ServiceError> errorMessages = validator.validateForRegister();

        User user = null;

        if (errorMessages.size() == 0) {
            user = userData;
            
            UserStatus userStatus = new UserStatus();
            String hash = UUID.randomUUID().toString();
            userStatus.setVerifyHash(hash);
            userStatus.setCreationDate(new Timestamp(System.currentTimeMillis()));
            userStatus.setUserStatus(UserStatusKey.Registration);
            user.addUserStatus(userStatus);
            
            user.setPasswordAndEncryptIt(user.getPassword());
            userDao.save(user);
            
            uponSuccessfulRegistration(user);
        }

        return new ServiceResult<User>(user, errorMessages);
    }

    private UserValidator buildUserValidator(User userData) {
        return new UserValidator(userDao, userData);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao UserDao) {
        this.userDao = UserDao;
    }

    @Override
	public User loadUserByUsername(String username) {
		User user = userDao.findOneByUsername(username);
		return user;
	}

	public void setMailhelper(MailHelper mailhelper) {
		this.mailhelper = mailhelper;
	}

	
}
