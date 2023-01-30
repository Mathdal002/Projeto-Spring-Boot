package br.gov.go.sefaz.hrs.web.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.gov.go.sefaz.hrs.web.controller.health.checker.AppDatasourceHealthChecker;
import br.gov.go.sefaz.hrs.web.support.AppPropertiesSupport;
import br.gov.go.sefaz.javaee.service.rest.controller.health.HealthRestController;

@RestController
public class HealthController extends HealthRestController {

	@Autowired
	private AppDatasourceHealthChecker appDatasourceHealthChecker;
	
	@Autowired
	private AppPropertiesSupport appPropertiesSupport;
	
	
	@PostConstruct
	public void addHealthCheckers() {
		addHealthChecker(appDatasourceHealthChecker);
	}

	@Override
	protected String projectVersion() {
		return appPropertiesSupport.getProperty("hrs.version");
	}
}