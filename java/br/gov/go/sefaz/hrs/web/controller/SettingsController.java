package br.gov.go.sefaz.hrs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.go.sefaz.hrs.web.constants.ControllerMapping;

@Controller
@RequestMapping(ControllerMapping.SETTINGS_PATH_ROOT)
public class SettingsController {

	@GetMapping(ControllerMapping.SETTINGS_PATH_INFORMATION)
	public ModelAndView information(){
		return new ModelAndView(ControllerMapping.SETTINGS_VIEW_INFORMATION);
	}
}
