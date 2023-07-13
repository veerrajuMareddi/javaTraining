package com.rgt.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.entity.Response;
import com.rgt.entity.UserEntity;
import com.rgt.service.UserService;
import com.rgt.util.RoleAuthenticate;

/**
 * <p>
 * InterceptController class handles all the inputs of user and Admin and give
 * access accordingly to the role given above the methods
 * 
 * @author VeerrajuMareddi
 *
 */
@RestController
@RequestMapping("/api/users")
public class InterceptController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Response> createAccount(@RequestBody UserEntity userEntity) throws IOException {
		if (userService.createAccount(userEntity) != null) {
			Response response = Response.buildResponse("Success", "200", "User Created successfully",
					userService.createAccount(userEntity));
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(
					Response.buildResponse("Failure", "500", "ErrorOccured while saving", null), HttpStatus.OK);
		}
	}

	@GetMapping("{userId}")
	@RoleAuthenticate("admin")
	UserEntity getUser(@PathVariable("userId") Long userId) throws IOException, ClassNotFoundException {
		return userService.getUser(userId);
	}

	@PutMapping("/{userId}")
	UserEntity updateUser(@RequestBody UserEntity userEntity, @PathVariable("userId") Long userId)
			throws ClassNotFoundException, IOException {
		return userService.updateUser(userEntity, userId);
	}

	@DeleteMapping("/{userId}")
	String deleteUser(@PathVariable("userId") Long userId) {
		return userService.deleteUser(userId) ? "Success" : "Failed To delete";
	}
	
	
	/**
	 * Accepts only Admin requests and sends every other request as unauthorized
	 * requests
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@GetMapping
	@RoleAuthenticate("admin")
	ResponseEntity<Response<List<UserEntity>>> getAllUsers() throws IOException, ClassNotFoundException {

		return new ResponseEntity<Response<List<UserEntity>>>(
				Response.buildResponse("Success", "200", "User Created successfully", userService.getAllUsers()),
				HttpStatus.OK);
	}
	@ExceptionHandler(IllegalAccessException.class)
	public ResponseEntity<Response> handleMyException(IllegalAccessException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(Response.buildResponse("UNAUTHORIZED", "401", "Access Denied", null));
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Response> handleMyException(NullPointerException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Response.buildResponse("LoginFailure", "401", "Please login before Accessing", null));
	}
}
