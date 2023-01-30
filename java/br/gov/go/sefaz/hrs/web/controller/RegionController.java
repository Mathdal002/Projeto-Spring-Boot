package br.gov.go.sefaz.hrs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.gov.go.sefaz.hrs.model.Region;
import br.gov.go.sefaz.hrs.service.RegionService;
import br.gov.go.sefaz.hrs.validation.input.RegionInputValidator;
import br.gov.go.sefaz.hrs.web.constants.ControllerMapping;
import br.gov.go.sefaz.hrs.web.constants.Messages;
import br.gov.go.sefaz.javaee.security.user.UsuarioAutenticadoDetails;
import br.gov.go.sefaz.javaee.web.pagination.Pagination;
import br.gov.go.sefaz.javaee.web.pagination.PaginationConfig;
import br.gov.go.sefaz.javaee.web.thymeleaf.constants.MessageElementTagParameters;

@Controller
@RequestMapping(ControllerMapping.REGION_PATH_ROOT)
public class RegionController {

	@Autowired
	RegionService regionService;
	
	@Autowired
	RegionInputValidator regionInputValidator;
	
	@GetMapping
	public ModelAndView list(@PageableDefault Pageable pageable, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(ControllerMapping.REGION_VIEW_LIST);
		Page<Region> page =  this.regionService.findAll(pageable);
		Pagination<Region> pageWrapper = new Pagination<>(page, request);
		mv.addObject(PaginationConfig.PAGE_MODEL_NAME, pageWrapper);
		return mv;
	}
	
	@GetMapping(ControllerMapping.PATH_ACTION_NEW)
	public ModelAndView add(Region region) {
		ModelAndView mv = new ModelAndView(ControllerMapping.REGION_VIEW_FORM);
		return mv;
	}
	
	@GetMapping(ControllerMapping.PATH_ACTION_EDIT)
	public ModelAndView edit(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView(ControllerMapping.REGION_VIEW_FORM);
		mv.addObject(ControllerMapping.REGION_MODEL_ENTITY_NAME, this.regionService.find(id));
		return mv;
	}
	
	@PostMapping(ControllerMapping.PATH_ACTION_SAVE)
	public ModelAndView save(Region region, BindingResult result, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UsuarioAutenticadoDetails usuarioAutenticadoDetails) {
		ModelAndView mv = new ModelAndView(ControllerMapping.REGION_VIEW_FORM_REDIRECT);
		this.regionInputValidator.validateSave(region, result);
		if (!result.hasErrors()) {
			this.regionService.save(usuarioAutenticadoDetails.getUsuarioAutenticado().getMatricula(), region);
			redirectAttributes.addFlashAttribute(MessageElementTagParameters.FORM_SUCCESS_MESSAGE_KEY, String.format(Messages.SUCCESSFULLY_SAVED, ControllerMapping.REGION_BUSINESS_NAME, region.getId(), region.getName()));
			return mv;
		} else {
			return this.add(region);
		}
	}
	
	@GetMapping(ControllerMapping.PATH_ACTION_DELETE)
	@PreAuthorize("hasAnyRole('VINCULO_BASICO','PORTALSEFAZ_PERFIL_ADMIN','VISUALIZACAO_DE_FUNCIONALIDADES_DE_SISTEMAS')")
	public ModelAndView delete(@PathVariable Integer id, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UsuarioAutenticadoDetails usuarioAutenticadoDetails) {
		this.regionService.delete(usuarioAutenticadoDetails.getUsuarioAutenticado().getMatricula(), id);
		redirectAttributes.addFlashAttribute(MessageElementTagParameters.FORM_SUCCESS_MESSAGE_KEY, String.format(Messages.SUCCESSFULLY_DELETED, ControllerMapping.REGION_BUSINESS_NAME, id));
		return new ModelAndView(ControllerMapping.REGION_VIEW_LIST_REDIRECT);
	}
}