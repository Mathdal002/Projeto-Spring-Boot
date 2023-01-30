package br.gov.go.sefaz.hrs.web.config;

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.gov.go.sefaz.javaee.commons.exception.SefazException;
import br.gov.go.sefaz.javaee.security.jwt.support.RSALoaderSupport;
import br.gov.go.sefaz.hrs.web.support.AppPropertiesSupport;
import br.gov.go.sefaz.portalsefaz.apps.web.client.config.PortalSefazAppsWebClientConfiguration;
import br.gov.go.sefaz.portalsefaz.apps.web.client.filter.PortalSefazAppsCookiePreAuthenticationFilter;

@EnableWebSecurity
@ComponentScan(basePackages = {AppWebConfigurationParameters.PACKAGE_SUPPORT})
public class AppWebJwtSecurityConfiguration extends PortalSefazAppsWebClientConfiguration {
	
	@Autowired
	private AppPropertiesSupport appPropertiesSupport;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.addFilter(portalSefazAppsCookiePreAuthenticationFilter())
			.addFilterBefore(portalSefazAppsCookiePreAuthenticationRedirectHandler(), PortalSefazAppsCookiePreAuthenticationFilter.class)
			.addFilterAfter(portalSefazAppsFormaAcessoAuthorizationFilter(), PortalSefazAppsCookiePreAuthenticationFilter.class)
		.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl(appPropertiesSupport.getProperty("portalsefaz.autenticacao.usuario.logout.service.endpointUrl"))
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
		.and()
			.authorizeRequests()                                                              
				.antMatchers("/logout").permitAll()
				.antMatchers("/hystrix**").permitAll()
			.anyRequest()
				.authenticated()
		.and()
			.sessionManagement()
				.sessionAuthenticationErrorUrl("/auth/logout/concurrency")
				.invalidSessionUrl("/auth/denied")
				.maximumSessions(1)
				.expiredUrl("/auth/logout/concurrency");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers(AppWebConfigurationParameters.WEB_IGNORING_PATHS);
	}
	
	@Override
	public PublicKey servicePublicKey() throws SefazException{
		return RSALoaderSupport.loadPublicKey(appPropertiesSupport.getProperty("hrs.security.public.key.path"));
	}
}