package com.rgt.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.rgt.entity.UserEntity;

@Aspect
@Component
public class AuthenticationAspect {
	private static UserEntity user;
	public AuthenticationAspect() {
	}

	public AuthenticationAspect(UserEntity userEntity) {
		user = userEntity;
	}

	@Around("@annotation(roleAuthenticate)")
	public Object authenticate(ProceedingJoinPoint joinPoint, RoleAuthenticate roleAuthenticate) throws Throwable {
		String requiredRole = roleAuthenticate.value();

		if (user.getRole().equalsIgnoreCase(requiredRole)) {
			// Only allow access if the required role is "admin"
			return joinPoint.proceed();
		} else {
			throw new IllegalAccessException("Invalid access");
		}
	}
}