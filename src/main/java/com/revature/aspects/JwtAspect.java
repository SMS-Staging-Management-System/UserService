package com.revature.aspects;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import com.revature.utils.CognitoUtil;
import com.revature.utils.ResponseMap;

@Aspect
@Component
public class JwtAspect {

	@Autowired
	private CognitoUtil sJwtUtil;
	
	@Autowired
	private CognitoUtil iUtil;
	
//	@Around(" @annotation(com.revature.annotations.JwtVerify)")
//	public Object verifyJwt(ProceedingJoinPoint pjp) throws Throwable {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		if(!sJwtUtil.jwtVerify(request)) {
//			System.out.println("Jwt Verifaction Not Passed.");
//			return ResponseEntity.status(401).body(ResponseMap.getBadResponse("Jwt Verifaction Not Passed."));
//		}
//		return pjp.proceed();
//    }
//	
//	@Around(" @annotation(com.revature.annotations.JwtUserIsSelf)")
//	public Object jwtUserIsSelf(ProceedingJoinPoint pjp) throws Throwable {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		Map tParams = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//		if(!sJwtUtil.isRequestFromSelf(request, Integer.parseInt((String) tParams.get("userId")))) {
//			System.out.println("User is not self");
//			return ResponseEntity.status(403).body(ResponseMap.getBadResponse("User is not self"));
//		}
//		return pjp.proceed();
//    }
//	
//	@Around(" @annotation(com.revature.annotations.JwtUserIsAdmin)")
//	public Object jwtUserIsAdmin(ProceedingJoinPoint pjp) throws Throwable {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//
//		if(!sJwtUtil.isRequestFromAdmin(request)) {
//			System.out.println("User is not admin");
//			return ResponseEntity.status(403).body(ResponseMap.getBadResponse("User is not admin"));
//		}
//		return pjp.proceed();
//    }
//	
//	@Around(" @annotation(com.revature.annotations.JwtUserIsSelf)")
//	public Object jwtUserIsSelfOrAdmin(ProceedingJoinPoint pjp) throws Throwable {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		Map tParams = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//		
//		if(sJwtUtil.isRequestFromSelf(request, Integer.parseInt((String) tParams.get("userId"))) || sJwtUtil.isRequestFromAdmin(request)) {
//			return pjp.proceed();
//			
//		}
//		System.out.println("User is not self or admin.");
//		return ResponseEntity.status(403).body(ResponseMap.getBadResponse("User is not self or Admin"));
//    }
//	


//	@Around(" @annotation(com.revature.annotations.CognitoAuth)")
//	public Object CognitoAuth(ProceedingJoinPoint pjp) throws Throwable {
//		
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		
//		if(iUtil.cognitoAuth(request)) {
//			System.out.println("User did not get verified rip");
//		}
//		
//		return pjp.proceed();
//    }
//	
//	
	
	
	
	@Around(" @annotation(com.revature.annotations.CognitoLogin)")
	public Object CognitoLogin(ProceedingJoinPoint pjp) throws Throwable {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		if(iUtil.cognitoLogin(request)) {
			System.out.println("User could not login");
		}
		
		return pjp.proceed();
    }

	
	
	
	
	
	
	
}
