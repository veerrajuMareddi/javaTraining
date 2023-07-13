package com.rgt.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class Response<T> {
	private String status;
	private String code;
	private String message;
	@JsonInclude(Include.NON_NULL)
	private T data;

	public static <T> Response<T> buildResponse(String status, String code, String message, T data) {
		Response<T> response = new Response();
		response.setStatus(status);
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		return response;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
