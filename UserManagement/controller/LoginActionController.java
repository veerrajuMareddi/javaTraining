package com.rgt.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.entity.Response;
import com.rgt.service.UserService;

/**
 * Handles login and logout functionality
 *
 * @author VeerrajuMareddi
 */

@RestController
public class LoginActionController {
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestParam(value = "userName") Long userName,
			@RequestParam(value = "password") String password)
			throws FileNotFoundException, ClassNotFoundException, IOException {

		if (userService.authenticateCredentials(userName, password)) {
			Response response = Response.buildResponse("Success", "200", "Login successfully", null);

			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}
		Response response = Response.buildResponse("Failure", "500", "Login failure", null);

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/logout")
	public String logout() {
		userService.logout();
		return "Successfully logged out";
	}

}
