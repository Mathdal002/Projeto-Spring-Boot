package br.gov.go.sefaz.hrs.config.init;

import br.gov.go.sefaz.hrs.config.AppDomainJdbcConfiguration;
import br.gov.go.sefaz.hrs.config.AppDomainJpaConfiguration;
import br.gov.go.sefaz.hrs.config.AppDomainServiceConfiguration;

public class AppDomainInitializer {

	private AppDomainInitializer() {
	}
	
	public static Class<?>[] getRootConfigClasses() {
		return new Class[]{
				AppDomainJpaConfiguration.class,
				AppDomainJdbcConfiguration.class,
				AppDomainServiceConfiguration.class};
	}
}