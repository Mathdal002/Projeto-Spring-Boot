package br.gov.go.sefaz.hrs.web.controller.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.gov.go.sefaz.hrs.web.config.AppWebConfigurationParameters;
import br.gov.go.sefaz.hrs.web.constants.ControllerMapping;
import br.gov.go.sefaz.hrs.web.constants.Messages;
import br.gov.go.sefaz.javaee.commons.support.ExceptionSupport;
import br.gov.go.sefaz.javaee.web.thymeleaf.constants.MessageElementTagParameters;

@ControllerAdvice(basePackages = {AppWebConfigurationParameters.PACKAGE_CONTROLLER})
public class ControllerAdviceHandler {

	private static final Logger logger = LogManager.getLogger(ControllerAdviceHandler.class);
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ModelAndView handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request, @AuthenticationPrincipal User user){
    	logger.error("Attempted unauthorized access: " + request.getRequestURI() + "[" + user.getUsername() + "]");
		ModelAndView mv = new ModelAndView(ControllerMapping.AUTH_VIEW_DENIED);
		mv.addObject(ControllerMapping.MODEL_ERROR_DETAILS_NAME, String.format(Messages.ACCESS_DENIED, request.getRequestURI()));
		return mv;
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ModelAndView handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		logger.error("Method not supported!");
		return new ModelAndView(ControllerMapping.ERRORS_VIEW_METHOD_NOT_SUPPORTED);
	}
	
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleThrowable(Throwable ex){
		logger.error("A general error has occurred!", ex);
		ModelAndView mv = new ModelAndView(ControllerMapping.ERRORS_VIEW_INTERNAL_ERROR);
		mv.addObject(ControllerMapping.MODEL_ERROR_DETAILS_NAME, ExceptionSupport.getExceptionDetails(ex));
		mv.addObject(MessageElementTagParameters.FORM_STACKTRACE_MESSAGE_KEY, ExceptionUtils.getStackTrace(ex));
		return mv;
	}
}
