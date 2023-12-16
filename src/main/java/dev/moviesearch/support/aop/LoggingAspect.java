package dev.moviesearch.support.aop;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@Order(2)
public class LoggingAspect {

	@JsonIgnoreType
	class JacksonMixInForIgnoreType {}

	@Around("execution(* dev.moviesearch..app..*(..))")
	public Object loggingAround(ProceedingJoinPoint joinPoint) throws Throwable {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.addMixIn(MultipartFile.class, JacksonMixInForIgnoreType.class);
	 	mapper.addMixIn(MultipartFile[].class, JacksonMixInForIgnoreType.class);
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		log.info("[S]======================================================================================");
		log.info("<AOP> hooked class------------>> {}.java", joinPoint.getSignature().getDeclaringType().getName());
		log.info("<AOP> hooked method----------->> {}()", joinPoint.getSignature().getName());
		log.info("<AOP> hooked arguments-------->> {}", Arrays.toString(joinPoint.getArgs()));

		RequestAttributes attrs = RequestContextHolder.getRequestAttributes();

		boolean isAttachment = false;

		if( attrs != null) {

			HttpServletRequest request = ((ServletRequestAttributes) attrs).getRequest();

			if ( request.getContentType() !=null ) {
				isAttachment = request.getContentType().indexOf("multipart/form-data") >= 0 ? true: false;
			}

			log.info("<AOP> hooked request method--->> {}", request.getMethod());
			log.info("<AOP> hooked request uri------>> {}", request.getRequestURI());
			log.info("<AOP> hooked request ip------->> {}", request.getRemoteAddr());
		}


		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object result = joinPoint.proceed();	//continue on the intercepted method
		stopWatch.stop();

		if(isAttachment) {
			log.info("<AOP> hooked request json ---->>\nrequest  : multipart/form-data\n");
		}else {
			log.info("<AOP> hooked request json ---->>\nrequest  :\n{}\n", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(joinPoint.getArgs()));
		}

		log.info("<AOP> hooked finished	 --------> {}(), {} msec", joinPoint.getSignature().getName() , stopWatch.getTotalTimeMillis());
		log.info("======================================================================================[E]");

		return result;

	}

}