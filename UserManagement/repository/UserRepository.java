package com.rgt.repository;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rgt.entity.UserEntity;
import com.rgt.util.AuthenticationAspect;

@Repository
public class UserRepository {

	protected static final String fileName = "data";

	public UserEntity createAccount(UserEntity userEntity) throws IOException {

		try (FileOutputStream fileOutputStream = new FileOutputStream(
				fileName + "\\" + userEntity.getUserId() + ".ser");
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(userEntity);
			objectOutputStream.flush();
		}
		return userEntity;
	}

	public List<UserEntity> getAllUsers() throws IOException, ClassNotFoundException {
		List<UserEntity> users = new ArrayList<>();
		File folder = new File(fileName);

		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".ser"));

			if (files != null) {
				for (File file : files) {
					try (FileInputStream fileInputStream = new FileInputStream(file);
							ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
						UserEntity user = (UserEntity) objectInputStream.readObject();
						users.add(user);
						System.out.println("Read object from file: " + file.getName());
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("Invalid folder path: ");
		}

		return users;
	}

	public UserEntity getUser(Long userId) throws IOException, ClassNotFoundException {
		File file = new File(fileName + "//" + userId + ".ser");
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
			while (true) {
				try {
					UserEntity user = (UserEntity) input.readObject();
					if (user.getUserId().equals(userId)) {
						return user; // Found the matching user
					}
				} catch (EOFException e) {
					break; // Reached end of file
				}
			}
		}
		// User not found
		return null;
	}

	public UserEntity updateUser(UserEntity userEntity, Long userId) throws IOException {
		File file = new File(fileName);
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
			while (true) {
				try {
					UserEntity user = (UserEntity) input.readObject();
					if (user.getUserId().equals(userId)) {
						userEntity.setUsername(user.getUsername());
						userEntity.setPassword(user.getPassword());
						return userEntity; // Found the matching user
					}
				} catch (EOFException e) {
					break; // Reached end of file
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		// User not found
		return null;
	}

	public boolean deleteUser(Long userId) {
		File file = new File(fileName + "//" + userId + ".ser");
		if (file.delete()) {
			System.out.println("User with ID: " + userId + " has been deleted.");
			return true;
		} else {
			System.out.println("Failed to delete user with ID: " + userId);
			return false;
		}
	}

	public boolean authenticateCredentials(Long userName, String password)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(fileName + "//" + userName + ".ser");
		if (file.exists()) {
			try (ObjectInputStream input1 = new ObjectInputStream(new FileInputStream(file))) {
				UserEntity user = (UserEntity) input1.readObject();
				if (user.getUserId().equals(userName) && user.getPassword().equals(password)) {
					new AuthenticationAspect(user);
					return true;
				}
			} catch (EOFException e) {
				return false;
			}
		}
		return false;
	}

	public void logout() {
		new AuthenticationAspect(new UserEntity());
	}
}
