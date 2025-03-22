package com.wardrobehub.service;

import com.wardrobehub.exception.UserException;
import com.wardrobehub.model.User;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;


}
