package br.gov.go.sefaz.hrs.web.controller.health.checker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.go.sefaz.hrs.service.RegionService;

import br.gov.go.sefaz.javaee.service.rest.controller.health.checker.HealthChecker;
import br.gov.go.sefaz.javaee.service.rest.controller.health.checker.exception.HealthCheckerException;

@Component
public class AppDatasourceHealthChecker implements HealthChecker {

	@Autowired	
	private RegionService regionService;
	
	@Override
	public String getName() {
		return "datasource";
	}

	@Override
	public boolean isHealthly(){
		try {
			// TODO testa o datasoruce para verificar se o banco de dados está ativo
			// Neste local pode ser realizado qualquer tipo de validaçao com banco de dados.
			return regionService.count() != 0; 
		} catch (Exception e) {
			throw new HealthCheckerException(e);
		}
	}
}
