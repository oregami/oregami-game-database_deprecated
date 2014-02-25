package org.oregami.service;

import org.oregami.entities.user.User;

public interface IUserService {

    ServiceResult<User> register(User userData);
    
}
