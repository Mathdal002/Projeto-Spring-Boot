package br.gov.go.sefaz.hrs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.go.sefaz.hrs.web.constants.ControllerMapping;
import br.gov.go.sefaz.hrs.service.CountryService;
import br.gov.go.sefaz.hrs.service.RegionService;
import br.gov.go.sefaz.hrs.web.dto.DashboardDto;

@Controller
@RequestMapping({ControllerMapping.DASHBOARD_PATH_ROOT, ControllerMapping.WELCOME_PATH_ROOT})
public class DashboardController {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	RegionService regionService;
	
	@GetMapping // Used as welcome path (WELCOME_PATH_ROOT)
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView(ControllerMapping.DASHBOARD_VIEW);
		DashboardDto dashboardDto = this.loadData();
		mv.addObject(ControllerMapping.DASHBOARD_MODEL_DTO, dashboardDto);
		return mv;
	}
	
	private DashboardDto loadData() {
		DashboardDto dashboardDto = new DashboardDto();
		dashboardDto.setCountriesQty(this.countryService.count());
		dashboardDto.setRegionsQty(this.regionService.count());
		return dashboardDto;
	}
}