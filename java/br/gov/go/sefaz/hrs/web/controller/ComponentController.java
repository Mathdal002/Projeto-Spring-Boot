package br.gov.go.sefaz.hrs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.gov.go.sefaz.hrs.model.Region;
import br.gov.go.sefaz.hrs.service.RegionService;
import br.gov.go.sefaz.hrs.web.constants.ControllerMapping;
import br.gov.go.sefaz.javaee.web.entitymanagerinview.OpenEntityManagerInView;
import br.gov.go.sefaz.javaee.web.pagination.Pagination;
import br.gov.go.sefaz.javaee.web.pagination.PaginationConfig;

@Controller
@RequestMapping(ControllerMapping.COMPONENTS_PATH_ROOT)
public class ComponentController {

	@Autowired
	private RegionService regionService;
	
	@OpenEntityManagerInView
	@GetMapping
	public ModelAndView components(@PageableDefault(size=2) Pageable pageable, HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView(ControllerMapping.COMPONENTS_VIEW_LIST);
		
		Page<Region> page =  this.regionService.findAll(pageable);
		Pagination<Region> pageWrapper = new Pagination<>(page, request);
		mv.addObject(PaginationConfig.PAGE_MODEL_NAME, pageWrapper);
		
		return mv;
	}
	@GetMapping(ControllerMapping.COMPONENTS_PATH_TEST)
	public ModelAndView tests(){
		return new ModelAndView(ControllerMapping.COMPONENTS_VIEW_TEST);
	}
	
	@PostMapping
	public ModelAndView pickList(@RequestParam("countries") String[] countries){
		return new ModelAndView("redirect:/" + ControllerMapping.COMPONENTS_PATH_ROOT);
	}
}
