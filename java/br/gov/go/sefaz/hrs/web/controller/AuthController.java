package br.gov.go.sefaz.hrs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.go.sefaz.hrs.web.constants.ControllerMapping;
import br.gov.go.sefaz.hrs.web.constants.Messages;
import br.gov.go.sefaz.javaee.security.config.SecurityConfigurationParameters;

@Controller
@RequestMapping(ControllerMapping.AUTH_PATH_ROOT)
public class AuthController {
	
	@GetMapping(ControllerMapping.AUTH_PATH_LOGIN)
	public ModelAndView login() {
		return new ModelAndView(ControllerMapping.AUTH_VIEW_LOGIN);
	}
	
	@GetMapping(ControllerMapping.AUTH_PATH_LOGIN_FORM)
	public ModelAndView formLogin() {
		return new ModelAndView(ControllerMapping.AUTH_VIEW_LOGIN_FORM);
	}
	
	@GetMapping(ControllerMapping.AUTH_PATH_LOGIN_ERROR)
	public ModelAndView loginError(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(ControllerMapping.AUTH_VIEW_DENIED);
		String errorDetails = (String) request.getAttribute(SecurityConfigurationParameters.REQUEST_ATTR_LOGIN_ERROR_DETAILS);
		mv.addObject(ControllerMapping.MODEL_ERROR_DETAILS_NAME, StringUtils.isEmpty(errorDetails) ? Messages.LOGIN_ERROR_UNHANDLED : errorDetails);
		return mv;
	}

	@GetMapping(ControllerMapping.AUTH_PATH_LOGOUT_SUCCESS)
	public ModelAndView logoutSuccess() {
		return new ModelAndView(ControllerMapping.AUTH_VIEW_LOGOUT_SUCCESS);
	}
	
	@GetMapping(ControllerMapping.AUTH_PATH_LOGOUT_CONCURRENCY)
	public ModelAndView logoutConcurrency() {
		return new ModelAndView(ControllerMapping.AUTH_VIEW_LOGOUT_CONCURRENCY);
	}

	@GetMapping(ControllerMapping.AUTH_PATH_DENIED)
	public ModelAndView accessDenied(HttpServletRequest request) {
		String errorDetails = (String) request.getAttribute(SecurityConfigurationParameters.REQUEST_ATTR_LOGIN_ERROR_DETAILS);
		ModelAndView mv = new ModelAndView(ControllerMapping.AUTH_VIEW_DENIED);
		mv.addObject(ControllerMapping.MODEL_ERROR_DETAILS_NAME, !StringUtils.isEmpty(errorDetails) ? errorDetails : String.format(Messages.ACCESS_DENIED, request.getRequestURI()));
		return mv;
	}
}