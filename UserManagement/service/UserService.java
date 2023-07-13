package com.rgt.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.rgt.entity.UserEntity;

public interface UserService {

	UserEntity createAccount(UserEntity userEntity) throws IOException;
	List<UserEntity> getAllUsers() throws IOException, ClassNotFoundException;
	UserEntity getUser(Long userId) throws IOException, ClassNotFoundException;
	UserEntity updateUser(UserEntity userEntity, Long userId) throws ClassNotFoundException, IOException;
	boolean deleteUser(Long userId);
	boolean authenticateCredentials(Long userName, String password) throws FileNotFoundException, ClassNotFoundException, IOException;
	void logout();
}
