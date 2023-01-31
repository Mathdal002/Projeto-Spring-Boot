package br.gov.go.sefaz.hrs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {AppDomainConfigurationParameters.PACKAGE_SERVICE, AppDomainConfigurationParameters.PACKAGE_VALIDATION})
public class AppDomainServiceConfiguration {
	
}