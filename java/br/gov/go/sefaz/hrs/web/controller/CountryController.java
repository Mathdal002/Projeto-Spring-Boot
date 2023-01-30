package br.gov.go.sefaz.hrs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.go.sefaz.hrs.model.Country;
import br.gov.go.sefaz.hrs.service.CountryService;
import br.gov.go.sefaz.hrs.service.RegionService;
import br.gov.go.sefaz.hrs.validation.input.CountryInputValidator;
import br.gov.go.sefaz.hrs.web.constants.ControllerMapping;
import br.gov.go.sefaz.hrs.web.constants.Messages;
import br.gov.go.sefaz.javaee.security.user.UsuarioAutenticadoDetails;
import br.gov.go.sefaz.javaee.web.thymeleaf.constants.MessageElementTagParameters;

@Controller
@RequestMapping(ControllerMapping.COUNTRY_PATH_ROOT)
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	CountryInputValidator countryInputValidator;
	
	@Autowired
	RegionService regionService;
	
	@GetMapping
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView(ControllerMapping.COUNTRY_VIEW_LIST);
		mv.addObject(ControllerMapping.REGION_MODEL_ENTITIES_NAME, regionService.findAllJdbc());
		return mv;
	}
	
	@GetMapping(ControllerMapping.PATH_ACTION_NEW)
	public ModelAndView add(Country country) {
		ModelAndView mv = new ModelAndView(ControllerMapping.COUNTRY_VIEW_FORM);
		this.initForm(mv);
		return mv;
	}
	
	@GetMapping(ControllerMapping.PATH_ACTION_EDIT)
	public ModelAndView edit(@PathVariable String id){
		ModelAndView mv = new ModelAndView(ControllerMapping.COUNTRY_VIEW_FORM);
		this.initForm(mv);
		mv.addObject(ControllerMapping.COUNTRY_MODEL_ENTITY_NAME, this.countryService.find(id));
		return mv;
	}
	
	@PostMapping(ControllerMapping.PATH_ACTION_SAVE)
	public ModelAndView save(Country country, BindingResult result, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UsuarioAutenticadoDetails usuarioAutenticadoDetails) {
		ModelAndView mv = new ModelAndView(ControllerMapping.COUNTRY_VIEW_FORM_REDIRECT);
		this.countryInputValidator.validateSave(country, result);
		if (!result.hasErrors()) {
			this.countryService.save(usuarioAutenticadoDetails.getUsuarioAutenticado().getMatricula(), country);
			redirectAttributes.addFlashAttribute(MessageElementTagParameters.FORM_SUCCESS_MESSAGE_KEY, String.format(Messages.SUCCESSFULLY_SAVED, ControllerMapping.COUNTRY_BUSINESS_NAME, country.getId(), country.getName()));
			return mv;
		} else {
			return this.add(country);
		}
	}
	
	@GetMapping(ControllerMapping.PATH_ACTION_DELETE)
	@PreAuthorize("hasAnyRole('VINCULO_BASICO','PORTALSEFAZ_PERFIL_ADMIN','VISUALIZACAO_DE_FUNCIONALIDADES_DE_SISTEMAS')")
	public ModelAndView delete(@PathVariable String id, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UsuarioAutenticadoDetails usuarioAutenticadoDetails) {
		this.countryService.delete(usuarioAutenticadoDetails.getUsuarioAutenticado().getMatricula(), id);
		redirectAttributes.addFlashAttribute(MessageElementTagParameters.FORM_SUCCESS_MESSAGE_KEY, String.format(Messages.SUCCESSFULLY_DELETED, ControllerMapping.COUNTRY_BUSINESS_NAME, id));
		return new ModelAndView(ControllerMapping.COUNTRY_VIEW_LIST_REDIRECT);
	}
	
	private void initForm(ModelAndView mv) {
		mv.addObject(ControllerMapping.REGION_MODEL_ENTITIES_NAME, regionService.findAll());
	}
}