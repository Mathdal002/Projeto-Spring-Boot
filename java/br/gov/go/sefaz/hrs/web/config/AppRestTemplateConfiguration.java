package br.gov.go.sefaz.hrs.web.config;

import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gov.go.sefaz.javaee.commons.exception.SefazException;
import br.gov.go.sefaz.javaee.commons.resource.jwt.v1.JwtCredentials;
import br.gov.go.sefaz.javaee.commons.resource.jwt.v1.JwtDetails;
import br.gov.go.sefaz.javaee.commons.resource.jwt.v1.JwtPrincipal;
import br.gov.go.sefaz.javaee.security.config.SecurityConfigurationParameters;
import br.gov.go.sefaz.javaee.security.jwt.support.RSALoaderSupport;
import br.gov.go.sefaz.javaee.service.rest.client.config.RestClientServiceRestTemplateConfiguration;
import br.gov.go.sefaz.javaee.service.rest.client.jwt.factory.JwtAuthorizationTokenClientFactory;
import br.gov.go.sefaz.javaee.service.rest.client.jwt.factory.JwtAuthorizationTokenClientFactoryConfiguration;
import br.gov.go.sefaz.hrs.web.support.AppPropertiesSupport;

@Configuration
public class AppRestTemplateConfiguration extends RestClientServiceRestTemplateConfiguration{

	@Autowired
	private AppPropertiesSupport appPropertiesSupport;
	
	@Bean
	public JwtAuthorizationTokenClientFactory restClientServiceJwtAuthorizationTokenClientFactory() throws SefazException {
		return new JwtAuthorizationTokenClientFactory(portalSefazServiceClientFactoryConfiguration());
	}
	
	private JwtAuthorizationTokenClientFactoryConfiguration portalSefazServiceClientFactoryConfiguration() throws SefazException{
		String username = this.appPropertiesSupport.getProperty("portalsefaz.sistema.sigla.autorizacao");
		String password = this.appPropertiesSupport.getProperty("portalsefaz.sistema.password");
		String resourceApp = SecurityConfigurationParameters.PORTALSEFAZ_AUTHENTICATION_SERVICE_NAME_DEFAULT;
		String servicePublicKeyPath = this.appPropertiesSupport.getProperty("hrs.security.public.key.path");
		String jwtAuthorizationTokenUrl = this.appPropertiesSupport.getProperty("portalsefaz.sistema.authentication.jwt.endpointUrl");
		
		JwtPrincipal jwtPrincipal = JwtPrincipal.build()
				.username(username)
				.credentials(JwtCredentials.build()
					.password(password))
				.details(JwtDetails.build()
					.resourceApp(resourceApp));
		
		RSAPublicKey publicKey = RSALoaderSupport.loadPublicKey(servicePublicKeyPath);

		return new JwtAuthorizationTokenClientFactoryConfiguration(jwtPrincipal,restClientServiceHttpMessageConverters(),publicKey,jwtAuthorizationTokenUrl);
	}
}
