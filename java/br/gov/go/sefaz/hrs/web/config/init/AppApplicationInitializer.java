package br.gov.go.sefaz.hrs.web.config.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.gov.go.sefaz.javaee.security.config.SecurityConfigurationParameters;
import br.gov.go.sefaz.hrs.config.init.AppDomainInitializer;
import br.gov.go.sefaz.hrs.web.config.AppWebMvcConfiguration;
import br.gov.go.sefaz.hrs.web.config.AppRestTemplateConfiguration;
import br.gov.go.sefaz.hrs.web.config.AppWebJwtSecurityConfiguration;
import br.gov.go.sefaz.portalsefaz.apps.web.client.config.init.PortalSefazAppsWebClientInitializer;
import br.gov.go.sefaz.javaee.security.x509.support.CertificadoDigitalTester;
import br.gov.go.sefaz.javaee.web.entitymanagerinview.OpenEntityManagerInViewSupport;


/**
 * Classe responsável por iniciar as configurações da aplicação
 */
public class AppApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		Class<?>[] rootConfigClasses = AppDomainInitializer.getRootConfigClasses();
		rootConfigClasses = ArrayUtils.add(rootConfigClasses, AppRestTemplateConfiguration.class);
		rootConfigClasses = ArrayUtils.add(rootConfigClasses, AppWebJwtSecurityConfiguration.class);
		rootConfigClasses = ArrayUtils.addAll(rootConfigClasses, PortalSefazAppsWebClientInitializer.getRootConfigClasses());
		return rootConfigClasses;
	}

    /**
     * Retorna a classe que instrui o Dispatcher a
     * localizar os Controllers.
     * @return
     */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{AppWebMvcConfiguration.class};
	}

    /**
     * Método responsável por definir o padrão de URL
     * que será delegado para o DispatcherServlet. "/"
     * significa que tudo após a / será entregue ao Dispatcher
     * (qualquer URL dentro da aplicação).
     *
     * <servlet-mapping>
     *	 <url-pattern>/</url-pattern>
     * </servlet-mapping>
     * @return
     */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	//Spring Request Context Listener
    	servletContext.addListener(new RequestContextListener());
    	
    	//SessionListener
    	servletContext.addListener(new AppHttpSessionListener());
    	
    	/**	Global Cookie configurations.
			This configuration is global, however, when a cookie is manually wrote, this configuration can be overwritten.
	 	*/
		SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
		// Indicate this cookie won't be explosed to javascript (httpOnly = true) 
		sessionCookieConfig.setHttpOnly(SecurityConfigurationParameters.COOKIE_HTTP_ONLY);
		// Indicate this cookie only will traffic if connection is secure (HTTPs)
		sessionCookieConfig.setSecure(SecurityConfigurationParameters.COOKIE_SECURE);
		// Indicate this cookie will live till browser closes (-1)
		sessionCookieConfig.setMaxAge(SecurityConfigurationParameters.COOKIE_MAX_AGE);
		// Indicate this cookie will be sent only to this path
		sessionCookieConfig.setPath(servletContext.getContextPath());
		
		//Open Entity Manager in View
    	OpenEntityManagerInViewSupport.addFilter(servletContext, AppWebMvcConfiguration.class);
    	
    	//CertificadoDigitalTester Servlet (tests certificates sent through direct and/or BigIp requests
    	ServletRegistration.Dynamic certificadoDigitalTesterServlet = servletContext.addServlet("certificadoDigitalTesterServlet", CertificadoDigitalTester.class);
    	certificadoDigitalTesterServlet.addMapping("/certificates/test");
		
    	super.onStartup(servletContext);
    }
}