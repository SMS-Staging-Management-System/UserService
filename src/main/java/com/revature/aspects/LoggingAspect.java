package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//import com.revature.annotations.Logging;

@Aspect
@Component
public class LoggingAspect {
	private Logger logger = Logger.getRootLogger();
	
	@Around("execution(* com.revature.services.*.*(..))")
	public Object Logging(ProceedingJoinPoint pjp) throws Throwable {
		String logInfo = "";
		logInfo = logInfo + "Method: " + pjp.getSignature().getName() + "\n";
		String args = "";
		if (pjp.getArgs().length > 0) {
			args = "Arguments:";
			for (Object o: pjp.getArgs()) {
				args = args + " " + o.toString();
			}
			logInfo = logInfo + args + "\n";
		}
		Object value = pjp.proceed();
		try {
			logInfo = logInfo + "Result: " + value.toString() + "\n";
			logger.info(logInfo);
		}catch (NullPointerException e) {
			
		}
		
		return value;
	}
}
