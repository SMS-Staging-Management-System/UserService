package com.revature.aspects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.utils.CognitoUtil;
import com.revature.utils.ResponseMap;

@Aspect
@Component
public class CognitoAspect {
	
	@Autowired
	private CognitoUtil iUtil;
	
	@Around(" @annotation(com.revature.annotations.CognitoAuth)")
	public Object CognitoAuth(ProceedingJoinPoint pjp) throws Throwable {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		ResponseEntity<String> response = iUtil.cognitoAuth(request);
		if(response.getStatusCodeValue() != 200) {
			return ResponseEntity.status(403).body(ResponseMap.getBadResponse("Token not verified"));
		}	
		return pjp.proceed();
    }
}
