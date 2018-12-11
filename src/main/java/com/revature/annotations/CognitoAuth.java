package com.revature.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CognitoAuth {
	
	public String highestRole() default "user";	  
}