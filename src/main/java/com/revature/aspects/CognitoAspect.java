package com.revature.aspects;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.annotations.CognitoAuth;
import com.revature.utils.CognitoUtil;

@Aspect
@Component
public class CognitoAspect {

	@Autowired
	private CognitoUtil iUtil;

	@Pointcut(" @annotation(ca)")
	public void annotationPointCutDefinition(CognitoAuth ca) {
	}

	// Defines a pointcut that we can use in the @Before,@After, @AfterThrowing,
	// @AfterReturning,@Around specifications
	// The pointcut is a catch-all pointcut with the scope of execution
	@Pointcut("execution(* *(..))")
	public void atExecution() {
	}

	@Around("annotationPointCutDefinition(ca) && atExecution()")
	public Object CognitoAuth(ProceedingJoinPoint pjp, CognitoAuth ca) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		List<String> authRole = iUtil.cognitoAuth(request);
		if (ca.isDevelopment()) {
			return pjp.proceed();
		}

		if (!authRole.contains("admin") && !authRole.contains(ca.highestRole())) {
			return ResponseEntity.status(403);
		}
		System.out.println("It went here:)");
		return pjp.proceed();
	}

	@After("annotationPointCutDefinition(ca) && atExecution()")
	// JointPoint = the reference of the call to the method
	public void printNewLine(JoinPoint pointcut, CognitoAuth ca) {
		// Just prints new lines after each method that's executed in
		System.out.print("\n\r");
	}

}
