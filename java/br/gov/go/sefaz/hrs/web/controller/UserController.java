package br.gov.go.sefaz.hrs.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.go.sefaz.hrs.web.constants.ControllerMapping;
import br.gov.go.sefaz.javaee.security.user.UsuarioAutenticadoDetails;

@Controller
@RequestMapping(ControllerMapping.PROFILE_PATH_ROOT)
public class UserController {
	
	@GetMapping(ControllerMapping.PROFILE_ACTION_INFO)
	public ModelAndView profile(@AuthenticationPrincipal UsuarioAutenticadoDetails usuarioAutenticadoDetails) {
		return new ModelAndView(ControllerMapping.PROFILE_VIEW_PROFILE);
	}
}