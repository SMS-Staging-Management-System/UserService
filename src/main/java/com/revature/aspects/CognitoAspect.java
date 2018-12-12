package com.revature.aspects;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.annotations.CognitoAuth;
import com.revature.utils.CognitoUtil;
import com.revature.utils.ResponseMap;

@Aspect
@Component
public class CognitoAspect {

	@Value("${spring.profiles}")
	private String stage;
	
	@Autowired
	private CognitoUtil iUtil;

	@Pointcut(" @annotation(ca)")
	public void annotationPointCutDefinition(CognitoAuth ca) {}

	// Defines a pointcut that we can use in the @Before,@After, @AfterThrowing,
	// @AfterReturning,@Around specifications
	// The pointcut is a catch-all pointcut with the scope of execution
	@Pointcut("execution(* *(..))")
	public void atExecution() {}

	@Around("annotationPointCutDefinition(ca) && atExecution()")
	public Object CognitoAuth(ProceedingJoinPoint pjp, CognitoAuth ca) throws Throwable {
		Logger log = Logger.getRootLogger();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		List<String> authRole = iUtil.cognitoAuth(request);
		
		if(stage.equals("dev")) {
			log.info("\n Authorization 401 bypassed by Dev Route");
			return pjp.proceed();
		}
		if (authRole == null) {
			return ResponseEntity.status(401).body(ResponseMap.getBadResponse("Invalid Authentication"));
		}
		
		if (ca.role().equals("user")) {
			return pjp.proceed();
		}
		
		if (!authRole.contains("admin") && !authRole.contains(ca.role())) {	
			return ResponseEntity.status(403).body(ResponseMap.getBadResponse("Access Forbidden"));
		}
		return pjp.proceed();
	}

//	@After("annotationPointCutDefinition(ca) && atExecution()")
//	// JointPoint = the reference of the call to the method
//	public void printNewLine(JoinPoint pointcut, CognitoAuth ca) {
//		// Just prints new lines after each method that's executed in
//		System.out.print("\n\r");
//	}

}
