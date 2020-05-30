package com.crestasom.CustomAnnotationUsingJDBC.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.crestasom.CustomAnnotationUsingJDBC.annotation.MyTransactional;
import com.crestasom.CustomAnnotationUsingJDBC.db.DbAccess;

@Aspect
@Configuration
public class MyAOP {
	@Autowired
	DbAccess db;
	private static final Logger LOG = LoggerFactory.getLogger(MyAOP.class);

	@Around("@annotation(com.crestasom.CustomAnnotationUsingJDBC.annotation.MyTransactional)")
	public Object transactionSQL(ProceedingJoinPoint pjp) throws Throwable {
		LOG.info("running transaction");
		Object output = null;

		try {
			db.beginTransaction();
			output = pjp.proceed();
			db.commitTransaction();
		} catch (Exception ex) {
			db.rollBackTransaction();
			LOG.info("exception occured inside transactional method");
			ex.printStackTrace();

		}

		return output;
	}

}
