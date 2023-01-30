package br.gov.go.sefaz.hrs.web.config;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import br.gov.go.sefaz.javaee.web.thymeleaf.dialect.SefazgoDialect;
import br.gov.go.sefaz.javaee.web.pagination.PaginationConfig;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = {AppWebConfigurationParameters.PACKAGE_CONTROLLER, AppWebConfigurationParameters.PACKAGE_VALIDATION})
public class AppWebMvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	@Bean
	public ViewResolver createViewResolver(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(createTemplateEngine());
		viewResolver.setCharacterEncoding(CharEncoding.UTF_8);
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
	
	@Bean
	public TemplateEngine createTemplateEngine(){
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(createTemplateResolver());
		engine.setEnableSpringELCompiler(true);
		engine.addDialect(new SefazgoDialect());
		engine.addDialect(new LayoutDialect());
		engine.addDialect(new SpringSecurityDialect());
		return engine;
	}

    /**
     * Método que cria o template resolver da aplicação
     * @return
     */
	private ITemplateResolver createTemplateResolver(){
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setPrefix("classpath:/views/");
		resolver.setSuffix(".html");
		resolver.setCharacterEncoding(CharEncoding.UTF_8);
		resolver.setCacheable(true); // Don't use FALSE on production's environment
		return resolver;
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/") 						// Application resources
			.addResourceLocations("classpath:/webapp/resources/");		// SEFAZGO resources (from sefazgo-javaee-web jar)
		registry.addResourceHandler("/jasmine/**").addResourceLocations("/jasmine/");
    }
 
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
 
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:/br/gov/go/sefaz/hrs/hrs-messages");
        resource.setDefaultEncoding(CharEncoding.UTF_8);
        return resource;
    }    
    
    @Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setOneIndexedParameters(true);
		resolver.setMaxPageSize(PaginationConfig.PAGE_MAX_SIZE);
		argumentResolvers.add(resolver);
		super.addArgumentResolvers(argumentResolvers);
	}
    
    @Bean
    public LocaleResolver localeResolver(){
    	return new FixedLocaleResolver(new Locale("pt","BR"));
    }
}