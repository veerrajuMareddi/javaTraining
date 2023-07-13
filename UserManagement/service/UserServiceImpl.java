package com.rgt.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgt.entity.UserEntity;
import com.rgt.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserEntity createAccount(UserEntity userEntity) throws IOException {
		userEntity.setUserId(System.currentTimeMillis());
		return userRepository.createAccount(userEntity);
	}

	@Override
	public List<UserEntity> getAllUsers() throws IOException, ClassNotFoundException {
		return userRepository.getAllUsers();
	}

	@Override
	public UserEntity getUser(Long userId) throws IOException, ClassNotFoundException {
		return userRepository.getUser(userId);
	}

	@Override
	public UserEntity updateUser(UserEntity userEntity, Long userId) throws ClassNotFoundException, IOException {
		return userRepository.updateUser(userEntity, userId);
	}

	@Override
	public boolean deleteUser(Long userId) {
		return userRepository.deleteUser(userId);
	}

	@Override
	public boolean authenticateCredentials(Long userName, String password) throws FileNotFoundException, ClassNotFoundException, IOException {
		 return userRepository.authenticateCredentials(userName,password);
	
	}

	@Override
	public void logout() {
		 userRepository.logout();
	}

}
